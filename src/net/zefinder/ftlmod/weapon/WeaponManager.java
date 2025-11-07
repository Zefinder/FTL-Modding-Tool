package net.zefinder.ftlmod.weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.zefinder.ftlmod.UsedObject;

public final class WeaponManager {

	private static final WeaponManager instance = new WeaponManager();

	private static final Map<String, UsedObject<Weapon>> ORIGINAL_GAME_WEAPONS = new HashMap<String, UsedObject<Weapon>>();
	private static final Map<String, UsedObject<Weapon>> USER_WEAPONS = new HashMap<String, UsedObject<Weapon>>();

	private WeaponManager() {
	}

	public final Optional<Weapon> getOriginalWeapon(final String name) {
		if (ORIGINAL_GAME_WEAPONS.containsKey(name)) {
			return Optional.ofNullable(ORIGINAL_GAME_WEAPONS.get(name).getObject());
		}

		return Optional.empty();
	}

	/**
	 * Tries to get a weapon from the user weapons. If it does not exist, then tries
	 * to get it from the original weapons. This throws a
	 * {@link NullPointerException} if the weapon has been redefined by the user BUT
	 * is null.
	 * 
	 * @param name the weapon name
	 * @return a defined weapon, or {@link Optional#empty()}
	 */
	public final Optional<Weapon> getWeapon(final String name) {
		if (USER_WEAPONS.containsKey(name)) {
			return Optional.of(USER_WEAPONS.get(name).getObject()); // User weapon cannot be null
		}

		return getOriginalWeapon(name);
	}

	/**
	 * Removes a weapon from the users weapons.
	 * 
	 * @param weapon the weapon's name to remove
	 */
	public final void removeWeapon(final String weaponName) {
		USER_WEAPONS.remove(weaponName);
	}

	/**
	 * Adds a weapons to either the user or the original game.
	 * 
	 * @param weapon the weapon to add
	 */
	public final void addWeapon(final Weapon weapon, final boolean isUser) {
		String weaponName = weapon.name();
		UsedObject<Weapon> usedObject = UsedObject.of(weapon);

		if (isUser) {
			if (ORIGINAL_GAME_WEAPONS.containsKey(weaponName) && ORIGINAL_GAME_WEAPONS.get(weaponName).isUsed()) {
				usedObject.use();
			}

			USER_WEAPONS.put(weapon.name(), usedObject);
		} else {
			ORIGINAL_GAME_WEAPONS.put(weapon.name(), UsedObject.of(weapon));
		}
	}

	public final void useWeapon(final String weaponName) {
		if (USER_WEAPONS.containsKey(weaponName)) {
			USER_WEAPONS.get(weaponName).use();
		} else if (ORIGINAL_GAME_WEAPONS.containsKey(weaponName)) {
			ORIGINAL_GAME_WEAPONS.get(weaponName).use();
		} else {
			ORIGINAL_GAME_WEAPONS.put(weaponName, UsedObject.empty());
		}
	}

	public final boolean isWeaponUsed(final String weaponName) {
		if (USER_WEAPONS.containsKey(weaponName)) {
			return USER_WEAPONS.get(weaponName).isUsed();
		} else if (ORIGINAL_GAME_WEAPONS.containsKey(weaponName)) {
			return ORIGINAL_GAME_WEAPONS.get(weaponName).isUsed();
		}

		return false;
	}

	public final void resetUsage() {
		USER_WEAPONS.forEach((name, usedObject) -> usedObject.reset());
	}

	public final List<Weapon> getUnusedWeapons() {
		Map<String, UsedObject<Weapon>> test = new HashMap<String, UsedObject<Weapon>>();
		test.putAll(ORIGINAL_GAME_WEAPONS);
		test.putAll(USER_WEAPONS);

		return test.values().stream().filter(usedObject -> !usedObject.isUsed())
				.map(unusedWeapon -> unusedWeapon.getObject()).toList();
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
