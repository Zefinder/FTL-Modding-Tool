package net.zefinder.ftlmod.game;

public enum DamageEffect {
	RANDOM("random"), ALL("all"), BREACH("breach"), FIRE("fire"),

	// None is also a possible option, although you can't get it from fromString
	NONE("");

	private final String effectName;

	private DamageEffect(String effectName) {
		this.effectName = effectName;
	}

	public String effectName() {
		return effectName;
	}

	public static final DamageEffect fromString(String effect) {
		if (effect == null) {
			return ALL;
		}

		return switch (effect) {
		case "random" -> RANDOM;
		case "all" -> ALL;
		case "breach" -> BREACH;
		case "fire" -> FIRE;
		default -> ALL;
		};
	}
}
