package net.zefinder.ftlmod.weapon;

import java.util.List;

import net.zefinder.ftlmod.Builder;

public class WeaponBuilder implements Builder {

	private String name;
	private boolean noloc;

	// True if they are a reference
	private boolean titleReference;
	private boolean shortTitleReference;
	private boolean descriptionReference;
	private boolean tooltipReference;
	private boolean flavorTypeReference;

	private WeaponType weaponType;
	private String tipReference;
	private String title;
	private String shortTitle;
	private String description;
	private String tooltip;

	private double cooldown;
	private int power;
	private int cost;
	private int rarity; // 0 - 5
	private int damage;
	private int shieldPiercing;
	private int bp; // Unused

	private int fireChance; // Only 0 to 10, each point = 10%
	private int breachChance; // Only 0 to 10, each point = 10%

	private String imageReference;
	private String iconImageReference;
	private String weaponArtReference;

	private List<WeaponSound> launchSounds;

	// On beams only
	private int length; // Mandatory
	private WeaponBeamColor color; // Optional

	// On burst only (flak-like weapons), mandatoryString
	private List<WeaponProjectile> projectiles;
	private int radius;
	private int spin;

	// If there is a value, can add
	private int shots;
	private String flavorType;
	private int stunChance; // Only 0 to 10, each point = 10%
	private int stun;
	private int speed;
	private int persDamage; // 1 point = 15 hp
	private boolean lockdown; // 0 or 1
	private int systemDamage; // Usually on bomb weapons
	private boolean hullBust; // 0 or 1
	private boolean droneTargetable; // 0 or 1
	private int missiles;
	private int ion;
	private String explosionReference;
	private boolean locked; // 0 or 1

	// Chain weapons
	private WeaponBoost weaponBoost;

	// Charged weapons
	private int chargeLevels;

	// Optional sounds
	private List<WeaponSound> hitShipSounds;
	private List<WeaponSound> hitShieldSounds;
	private List<WeaponSound> missSounds;

	public WeaponBuilder(Weapon weapon) {
		copyFrom(weapon);
	}
	
	public WeaponBuilder() {
		name = null;
		noloc = false;
		titleReference = false;
		shortTitleReference = false;
		descriptionReference = false;
		tooltipReference = false;
		flavorTypeReference = false;
		weaponType = WeaponType.LASER;
		tipReference = null;
		title = null;
		shortTitle = null;
		description = null;
		tooltip = null;
		cooldown = -1;
		power = 0;
		cost = -1;
		rarity = -1;
		damage = 0;
		shieldPiercing = -1;
		bp = 0;
		fireChance = -1;
		breachChance = -1;
		imageReference = null;
		iconImageReference = null;
		weaponArtReference = null;
		launchSounds = null;
		length = -1;
		color = null;
		projectiles = null;
		radius = -1;
		spin = -1;
		shots = -1;
		flavorType = null;
		stunChance = -1;
		stun = -1;
		speed = -1;
		persDamage = 0;
		lockdown = false;
		systemDamage = 0;
		hullBust = false;
		droneTargetable = false;
		missiles = -1;
		ion = -1;
		explosionReference = null;
		locked = false;
		weaponBoost = null;
		chargeLevels = -1;
		hitShipSounds = null;
		hitShieldSounds = null;
		missSounds = null;
	}

	public String getName() {
		return name;
	}

	public boolean isNoloc() {
		return noloc;
	}

	public boolean isTitleReference() {
		return titleReference;
	}

	public boolean isShortTitleReference() {
		return shortTitleReference;
	}

	public boolean isDescriptionReference() {
		return descriptionReference;
	}

	public boolean isTooltipReference() {
		return tooltipReference;
	}

	public boolean isFlavorTypeReference() {
		return flavorTypeReference;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public String getTipReference() {
		return tipReference;
	}

	public String getTitle() {
		return title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public String getDescription() {
		return description;
	}

	public String getTooltip() {
		return tooltip;
	}

	public double getCooldown() {
		return cooldown;
	}

	public int getPower() {
		return power;
	}

	public int getCost() {
		return cost;
	}

	public int getRarity() {
		return rarity;
	}

	public int getDamage() {
		return damage;
	}

	public int getShieldPiercing() {
		return shieldPiercing;
	}

	public int getBp() {
		return bp;
	}

	public int getFireChance() {
		return fireChance;
	}

	public int getBreachChance() {
		return breachChance;
	}

	public String getImageReference() {
		return imageReference;
	}

	public String getIconImageReference() {
		return iconImageReference;
	}

	public String getWeaponArtReference() {
		return weaponArtReference;
	}

	public List<WeaponSound> getLaunchSounds() {
		return launchSounds;
	}

	public int getLength() {
		return length;
	}

	public WeaponBeamColor getColor() {
		return color;
	}

	public List<WeaponProjectile> getProjectiles() {
		return projectiles;
	}

	public int getRadius() {
		return radius;
	}

	public int getSpin() {
		return spin;
	}

	public int getShots() {
		return shots;
	}

	public String getFlavorType() {
		return flavorType;
	}

	public int getStunChance() {
		return stunChance;
	}

	public int getStun() {
		return stun;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPersDamage() {
		return persDamage;
	}

	public boolean isLockdown() {
		return lockdown;
	}

	public int getSystemDamage() {
		return systemDamage;
	}

	public boolean isHullBust() {
		return hullBust;
	}

	public boolean isDroneTargetable() {
		return droneTargetable;
	}

	public int getMissiles() {
		return missiles;
	}

	public int getIon() {
		return ion;
	}

	public String getExplosionReference() {
		return explosionReference;
	}

	public boolean isLocked() {
		return locked;
	}

	public WeaponBoost getWeaponBoost() {
		return weaponBoost;
	}

	public int getChargeLevels() {
		return chargeLevels;
	}

	public List<WeaponSound> getHitShipSounds() {
		return hitShipSounds;
	}

	public List<WeaponSound> getHitShieldSounds() {
		return hitShieldSounds;
	}

	public List<WeaponSound> getMissSounds() {
		return missSounds;
	}

	public WeaponBuilder setName(final String name) {
		this.name = name;
		return this;
	}

	public WeaponBuilder setNoloc(final boolean noloc) {
		this.noloc = noloc;
		return this;
	}

	public WeaponBuilder setTitleReference(final boolean titleReference) {
		this.titleReference = titleReference;
		return this;
	}

	public WeaponBuilder setShortTitleReference(final boolean shortTitleReference) {
		this.shortTitleReference = shortTitleReference;
		return this;
	}

	public WeaponBuilder setDescriptionReference(final boolean descriptionReference) {
		this.descriptionReference = descriptionReference;
		return this;
	}

	public WeaponBuilder setTooltipReference(final boolean tooltipReference) {
		this.tooltipReference = tooltipReference;
		return this;
	}

	public WeaponBuilder setFlavorTypeReference(final boolean flavorTypeReference) {
		this.flavorTypeReference = flavorTypeReference;
		return this;
	}

	public WeaponBuilder setWeaponType(final WeaponType weaponType) {
		this.weaponType = weaponType;
		return this;
	}

	public WeaponBuilder setTipReference(final String tipReference) {
		this.tipReference = tipReference;
		return this;
	}

	public WeaponBuilder setTitle(final String title) {
		this.title = title;
		return this;
	}

	public WeaponBuilder setShortTitle(final String shortTitle) {
		this.shortTitle = shortTitle;
		return this;
	}

	public WeaponBuilder setDescription(final String description) {
		this.description = description;
		return this;
	}

	public WeaponBuilder setTooltip(final String tooltip) {
		this.tooltip = tooltip;
		return this;
	}

	public WeaponBuilder setCooldown(final double cooldown) {
		this.cooldown = cooldown;
		return this;
	}

	public WeaponBuilder setPower(final int power) {
		this.power = power;
		return this;
	}

	public WeaponBuilder setCost(final int cost) {
		this.cost = cost;
		return this;
	}

	public WeaponBuilder setRarity(final int rarity) {
		this.rarity = rarity;
		return this;
	}

	public WeaponBuilder setDamage(final int damage) {
		this.damage = damage;
		return this;
	}

	public WeaponBuilder setShieldPiercing(final int shieldPiercing) {
		this.shieldPiercing = shieldPiercing;
		return this;
	}

	public WeaponBuilder setBp(final int bp) {
		this.bp = bp;
		return this;
	}

	public WeaponBuilder setFireChance(final int fireChance) {
		this.fireChance = fireChance;
		return this;
	}

	public WeaponBuilder setBreachChance(final int breachChance) {
		this.breachChance = breachChance;
		return this;
	}

	public WeaponBuilder setImageReference(final String imageReference) {
		this.imageReference = imageReference;
		return this;
	}

	public WeaponBuilder setIconImageReference(final String iconImageReference) {
		this.iconImageReference = iconImageReference;
		return this;
	}

	public WeaponBuilder setWeaponArtReference(final String weaponArtReference) {
		this.weaponArtReference = weaponArtReference;
		return this;
	}

	public WeaponBuilder setLaunchSounds(final List<WeaponSound> launchSounds) {
		this.launchSounds = launchSounds;
		return this;
	}

	public WeaponBuilder setLength(final int length) {
		this.length = length;
		return this;
	}

	public WeaponBuilder setColor(final WeaponBeamColor color) {
		this.color = color;
		return this;
	}

	public WeaponBuilder setProjectiles(final List<WeaponProjectile> projectiles) {
		this.projectiles = projectiles;
		return this;
	}

	public WeaponBuilder setRadius(final int radius) {
		this.radius = radius;
		return this;
	}

	public WeaponBuilder setSpin(final int spin) {
		this.spin = spin;
		return this;
	}

	public WeaponBuilder setShots(final int shots) {
		this.shots = shots;
		return this;
	}

	public WeaponBuilder setFlavorType(final String flavorType) {
		this.flavorType = flavorType;
		return this;
	}

	public WeaponBuilder setStunChance(final int stunChance) {
		this.stunChance = stunChance;
		return this;
	}

	public WeaponBuilder setStun(final int stun) {
		this.stun = stun;
		return this;
	}

	public WeaponBuilder setSpeed(final int speed) {
		this.speed = speed;
		return this;
	}

	public WeaponBuilder setPersDamage(final int persDamage) {
		this.persDamage = persDamage;
		return this;
	}

	public WeaponBuilder setLockdown(final boolean lockdown) {
		this.lockdown = lockdown;
		return this;
	}

	public WeaponBuilder setSystemDamage(final int systemDamage) {
		this.systemDamage = systemDamage;
		return this;
	}

	public WeaponBuilder setHullBust(final boolean hullBust) {
		this.hullBust = hullBust;
		return this;
	}

	public WeaponBuilder setDroneTargetable(final boolean droneTargetable) {
		this.droneTargetable = droneTargetable;
		return this;
	}

	public WeaponBuilder setMissiles(final int missiles) {
		this.missiles = missiles;
		return this;
	}

	public WeaponBuilder setIon(final int ion) {
		this.ion = ion;
		return this;
	}

	public WeaponBuilder setExplosionReference(final String explosionReference) {
		this.explosionReference = explosionReference;
		return this;
	}

	public WeaponBuilder setLocked(final boolean locked) {
		this.locked = locked;
		return this;
	}

	public WeaponBuilder setWeaponBoost(final WeaponBoost weaponBoost) {
		this.weaponBoost = weaponBoost;
		return this;
	}

	public WeaponBuilder setChargeLevels(final int chargeLevels) {
		this.chargeLevels = chargeLevels;
		return this;
	}

	public WeaponBuilder setHitShipSounds(final List<WeaponSound> hitShipSounds) {
		this.hitShipSounds = hitShipSounds;
		return this;
	}

	public WeaponBuilder setHitShieldSounds(final List<WeaponSound> hitShieldSounds) {
		this.hitShieldSounds = hitShieldSounds;
		return this;
	}

	public WeaponBuilder setMissSounds(final List<WeaponSound> missSounds) {
		this.missSounds = missSounds;
		return this;
	}

	public WeaponBuilder copyFrom(Weapon other) {
		this.name = other.name();
		this.noloc = other.noloc();
		this.titleReference = other.titleReference();
		this.shortTitleReference = other.shortTitleReference();
		this.descriptionReference = other.descriptionReference();
		this.tooltipReference = other.tooltipReference();
		this.flavorTypeReference = other.flavorTypeReference();
		this.weaponType = other.weaponType();
		this.tipReference = other.tipReference();
		this.title = other.title();
		this.shortTitle = other.shortTitle();
		this.description = other.description();
		this.tooltip = other.tooltip();
		this.cooldown = other.cooldown();
		this.power = other.power();
		this.cost = other.cost();
		this.rarity = other.rarity();
		this.damage = other.damage();
		this.shieldPiercing = other.shieldPiercing();
		this.bp = other.bp();
		this.fireChance = other.fireChance();
		this.breachChance = other.breachChance();
		this.imageReference = other.imageReference();
		this.iconImageReference = other.iconImageReference();
		this.weaponArtReference = other.weaponArtReference();
		this.launchSounds = other.launchSounds();
		this.length = other.length();
		this.color = other.color();
		this.projectiles = other.projectiles();
		this.radius = other.radius();
		this.spin = other.spin();
		this.shots = other.shots();
		this.flavorType = other.flavorType();
		this.stunChance = other.stunChance();
		this.stun = other.stun();
		this.speed = other.speed();
		this.persDamage = other.persDamage();
		this.lockdown = other.lockdown();
		this.systemDamage = other.systemDamage();
		this.hullBust = other.hullBust();
		this.droneTargetable = other.droneTargetable();
		this.missiles = other.missiles();
		this.ion = other.ion();
		this.explosionReference = other.explosionReference();
		this.locked = other.locked();
		this.weaponBoost = other.weaponBoost();
		this.chargeLevels = other.chargeLevels();
		this.hitShipSounds = other.hitShipSounds();
		this.hitShieldSounds = other.hitShieldSounds();
		this.missSounds = other.missSounds();
		
		return this;
	}

	@Override
	public Weapon build() throws WeaponCreationException {
		return new Weapon(this);
	}

}
