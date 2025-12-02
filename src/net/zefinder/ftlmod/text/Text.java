package net.zefinder.ftlmod.text;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.NamedObject;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record Text(TextType textType, String name, String text, TextLanguage language) implements NamedObject, XmlObject {

	public static final Text EMPTY = new Text(TextType.NORMAL, "", "", TextLanguage.EN);

	public Text(final TextType textType, final String name, final String text, final TextLanguage language) {
		this.textType = textType == null ? TextType.NORMAL : textType;
		this.name = name == null ? "" : name;
		this.text = text == null ? "" : text;
		this.language = language == null ? TextLanguage.EN : language;
	}

	public Text(final TextType textType, final String name, final String text) {
		this(textType, name, text, TextLanguage.EN);
	}
	
	public Text(final TextType textType, final String text) {
		this(textType, "", text, TextLanguage.EN);
	}

	@Override
	public XmlTag<?> toXmlTag() {
		if (textType == TextType.LOAD) {
			return new XmlTag<Void>(TEXT_TAG_NAME, new Attribute(LOAD_ATTRIBUTE_NAME, name));
		}

		if (textType == TextType.REFERENCE) {
			return new XmlTag<Void>(TEXT_TAG_NAME, new Attribute(ID_ATTRIBUTE_NAME, name));
		}

		List<Attribute> attributes = new ArrayList<Attribute>();
		if (language != TextLanguage.EN) {
			attributes.add(new Attribute(LANGUAGE_ATTRIBUTE_NAME, language.languageCode()));
		}

		if (textType == TextType.NAMED) {
			attributes.add(new Attribute(NAME_ATTRIBUTE_NAME, name));
		}

		return new XmlTag<String>(TEXT_TAG_NAME, text, attributes.toArray(Attribute[]::new));
	}

}
