package net.zefinder.ftlmod.analyser;

import static net.zefinder.ftlmod.Consts.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.text.TextList;
import net.zefinder.ftlmod.text.TextListManager;
import net.zefinder.ftlmod.text.TextManager;
import net.zefinder.ftlmod.text.TextType;

public final class TextListAnalyser {

	private static final Logger log = LoggerFactory.getLogger(TextListAnalyser.class);

	private TextListAnalyser() {
	}

	public static final void analyse(final List<Element> elements, final boolean isUser) {
		for (Element textListElement : elements) {
			if (!textListElement.getName().equals(TEXT_LIST_TAG_NAME)) {
				continue;
			}

			final String name = textListElement.attributeValue(NAME_ATTRIBUTE_NAME);
			if (name == null || name.isBlank()) {
				log.error("Empty text list name... ignore!");
				continue;
			}
			
			log.info("Registering text list %s".formatted(name));

			// Search for all text tags
			final Set<Text> texts = new LinkedHashSet<Text>();
			for (Element textElement : textListElement.elements(TEXT_TAG_NAME)) {
				// If empty text, ignore
				final Text text = TextAnalyser.getTextFromElement(textElement);

				if (!text.equals(Text.EMPTY)) {
					final String textName = text.name();
					final TextType type = text.textType();
					// If the text refers to another text, then use in text manager
					if (type == TextType.REFERENCE) {
						TextManager.getInstance().useText(textName);
					}
					// If the text refers to a text list, then use in text list manager
					else if (type == TextType.LOAD) {
						TextListManager.getInstance().useObject(textName);
					}

					texts.add(text);
				}
			}

			TextListManager.getInstance().addObject(new TextList(name, texts.toArray(Text[]::new)), isUser);
		}
	}
}
