package net.zefinder.ftlmod.weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class WeaponManager {

	private static final WeaponManager instance = new WeaponManager();

	private static final Map<String, Weapon> ORIGINAL_GAME_WEAPONS = new HashMap<String, Weapon>();
	private static final Map<String, Weapon> USER_WEAPONS = new HashMap<String, Weapon>();

	private WeaponManager() {
	}

	public final Optional<Weapon> getOriginalWeapon(String name) {
		if (ORIGINAL_GAME_WEAPONS.containsKey(name)) {
			return Optional.of(ORIGINAL_GAME_WEAPONS.get(name));
		}

		return Optional.empty();
	}

	/**
	 * Tries to get a weapon from the user weapons. If it does not exist, then tries
	 * to get it from the original weapons.
	 * 
	 * @param name the weapon name
	 * @return a defined weapon, or {@link Optional#empty()}
	 */
	public final Optional<Weapon> getWeapon(String name) {
		if (USER_WEAPONS.containsKey(name)) {
			return Optional.of(USER_WEAPONS.get(name));
		}

		return getOriginalWeapon(name);
	}

	/**
	 * Removes a weapon from the users weapons.
	 * 
	 * @param weapon the weapon's name to remove
	 */
	public final void removeWeapon(String weaponName) {
		USER_WEAPONS.remove(weaponName);
	}

	/**
	 * Adds a weapon to the users weapons.
	 * 
	 * @param weapon the weapon to add
	 */
	public final void addWeapon(Weapon weapon) {
		USER_WEAPONS.put(weapon.name(), weapon);
	}

	public final void clearOriginalWeapons() {
		ORIGINAL_GAME_WEAPONS.clear();
	}

	public final void clearUserWeapons() {
		USER_WEAPONS.clear();
	}

	public static final WeaponManager getInstance() {
		return instance;
	}

}
