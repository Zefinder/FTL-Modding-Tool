package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.event.Event.ID_ATTRIBUTE_NAME;
import static net.zefinder.ftlmod.event.Event.LOAD_ATTRIBUTE_NAME;
import static net.zefinder.ftlmod.event.Event.TEXT_TAG_NAME;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventText(EventTextType textType, String text) implements XmlObject {
	
	public static final EventText EMPTY = new EventText(EventTextType.NORMAL, "");

	public EventText(final EventTextType textType, final String text) {
		this.textType = textType == null ? EventTextType.NORMAL : textType;
		this.text = text == null ? "" : text;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		if (textType == EventTextType.LOAD) {
			return new XmlTag<String>(TEXT_TAG_NAME, new Attribute(LOAD_ATTRIBUTE_NAME, text));
		}

		if (textType == EventTextType.REFERENCE) {
			return new XmlTag<String>(TEXT_TAG_NAME, new Attribute(ID_ATTRIBUTE_NAME, text));
		}

		return new XmlTag<String>(TEXT_TAG_NAME, text);
	}

}
