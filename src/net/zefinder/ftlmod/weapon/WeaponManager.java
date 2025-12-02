package net.zefinder.ftlmod.weapon;

import net.zefinder.ftlmod.ObjectManager;

public final class WeaponManager extends ObjectManager<Weapon> {

	private static final WeaponManager instance = new WeaponManager();
	
	private WeaponManager() {
		// TODO Auto-generated constructor stub
	}

	public static final WeaponManager getInstance() {
		return instance;
	}

}
