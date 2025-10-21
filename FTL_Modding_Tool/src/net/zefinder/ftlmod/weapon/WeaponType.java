package net.zefinder.ftlmod.weapon;

public enum WeaponType {
	LASER("LASER"), BEAM("BEAM"), BOMB("BOMB"), MISSILE("MISSILE"), BURST("BURST");

	private final String propertyName;

	private WeaponType(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String propertyName() {
		return propertyName;
	}

	public static WeaponType fromString(final String type) {
		if (type == null) {
			return LASER;
		}
		
		return switch (type.toUpperCase()) {
		case "LASER" -> LASER;
		case "BEAM" -> BEAM;
		case "BOMB" -> BOMB;
		case "MISSILE" -> MISSILE;
		case "BURST" -> BURST;
		default -> LASER;
		};
	}
}
