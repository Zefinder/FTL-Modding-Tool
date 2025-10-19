package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.weapon.Weapon.BP_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.BREACH_CHANCE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.CHARGE_LEVELS_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.COLOR_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.COOLDOWN_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.COST_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.DAMAGE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.DESCRIPTION_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.DRONE_TARGETABLE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.EXPLOSION_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.FIRE_CHANCE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.FLAVOR_TYPE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.HIT_SHIELD_SOUNDS_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.HIT_SHIP_SOUNDS_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.HULL_BUST_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.ICON_IMAGE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.IMAGE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.ION_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.LAUNCH_SOUNDS_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.LENGTH_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.LOCKDOWN_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.LOCKED_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.MISSILES_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.MISS_SOUNDS_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.PERS_DAMAGE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.POWER_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.PROJECTILES_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.RADIUS_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.RARITY_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.SHIELD_PIERCING_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.SHORT_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.SPEED_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.SPIN_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.STUN_CHANCE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.SYSTEM_DAMAGE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.TIP_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.TITLE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.TOOLTIP_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.WEAPON_ART_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.WEAPON_TYPE_TAG_NAME_STRING;

import java.awt.Color;
import java.util.List;
import java.util.stream.Stream;

import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

/**
 * Factory to easily create XmlTags for weapons. This is only available to this
 * package!
 */
final class WeaponPropertyFactory {

	private WeaponPropertyFactory() {
	}

	public static final XmlTag<WeaponType> createWeaponType(WeaponType weaponType) {
		return new XmlTag<WeaponType>(WEAPON_TYPE_TAG_NAME_STRING, weaponType);
	}

	public static final XmlTag<Void> createTip(String tipReference) {
		return new XmlTag<Void>(TIP_TAG_NAME_STRING, new Attribute("id", tipReference));
	}

	public static final XmlTag<Void> createTitle(String titleReference) {
		return new XmlTag<Void>(TITLE_TAG_NAME_STRING, new Attribute("id", titleReference));
	}

	public static final XmlTag<Void> createShortTitle(String shortTitleReference) {
		return new XmlTag<Void>(SHORT_TAG_NAME_STRING, new Attribute("id", shortTitleReference));
	}

	public static final XmlTag<Void> createDescription(String descriptionReference) {
		return new XmlTag<Void>(DESCRIPTION_TAG_NAME_STRING, new Attribute("id", descriptionReference));
	}

	public static final XmlTag<Void> createTooltip(String tooltipReference) {
		return new XmlTag<Void>(TOOLTIP_TAG_NAME_STRING, new Attribute("id", tooltipReference));
	}

	public static final XmlTag<Integer> createCooldown(int cooldown) {
		return new XmlTag<Integer>(COOLDOWN_TAG_NAME_STRING, cooldown);
	}

	public static final XmlTag<Integer> createPower(int power) {
		return new XmlTag<Integer>(POWER_TAG_NAME_STRING, power);
	}

	public static final XmlTag<Integer> createCost(int cost) {
		return new XmlTag<Integer>(COST_TAG_NAME_STRING, cost);
	}

	public static final XmlTag<Integer> createRarity(int rarity) {
		return new XmlTag<Integer>(RARITY_TAG_NAME_STRING, rarity);
	}

	public static final XmlTag<Integer> createDamage(int damage) {
		return new XmlTag<Integer>(DAMAGE_TAG_NAME_STRING, damage);
	}

	public static final XmlTag<Integer> createSp(int sp) {
		return new XmlTag<Integer>(SHIELD_PIERCING_TAG_NAME_STRING, sp);
	}

	public static final XmlTag<Integer> createBp(int bp) {
		return new XmlTag<Integer>(BP_TAG_NAME_STRING, bp);
	}

	public static final XmlTag<Integer> createFireChance(int fireChance) {
		return new XmlTag<Integer>(FIRE_CHANCE_TAG_NAME_STRING, fireChance);
	}

	public static final XmlTag<Integer> createBreachChance(int breachChance) {
		return new XmlTag<Integer>(BREACH_CHANCE_TAG_NAME_STRING, breachChance);
	}

	public static final XmlTag<String> createImage(String imageReference) {
		return new XmlTag<String>(IMAGE_TAG_NAME_STRING, imageReference);
	}

	public static final XmlTag<String> createIconImage(String iconImageReference) {
		return new XmlTag<String>(ICON_IMAGE_TAG_NAME_STRING, iconImageReference);
	}

	public static final XmlTag<String> createWeaponArt(String weaponArtReference) {
		return new XmlTag<String>(WEAPON_ART_TAG_NAME_STRING, weaponArtReference);
	}

	public static final XmlTag<List<WeaponSound>> createLaunchSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>(LAUNCH_SOUNDS_TAG_NAME_STRING, weaponSounds);
	}

	public static final XmlTag<Integer> createLength(int length) {
		return new XmlTag<Integer>(LENGTH_TAG_NAME_STRING, length);
	}

	public static final XmlTag<WeaponBeamColor> createColor(Color color) {
		return new XmlTag<WeaponBeamColor>(COLOR_TAG_NAME_STRING,
				new WeaponBeamColor(color.getRed(), color.getGreen(), color.getBlue()));
	}

	public static final XmlTag<WeaponBeamColor> createColor(WeaponBeamColor color) {
		return new XmlTag<WeaponBeamColor>(COLOR_TAG_NAME_STRING, color);
	}

	public static final XmlTag<List<WeaponProjectile>> createProjectiles(WeaponProjectile... projectiles) {
		List<WeaponProjectile> weaponProjectiles = List.of(projectiles);
		return new XmlTag<List<WeaponProjectile>>(PROJECTILES_TAG_NAME_STRING, weaponProjectiles);
	}

	public static final XmlTag<Integer> createRadius(int radius) {
		return new XmlTag<Integer>(RADIUS_TAG_NAME_STRING, radius);
	}

	public static final XmlTag<Integer> createSpin(int spin) {
		return new XmlTag<Integer>(SPIN_TAG_NAME_STRING, spin);
	}

	public static final XmlTag<Integer> createShots(int shots) {
		return new XmlTag<Integer>(SHORT_TAG_NAME_STRING, shots);
	}

	public static final XmlTag<Void> createFlavorType(String flavorType) {
		return new XmlTag<Void>(FLAVOR_TYPE_TAG_NAME_STRING, new Attribute("id", flavorType));
	}

	public static final XmlTag<Integer> createStunChance(int stunChance) {
		return new XmlTag<Integer>(STUN_CHANCE_TAG_NAME_STRING, stunChance);
	}

	public static final XmlTag<Integer> createSpeed(int speed) {
		return new XmlTag<Integer>(SPEED_TAG_NAME_STRING, speed);
	}

	public static final XmlTag<Integer> createPersDamage(int persDamage) {
		return new XmlTag<Integer>(PERS_DAMAGE_TAG_NAME_STRING, persDamage);
	}

	public static final XmlTag<Integer> createLockdown(boolean lockdown) {
		return new XmlTag<Integer>(LOCKDOWN_TAG_NAME_STRING, lockdown ? 1 : 0);
	}

	public static final XmlTag<Integer> createSysDamage(int sysDamage) {
		return new XmlTag<Integer>(SYSTEM_DAMAGE_TAG_NAME_STRING, sysDamage);
	}

	public static final XmlTag<Integer> createHullBust(boolean hullBust) {
		return new XmlTag<Integer>(HULL_BUST_TAG_NAME_STRING, hullBust ? 1 : 0);
	}

	public static final XmlTag<Integer> createDroneTargetable(boolean droneTargetable) {
		return new XmlTag<Integer>(DRONE_TARGETABLE_TAG_NAME_STRING, droneTargetable ? 1 : 0);
	}

	public static final XmlTag<Integer> createMissiles(int missiles) {
		return new XmlTag<Integer>(MISSILES_TAG_NAME_STRING, missiles);
	}

	public static final XmlTag<Integer> createIon(int ion) {
		return new XmlTag<Integer>(ION_TAG_NAME_STRING, ion);
	}

	public static final XmlTag<String> createExplosion(String explosionReference) {
		return new XmlTag<String>(EXPLOSION_TAG_NAME_STRING, explosionReference);
	}

	public static final XmlTag<Integer> createLocked(boolean locked) {
		return new XmlTag<Integer>(LOCKED_TAG_NAME_STRING, locked ? 1 : 0);
	}

	public static final WeaponBoost createBoost(WeaponBoostType weaponBoostType, int amount, int count) {
		return new WeaponBoost(weaponBoostType, amount, count);
	}

	public static final XmlTag<Integer> createChargeLevels(int chargeLevels) {
		return new XmlTag<Integer>(CHARGE_LEVELS_TAG_NAME_STRING, chargeLevels);
	}

	public static final XmlTag<List<WeaponSound>> createHitShipSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>(HIT_SHIP_SOUNDS_TAG_NAME_STRING, weaponSounds);
	}

	public static final XmlTag<List<WeaponSound>> createHitShieldSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>(HIT_SHIELD_SOUNDS_TAG_NAME_STRING, weaponSounds);
	}

	public static final XmlTag<List<WeaponSound>> createMissSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>(MISS_SOUNDS_TAG_NAME_STRING, weaponSounds);
	}
}
