package net.zefinder.ftlmod.text;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record Text(String name, String text, TextLanguage language) implements XmlObject {

	public static final Text EMPTY = new Text("", "", TextLanguage.EN);
	
	public static final String TEXT_TAG_NAME = "text";
	public static final String TEXT_NAME_ATTRIBUTE = "name";
	public static final String TEXT_LANGUAGE_ATTRIBUTE = "language";

	public Text(String name, String text, TextLanguage language) {
		this.name = name == null ? "" : name;
		this.text = text == null ? "" : text;
		this.language = language == null ? TextLanguage.EN : language;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(TEXT_NAME_ATTRIBUTE, name));
		if (language != TextLanguage.EN) {
			attributes.add(new Attribute(TEXT_LANGUAGE_ATTRIBUTE, language.languageCode()));
		}

		return new XmlTag<String>(TEXT_TAG_NAME, text, attributes.toArray(Attribute[]::new));
	}

}
