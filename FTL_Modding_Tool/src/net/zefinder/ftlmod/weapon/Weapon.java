package net.zefinder.ftlmod.weapon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

/**
 * Defines a weapon with its attributes
 */
public class Weapon implements XmlObject {

	private final String name;

	// On all weapons
	/**
	 * Defines the weapon type. Default: LASER
	 */
	private XmlTag<WeaponType> weaponType;
	private XmlTag<Void> tipReference;
	private XmlTag<Void> titleReference;
	private XmlTag<Void> shortTitleReference;
	private XmlTag<Void> descriptionReference;
	private XmlTag<Void> tooltipReference;

	private XmlTag<Integer> cooldown;
	private XmlTag<Integer> power;
	private XmlTag<Integer> cost;
	private XmlTag<Integer> rarity; // 0 - 5
	private XmlTag<Integer> damage;
	private XmlTag<Integer> shieldPiercing;
	private XmlTag<Integer> bp; // Unused

	private XmlTag<Integer> fireChance; // Only 0 to 10, each point = 10%
	private XmlTag<Integer> breachChance; // Only 0 to 10, each point = 10%

	private XmlTag<String> imageReference;
	private XmlTag<String> iconImageReference;
	private XmlTag<String> weaponArtReference;

	private XmlTag<List<WeaponSound>> launchSounds;

	// On beams only
	private XmlTag<Integer> length; // Mandatory
	private XmlTag<WeaponBeamColor> color; // Optional

	// On burst only (flak-like weapons), mandatory?
	private XmlTag<List<WeaponProjectile>> projectiles;
	private XmlTag<Integer> radius;
	private XmlTag<Integer> spin;

	// If there is a value, can add
	private XmlTag<Integer> shots;
	private XmlTag<Void> flavorType;
	private XmlTag<Integer> stunChance; // Only 0 to 10, each point = 10%
	private XmlTag<Integer> speed;
	private XmlTag<Integer> persDamage; // 1 point = 15 hp
	private XmlTag<Integer> lockdown; // 0 or 1
	private XmlTag<Integer> systemDamage; // Usually on bomb weapons
	private XmlTag<Integer> hullBust; // 0 or 1
	private XmlTag<Integer> droneTargetable; // 0 or 1
	private XmlTag<Integer> missiles;
	private XmlTag<Integer> ion;
	private XmlTag<String> explosionReference;
	private XmlTag<Integer> locked; // 0 or 1

	// Chain weapons
	private WeaponBoost weaponBoost;

	// Charged weapons
	private XmlTag<Integer> chargeLevels;

	// Optional sounds
	private XmlTag<List<WeaponSound>> hitShipSounds;
	private XmlTag<List<WeaponSound>> hitShieldSounds;
	private XmlTag<List<WeaponSound>> missSounds;

	public Weapon(final String name) throws WeaponCreationException {
		if (name == null || name.isBlank()) {
			// Should never go there if created using the GUI
			throw new WeaponCreationException("Weapon name cannot be null or empty!");
		}

		this.name = name;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = WeaponPropertyFactory.createWeaponType(weaponType);
	}

	public void setTip(String tipReference) {
		this.tipReference = WeaponPropertyFactory.createTip(tipReference);
	}

	public void setTitle(String titleReference) {
		this.titleReference = WeaponPropertyFactory.createTitle(titleReference);
	}

	public void setShortTitle(String shortTitleReference) {
		this.shortTitleReference = WeaponPropertyFactory.createShortTitle(shortTitleReference);
	}

	public void setDescription(String descriptionReference) {
		this.descriptionReference = WeaponPropertyFactory.createDescription(descriptionReference);
	}

	public void setToolTip(String tooltipReference) {
		this.tooltipReference = WeaponPropertyFactory.createDescription(tooltipReference);
	}

	public void setCooldown(int cooldown) {
		this.cooldown = WeaponPropertyFactory.createCooldown(cooldown);
	}

	public void setPower(int power) {
		this.power = WeaponPropertyFactory.createPower(power);
	}

	public void setCost(int cost) {
		this.cost = WeaponPropertyFactory.createCost(cost);
	}

	public void setRarity(int rarity) {
		this.rarity = WeaponPropertyFactory.createRarity(rarity);
	}

	public void setDamage(int damage) {
		this.damage = WeaponPropertyFactory.createDamage(damage);
	}

	public void setSp(int sp) {
		this.shieldPiercing = WeaponPropertyFactory.createSp(sp);
	}

	public void setBp(int bp) {
		this.bp = WeaponPropertyFactory.createBp(bp);
	}

	public void setFireChance(int fireChance) {
		this.fireChance = WeaponPropertyFactory.createFireChance(fireChance);
	}

	public void setBreachChance(int breachChance) {
		this.breachChance = WeaponPropertyFactory.createBreachChance(breachChance);
	}

	public void setImage(String imageReference) {
		this.imageReference = WeaponPropertyFactory.createImage(imageReference);
	}

	public void setIconImage(String iconImageReference) {
		this.iconImageReference = WeaponPropertyFactory.createIconImage(iconImageReference);
	}

	public void setWeaponArt(String weaponArtReference) {
		this.weaponArtReference = WeaponPropertyFactory.createWeaponArt(weaponArtReference);
	}

	public void setLaunchSounds(String... sounds) {
		this.launchSounds = WeaponPropertyFactory.createLaunchSounds(sounds);
	}

	public void setLength(int length) {
		this.length = WeaponPropertyFactory.createLength(length);
	}

	public void setColor(Color color) {
		this.color = WeaponPropertyFactory.createColor(color);
	}

	public void setProjectiles(WeaponProjectile... projectiles) {
		this.projectiles = WeaponPropertyFactory.createProjectiles(projectiles);
	}

	public void setRadius(int radius) {
		this.radius = WeaponPropertyFactory.createRadius(radius);
	}

	public void setSpin(int spin) {
		this.spin = WeaponPropertyFactory.createSpin(spin);
	}

	public void setShots(int shots) {
		this.shots = WeaponPropertyFactory.createShots(shots);
	}

	public void setFlavorType(String flavorType) {
		this.flavorType = WeaponPropertyFactory.createFlavorType(flavorType);
	}

	public void setStunChance(int stunChance) {
		this.stunChance = WeaponPropertyFactory.createStunChance(stunChance);
	}

	public void setSpeed(int speed) {
		this.speed = WeaponPropertyFactory.createSpeed(speed);
	}

	public void setPersDamage(int persDamage) {
		this.persDamage = WeaponPropertyFactory.createPersDamage(persDamage);
	}

	public void setLockdown(int lockdown) {
		this.lockdown = WeaponPropertyFactory.createLockdown(lockdown);
	}

	public void setSysDamage(int sysDamage) {
		this.systemDamage = WeaponPropertyFactory.createSysDamage(sysDamage);
	}

	public void setHullBust(int hullBust) {
		this.hullBust = WeaponPropertyFactory.createHullBust(hullBust);
	}

	public void setDroneTargetable(int droneTargetable) {
		this.droneTargetable = WeaponPropertyFactory.createDroneTargetable(droneTargetable);
	}

	public void setMissiles(int missiles) {
		this.missiles = WeaponPropertyFactory.createMissiles(missiles);
	}

	public void setIon(int ion) {
		this.ion = WeaponPropertyFactory.createIon(ion);
	}

	public void setExplosion(String explosionReference) {
		this.explosionReference = WeaponPropertyFactory.createExplosion(explosionReference);
	}

	public void setLocked(int locked) {
		this.locked = WeaponPropertyFactory.createIon(locked);
	}

	public void setBoost(WeaponBoostType weaponBoostType, int amount, int count) {
		this.weaponBoost = WeaponPropertyFactory.createBoost(weaponBoostType, amount, count);
	}

	public void setChargeLevels(int chargeLevels) {
		this.chargeLevels = WeaponPropertyFactory.createChargeLevels(chargeLevels);
	}

	public void setHitShipSounds(String... sounds) {
		this.hitShipSounds = WeaponPropertyFactory.createHitShipSounds(sounds);
	}

	public void setHitShieldSounds(String... sounds) {
		this.hitShieldSounds = WeaponPropertyFactory.createHitShieldSounds(sounds);
	}

	public void setMissSounds(String... sounds) {
		this.missSounds = WeaponPropertyFactory.createMissSounds(sounds);
	}

	/**
	 * Resets the weapon to an empty weapon, being the basic laser 1.
	 */
	public void reset() {
		
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();

		return null;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Weapon other && other.name.equals(this.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
