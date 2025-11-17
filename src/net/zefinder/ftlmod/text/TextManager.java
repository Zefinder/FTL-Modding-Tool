package net.zefinder.ftlmod.text;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TextManager {
	
	// TODO Use ObjectManager

	private static final TextManager instance = new TextManager();

	private static final Map<TextLanguage, Map<String, Text>> ORIGINAL_TEXTS_PER_LANGUAGE = new HashMap<TextLanguage, Map<String, Text>>();
	private static final Map<TextLanguage, Map<String, Text>> USER_TEXTS_PER_LANGUAGE = new HashMap<TextLanguage, Map<String, Text>>();

	private TextManager() {
	}

	public final void addText(final Text text, final boolean isUser) {
		if (isUser) {
			USER_TEXTS_PER_LANGUAGE.computeIfAbsent(text.language(), t -> new LinkedHashMap<String, Text>())
					.put(text.name(), text);
		} else {
			ORIGINAL_TEXTS_PER_LANGUAGE.computeIfAbsent(text.language(), t -> new LinkedHashMap<String, Text>())
					.put(text.name(), text);
		}
	}

	public final Text getOriginalText(String name, TextLanguage language) {
		Map<String, Text> languageTexts = ORIGINAL_TEXTS_PER_LANGUAGE.get(language);
		if (languageTexts != null) {
			return languageTexts.getOrDefault(name, Text.EMPTY);
		}

		return Text.EMPTY;
	}

	public final Text getText(String name, TextLanguage language) {
		Map<String, Text> languageTexts = USER_TEXTS_PER_LANGUAGE.get(language);
		if (languageTexts != null) {
			return languageTexts.getOrDefault(name, getOriginalText(name, language));
		}

		return getOriginalText(name, language);
	}

	public final Text getOriginalText(String name) {
		return getOriginalText(name, TextLanguage.EN);
	}

	public final Text getText(String name) {
		return getText(name, TextLanguage.EN);
	}

	public final void clearOriginalTexts() {
		ORIGINAL_TEXTS_PER_LANGUAGE.clear();
	}
	
	public final void clearUserTexts() {
		USER_TEXTS_PER_LANGUAGE.clear();
	}

	public static TextManager getInstance() {
		return instance;
	}

}
