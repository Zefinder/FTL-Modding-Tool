package net.zefinder.ftlmod.event;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public class Event implements XmlObject {

	public static final String EVENT_TAG_NAME = "event";
	public static final String TEXT_TAG_NAME = "text";

	public static final String LOAD_ATTRIBUTE_NAME = "load";
	public static final String NAME_ATTRIBUTE_NAME = "name";
	public static final String UNIQUE_ATTRIBUTE_NAME = "unique";
	public static final String ID_ATTRIBUTE_NAME = "id";

	public static final Event EMPTY = new Event();

	private final EventType eventType;
	private final String name;
	private final boolean unique;

	private final Text eventText;
	private final List<EventChoice> choices;

	/**
	 * To create empty events
	 */
	private Event() {
		eventType = EventType.NONE;
		name = "";
		unique = false;
		eventText = Text.EMPTY;
		choices = List.of();
	}

	public Event(final EventType eventType, final String name, final boolean unique, Text eventText,
			final EventChoice... choices) throws EventCreationException {
		if (eventType == null) {
			throw new EventCreationException("Event type cannot be null or empty, error!");
		}

		if (eventType == EventType.LOAD) {
			// If loading an event, the name cannot be null!
			if (name == null || name.isBlank()) {
				throw new EventCreationException("Event load cannot be null or empty, error!");
			}
		}

		this.eventType = eventType;
		this.name = name == null ? "" : name;
		this.unique = unique;
		this.eventText = eventText;
		this.choices = List.of(choices);
	}

	@Override
	public XmlTag<?> toXmlTag() {
		if (eventType == EventType.NONE) {
			return new XmlTag<Void>(EVENT_TAG_NAME);
		}

		if (eventType == EventType.LOAD) {
			return new XmlTag<Void>(EVENT_TAG_NAME, new Attribute(LOAD_ATTRIBUTE_NAME, name));
		}

		// From here normal event
		List<Attribute> attributes = new ArrayList<Attribute>();
		if (!name.isBlank()) {
			attributes.add(new Attribute(NAME_ATTRIBUTE_NAME, name));
		}

		if (unique) {
			attributes.add(new Attribute(UNIQUE_ATTRIBUTE_NAME, Boolean.toString(unique)));
		}
		
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		if (!choices.isEmpty()) {
			tags.addAll(choices.stream().map(eventChoice -> eventChoice.toXmlTag()).toList());
		}

		return new XmlTag<List<XmlTag<?>>>(EVENT_TAG_NAME, tags, attributes.toArray(Attribute[]::new));
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Event other && other.eventType == eventType && other.name.equals(name)
				&& other.unique == unique;
	}

	@Override
	public int hashCode() {
		return (eventType.name() + Boolean.toString(unique) + name).hashCode();
	}

}
