package net.zefinder.ftlmod.weapon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

/**
 * Defines a weapon with its attributes
 */
public class Weapon implements XmlObject {

	private static final Logger log = LoggerFactory.getLogger(Weapon.class);

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
			log.error("Weapon name null or empty, error!");
			throw new WeaponCreationException("Weapon name cannot be null or empty!");
		}

		this.name = name;
		reset();
	}

	public void setWeaponType(WeaponType weaponType) {
		if (weaponType == null) {
			log.warn("The weapon type cannot be null! Ignore setWeaponType...");
			return;
		}

		if (weaponType == WeaponType.BEAM) {
			if (!length.hasElement()) {
				setLength(1);
			}
			
		} else if (weaponType == WeaponType.BURST) {
			if (!projectiles.hasElement()) {
				setProjectiles();
			}

			if (!radius.hasElement()) {
				setRadius(1);
			}

			if (!spin.hasElement()) {
				setSpin(1);
			}
		}

		this.weaponType = WeaponPropertyFactory.createWeaponType(weaponType);
	}

	public void setTip(String tipReference) {
		if (tipReference == null || tipReference.isBlank()) {
			log.warn("The tip reference cannot be null nor empty! Ignore setTip...");
			return;
		}

		this.tipReference = WeaponPropertyFactory.createTip(tipReference);
	}

	public void setTitle(String titleReference) {
		if (titleReference == null || titleReference.isBlank()) {
			log.warn("The title reference cannot be null nor empty! Ignore setTitle...");
			return;
		}

		this.titleReference = WeaponPropertyFactory.createTitle(titleReference);
	}

	public void setShortTitle(String shortTitleReference) {
		if (shortTitleReference == null || shortTitleReference.isBlank()) {
			log.warn("The short title reference cannot be null nor empty! Ignore setShortTitle...");
			return;
		}

		this.shortTitleReference = WeaponPropertyFactory.createShortTitle(shortTitleReference);
	}

	public void setDescription(String descriptionReference) {
		if (descriptionReference == null || descriptionReference.isBlank()) {
			log.warn("The description reference cannot be null nor empty! Ignore setDescription...");
			return;
		}

		this.descriptionReference = WeaponPropertyFactory.createDescription(descriptionReference);
	}

	public void setToolTip(String tooltipReference) {
		if (tooltipReference == null || tooltipReference.isBlank()) {
			log.warn("The tooltip reference cannot be null nor empty! Ignore setToolTip...");
			return;
		}

		this.tooltipReference = WeaponPropertyFactory.createTooltip(tooltipReference);
	}

	public void setCooldown(int cooldown) {
		if (cooldown < 0) {
			log.warn("The cooldown cannot be negative! Set to 0...");
			cooldown = 0;
		}

		this.cooldown = WeaponPropertyFactory.createCooldown(cooldown);
	}

	public void setPower(int power) {
		this.power = WeaponPropertyFactory.createPower(power);
	}

	public void setCost(int cost) {
		if (cost < 0) {
			log.warn("The cost cannot be negative! Set to 0...");
			cost = 0;
		}

		this.cost = WeaponPropertyFactory.createCost(cost);
	}

	public void setRarity(int rarity) {
		if (rarity < 0) {
			log.warn("The rarity cannot be negative! Set to 0...");
			rarity = 0;
		} else if (rarity > 5) {
			log.warn("The rarity cannot be greater than 5! Set to 5...");
			rarity = 5;
		}

		this.rarity = WeaponPropertyFactory.createRarity(rarity);
	}

	public void setDamage(int damage) {
		this.damage = WeaponPropertyFactory.createDamage(damage);
	}

	public void setSp(int sp) {
		if (sp < 0 && weaponType.getElement().get() != WeaponType.BEAM) {
			log.warn("The shield piercing cannot be negative if the weapon is not a beam! Set to 0...");
			sp = 0;
		}

		this.shieldPiercing = WeaponPropertyFactory.createSp(sp);
	}

	public void setBp(int bp) { // What is this even????
		this.bp = WeaponPropertyFactory.createBp(bp);
	}

	public void setFireChance(int fireChance) {
		if (fireChance < 0) {
			log.warn("The fire chance cannot be negative! Set to 0...");
			fireChance = 0;
		} else if (fireChance > 10) {
			log.warn("The fire chance cannot be greater than 10! Set to 10...");
			fireChance = 10;
		}

		this.fireChance = WeaponPropertyFactory.createFireChance(fireChance);
	}

	public void setBreachChance(int breachChance) {
		if (breachChance < 0) {
			log.warn("The breach chance cannot be negative! Set to 0...");
			breachChance = 0;
		} else if (breachChance > 10) {
			log.warn("The breach chance cannot be greater than 10! Set to 10...");
			breachChance = 10;
		}

		this.breachChance = WeaponPropertyFactory.createBreachChance(breachChance);
	}

	public void setImage(String imageReference) {
		if (imageReference == null) {
			log.warn("The image reference cannot be null! Ignore setImage...");
			return;
		}

		this.imageReference = WeaponPropertyFactory.createImage(imageReference);
	}

	public void setIconImage(String iconImageReference) {
		if (iconImageReference == null) {
			log.warn("The icon image reference cannot be null! Ignore setIconImage...");
			return;
		}

		this.iconImageReference = WeaponPropertyFactory.createIconImage(iconImageReference);
	}

	public void setWeaponArt(String weaponArtReference) {
		if (weaponArtReference == null) {
			log.warn("The weapon art reference cannot be null! Ignore setWeaponArt...");
			return;
		}

		this.weaponArtReference = WeaponPropertyFactory.createWeaponArt(weaponArtReference);
	}

	public void setLaunchSounds(String... sounds) {
		if (sounds == null) {
			log.warn("The launch sounds cannot be null! Ignore setLaunchSounds...");
			return;
		}

		this.launchSounds = WeaponPropertyFactory.createLaunchSounds(sounds);
	}

	@SuppressWarnings("unchecked")
	public void setLength(int length) {
		if (length < 0) {
			this.length = XmlTag.EMPTY;
		} else {
			if (length < 1) {
				log.warn("The length must be geater than 0! Set to 1...");
				length = 1;
			}

			this.length = WeaponPropertyFactory.createLength(length);
		}
	}

	@SuppressWarnings("unchecked")
	public void setColor(Color color) {
		if (color == null) {
			this.color = XmlTag.EMPTY;
		} else {
			this.color = WeaponPropertyFactory.createColor(color);
		}
	}

	@SuppressWarnings("unchecked")
	public void setProjectiles(WeaponProjectile... projectiles) {
		if (projectiles == null) {
			this.projectiles = XmlTag.EMPTY;
		} else {
			this.projectiles = WeaponPropertyFactory.createProjectiles(projectiles);
		}
	}

	@SuppressWarnings("unchecked")
	public void setRadius(int radius) {
		if (radius < 0) {
			this.radius = XmlTag.EMPTY;
		} else {
			if (radius < 1) {
				log.warn("The radius must be geater than 0! Set to 1...");
				radius = 1;
			}

			this.radius = WeaponPropertyFactory.createRadius(radius);
		}
	}

	@SuppressWarnings("unchecked")
	public void setSpin(int spin) {
		if (spin < 0) {
			this.spin = XmlTag.EMPTY;
		} else {
			if (spin < 1) {
				log.warn("The spin must be geater than 0! Set to 1...");
				spin = 1;
			}

			this.spin = WeaponPropertyFactory.createSpin(spin);
		}
	}

	@SuppressWarnings("unchecked")
	public void setShots(int shots) {
		if (shots < 0) {
			this.shots = XmlTag.EMPTY;
		} else {
			if (shots < 1) {
				log.warn("The shots must be geater than 0! Set to 1...");
				shots = 1;
			}

			this.shots = WeaponPropertyFactory.createShots(shots);
		}
	}

	@SuppressWarnings("unchecked")
	public void setFlavorType(String flavorType) {
		if (flavorType == null) {
			this.flavorType = XmlTag.EMPTY;
		} else {
			this.flavorType = WeaponPropertyFactory.createFlavorType(flavorType);
		}
	}

	@SuppressWarnings("unchecked")
	public void setStunChance(int stunChance) {
		if (stunChance < 0) {
			this.stunChance = XmlTag.EMPTY;
		} else {
			if (stunChance > 10) {
				log.warn("The breach chance cannot be greater than 10! Set to 10...");
				stunChance = 10;
			}

			this.stunChance = WeaponPropertyFactory.createStunChance(stunChance);
		}
	}

	@SuppressWarnings("unchecked")
	public void setSpeed(int speed) {
		if (speed < 0) {
			this.speed = XmlTag.EMPTY;
		} else {
			if (speed < 1) {
				log.warn("The speed must be geater than 0! Set to 1...");
				speed = 1;
			}

			this.speed = WeaponPropertyFactory.createSpeed(speed);
		}
	}

	public void setPersDamage(int persDamage) {
		this.persDamage = WeaponPropertyFactory.createPersDamage(persDamage);
	}

	public void setLockdown(boolean lockdown) {
		this.lockdown = WeaponPropertyFactory.createLockdown(lockdown);
	}

	public void setSysDamage(int sysDamage) {
		this.systemDamage = WeaponPropertyFactory.createSysDamage(sysDamage);
	}

	public void setHullBust(boolean hullBust) {
		this.hullBust = WeaponPropertyFactory.createHullBust(hullBust);
	}

	public void setDroneTargetable(boolean droneTargetable) {
		this.droneTargetable = WeaponPropertyFactory.createDroneTargetable(droneTargetable);
	}

	@SuppressWarnings("unchecked")
	public void setMissiles(int missiles) {
		if (missiles < 0) {
			this.missiles = XmlTag.EMPTY;
		} else {
			this.missiles = WeaponPropertyFactory.createMissiles(missiles);
		}
	}

	@SuppressWarnings("unchecked")
	public void setIon(int ion) {
		if (ion < 0) {
			this.ion = XmlTag.EMPTY;
		} else {
			this.ion = WeaponPropertyFactory.createIon(ion);
		}
	}

	public void setExplosion(String explosionReference) {
		// Null makes an empty XmlTag
		this.explosionReference = WeaponPropertyFactory.createExplosion(explosionReference);
	}

	public void setLocked(boolean locked) {
		this.locked = WeaponPropertyFactory.createLocked(locked);
	}

	public void setBoost(WeaponBoostType weaponBoostType, int amount, int count) {
		if (weaponBoostType == null) {
			this.weaponBoost = WeaponBoost.EMPTY;
		} else {
			if (count < 0) {
				log.warn("The boost count cannot be negative! Set to 0...");
				count = 0;
			}

			this.weaponBoost = WeaponPropertyFactory.createBoost(weaponBoostType, amount, count);
		}
	}

	@SuppressWarnings("unchecked")
	public void setChargeLevels(int chargeLevels) {
		if (chargeLevels < 0) {
			this.chargeLevels = XmlTag.EMPTY;
		} else {
			this.chargeLevels = WeaponPropertyFactory.createChargeLevels(chargeLevels);
		}
	}

	@SuppressWarnings("unchecked")
	public void setHitShipSounds(String... sounds) {
		if (sounds == null) {
			this.hitShipSounds = XmlTag.EMPTY;
		} else {
			this.hitShipSounds = WeaponPropertyFactory.createHitShipSounds(sounds);
		}
	}

	@SuppressWarnings("unchecked")
	public void setHitShieldSounds(String... sounds) {
		if (sounds == null) {
			this.hitShieldSounds = XmlTag.EMPTY;
		} else {
			this.hitShieldSounds = WeaponPropertyFactory.createHitShieldSounds(sounds);
		}
	}

	@SuppressWarnings("unchecked")
	public void setMissSounds(String... sounds) {
		if (sounds == null) {
			this.missSounds = XmlTag.EMPTY;
		} else {
			this.missSounds = WeaponPropertyFactory.createMissSounds(sounds);
		}
	}

	/**
	 * Resets the weapon to an empty weapon, being the basic laser 1 without the
	 * optional properties.
	 */
	public void reset() {
		setWeaponType(WeaponType.LASER);
		setTip("tip_laser");
		setTitle("weapon_LASER_BURST_1_title");
		setShortTitle("weapon_LASER_BURST_1_short");
		setDescription("weapon_LASER_BURST_1_desc");
		setToolTip("weapon_LASER_BURST_1_tooltip");
		setCooldown(10);
		setPower(1);
		setCost(20);
		setRarity(0);
		setDamage(1);
		setSp(0);
		setBp(2);
		setFireChance(1);
		setBreachChance(0);
		setImage("laser_light1");
		setIconImage("laser");
		setWeaponArt("laser_burst_1");
		setLaunchSounds("lightLaser1", "lightLaser2", "lightLaser3");

		// Optional properties are set to empty or no impact
		setLength(-1);
		setColor(null);
		setProjectiles((WeaponProjectile[]) null);
		setRadius(-1);
		setSpin(-1);
		setShots(-1);
		setFlavorType(null);
		setStunChance(-1);
		setSpeed(-1);
		setPersDamage(0); // negative are possible
		setLockdown(false);
		setSysDamage(0); // negative are possible
		setHullBust(false);
		setDroneTargetable(false);
		setMissiles(-1);
		setIon(-1);
		setExplosion(null);
		setLocked(false);
		setBoost(null, 0, 0);
		setChargeLevels(-1);
		setHitShipSounds((String[]) null);
		setHitShieldSounds((String[]) null);
		setMissSounds((String[]) null);
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();

		// Mandatory properties
		tags.add(weaponType);
		tags.add(tipReference);
		tags.add(titleReference);
		tags.add(shortTitleReference);
		tags.add(descriptionReference);
		tags.add(tooltipReference);
		tags.add(cooldown);
		tags.add(power);
		tags.add(cost);
		tags.add(rarity);
		tags.add(damage);
		tags.add(shieldPiercing);
		tags.add(bp);
		tags.add(fireChance);
		tags.add(breachChance);
		tags.add(imageReference);
		tags.add(iconImageReference);
		tags.add(weaponArtReference);
		tags.add(launchSounds);

		// Beam specific (length is mandatory but automatically set when setting type to
		// BEAM)
		if (weaponType.getElement().get() == WeaponType.BEAM) {
			tags.add(length);
			if (color.hasElement()) {
				tags.add(color);
			}
		}

		// Burst specific (all are mandatory)
		else if (weaponType.getElement().get() == WeaponType.BURST) {
			tags.add(projectiles);
			tags.add(radius);
			tags.add(spin);
		}

		// Put the remaining
		if (shots.hasElement()) {
			tags.add(shots);
		}

		if (flavorType.hasElement()) {
			tags.add(flavorType);
		}

		if (stunChance.hasElement() && stunChance.getElement().get() != 0) {
			tags.add(stunChance);
		}

		if (speed.hasElement()) {
			tags.add(speed);
		}

		if (persDamage.hasElement() && persDamage.getElement().get() != 0) {
			tags.add(persDamage);
		}

		if (lockdown.hasElement() && lockdown.getElement().get() != 0) {
			tags.add(lockdown);
		}

		if (systemDamage.hasElement() && systemDamage.getElement().get() != 0) {
			tags.add(systemDamage);
		}

		if (hullBust.hasElement() && hullBust.getElement().get() != 0) {
			tags.add(hullBust);
		}

		if (droneTargetable.hasElement() && droneTargetable.getElement().get() != 0) {
			tags.add(droneTargetable);
		}

		if (missiles.hasElement() && missiles.getElement().get() != 0) {
			tags.add(missiles);
		}

		if (ion.hasElement() && ion.getElement().get() != 0) {
			tags.add(ion);
		}

		if (explosionReference.hasElement()) {
			tags.add(explosionReference);
		}

		if (locked.hasElement() && locked.getElement().get() != 0) {
			tags.add(locked);
		}

		if (!weaponBoost.equals(WeaponBoost.EMPTY)) {
			tags.add(weaponBoost.toXmlTag());
		}

		if (chargeLevels.hasElement()) {
			tags.add(chargeLevels);
		}

		if (hitShipSounds.hasElement()) {
			tags.add(hitShipSounds);
		}

		if (hitShieldSounds.hasElement()) {
			tags.add(hitShieldSounds);
		}

		if (missSounds.hasElement()) {
			tags.add(missSounds);
		}

		return new XmlTag<List<XmlTag<?>>>("weaponBlueprint", tags, new Attribute("name", name));
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Weapon other && other.name.equals(this.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public static void main(String[] args) throws WeaponCreationException {
		Weapon w = new Weapon("test");
		w.setLength(50);
		w.setWeaponType(WeaponType.BEAM);
		System.out.println(w.toXmlTag().toString());
	}

}
