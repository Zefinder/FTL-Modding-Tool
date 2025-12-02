package net.zefinder.ftlmod.analyser;

import static net.zefinder.ftlmod.Consts.*;

import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.event.Event;
import net.zefinder.ftlmod.event.EventBuilder;
import net.zefinder.ftlmod.event.EventCreationException;
import net.zefinder.ftlmod.event.EventManager;
import net.zefinder.ftlmod.event.EventType;
import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.text.TextListManager;
import net.zefinder.ftlmod.text.TextManager;
import net.zefinder.ftlmod.text.TextType;

public class EventAnalyser {

	private static final Logger log = LoggerFactory.getLogger(EventAnalyser.class);

	private EventAnalyser() {
	}

	public static final void analyseEvent(final List<Element> elements, final boolean isUser) throws EventCreationException {
		for (Element eventElement : elements) {
			if (!eventElement.getName().equals(EVENT_TAG_NAME)) {
				continue;
			}
			
			String load = eventElement.attributeValue(LOAD_ATTRIBUTE_NAME);
			String name = eventElement.attributeValue(NAME_ATTRIBUTE_NAME);
			if (load != null) {
				// Load event, register usage in manager
				log.info("Load event %s, register usage in manager...".formatted(load));
				EventManager.getInstance().useObject(load);
			} else if (name == null) {
				// Empty event
				log.info("Empty event, skip!");
			} else {
				// Normal event
				analyseNormalEvent(eventElement, name, isUser);
			}
			
			
//			System.exit(0);
		}
	}
	
	public static void analyseEventList(final List<Element> elements, final boolean isUser) {
		for (Element eventListElement : elements) {
			if (!eventListElement.getName().equals("eventList")) {
				continue;
			}
			
			final String listName = eventListElement.attributeValue("name");
			if (listName == null || listName.isBlank()) {
				log.error("Empty name, ignore!");
				continue;
			}
			
			log.info("Registering event list " + listName);
			
			// Each element here should be an event
			for (Element eventElement : eventListElement.elements(EVENT_TAG_NAME)) {
				final String load = eventElement.attributeValue(LOAD_ATTRIBUTE_NAME);
				final String name = eventElement.attributeValue(NAME_ATTRIBUTE_NAME);
				if (load != null) {
					// Load event, register usage in manager
					log.info("Load event %s for event list...".formatted(load));
					EventManager.getInstance().useObject(load);
					
					// Create custom event for list
					// TODO Change to EventListManager!
					Event loadEvent = new EventBuilder().setEventType(EventType.LOAD).setName(load).build();
					EventManager.getInstance().addObject(loadEvent, isUser);
				} else if (name == null) {
					// Empty event
					log.info("Empty event, skip!");
				} else {
					// Normal event
					analyseNormalEvent(eventElement, name, isUser);
				}
			}
		}
	}
	
	private static void analyseNormalEvent(Element eventElement, String name, boolean isUser) {
		EventBuilder builder = new EventBuilder();
		boolean unique = Boolean.valueOf(eventElement.attributeValue(UNIQUE_ATTRIBUTE_NAME));
		log.info("Registering event %s (unique: %b)".formatted(name, unique));
		
		builder.setEventType(EventType.NORMAL);
		builder.setName(name);
		builder.setUnique(unique);
		builder.setEventText(getTextFromElement(eventElement));
		
		// Add to manager
		EventManager.getInstance().addObject(builder.build(), isUser);	
	}

	private static Text getTextFromElement(Element eventElement) {
		final Element textElement = eventElement.element(TEXT_TAG_NAME);
		if (textElement == null) {
			return Text.EMPTY;
		}
		
		final Text text = TextAnalyser.getTextFromElement(textElement);
		final TextType type = text.textType();
		
		// If the text refers to another text, then use in text manager
		if (type == TextType.REFERENCE) {
			TextManager.getInstance().useText(text.name());
		}
		// If the text refers to a text list, then use in text list manager
		else if (type == TextType.LOAD) {
			TextListManager.getInstance().useObject(text.name());
		}
		
		return text;
	}
	
}
