package net.zefinder.ftlmod.text;

import static net.zefinder.ftlmod.Consts.*;

import java.util.List;
import java.util.stream.Stream;

import net.zefinder.ftlmod.NamedObject;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record TextList(String name, Text... texts) implements XmlObject, NamedObject {

	public TextList(String name, Text... texts) {
		this.name = name == null ? "" : name;
		this.texts = texts == null ? new Text[0] : texts;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		final List<XmlTag<?>> textTags = Stream.of(texts).<XmlTag<?>>map(text -> text.toXmlTag()).toList();
		return new XmlTag<List<XmlTag<?>>>(TEXT_LIST_TAG_NAME, textTags, new Attribute(NAME_ATTRIBUTE_NAME, name));
	}

}
