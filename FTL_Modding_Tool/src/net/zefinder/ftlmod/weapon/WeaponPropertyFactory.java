package net.zefinder.ftlmod.weapon;

import java.awt.Color;
import java.util.List;
import java.util.stream.Stream;

import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

/**
 * Factory to easily create XmlTags for weapons. This is only available to this
 * package!
 */
class WeaponPropertyFactory {

	private WeaponPropertyFactory() {
	}

	public static XmlTag<WeaponType> createWeaponType(WeaponType weaponType) {
		return new XmlTag<WeaponType>("type", weaponType);
	}

	public static XmlTag<Void> createTip(String tipReference) {
		return new XmlTag<Void>("tip", new Attribute("id", tipReference));
	}

	public static XmlTag<Void> createTitle(String titleReference) {
		return new XmlTag<Void>("title", new Attribute("id", titleReference));
	}

	public static XmlTag<Void> createShortTitle(String shortTitleReference) {
		return new XmlTag<Void>("short", new Attribute("id", shortTitleReference));
	}

	public static XmlTag<Void> createDescription(String descriptionReference) {
		return new XmlTag<Void>("desc", new Attribute("id", descriptionReference));
	}

	public static XmlTag<Void> createTooltip(String tooltipReference) {
		return new XmlTag<Void>("tooltip", new Attribute("id", tooltipReference));
	}

	public static XmlTag<Integer> createCooldown(int cooldown) {
		return new XmlTag<Integer>("cooldown", cooldown);
	}

	public static XmlTag<Integer> createPower(int power) {
		return new XmlTag<Integer>("power", power);
	}

	public static XmlTag<Integer> createCost(int cost) {
		return new XmlTag<Integer>("cost", cost);
	}

	public static XmlTag<Integer> createRarity(int rarity) {
		return new XmlTag<Integer>("rarity", rarity);
	}

	public static XmlTag<Integer> createDamage(int damage) {
		return new XmlTag<Integer>("damage", damage);
	}

	public static XmlTag<Integer> createSp(int sp) {
		return new XmlTag<Integer>("sp", sp);
	}

	public static XmlTag<Integer> createBp(int bp) {
		return new XmlTag<Integer>("bp", bp);
	}

	public static XmlTag<Integer> createFireChance(int fireChance) {
		return new XmlTag<Integer>("fireChance", fireChance);
	}

	public static XmlTag<Integer> createBreachChance(int breachChance) {
		return new XmlTag<Integer>("breachChance", breachChance);
	}

	public static XmlTag<String> createImage(String imageReference) {
		return new XmlTag<String>("image", imageReference);
	}

	public static XmlTag<String> createIconImage(String iconImageReference) {
		return new XmlTag<String>("iconImage", iconImageReference);
	}

	public static XmlTag<String> createWeaponArt(String weaponArtReference) {
		return new XmlTag<String>("weaponArt", weaponArtReference);
	}

	public static XmlTag<List<WeaponSound>> createLaunchSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>("launchSounds", weaponSounds);
	}

	public static XmlTag<Integer> createLength(int length) {
		return new XmlTag<Integer>("length", length);
	}

	public static XmlTag<WeaponBeamColor> createColor(Color color) {
		return new XmlTag<WeaponBeamColor>("color",
				new WeaponBeamColor(color.getRed(), color.getGreen(), color.getBlue()));
	}

	public static XmlTag<WeaponBeamColor> createColor(WeaponBeamColor color) {
		return new XmlTag<WeaponBeamColor>("color", color);
	}

	public static XmlTag<List<WeaponProjectile>> createProjectiles(WeaponProjectile... projectiles) {
		List<WeaponProjectile> weaponProjectiles = List.of(projectiles);
		return new XmlTag<List<WeaponProjectile>>("projectiles", weaponProjectiles);
	}

	public static XmlTag<Integer> createRadius(int radius) {
		return new XmlTag<Integer>("radius", radius);
	}

	public static XmlTag<Integer> createSpin(int spin) {
		return new XmlTag<Integer>("spin", spin);
	}

	public static XmlTag<Integer> createShots(int shots) {
		return new XmlTag<Integer>("shots", shots);
	}

	public static XmlTag<Void> createFlavorType(String flavorType) {
		return new XmlTag<Void>("flavorType", new Attribute("flavorType", flavorType));
	}

	public static XmlTag<Integer> createStunChance(int stunChance) {
		return new XmlTag<Integer>("stunChance", stunChance);
	}

	public static XmlTag<Integer> createSpeed(int speed) {
		return new XmlTag<Integer>("speed", speed);
	}

	public static XmlTag<Integer> createPersDamage(int persDamage) {
		return new XmlTag<Integer>("persDamage", persDamage);
	}

	public static XmlTag<Integer> createLockdown(boolean lockdown) {
		return new XmlTag<Integer>("lockdown", lockdown ? 1 : 0);
	}

	public static XmlTag<Integer> createSysDamage(int sysDamage) {
		return new XmlTag<Integer>("sysDamage", sysDamage);
	}

	public static XmlTag<Integer> createHullBust(boolean hullBust) {
		return new XmlTag<Integer>("hullBust", hullBust ? 1 : 0);
	}

	public static XmlTag<Integer> createDroneTargetable(boolean droneTargetable) {
		return new XmlTag<Integer>("droneTargetable", droneTargetable ? 1 : 0);
	}

	public static XmlTag<Integer> createMissiles(int missiles) {
		return new XmlTag<Integer>("missiles", missiles);
	}

	public static XmlTag<Integer> createIon(int ion) {
		return new XmlTag<Integer>("ion", ion);
	}

	public static XmlTag<String> createExplosion(String explosionReference) {
		return new XmlTag<String>("explosion", explosionReference);
	}

	public static XmlTag<Integer> createLocked(boolean locked) {
		return new XmlTag<Integer>("locked", locked ? 1 : 0);
	}

	public static WeaponBoost createBoost(WeaponBoostType weaponBoostType, int amount, int count) {
		return new WeaponBoost(weaponBoostType, amount, count);
	}

	public static XmlTag<Integer> createChargeLevels(int chargeLevels) {
		return new XmlTag<Integer>("chargeLevels", chargeLevels);
	}

	public static XmlTag<List<WeaponSound>> createHitShipSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>("hitShipSounds", weaponSounds);
	}

	public static XmlTag<List<WeaponSound>> createHitShieldSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>("hitShieldSounds", weaponSounds);
	}

	public static XmlTag<List<WeaponSound>> createMissSounds(String... sounds) {
		List<WeaponSound> weaponSounds = Stream.of(sounds).map(sound -> new WeaponSound(sound)).toList();
		return new XmlTag<List<WeaponSound>>("missSounds", weaponSounds);
	}
}
