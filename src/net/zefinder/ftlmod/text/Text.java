package net.zefinder.ftlmod.text;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.NamedObject;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record Text(TextType textType, String name, String text, TextLanguage language) implements NamedObject, XmlObject {

	public static final Text EMPTY = new Text(TextType.NORMAL, "", "", TextLanguage.EN);

	public static final String TEXT_TAG_NAME = "text";
	public static final String TEXT_NAME_ATTRIBUTE = "name";
	public static final String LOAD_ATTRIBUTE_NAME = "load";
	public static final String ID_ATTRIBUTE_NAME = "id";
	public static final String TEXT_LANGUAGE_ATTRIBUTE = "language";

	public Text(final TextType textType, final String name, final String text, final TextLanguage language) {
		this.textType = textType == null ? TextType.NORMAL : textType;
		this.name = name == null ? "" : name;
		this.text = text == null ? "" : text;
		this.language = language == null ? TextLanguage.EN : language;
	}

	public Text(final TextType textType, final String text) {
		this(textType, "", text, TextLanguage.EN);
	}

	@Override
	public XmlTag<?> toXmlTag() {
		if (textType == TextType.LOAD) {
			return new XmlTag<String>(TEXT_TAG_NAME, new Attribute(LOAD_ATTRIBUTE_NAME, text));
		}

		if (textType == TextType.REFERENCE) {
			return new XmlTag<String>(TEXT_TAG_NAME, new Attribute(ID_ATTRIBUTE_NAME, text));
		}

		List<Attribute> attributes = new ArrayList<Attribute>();
		if (language != TextLanguage.EN) {
			attributes.add(new Attribute(TEXT_LANGUAGE_ATTRIBUTE, language.languageCode()));
		}

		if (textType == TextType.NAMED) {
			attributes.add(new Attribute(TEXT_NAME_ATTRIBUTE, name));
		}

		return new XmlTag<String>(TEXT_TAG_NAME, text, attributes.toArray(Attribute[]::new));
	}

}
