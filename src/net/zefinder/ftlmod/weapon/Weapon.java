package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.NamedObject;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

/**
 * Defines a weapon with its attributes
 */
public record Weapon(String name, boolean noloc, boolean titleReference, boolean shortTitleReference,
		boolean descriptionReference, boolean tooltipReference, boolean flavorTypeReference, WeaponType weaponType,
		String tipReference, String title, String shortTitle, String description, String tooltip, double cooldown,
		int power, int cost, int rarity, int damage, int shieldPiercing, int bp, int fireChance, int breachChance,
		String imageReference, String iconImageReference, String weaponArtReference, List<WeaponSound> launchSounds,
		int length, WeaponBeamColor color, List<WeaponProjectile> projectiles, int radius, int spin, int shots,
		String flavorType, int stunChance, int stun, int speed, int persDamage, boolean lockdown, int systemDamage,
		boolean hullBust, boolean droneTargetable, int missiles, int ion, String explosionReference, boolean locked,
		WeaponBoost weaponBoost, int chargeLevels, List<WeaponSound> hitShipSounds, List<WeaponSound> hitShieldSounds,
		List<WeaponSound> missSounds) implements NamedObject, XmlObject {

	private static final Logger log = LoggerFactory.getLogger(Weapon.class);

	/**
	 * Default weapon is only the mandatory parts of the laser burst 1 shot
	 */
	public static final Weapon DEFAULT_WEAPON = new WeaponBuilder()
			.setName("LASER_BURST_1")
			.setTitleReference(true)
			.setWeaponType(WeaponType.LASER)
			.setTitle("weapon_LASER_BURST_1_title")
			.setPower(1)
			.setRarity(0)
			.setDamage(1)
			.setBp(2)
			.setFireChance(1)
			.setBreachChance(0)
			.setWeaponArtReference("laser_burst_1")
			.setLaunchSounds(List.of(new WeaponSound("lightLaser1"),
					new WeaponSound("lightLaser2"), new WeaponSound("lightLaser3")))
			.build();
	
	public Weapon(String name, boolean noloc, boolean titleReference, boolean shortTitleReference,
			boolean descriptionReference, boolean tooltipReference, boolean flavorTypeReference, WeaponType weaponType,
			String tipReference, String title, String shortTitle, String description, String tooltip, double cooldown,
			int power, int cost, int rarity, int damage, int shieldPiercing, int bp, int fireChance, int breachChance,
			String imageReference, String iconImageReference, String weaponArtReference, List<WeaponSound> launchSounds,
			int length, WeaponBeamColor color, List<WeaponProjectile> projectiles, int radius, int spin, int shots,
			String flavorType, int stunChance, int stun, int speed, int persDamage, boolean lockdown, int systemDamage,
			boolean hullBust, boolean droneTargetable, int missiles, int ion, String explosionReference, boolean locked,
			WeaponBoost weaponBoost, int chargeLevels, List<WeaponSound> hitShipSounds, List<WeaponSound> hitShieldSounds,
			List<WeaponSound> missSounds) {
		this.name = name;
		this.noloc = noloc;
		
		this.titleReference = titleReference;
		this.shortTitleReference = shortTitleReference;
		this.descriptionReference = descriptionReference;
		this.tooltipReference = tooltipReference;
		this.flavorTypeReference = flavorTypeReference;

		this.weaponType = checkWeaponType(weaponType);
		this.tipReference = checkTip(tipReference);
		this.title = checkTitle(title);
		this.shortTitle = checkShortTitle(shortTitle);
		this.description = checkDescription(description);
		this.tooltip = checkTooltip(tooltip);
		this.cooldown = checkCooldown(cooldown);
		this.power = power;
		this.cost = checkCost(cost);
		this.rarity = checkRarity(rarity);
		this.damage = damage;
		this.shieldPiercing = checkSp(weaponType, shieldPiercing);
		this.bp = bp;
		this.fireChance = checkFireChance(fireChance);
		this.breachChance = checkBreachChance(breachChance);
		this.imageReference = checkImage(imageReference);
		this.iconImageReference = checkIconImage(iconImageReference);
		this.weaponArtReference = checkWeaponArt(weaponArtReference);
		this.launchSounds = checkLaunchSounds(launchSounds);
		this.length = checkLength(length);
		this.color = color;
		this.projectiles = checkProjectiles(projectiles);
		this.radius = checkRadius(radius);
		this.spin = checkSpin(spin);
		this.shots = checkShots(shots);
		this.flavorType = checkFlavorType(flavorType);
		this.stunChance = checkStunChance(stunChance);
		this.stun = checkStun(stun);
		this.speed = checkSpeed(speed);
		this.persDamage = persDamage;
		this.lockdown = lockdown;
		this.systemDamage = systemDamage;
		this.hullBust = hullBust;
		this.droneTargetable = droneTargetable;
		this.missiles = checkMissiles(missiles);
		this.ion = checkIon(ion);
		this.explosionReference = checkExplosion(explosionReference);
		this.locked = locked;
		this.weaponBoost = checkBoost(weaponBoost);
		this.chargeLevels = checkChargeLevels(chargeLevels);
		this.hitShipSounds = checkHitShipSounds(hitShipSounds);
		this.hitShieldSounds = checkHitShieldSounds(hitShieldSounds);
		this.missSounds = checkMissSounds(missSounds);
		
		if (name == null || name.isBlank()) {
			// Should never go there if created using the GUI
			log.error("Weapon name null or empty, error!");
			throw new WeaponCreationException("Weapon name cannot be null or empty!");
		}

		// Check if beam that length has been set
		if (weaponType == WeaponType.BEAM && length == 0) {
			log.error("Weapon is a beam with no length!");
			throw new WeaponCreationException("Beam length must be set!");
		}

		// Check if burst that projectiles, radius and spin has been set
		if (weaponType == WeaponType.BURST) {
			if (projectiles.isEmpty()) {
				log.error("Weapon is a burst with no projectile!");
				throw new WeaponCreationException("Burst projectiles must be set!");
			}

			if (radius == 0) {
				log.error("Weapon is a burst with no radius!");
				throw new WeaponCreationException("Burst radius must be set!");
			}

			if (spin == 0) {
				log.error("Weapon is a burst with no projectile spin!");
				throw new WeaponCreationException("Burst spin must be set!");
			}
		}
	}
	
	Weapon(WeaponBuilder builder) throws WeaponCreationException {
		this(builder.getName(), 
				builder.isNoloc(),
				builder.isTitleReference(),
				builder.isShortTitleReference(),
				builder.isDescriptionReference(),
				builder.isTooltipReference(),
				builder.isFlavorTypeReference(),
				builder.getWeaponType(),
				builder.getTipReference(),
				builder.getTitle(),
				builder.getShortTitle(),
				builder.getDescription(),
				builder.getTooltip(),
				builder.getCooldown(),
				builder.getPower(),
				builder.getCost(),
				builder.getRarity(),
				builder.getDamage(),
				builder.getShieldPiercing(),
				builder.getBp(),
				builder.getFireChance(),
				builder.getBreachChance(),
				builder.getImageReference(),
				builder.getIconImageReference(),
				builder.getWeaponArtReference(),
				builder.getLaunchSounds(),
				builder.getLength(),
				builder.getColor(),
				builder.getProjectiles(),
				builder.getRadius(),
				builder.getSpin(),
				builder.getShots(),
				builder.getFlavorType(),
				builder.getStunChance(),
				builder.getStun(),
				builder.getSpeed(),
				builder.getPersDamage(),
				builder.isLockdown(),
				builder.getSystemDamage(),
				builder.isHullBust(),
				builder.isDroneTargetable(),
				builder.getMissiles(),
				builder.getIon(),
				builder.getExplosionReference(),
				builder.isLocked(),
				builder.getWeaponBoost(),
				builder.getChargeLevels(),
				builder.getHitShipSounds(),
				builder.getHitShieldSounds(),
				builder.getMissSounds());
	}

	private static final WeaponType checkWeaponType(WeaponType weaponType) {
		if (weaponType == null) {
			log.error("The weapon type cannot be null!");
			throw new WeaponCreationException("The weapon type cannot be null!");
		}
		return weaponType;
	}

	private static final String checkTip(String tip) {
		if (tip == null || tip.isBlank()) {
			return "";
		}

		return tip;
	}

	private static final String checkTitle(String title) {
		if (title == null || title.isBlank()) {
			log.error("The title cannot be null nor empty!");
			throw new WeaponCreationException("The title cannot be null nor empty!");
		}

		return title;
	}

	private static final String checkShortTitle(String shortTitle) {
		if (shortTitle == null || shortTitle.isBlank()) {
			return "";
		}

		return shortTitle;
	}

	private static final String checkDescription(String description) {
		if (description == null || description.isBlank()) {
			return "";
		}

		return description;
	}

	private static final String checkTooltip(String tooltip) {
		if (tooltip == null || tooltip.isBlank()) {
			return "";
		}

		return tooltip;
	}

	private static final double checkCooldown(double cooldown) {
		if (cooldown < 0) {
			log.warn("Cooldown is not set! Is it a mistake?");
			cooldown = -1;
		}

		return cooldown;
	}

	private static final int checkCost(int cost) {
		if (cost < 0) {
			log.warn("Cost is not set! Is it a mistake?");
			cost = -1;
		}

		return cost;
	}

	private static final int checkRarity(int rarity) {
		if (rarity < 0) {
			log.warn("The rarity cannot be negative! Set to 0...");
			rarity = 0;
		} else if (rarity > 5) {
			log.warn("The rarity cannot be greater than 5! Set to 5...");
			rarity = 5;
		}

		return rarity;
	}

	private static final int checkSp(WeaponType weaponType, int sp) {
		if (sp < 0 && weaponType != WeaponType.BEAM) {
			sp = 0;
		}

		return sp;
	}

	private static final int checkFireChance(int fireChance) {
		if (fireChance < 0) {
			log.warn("The fire chance cannot be negative! Set to 0...");
			fireChance = 0;
		} else if (fireChance > 10) {
			log.warn("The fire chance cannot be greater than 10! Set to 10...");
			fireChance = 10;
		}

		return fireChance;
	}

	private static final int checkBreachChance(int breachChance) {
		if (breachChance < 0) {
			log.warn("The breach chance cannot be negative! Set to 0...");
			breachChance = 0;
		} else if (breachChance > 10) {
			log.warn("The breach chance cannot be greater than 10! Set to 10...");
			breachChance = 10;
		}

		return breachChance;
	}

	private static final String checkImage(String imageReference) {
		if (imageReference == null) {
			log.warn("Image reference not set! Is it a mistake?");
			return "";
		}

		return imageReference;
	}

	private static final String checkIconImage(String iconImageReference) {
		if (iconImageReference == null) {
			return "";
		}

		return iconImageReference;
	}

	private static final String checkWeaponArt(String weaponArtReference) {
		if (weaponArtReference == null) {
			log.error("The weapon art reference cannot be null!");
			throw new WeaponCreationException("The weapon art reference cannot be null!");
		}

		return weaponArtReference;
	}

	private static final List<WeaponSound> checkLaunchSounds(List<WeaponSound> sounds) {
		if (sounds == null) {
			log.error("The launch sounds cannot be null!");
			throw new WeaponCreationException("The launch sounds cannot be null!");
		}

		return List.copyOf(sounds);
	}

	private static final int checkLength(int length) {
		if (length < 0) {
			return -1;
		} else {
			if (length < 1) {
				log.warn("The length must be geater than 0! Set to 1...");
				length = 1;
			}

			return length;
		}
	}

	private static final List<WeaponProjectile> checkProjectiles(List<WeaponProjectile> projectiles) {
		if (projectiles == null) {
			return List.of();
		} else {
			return List.copyOf(projectiles);
		}
	}

	private static final int checkRadius(int radius) {
		if (radius < 0) {
			return -1;
		} else {
			if (radius < 1) {
				log.warn("The radius must be geater than 0! Set to 1...");
				radius = 1;
			}

			return radius;
		}
	}

	private static final int checkSpin(int spin) {
		if (spin < 0) {
			return -1;
		} else {
			if (spin < 1) {
				log.warn("The spin must be geater than 0! Set to 1...");
				spin = 1;
			}

			return spin;
		}
	}

	private static final int checkShots(int shots) {
		if (shots < 0) {
			return -1;
		} else {
			if (shots < 1) {
				log.warn("The shots must be geater than 0! Set to 1...");
				shots = 1;
			}

			return shots;
		}
	}

	private static final String checkFlavorType(String flavorType) {
		if (flavorType == null) {
			return "";
		} else {
			return flavorType;
		}
	}

	private static final int checkStunChance(int stunChance) {
		if (stunChance < 0) {
			return -1;
		} else {
			if (stunChance > 10) {
				log.warn("The breach chance cannot be greater than 10! Set to 10...");
				stunChance = 10;
			}

			return stunChance;
		}
	}

	private static final int checkStun(int stun) {
		if (stun < 0) {
			return -1;
		} else {
			return stun;
		}
	}

	private static final int checkSpeed(int speed) {
		if (speed < 0) {
			return -1;
		} else {
			if (speed < 1) {
				log.warn("The speed must be geater than 0! Set to 1...");
				speed = 1;
			}

			return speed;
		}
	}

	private static final int checkMissiles(int missiles) {
		if (missiles < 0) {
			return -1;
		} else {
			return missiles;
		}
	}

	private static final int checkIon(int ion) {
		if (ion < 0) {
			return -1;
		} else {
			return ion;
		}
	}

	private static final String checkExplosion(String explosionReference) {
		if (explosionReference == null) {
			return "";
		} else {
			return explosionReference;
		}
	}

	private static final WeaponBoost checkBoost(WeaponBoost weaponBoost) {
		if (weaponBoost == null) {
			return WeaponBoost.EMPTY;
		} else {
			int count = weaponBoost.count();
			if (count < 0) {
				log.warn("The boost count cannot be negative! Set to 0...");
				count = 0;
			}

			return new WeaponBoost(weaponBoost.weaponBoostType(), weaponBoost.amount(), count);
		}
	}

	private static final int checkChargeLevels(int chargeLevels) {
		if (chargeLevels < 0) {
			return -1;
		} else {
			return chargeLevels;
		}
	}

	private static final List<WeaponSound> checkHitShipSounds(List<WeaponSound> sounds) {
		if (sounds == null) {
			return List.of();
		} else {
			return List.copyOf(sounds);
		}
	}

	private static final List<WeaponSound> checkHitShieldSounds(List<WeaponSound> sounds) {
		if (sounds == null) {
			return List.of();
		} else {
			return List.copyOf(sounds);
		}
	}

	private static final List<WeaponSound> checkMissSounds(List<WeaponSound> sounds) {
		if (sounds == null) {
			return List.of();
		} else {
			return List.copyOf(sounds);
		}
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();

		// Mandatory properties
		tags.add(WeaponPropertyFactory.createWeaponType(weaponType));
		tags.add(WeaponPropertyFactory.createTitle(title, titleReference));
		tags.add(WeaponPropertyFactory.createPower(power));
		tags.add(WeaponPropertyFactory.createRarity(rarity));
		tags.add(WeaponPropertyFactory.createDamage(damage));
		tags.add(WeaponPropertyFactory.createBp(bp));
		tags.add(WeaponPropertyFactory.createFireChance(fireChance));
		tags.add(WeaponPropertyFactory.createBreachChance(breachChance));
		tags.add(WeaponPropertyFactory.createWeaponArt(weaponArtReference));
		tags.add(WeaponPropertyFactory.createLaunchSounds(launchSounds));

		// Beam specific (length is mandatory but automatically set when setting type to
		// BEAM)
		if (weaponType == WeaponType.BEAM) {
			tags.add(WeaponPropertyFactory.createLength(length));
			if (color != null) {
				tags.add(WeaponPropertyFactory.createColor(color));
			}
		}

		// Burst specific (all are mandatory)
		else if (weaponType == WeaponType.BURST) {
			tags.add(WeaponPropertyFactory.createProjectiles(projectiles));
			tags.add(WeaponPropertyFactory.createRadius(radius));
			tags.add(WeaponPropertyFactory.createSpin(spin));
		}

		// Put the remaining
		if (!tipReference.isBlank()) {			
			tags.add(WeaponPropertyFactory.createTip(tipReference));
		}
		
		if (!shortTitle.isBlank()) {			
			tags.add(WeaponPropertyFactory.createShortTitle(shortTitle, shortTitleReference));
		}
	
		if (!description.isBlank()) {
			tags.add(WeaponPropertyFactory.createDescription(description, descriptionReference));
		}
		
		if (!tooltip.isBlank()) {
			tags.add(WeaponPropertyFactory.createTooltip(tooltip, tooltipReference));
		}
		
		if (cooldown > 0) {			
			tags.add(WeaponPropertyFactory.createCooldown(cooldown));
		}
		
		if (cost > 0) {
			tags.add(WeaponPropertyFactory.createCost(cost));
		}
		
		if (shieldPiercing > 0) {
			tags.add(WeaponPropertyFactory.createSp(shieldPiercing));
		}
		
		if (!imageReference.isBlank()) {
			tags.add(WeaponPropertyFactory.createImage(imageReference));
		}
		
		if (!iconImageReference.isBlank()) {			
			tags.add(WeaponPropertyFactory.createIconImage(iconImageReference));
		}
		
		if (shots > 0) {
			tags.add(WeaponPropertyFactory.createShots(shots));
		}

		if (!flavorType.isBlank()) {
			tags.add(WeaponPropertyFactory.createFlavorType(flavorType, flavorTypeReference));
		}

		if (stunChance > 0) {
			tags.add(WeaponPropertyFactory.createStunChance(stunChance));
		}

		if (stun > 0) {
			tags.add(WeaponPropertyFactory.createStun(stun));
		}

		if (speed > 0) {
			tags.add(WeaponPropertyFactory.createSpeed(speed));
		}

		if (persDamage != 0) {
			tags.add(WeaponPropertyFactory.createPersDamage(persDamage));
		}

		if (lockdown) {
			tags.add(WeaponPropertyFactory.createLockdown(lockdown));
		}

		if (systemDamage != 0) {
			tags.add(WeaponPropertyFactory.createSysDamage(systemDamage));
		}

		if (hullBust) {
			tags.add(WeaponPropertyFactory.createHullBust(hullBust));
		}

		if (droneTargetable) {
			tags.add(WeaponPropertyFactory.createDroneTargetable(droneTargetable));
		}

		if (missiles > 0) {
			tags.add(WeaponPropertyFactory.createMissiles(missiles));
		}

		if (ion > 0) {
			tags.add(WeaponPropertyFactory.createIon(ion));
		}

		if (!explosionReference.isBlank()) {
			tags.add(WeaponPropertyFactory.createExplosion(explosionReference));
		}

		if (locked) {
			tags.add(WeaponPropertyFactory.createLocked(locked));
		}

		if (!weaponBoost.equals(WeaponBoost.EMPTY)) {
			tags.add(weaponBoost.toXmlTag());
		}

		if (chargeLevels > 0) {
			tags.add(WeaponPropertyFactory.createChargeLevels(chargeLevels));
		}

		if (!hitShipSounds.isEmpty()) {
			tags.add(WeaponPropertyFactory.createHitShipSounds(hitShipSounds));
		}

		if (!hitShieldSounds.isEmpty()) {
			tags.add(WeaponPropertyFactory.createHitShieldSounds(hitShieldSounds));
		}

		if (!missSounds.isEmpty()) {
			tags.add(WeaponPropertyFactory.createMissSounds(missSounds));
		}

		final List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(NAME_ATTRIBUTE_NAME, name));
		if (noloc) {
			attributes.add(new Attribute(NOLOC_ATTRIBUTE_NAME, NOLOC_ATTRIBUTE_TRUE));
		}

		return new XmlTag<List<XmlTag<?>>>(WEAPON_BLUEPRINT_TAG_NAME, tags, attributes.toArray(Attribute[]::new));
	}

}
