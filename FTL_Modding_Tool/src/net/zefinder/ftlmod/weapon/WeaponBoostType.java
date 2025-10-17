package net.zefinder.ftlmod.weapon;

public enum WeaponBoostType {
	DAMAGE("damage"), COOLDOWN("cooldown");
	
	private final String propertyName;
	
	private WeaponBoostType(final String propertyName) {
		this.propertyName = propertyName;
	}
	
	public final String propertyName() {
		return propertyName;
	}
}
