package net.zefinder.ftlmod.text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.zefinder.ftlmod.ObjectManager;
import net.zefinder.ftlmod.analyser.TextListAnalyser;

public final class TextManager {

	// TODO TextLists
	// This uses one manager per language
	private static final Map<TextLanguage, LanguageTextManager> LANGUAGE_MAP = new HashMap<TextLanguage, LanguageTextManager>();

	private static final class LanguageTextManager extends ObjectManager<Text> {
				
		public LanguageTextManager(TextLanguage language) {
			super(language.name());
		}
		
	}

	private static final TextManager instance = new TextManager();

	private TextManager() {
	}

	public final Optional<Text> getOriginalText(final String name, final TextLanguage language) {
		LanguageTextManager languageTextManager = LANGUAGE_MAP.get(language);
		if (languageTextManager != null) {
			return languageTextManager.getOriginalObject(name);
		}

		return Optional.empty();
	}

	public final Optional<Text> getOriginalText(final String name) {
		return getOriginalText(name, TextLanguage.EN);
	}

	public final Optional<Text> getText(final String name, final TextLanguage language) {
		LanguageTextManager languageTextManager = LANGUAGE_MAP.get(language);
		if (languageTextManager != null) {
			return languageTextManager.getObject(name);
		}

		return Optional.empty();
	}

	public final Optional<Text> getText(final String name) {
		return getText(name, TextLanguage.EN);
	}

	public final void removeText(final String name, final TextLanguage language) {
		LanguageTextManager languageTextManager = LANGUAGE_MAP.get(language);
		if (languageTextManager != null) {
			languageTextManager.removeObject(name);
		}
	}

	public final void removeText(final String name) {
		LANGUAGE_MAP.forEach((language, manager) -> manager.removeObject(name));
	}

	public final void addText(final Text text, final TextLanguage language, final boolean isUser) {
		if (text.name().equals("ENGI_MANTIS_FIGHT")) {
			System.out.println("");
		}
		LANGUAGE_MAP.computeIfAbsent(language, l -> new LanguageTextManager(l)).addObject(text, isUser);
	}

	public final void addText(final Text text, final boolean isUser) {
		addText(text, TextLanguage.EN, isUser);
	}

	public final void useText(final String name, final TextLanguage language) {
		LANGUAGE_MAP.computeIfAbsent(language, l -> new LanguageTextManager(l)).useObject(name);
	}

	/**
	 * Set the text usage for all languages.
	 * 
	 * @param name
	 */
	public final void useText(final String name) {
		for (TextLanguage language : TextLanguage.values()) {			
			useText(name, language);
		}
	}

	public final boolean isTextUsed(final String name, final TextLanguage language) {
		LanguageTextManager languageTextManager = LANGUAGE_MAP.get(language);
		if (languageTextManager != null) {
			return languageTextManager.isObjectUsed(name);
		}

		return false;
	}

	public final void resetUsage() {
		LANGUAGE_MAP.forEach((language, manager) -> manager.resetUsage());
	}

	public final List<Text> getUnusedText(final TextLanguage language) {
		LanguageTextManager languageTextManager = LANGUAGE_MAP.get(language);
		if (languageTextManager != null) {
			return languageTextManager.getUnusedObjects();
		}

		return List.of();
	}

	public final void clearOriginalText() {
		LANGUAGE_MAP.forEach((language, manager) -> manager.clearOriginalObjects());
	}

	public final void clearUserText() {
		LANGUAGE_MAP.forEach((language, manager) -> manager.clearUserObjects());
	}

	public static TextManager getInstance() {
		return instance;
	}

}
