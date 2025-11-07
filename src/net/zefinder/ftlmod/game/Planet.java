package net.zefinder.ftlmod.game;

public enum Planet {
	NONE, PLANET_UNPOPULATED, PLANET_POPULATED_SMALL, PLANET_POPULATED, PLANET_GAS;

	public static final Planet fromString(String planet) {
		try {
			return Planet.valueOf(planet);
		} catch (Exception e) {
			return NONE;
		}
	}
}
