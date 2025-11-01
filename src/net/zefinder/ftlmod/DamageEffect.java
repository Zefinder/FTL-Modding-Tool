package net.zefinder.ftlmod;

public enum DamageEffect {
	RANDOM("random"), ALL("all"), BREACH("breach"), FIRE("fire");
	
	private final String effectName;
	
	private DamageEffect(String effectName) {
		this.effectName = effectName;
	}
	
	public String effectName() {
		return effectName;
	}
	
	public static final DamageEffect fromString(String effect) {
		return switch (effect) {
		case "random" -> RANDOM;
		case "all" -> ALL;
		case "breach" -> BREACH;
		case "fire" -> FIRE;
		default -> ALL; 
		};
	}
}
