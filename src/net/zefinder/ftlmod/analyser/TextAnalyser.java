package net.zefinder.ftlmod.analyser;

import static net.zefinder.ftlmod.Consts.*;

import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.text.TextLanguage;
import net.zefinder.ftlmod.text.TextManager;
import net.zefinder.ftlmod.text.TextType;

/**
 * Analyser used to get all isolated text tags (they must be named)
 */
public final class TextAnalyser {

	private static final Logger log = LoggerFactory.getLogger(TextAnalyser.class);

	private TextAnalyser() {
	}

	public static final void analyse(final List<Element> elements, final boolean isUser) {
		for (Element textElement : elements) {
			if (!textElement.getName().equals(TEXT_TAG_NAME)) {
				continue;
			}

			final Text text = getTextFromElement(textElement);
			final String name = text.name();
			if (name.isBlank()) {
				log.error("Empty text element name, ignore!");
				continue;
			}
			final TextLanguage language = text.language();

			log.info("Registering text %s (language %s)".formatted(name, language.languageCode()));

			if (text.text().isEmpty()) {
				log.warn("Empty text for element " + name);
			}

			TextManager.getInstance().addText(new Text(TextType.NAMED, name, text.text(), language), language, isUser);
		}
	}

	public static final Text getTextFromElement(final Element textElement) {
		if (!textElement.getName().equals(TEXT_TAG_NAME)) {
			return Text.EMPTY;
		}

		final String name = textElement.attributeValue(NAME_ATTRIBUTE_NAME);
		final String id = textElement.attributeValue(ID_ATTRIBUTE_NAME);
		final String load = textElement.attributeValue(LOAD_ATTRIBUTE_NAME);

		if (load != null) {
			if (load.isBlank()) {
				log.error("Empty text element load, ignore!");
				return Text.EMPTY;
			}

			return new Text(TextType.LOAD, load, "");
		}

		if (id != null) {
			if (id.isBlank()) {
				log.error("Empty text element id, ignore!");
				return Text.EMPTY;
			}

			return new Text(TextType.REFERENCE, id, "");
		}

		// From here there will be a text
		final String text = (String) textElement.getData();
		if (name == null) {
			return new Text(TextType.NORMAL, text);
		}

		// Empty name can be fine?
		if (name.isBlank()) {
			log.warn("Empty name for text... intended?");
		}

		final TextLanguage language = TextLanguage.fromString(textElement.attributeValue(LANGUAGE_ATTRIBUTE_NAME));
		if (text == null || text.isBlank()) {
			log.warn("Empty text... Intended?");
		}

		return new Text(TextType.NAMED, name, text, language);
	}

}
