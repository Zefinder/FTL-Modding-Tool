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
}
