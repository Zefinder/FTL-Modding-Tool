package net.zefinder.ftlmod.analyser;

import static net.zefinder.ftlmod.weapon.Weapon.BOOST_AMOUNT_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.BOOST_COUNT_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.BOOST_TYPE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.BP_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.BREACH_CHANCE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.B_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.CHARGE_LEVELS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.COLOR_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.COOLDOWN_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.COST_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.COUNT_ATTRIBUTE;
import static net.zefinder.ftlmod.weapon.Weapon.DAMAGE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.DESCRIPTION_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.DRONE_TARGETABLE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.EXPLOSION_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.FAKE_ATTRIBUTE;
import static net.zefinder.ftlmod.weapon.Weapon.FIRE_CHANCE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.FLAVOR_TYPE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.G_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.HIT_SHIELD_SOUNDS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.HIT_SHIP_SOUNDS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.HULL_BUST_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.ICON_IMAGE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.ID_ATTRIBUTE;
import static net.zefinder.ftlmod.weapon.Weapon.IMAGE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.ION_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.LAUNCH_SOUNDS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.LENGTH_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.LOCKDOWN_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.LOCKED_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.MISSILES_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.MISS_SOUNDS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.NAME_ATTRIBUTE;
import static net.zefinder.ftlmod.weapon.Weapon.NOLOC_ATTRIBUTE;
import static net.zefinder.ftlmod.weapon.Weapon.NOLOC_ATTRIBUTE_TRUE;
import static net.zefinder.ftlmod.weapon.Weapon.PERS_DAMAGE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.POWER_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.PROJECTILES_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.PROJECTILE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.RADIUS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.RARITY_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.R_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SHIELD_PIERCING_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SHORT_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SHOTS_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SOUND_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SPEED_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SPIN_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.STUN_CHANCE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.STUN_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.SYSTEM_DAMAGE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.TIP_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.TITLE_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.TOOLTIP_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.WEAPON_ART_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.WEAPON_BOOST_TAG_NAME;
import static net.zefinder.ftlmod.weapon.Weapon.WEAPON_TYPE_TAG_NAME;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.weapon.WeaponBeamColor;
import net.zefinder.ftlmod.weapon.WeaponBoost;
import net.zefinder.ftlmod.weapon.WeaponBoostType;
import net.zefinder.ftlmod.weapon.WeaponBuilder;
import net.zefinder.ftlmod.weapon.WeaponCreationException;
import net.zefinder.ftlmod.weapon.WeaponManager;
import net.zefinder.ftlmod.weapon.WeaponProjectile;
import net.zefinder.ftlmod.weapon.WeaponSound;
import net.zefinder.ftlmod.weapon.WeaponType;

final class WeaponAnalyser {

	private static final Logger log = LoggerFactory.getLogger(WeaponAnalyser.class);

	private WeaponAnalyser() {
	}

	public static final void analyse(final List<Element> weaponElements, boolean isUser)
			throws WeaponCreationException {
		WeaponManager manager = WeaponManager.getInstance();
		for (Element weaponElement : weaponElements) {
			final String name = weaponElement.attributeValue(NAME_ATTRIBUTE);
			String noLocAttribute = weaponElement.attributeValue(NOLOC_ATTRIBUTE);
			final boolean noLoc = noLocAttribute != null && noLocAttribute.equals(NOLOC_ATTRIBUTE_TRUE);

			WeaponBuilder weaponBuilder = new WeaponBuilder();
			weaponBuilder.setName(name).setNoloc(noLoc);
			log.info("Registering weapon %s (noloc: %b)".formatted(name, noLoc));

			// Fill the weapon with elements found in the XML
			for (Element weaponProperty : weaponElement.elements()) {
				String propertyName = weaponProperty.getName();
				String data = (String) weaponProperty.getData();
//				System.out.println(propertyName);

				switch (propertyName) {
				case WEAPON_TYPE_TAG_NAME:
					weaponBuilder.setWeaponType(WeaponType.fromString(data));
					break;

				case TIP_TAG_NAME:
					weaponBuilder.setTipReference(data);
					break;

				case TITLE_TAG_NAME:
					if (data == null || data.isBlank()) {
						weaponBuilder.setTitleReference(true);
						weaponBuilder.setTitle(weaponProperty.attributeValue(ID_ATTRIBUTE));
					} else {
						weaponBuilder.setTitleReference(true);
						weaponBuilder.setTitle(data);
					}
					break;

				case SHORT_TAG_NAME:
					if (data == null || data.isBlank()) {
						weaponBuilder.setShortTitleReference(true);
						weaponBuilder.setShortTitle(weaponProperty.attributeValue(ID_ATTRIBUTE));
					} else {
						weaponBuilder.setShortTitleReference(false);
						weaponBuilder.setShortTitle(data);
					}
					break;

				case DESCRIPTION_TAG_NAME:
					if (data == null || data.isBlank()) {
						weaponBuilder.setDescriptionReference(true);
						weaponBuilder.setDescription(weaponProperty.attributeValue(ID_ATTRIBUTE));
					} else {
						weaponBuilder.setDescriptionReference(false);
						weaponBuilder.setDescription(data);
					}
					break;

				case TOOLTIP_TAG_NAME:
					if (data == null || data.isBlank()) {
						weaponBuilder.setTooltipReference(true);
						weaponBuilder.setTooltip(weaponProperty.attributeValue(ID_ATTRIBUTE));
					} else {
						weaponBuilder.setTooltipReference(false);
						weaponBuilder.setTooltip(data);
					}
					break;

				case COOLDOWN_TAG_NAME:
					weaponBuilder.setCooldown(Double.valueOf(data));
					break;

				case POWER_TAG_NAME:
					weaponBuilder.setPower(Integer.valueOf(data));
					break;

				case COST_TAG_NAME:
					weaponBuilder.setCost(Integer.valueOf(data));
					break;

				case RARITY_TAG_NAME:
					weaponBuilder.setRarity(Integer.valueOf(data));
					break;

				case DAMAGE_TAG_NAME:
					weaponBuilder.setDamage(Integer.valueOf(data));
					break;

				case SHIELD_PIERCING_TAG_NAME:
					weaponBuilder.setShieldPiercing(Integer.valueOf(data));
					break;

				case BP_TAG_NAME:
					weaponBuilder.setBp(Integer.valueOf(data));
					break;

				case FIRE_CHANCE_TAG_NAME:
					weaponBuilder.setFireChance(Integer.valueOf(data));
					break;

				case BREACH_CHANCE_TAG_NAME:
					weaponBuilder.setBreachChance(Integer.valueOf(data));
					break;

				case IMAGE_TAG_NAME:
					weaponBuilder.setImageReference(data);
					break;

				case ICON_IMAGE_TAG_NAME:
					weaponBuilder.setIconImageReference(data);
					break;

				case WEAPON_ART_TAG_NAME:
					weaponBuilder.setWeaponArtReference(data);
					break;

				case LAUNCH_SOUNDS_TAG_NAME:
					weaponBuilder.setLaunchSounds(getSoundsFromElement(weaponProperty));
					break;

				case LENGTH_TAG_NAME:
					weaponBuilder.setLength(Integer.valueOf(data));
					break;

				case COLOR_TAG_NAME: {
					WeaponBeamColor color = getWeaponBeamColorFromElement(weaponProperty);

					if (color == null) {
						log.info("Error when reading color, skip!");
					} else {
						weaponBuilder.setColor(color);
					}
					break;
				}

				case PROJECTILES_TAG_NAME:
					weaponBuilder.setProjectiles(getWeaponProjectilesFromElement(weaponProperty));
					break;

				case RADIUS_TAG_NAME:
					weaponBuilder.setRadius(Integer.valueOf(data));
					break;

				case SPIN_TAG_NAME:
					weaponBuilder.setSpin(Integer.valueOf(data));
					break;

				case SHOTS_TAG_NAME:
					weaponBuilder.setShots(Integer.valueOf(data));
					break;

				case FLAVOR_TYPE_TAG_NAME:
					if (data == null || data.isBlank()) {
						weaponBuilder.setFlavorTypeReference(true);
						weaponBuilder.setFlavorType(weaponProperty.attributeValue(ID_ATTRIBUTE));
					} else {
						weaponBuilder.setFlavorTypeReference(false);
						weaponBuilder.setFlavorType(data);
					}
					break;

				case STUN_CHANCE_TAG_NAME:
					weaponBuilder.setStunChance(Integer.valueOf(data));
					break;

				case STUN_TAG_NAME:
					weaponBuilder.setStun(Integer.valueOf(data));
					break;

				case SPEED_TAG_NAME:
					weaponBuilder.setSpeed(Integer.valueOf(data));
					break;

				case PERS_DAMAGE_TAG_NAME:
					weaponBuilder.setPersDamage(Integer.valueOf(data));
					break;

				case LOCKDOWN_TAG_NAME:
					weaponBuilder.setLockdown(Integer.valueOf(data) == 1);
					break;

				case SYSTEM_DAMAGE_TAG_NAME:
					weaponBuilder.setSystemDamage(Integer.valueOf(data));
					break;

				case HULL_BUST_TAG_NAME:
					weaponBuilder.setHullBust(Integer.valueOf(data) == 1);
					break;

				case DRONE_TARGETABLE_TAG_NAME:
					weaponBuilder.setDroneTargetable(Integer.valueOf(data) == 1);
					break;

				case MISSILES_TAG_NAME:
					weaponBuilder.setMissiles(Integer.valueOf(data));
					break;

				case ION_TAG_NAME:
					weaponBuilder.setIon(Integer.valueOf(data));
					break;

				case EXPLOSION_TAG_NAME:
					weaponBuilder.setExplosionReference(data);
					break;

				case LOCKED_TAG_NAME:
					weaponBuilder.setLocked(Integer.valueOf(data) == 1);
					break;

				case WEAPON_BOOST_TAG_NAME:
					WeaponBoost weaponBoost = getWeaponBoostFromElement(weaponProperty);

					if (weaponBoost == null) {
						log.info("Error when reading boost, skip!");
					} else {
						weaponBuilder.setWeaponBoost(weaponBoost);
					}
					break;

				case CHARGE_LEVELS_TAG_NAME:
					weaponBuilder.setChargeLevels(Integer.valueOf(data));
					break;

				case HIT_SHIP_SOUNDS_TAG_NAME:
					weaponBuilder.setHitShipSounds(getSoundsFromElement(weaponProperty));
					break;

				case HIT_SHIELD_SOUNDS_TAG_NAME:
					weaponBuilder.setHitShieldSounds(getSoundsFromElement(weaponProperty));
					break;

				case MISS_SOUNDS_TAG_NAME:
					weaponBuilder.setMissSounds(getSoundsFromElement(weaponProperty));
					break;

				default:
					log.warn("Unknown element %s (value %s), ignored...".formatted(propertyName, data));
					break;
				}
			}

			// Add the weapon to the manager
			try {
				manager.addWeapon(weaponBuilder.build(), isUser);
			} catch (WeaponCreationException e) {
				log.error("Something happened during weapon build...", e);
			}
		}
	}

	// TODO Create a getValueFromElement or something like that so it'll easier to
	// read (and less code duplication)

	private static final List<WeaponSound> getSoundsFromElement(Element element) {
		List<Element> soundElements = element.elements(SOUND_TAG_NAME);
		if (soundElements == null) {
			log.warn("Empty %s, returning empty array instead of null".formatted(element.getName()));
			return List.of();
		}

		return soundElements.stream().map(soundElement -> (String) soundElement.getData())
				.filter(sound -> sound != null && !sound.isBlank()).map(sound -> new WeaponSound(sound)).toList();
	}

	private static final WeaponBeamColor getWeaponBeamColorFromElement(Element element) {
		Element rElement = element.element(R_TAG_NAME);
		if (rElement == null) {
			log.error("r tag missing for color element!");
			return null;
		}
		int r;
		try {
			r = Integer.valueOf((String) rElement.getData());
		} catch (NumberFormatException e) {
			log.error("Wrong format for red color, must be an integer!", e);
			return null;
		}

		Element gElement = element.element(G_TAG_NAME);
		if (gElement == null) {
			log.error("g tag missing for color element!");
			return null;
		}
		int g;
		try {
			g = Integer.valueOf((String) gElement.getData());
		} catch (NumberFormatException e) {
			log.error("Wrong format for green color, must be an integer!", e);
			return null;
		}

		Element bElement = element.element(B_TAG_NAME);
		if (bElement == null) {
			log.error("b tag missing for color element!");
			return null;
		}
		int b;
		try {
			b = Integer.valueOf((String) bElement.getData());
		} catch (NumberFormatException e) {
			log.error("Wrong format for blue color, must be an integer!", e);
			return null;
		}

		return new WeaponBeamColor(r, g, b);
	}

	private static final List<WeaponProjectile> getWeaponProjectilesFromElement(Element element) {
		List<Element> projectileElements = element.elements(PROJECTILE_TAG_NAME);
		if (projectileElements == null) {
			log.warn("Empty projectiles, returning empty array instead of null");
			return List.of();
		}

		List<WeaponProjectile> projectiles = new ArrayList<WeaponProjectile>();
		int projectileNumber = 0;
		for (Element projectileElement : projectileElements) {
			projectileNumber++;
			String countAttribute = projectileElement.attributeValue(COUNT_ATTRIBUTE);
			if (countAttribute == null) {
				log.error("count attribute missing for projectile %d!".formatted(projectileNumber));
				continue;
			}
			int count;
			try {
				count = Integer.valueOf(countAttribute);
			} catch (NumberFormatException e) {
				log.error("Wrong format for count attribute, must be an integer!", e);
				continue;
			}

			String fakeAttribute = projectileElement.attributeValue(FAKE_ATTRIBUTE);
			if (fakeAttribute == null) {
				log.error("fake attribute missing for projectile %d!".formatted(projectileNumber));
				continue;
			}
			boolean fake = Boolean.valueOf(countAttribute);

			String data = (String) projectileElement.getData();
			if (data == null || data.isBlank()) {
				log.error("Null or empty value for projectile %d!".formatted(projectileNumber));
				continue;
			}

			projectiles.add(new WeaponProjectile(count, fake, data));
		}

		return List.copyOf(projectiles);
	}

	private static final WeaponBoost getWeaponBoostFromElement(Element element) {
		Element boostTypeElement = element.element(BOOST_TYPE_TAG_NAME);
		if (boostTypeElement == null) {
			log.error("type tag missing for boost element!");
			return null;
		}
		String boostType = (String) boostTypeElement.getData();
		if (boostType == null || boostType.isBlank()) {
			log.error("Null or empty value for boost!");
			return null;
		}
		WeaponBoostType weaponBoostType = WeaponBoostType.fromString(boostType);

		Element countElement = element.element(BOOST_COUNT_TAG_NAME);
		if (countElement == null) {
			log.error("count tag missing for boost element!");
			return null;
		}
		int count;
		try {
			count = Integer.valueOf((String) countElement.getData());
		} catch (NumberFormatException e) {
			log.error("Wrong format for boost count, must be an integer!", e);
			return null;
		}

		Element amountElement = element.element(BOOST_AMOUNT_TAG_NAME);
		if (amountElement == null) {
			log.error("amount tag missing for boost element!");
			return null;
		}
		int amount;
		try {
			amount = Integer.valueOf((String) amountElement.getData());
		} catch (NumberFormatException e) {
			log.error("Wrong format for amount count, must be an integer!", e);
			return null;
		}

		return new WeaponBoost(weaponBoostType, amount, count);
	}

}
