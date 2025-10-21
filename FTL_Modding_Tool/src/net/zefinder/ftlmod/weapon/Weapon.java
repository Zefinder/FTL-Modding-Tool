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

	public static final String WEAPON_BLUEPRINT_TAG_NAME = "weaponBlueprint";
	public static final String WEAPON_TYPE_TAG_NAME = "type";
	public static final String TIP_TAG_NAME = "tip";
	public static final String TITLE_TAG_NAME = "title";
	public static final String SHORT_TAG_NAME = "short";
	public static final String DESCRIPTION_TAG_NAME = "desc";
	public static final String TOOLTIP_TAG_NAME = "tooltip";
	public static final String COOLDOWN_TAG_NAME = "cooldown";
	public static final String POWER_TAG_NAME = "power";
	public static final String COST_TAG_NAME = "cost";
	public static final String RARITY_TAG_NAME = "rarity";
	public static final String DAMAGE_TAG_NAME = "damage";
	public static final String SHIELD_PIERCING_TAG_NAME = "sp";
	public static final String BP_TAG_NAME = "bp";
	public static final String FIRE_CHANCE_TAG_NAME = "fireChance";
	public static final String BREACH_CHANCE_TAG_NAME = "breachChance";
	public static final String IMAGE_TAG_NAME = "image";
	public static final String ICON_IMAGE_TAG_NAME = "iconImage";
	public static final String WEAPON_ART_TAG_NAME = "weaponArt";
	public static final String LAUNCH_SOUNDS_TAG_NAME = "launchSounds";
	public static final String SOUND_TAG_NAME = "sound";
	public static final String LENGTH_TAG_NAME = "length";
	public static final String COLOR_TAG_NAME = "color";
	public static final String R_TAG_NAME = "r";
	public static final String G_TAG_NAME = "g";
	public static final String B_TAG_NAME = "b";
	public static final String PROJECTILES_TAG_NAME = "projectiles";
	public static final String PROJECTILE_TAG_NAME = "projectile";
	public static final String RADIUS_TAG_NAME = "radius";
	public static final String SPIN_TAG_NAME = "spin";
	public static final String SHOTS_TAG_NAME = "shots";
	public static final String FLAVOR_TYPE_TAG_NAME = "flavorType";
	public static final String STUN_CHANCE_TAG_NAME = "stunChance";
	public static final String STUN_TAG_NAME = "stun";
	public static final String SPEED_TAG_NAME = "speed";
	public static final String PERS_DAMAGE_TAG_NAME = "persDamage";
	public static final String LOCKDOWN_TAG_NAME = "lockdown";
	public static final String SYSTEM_DAMAGE_TAG_NAME = "sysDamage";
	public static final String HULL_BUST_TAG_NAME = "hullBust";
	public static final String DRONE_TARGETABLE_TAG_NAME = "drone_targetable";
	public static final String MISSILES_TAG_NAME = "missiles";
	public static final String ION_TAG_NAME = "ion";
	public static final String EXPLOSION_TAG_NAME = "explosion";
	public static final String LOCKED_TAG_NAME = "locked";
	public static final String WEAPON_BOOST_TAG_NAME = "boost";
	public static final String BOOST_TYPE_TAG_NAME = "type";
	public static final String BOOST_AMOUNT_TAG_NAME = "amount";
	public static final String BOOST_COUNT_TAG_NAME = "count";
	public static final String CHARGE_LEVELS_TAG_NAME = "chargeLevels";
	public static final String HIT_SHIP_SOUNDS_TAG_NAME = "hitShipSounds";
	public static final String HIT_SHIELD_SOUNDS_TAG_NAME = "hitShieldSounds";
	public static final String MISS_SOUNDS_TAG_NAME = "missSounds";

	public static final String NAME_ATTRIBUTE = "name";
	public static final String ID_ATTRIBUTE = "id";
	public static final String COUNT_ATTRIBUTE = "count";
	public static final String FAKE_ATTRIBUTE = "fake";
	public static final String NOLOC_ATTRIBUTE = "NOLOC";

	public static final String NOLOC_ATTRIBUTE_FALSE = "0";
	public static final String NOLOC_ATTRIBUTE_TRUE = "1";

	private static final String TITLE_REFERENCE_DEFAULT_FORMAT = "weapon_%s_title";
	private static final String SHORT_REFERENCE_DEFAULT_FORMAT = "weapon_%s_short";
	private static final String DESCRIPTION_REFERENCE_DEFAULT_FORMAT = "weapon_%s_desc";
	private static final String TOOLTIP_REFERENCE_DEFAULT_FORMAT = "weapon_%s_tooltip";

	private final String name;
	private boolean noloc;
	
	// True if they are a reference
	private boolean titleReference;
	private boolean shortTitleReference;
	private boolean descriptionReference;
	private boolean tooltipReference;
	private boolean flavorTypeReference;

	// On all weapons
	/**
	 * Defines the weapon type. Default: LASER
	 */
	private XmlTag<WeaponType> weaponType;
	private XmlTag<Void> tipReference;
	private XmlTag<?> title;
	private XmlTag<?> shortTitle;
	private XmlTag<?> description;
	private XmlTag<?> tooltip;

	private XmlTag<Double> cooldown;
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
	private XmlTag<?> flavorType;
	private XmlTag<Integer> stunChance; // Only 0 to 10, each point = 10%
	private XmlTag<Integer> stun;
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
	
	public Weapon(final String name, boolean noloc) throws WeaponCreationException {
		if (name == null || name.isBlank()) {
			// Should never go there if created using the GUI
			log.error("Weapon name null or empty, error!");
			throw new WeaponCreationException("Weapon name cannot be null or empty!");
		}

		this.name = name;
		reset();
	}

	public Weapon(final String name) throws WeaponCreationException {
		this(name, false);
	}

	public void setNoloc(boolean noloc) {
		this.noloc = noloc;
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

	public void setTitle(String title, boolean isReference) {
		if (title == null || title.isBlank()) {
			log.warn("The title cannot be null nor empty! Ignore setTitle...");
			return;
		}

		this.title = WeaponPropertyFactory.createTitle(title, isReference);
	}

	public void setShortTitle(String shortTitle, boolean isReference) {
		if (shortTitle == null || shortTitle.isBlank()) {
			log.warn("The short title cannot be null nor empty! Ignore setShortTitle...");
			return;
		}

		this.shortTitle = WeaponPropertyFactory.createShortTitle(shortTitle, isReference);
	}

	public void setDescription(String description, boolean isReference) {
		if (description == null || description.isBlank()) {
			log.warn("The description cannot be null nor empty! Ignore setDescription...");
			return;
		}

		this.description = WeaponPropertyFactory.createDescription(description, isReference);
	}

	public void setTooltip(String tooltip, boolean isReference) {
		if (tooltip == null || tooltip.isBlank()) {
			log.warn("The tooltip cannot be null nor empty! Ignore setToolTip...");
			return;
		}

		this.tooltip = WeaponPropertyFactory.createTooltip(tooltip, isReference);
	}

	public void setCooldown(double cooldown) {
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

	public void setLength(int length) {
		if (length < 0) {
			this.length = XmlTag.empty();
		} else {
			if (length < 1) {
				log.warn("The length must be geater than 0! Set to 1...");
				length = 1;
			}

			this.length = WeaponPropertyFactory.createLength(length);
		}
	}

	public void setColor(WeaponBeamColor color) {
		if (color == null) {
			this.color = XmlTag.empty();
		} else {
			this.color = WeaponPropertyFactory.createColor(color);
		}
	}

	public void setColor(Color color) {
		if (color == null) {
			this.color = XmlTag.empty();
		} else {
			this.color = WeaponPropertyFactory.createColor(color);
		}
	}

	public void setProjectiles(WeaponProjectile... projectiles) {
		if (projectiles == null) {
			this.projectiles = XmlTag.empty();
		} else {
			this.projectiles = WeaponPropertyFactory.createProjectiles(projectiles);
		}
	}

	public void setRadius(int radius) {
		if (radius < 0) {
			this.radius = XmlTag.empty();
		} else {
			if (radius < 1) {
				log.warn("The radius must be geater than 0! Set to 1...");
				radius = 1;
			}

			this.radius = WeaponPropertyFactory.createRadius(radius);
		}
	}

	public void setSpin(int spin) {
		if (spin < 0) {
			this.spin = XmlTag.empty();
		} else {
			if (spin < 1) {
				log.warn("The spin must be geater than 0! Set to 1...");
				spin = 1;
			}

			this.spin = WeaponPropertyFactory.createSpin(spin);
		}
	}

	public void setShots(int shots) {
		if (shots < 0) {
			this.shots = XmlTag.empty();
		} else {
			if (shots < 1) {
				log.warn("The shots must be geater than 0! Set to 1...");
				shots = 1;
			}

			this.shots = WeaponPropertyFactory.createShots(shots);
		}
	}

	public void setFlavorType(String flavorType, boolean isReference) {
		if (flavorType == null) {
			this.flavorType = XmlTag.empty();
		} else {
			this.flavorType = WeaponPropertyFactory.createFlavorType(flavorType, isReference);
		}
	}

	public void setStunChance(int stunChance) {
		if (stunChance < 0) {
			this.stunChance = XmlTag.empty();
		} else {
			if (stunChance > 10) {
				log.warn("The breach chance cannot be greater than 10! Set to 10...");
				stunChance = 10;
			}

			this.stunChance = WeaponPropertyFactory.createStunChance(stunChance);
		}
	}

	public void setStun(int stun) {
		if (stun < 0) {
			this.stun = XmlTag.empty();
		} else {
			this.stun = WeaponPropertyFactory.createStun(stun);
		}
	}

	public void setSpeed(int speed) {
		if (speed < 0) {
			this.speed = XmlTag.empty();
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

	public void setMissiles(int missiles) {
		if (missiles < 0) {
			this.missiles = XmlTag.empty();
		} else {
			this.missiles = WeaponPropertyFactory.createMissiles(missiles);
		}
	}

	public void setIon(int ion) {
		if (ion < 0) {
			this.ion = XmlTag.empty();
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

	public void setChargeLevels(int chargeLevels) {
		if (chargeLevels < 0) {
			this.chargeLevels = XmlTag.empty();
		} else {
			this.chargeLevels = WeaponPropertyFactory.createChargeLevels(chargeLevels);
		}
	}

	public void setHitShipSounds(String... sounds) {
		if (sounds == null) {
			this.hitShipSounds = XmlTag.empty();
		} else {
			this.hitShipSounds = WeaponPropertyFactory.createHitShipSounds(sounds);
		}
	}

	public void setHitShieldSounds(String... sounds) {
		if (sounds == null) {
			this.hitShieldSounds = XmlTag.empty();
		} else {
			this.hitShieldSounds = WeaponPropertyFactory.createHitShieldSounds(sounds);
		}
	}

	public void setMissSounds(String... sounds) {
		if (sounds == null) {
			this.missSounds = XmlTag.empty();
		} else {
			this.missSounds = WeaponPropertyFactory.createMissSounds(sounds);
		}
	}

	/**
	 * Resets the weapon to an empty weapon, being the basic laser 1 without the
	 * optional properties.
	 */
	public void reset() {
		noloc = false;
		titleReference = true;
		shortTitleReference = true;
		descriptionReference = true;
		tooltipReference = true;
		flavorTypeReference = false;
		
		setWeaponType(WeaponType.LASER);
		setTip("tip_laser");
		setTitle(TITLE_REFERENCE_DEFAULT_FORMAT.formatted(name), titleReference);
		setShortTitle(SHORT_REFERENCE_DEFAULT_FORMAT.formatted(name), shortTitleReference);
		setDescription(DESCRIPTION_REFERENCE_DEFAULT_FORMAT.formatted(name), descriptionReference);
		setTooltip(TOOLTIP_REFERENCE_DEFAULT_FORMAT.formatted(name), tooltipReference);
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
		setColor((Color) null);
		setProjectiles((WeaponProjectile[]) null);
		setRadius(-1);
		setSpin(-1);
		setShots(-1);
		setFlavorType(null, flavorTypeReference);
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
		tags.add(title);
		tags.add(shortTitle);
		tags.add(description);
		tags.add(tooltip);
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

		if (stun.hasElement() && stun.getElement().get() != 0) {
			tags.add(stun);
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

		final List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(NAME_ATTRIBUTE, name));
		if (noloc) {
			attributes.add(new Attribute(NOLOC_ATTRIBUTE, NOLOC_ATTRIBUTE_TRUE));
		}

		return new XmlTag<List<XmlTag<?>>>(WEAPON_BLUEPRINT_TAG_NAME, tags,
				attributes.toArray(Attribute[]::new));
	}

	public String name() {
		return name;
	}

	public void copyFrom(Weapon other) {
		// XmlTags are immutable types and their values (String, Integer and immutable
		// List) are immutable too.
		// WeaponBoost is immutable since it's a record of enum and int
		this.noloc = other.noloc;
		this.weaponType = other.weaponType;
		this.tipReference = other.tipReference;
		this.title = other.title;
		this.shortTitle = other.shortTitle;
		this.description = other.description;
		this.tooltip = other.tooltip;
		this.cooldown = other.cooldown;
		this.power = other.power;
		this.cost = other.cost;
		this.rarity = other.rarity;
		this.damage = other.damage;
		this.shieldPiercing = other.shieldPiercing;
		this.bp = other.bp;
		this.fireChance = other.fireChance;
		this.breachChance = other.breachChance;
		this.imageReference = other.imageReference;
		this.iconImageReference = other.iconImageReference;
		this.weaponArtReference = other.weaponArtReference;
		this.launchSounds = other.launchSounds;
		this.length = other.length;
		this.color = other.color;
		this.projectiles = other.projectiles;
		this.radius = other.radius;
		this.spin = other.spin;
		this.shots = other.shots;
		this.flavorType = other.flavorType;
		this.stunChance = other.stunChance;
		this.stun = other.stun;
		this.speed = other.speed;
		this.persDamage = other.persDamage;
		this.lockdown = other.lockdown;
		this.systemDamage = other.systemDamage;
		this.hullBust = other.hullBust;
		this.droneTargetable = other.droneTargetable;
		this.missiles = other.missiles;
		this.ion = other.ion;
		this.explosionReference = other.explosionReference;
		this.locked = other.locked;
		this.weaponBoost = other.weaponBoost;
		this.chargeLevels = other.chargeLevels;
		this.hitShipSounds = other.hitShipSounds;
		this.hitShieldSounds = other.hitShieldSounds;
		this.missSounds = other.missSounds;
	}

	@Override
	public Weapon clone() {
		Weapon clone = null;
		try {
			clone = new Weapon(name);
			clone.copyFrom(this);
		} catch (WeaponCreationException e) {
			// Cannot go here since name is final and cannot be null
			e.printStackTrace();
		}

		return clone;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Weapon other && other.name.equals(this.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

//	public static void main(String[] args) throws WeaponCreationException {
//		Weapon w = new Weapon("test", true);
//		w.setLength(50);
//		w.setStun(0);
//		w.setWeaponType(WeaponType.BEAM);
//		System.out.println(w.toXmlTag());
//	}

}
