package net.zefinder.ftlmod.analyser;

import static net.zefinder.ftlmod.text.Text.*;

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
public class TextAnalyser {

	private static final Logger log = LoggerFactory.getLogger(TextAnalyser.class);

	private TextAnalyser() {
	}

	public static final void analyse(final List<Element> elements, final boolean isUser) {
		for (Element textElement : elements) {
			final String name = textElement.attributeValue(TEXT_NAME_ATTRIBUTE);
			if (name == null || name.isBlank()) {
				log.error("Empty text element name, ignore!");
				continue;
			}
			final TextLanguage language = TextLanguage.fromString(textElement.attributeValue(TEXT_LANGUAGE_ATTRIBUTE));

			log.info("Registering text %s (language %s)".formatted(name, language.languageCode()));

			final String text = (String) textElement.getData();
			if (text == null || text.isBlank()) {
				log.error("Empty text, ignore!");
				continue;
			}

			TextManager.getInstance().addText(new Text(TextType.NAMED, name, text, language), language, isUser);
		}
	}

}
