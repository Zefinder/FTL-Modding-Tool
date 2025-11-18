package net.zefinder.ftlmod.analyser;

import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.event.Event;
import net.zefinder.ftlmod.event.EventCreationException;

public class EventAnalyser {

	private static final Logger log = LoggerFactory.getLogger(EventAnalyser.class);

	private EventAnalyser() {
	}

	public static final void analyse(final List<Element> elements, final boolean isUser) throws EventCreationException {
		for (Element eventElement : elements) {
			String load = eventElement.attributeValue(Event.LOAD_ATTRIBUTE_NAME);
			String name = eventElement.attributeValue(Event.NAME_ATTRIBUTE_NAME);
			
			if (load != null) {
				// Load event
				log.info("Load event %s, register usage in manager...".formatted(load));
			} else if (name == null) {
				// Empty event
				log.info("Empty event, skip!");
			} else {
				// Normal event
				boolean unique = Boolean.valueOf(eventElement.attributeValue(Event.UNIQUE_ATTRIBUTE_NAME));
				log.info("Registering event %s (unique: %b)".formatted(name, unique));
			}
			
//			System.exit(0);
		}

	}

}
