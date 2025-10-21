package net.zefinder.ftlmod.text;

public enum TextLanguage {
	EN("en"), DE("de"), ES("es"), FR("fr"), IT("it"), JA("ja"), PL("pl"), PT("pt"), RU("ru"), ZH_HANS("zh-Hans");

	private final String languageCode;

	private TextLanguage(final String languageCode) {
		this.languageCode = languageCode;
	}

	public String languageCode() {
		return languageCode;
	}

	public static TextLanguage fromString(final String code) {
		if (code == null) {
			return EN;
		}
		
		return switch (code) {
		case "en" -> EN;
		case "de" -> DE;
		case "es" -> ES;
		case "fr" -> FR;
		case "it" -> IT;
		case "ja" -> JA;
		case "pl" -> PL;
		case "pt" -> PT;
		case "ru" -> RU;
		case "zh-Hans" -> ZH_HANS;
		default -> EN;
		};
	}
}
