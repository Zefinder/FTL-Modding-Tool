package net.zefinder.ftlmod.analyser;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.event.Event;
import net.zefinder.ftlmod.event.EventBoarders;
import net.zefinder.ftlmod.event.EventBuilder;
import net.zefinder.ftlmod.event.EventCreationException;
import net.zefinder.ftlmod.event.EventDamage;
import net.zefinder.ftlmod.event.EventFleetType;
import net.zefinder.ftlmod.event.EventImg;
import net.zefinder.ftlmod.event.EventItemModify;
import net.zefinder.ftlmod.event.EventItemModify.Item;
import net.zefinder.ftlmod.event.EventItemModify.ItemType;
import net.zefinder.ftlmod.event.EventManager;
import net.zefinder.ftlmod.event.EventShip;
import net.zefinder.ftlmod.event.EventType;
import net.zefinder.ftlmod.game.Background;
import net.zefinder.ftlmod.game.DamageEffect;
import net.zefinder.ftlmod.game.Planet;
import net.zefinder.ftlmod.game.Race;
import net.zefinder.ftlmod.game.ShipSystem;
import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.text.TextListManager;
import net.zefinder.ftlmod.text.TextManager;
import net.zefinder.ftlmod.text.TextType;
import net.zefinder.ftlmod.weapon.WeaponManager;

public class EventAnalyser {

	private static final Logger log = LoggerFactory.getLogger(EventAnalyser.class);

	private EventAnalyser() {
	}

	public static final void analyseEvent(final List<Element> elements, final boolean isUser)
			throws EventCreationException {
		for (Element eventElement : elements) {
			if (!eventElement.getName().equals(EVENT_TAG_NAME)) {
				continue;
			}

			Event event = getEventFromElement(eventElement);
			EventType type = event.eventType();

			if (type == EventType.LOAD) {
				// Load event, register usage in manager
				log.info("Load event %s, register usage in manager...".formatted(event.name()));
				EventManager.getInstance().useObject(event.name());
//				System.exit(0);
			} else if (type == EventType.NONE) {
				// Empty event
				log.info("Empty event, skip!");
			} else {
				// Normal event
				EventManager.getInstance().addObject(event, isUser);
				System.out.println(event.toXmlTag());
			}

			// FUEL_ESCAPE_PULSAR
			System.exit(0);
		}
	}

//	public static void analyseEventList(final List<Element> elements, final boolean isUser) {
//		for (Element eventListElement : elements) {
//			if (!eventListElement.getName().equals("eventList")) {
//				continue;
//			}
//
//			final String listName = eventListElement.attributeValue("name");
//			if (listName == null || listName.isBlank()) {
//				log.error("Empty name, ignore!");
//				continue;
//			}
//
//			log.info("Registering event list " + listName);
//
//			// Each element here should be an event
//			for (Element eventElement : eventListElement.elements(EVENT_TAG_NAME)) {
//				final String load = eventElement.attributeValue(LOAD_ATTRIBUTE_NAME);
//				final String name = eventElement.attributeValue(NAME_ATTRIBUTE_NAME);
//				if (load != null) {
//					// Load event, register usage in manager
//					log.info("Load event %s for event list...".formatted(load));
//					EventManager.getInstance().useObject(load);
//
//					// Create custom event for list
//					// TODO Change to EventListManager!
//					Event loadEvent = new EventBuilder().setEventType(EventType.LOAD).setName(load).build();
//					EventManager.getInstance().addObject(loadEvent, isUser);
//				} else if (name == null) {
//					// Empty event
//					log.info("Empty event, skip!");
//				} else {
//					// Normal event
//					Event event = getEventFromElement(eventElement, name);
//					EventManager.getInstance().addObject(event, isUser);
//
//				}
//			}
//		}
//	}

	public static Event getEventFromElement(Element eventElement) {
		EventBuilder builder = new EventBuilder();
		boolean unique = Boolean.valueOf(eventElement.attributeValue(UNIQUE_ATTRIBUTE_NAME));
		String load = eventElement.attributeValue(LOAD_ATTRIBUTE_NAME);
		String name = eventElement.attributeValue(NAME_ATTRIBUTE_NAME);

		if (load != null && !load.isBlank()) {
			builder.setEventType(EventType.LOAD);
			builder.setName(load);
			return builder.build();
		}

		if (name == null || name.isBlank()) {
			builder.setEventType(EventType.NONE);
			return builder.build();
		}

		// From here normal event
		log.info("Registering event %s (unique: %b)".formatted(name, unique));
		builder.setEventType(EventType.NORMAL);
		builder.setName(name);
		builder.setUnique(unique);

		for (Element eventProperty : eventElement.elements()) {
			String propertyName = eventProperty.getName();
			String data = (String) eventProperty.getData();

			switch (propertyName) {
			case TEXT_TAG_NAME:
				builder.setEventText(getTextFromElement(eventElement));
				break;

			case REPAIR_TAG_NAME:
				builder.setRepair(true);
				break;

			case FLEET_TAG_NAME:
				final EventFleetType fleetType = EventFleetType.fromString(data);
				if (fleetType == EventFleetType.NONE) {
					log.warn("Empty tag or unknown value when reading fleet... ignore!");
				}
				builder.setFleet(fleetType);
				break;

			case DAMAGE_TAG_NAME:
				final EventDamage damage = getEventDamageFromElement(eventProperty);
				if (damage == null) {
					log.error("Error when reading event damage... ignore!");
				} else {
					builder.addEventDamage(damage);
				}
				break;

			case IMG_TAG_NAME:
				builder.setEventImg(getEventImgFromElement(eventProperty));
				break;

			case BOARDERS_TAG_NAME:
				final EventBoarders boarders = getEventBoardersFromElement(eventProperty);
				if (boarders == null) {
					log.error("Error when reading event boarders... ignore!");
				} else {
					builder.setEventBoarders(boarders);
				}
				break;

			case SECRET_SECTOR_TAG_NAME:
				builder.setSecretSector(true);
				break;

			case ITEM_MODIFY_TAG_NAME:
				builder.setEventItemModify(getItemModifyFromElement(eventProperty));
				break;

			case SHIP_TAG_NAME:
				builder.setEventShip(getEventShipFromElement(eventProperty));
				break;

			case QUEST_TAG_NAME:
				final String questName = eventProperty.attributeValue(EVENT_ATTRIBUTE_NAME);
				if (questName == null || questName.isBlank()) {
					log.error("Empty event quest... ignore!");
				} else {
					// Use event in the manager
					EventManager.getInstance().useObject(questName);
					builder.setEventQuest(questName);
				}
				break;

			case REMOVE_TAG_NAME:
				final String removeName = eventProperty.attributeValue(NAME_ATTRIBUTE_NAME);
				if (removeName == null || removeName.isBlank()) {
					log.error("Empty remove... ignore!");
				} else {
					// Use event in the manager
					builder.setRemove(removeName);
				}
				break;

			case MODIFY_PURSUIT_TAG_NAME:
				final String modifyPursuitString = eventProperty.attributeValue(AMOUNT_ATTRIBUTE_NAME);
				try {
					builder.setModifyPursuit(Integer.valueOf(modifyPursuitString));
				} catch (NumberFormatException e) {
					log.error("Wrong type for modify pursuit amount, must be an integer... ignore!", e);
				}
				break;

			case AUGMENT_TAG_NAME:
				final String augmentName = eventProperty.attributeValue(NAME_ATTRIBUTE_NAME);
				if (augmentName == null || augmentName.isBlank()) {
					log.error("Empty augment name... ignore!");
				} else {
					if (!augmentName.equals(RANDOM_NAME)) {
						// TODO Set use of augment in manager
					}
					builder.setAugmentName(augmentName);
				}
				break;

			case WEAPON_TAG_NAME:
				final String weaponName = eventProperty.attributeValue(NAME_ATTRIBUTE_NAME);
				if (weaponName == null || weaponName.isBlank()) {
					log.error("Empty weapon name... ignore!");
				} else {
					// Use weapon in manager
					if (!weaponName.equals(RANDOM_NAME)) {
						WeaponManager.getInstance().useObject(weaponName);
					}
					builder.setWeaponName(weaponName);
				}
				break;
				
			case REVEAL_MAP_TAG_NAME:
				builder.setRevealMap(true);
				break;

			default:
				log.info("Unknown tag %s... ignore!".formatted(propertyName));
			}
		}

		return builder.build();
	}

	private static final Text getTextFromElement(Element eventElement) {
		final Element textElement = eventElement.element(TEXT_TAG_NAME);
		if (textElement == null) {
			return Text.EMPTY;
		}

		final Text text = TextAnalyser.getTextFromElement(textElement);
		final TextType type = text.textType();

		// If the text refers to another text, then use in text manager
		if (type == TextType.REFERENCE) {
			TextManager.getInstance().useText(text.name());
		}
		// If the text refers to a text list, then use in text list manager
		else if (type == TextType.LOAD) {
			TextListManager.getInstance().useObject(text.name());
		}

		return text;
	}

	private static final EventDamage getEventDamageFromElement(Element damageElement) {
		final String amountString = damageElement.attributeValue(AMOUNT_ATTRIBUTE_NAME);
		final int amount;
		try {
			amount = Integer.valueOf(amountString);
		} catch (NumberFormatException e) {
			log.error("Wrong type for damage amount, must be an integer!", e);
			return null;
		}

		ShipSystem system = ShipSystem.NONE;
		DamageEffect effect = DamageEffect.NONE;

		final String systemName = damageElement.attributeValue(SYSTEM_ATTRIBUTE_NAME);
		if (systemName != null && !systemName.isBlank()) {
			system = ShipSystem.fromString(systemName);

			final String effectName = damageElement.attributeValue(EFFECT_ATTRIBUTE_NAME);
			if (effectName != null && !effectName.isBlank()) {
				effect = DamageEffect.fromString(effectName);
			}
		}

		return new EventDamage(amount, system, effect);
	}

	private static final EventImg getEventImgFromElement(Element imgElement) {
		final String planetString = imgElement.attributeValue(PLANET_ATTRIBUTE_NAME);
		final String backgroundString = imgElement.attributeValue(BACKGROUND_ATTRIBUTE_NAME);

		final Planet planet = Planet.fromString(planetString);
		final Background background = Background.fromString(backgroundString);

		return new EventImg(planet, background);
	}

	private static final EventBoarders getEventBoardersFromElement(Element boarderElement) {
		final boolean breach = Boolean.valueOf(boarderElement.attributeValue(BREACH_ATTRIBUTE_NAME));
		final String minString = boarderElement.attributeValue(MIN_ATTRIBUTE_NAME);
		final int min;
		try {
			min = Integer.valueOf(minString);
		} catch (NumberFormatException e) {
			log.error("Wrong type for min boarders, must be an integer!", e);
			return null;
		}

		final String maxString = boarderElement.attributeValue(MAX_ATTRIBUTE_NAME);
		final int max;
		try {
			max = Integer.valueOf(maxString);
		} catch (NumberFormatException e) {
			log.error("Wrong type for max boarders, must be an integer!", e);
			return null;
		}

		final Race race = Race.fromString(boarderElement.attributeValue(CLASS_ATTRIBUTE_NAME));
		return new EventBoarders(breach, min, max, race);
	}

	private static final EventItemModify getItemModifyFromElement(Element itemModifyElement) {
		final boolean steal = Boolean.valueOf(itemModifyElement.attributeValue(STEAL_ATTRIBUTE_NAME));
		final List<Element> itemElements = itemModifyElement.elements(ITEM_TAG_NAME);
		final List<Item> items = new ArrayList<Item>();

		// Message if no items
		if (itemElements.size() == 0) {
			log.warn("Item modify tag does not have any items in it... Is it a mistake?");
		}

		for (Element itemElement : itemElements) {
			final String minString = itemElement.attributeValue(MIN_ATTRIBUTE_NAME);
			final int min;
			try {
				min = Integer.valueOf(minString);
			} catch (NumberFormatException e) {
				log.error("Wrong type for min items, must be an integer... ignore!", e);
				continue;
			}

			final String maxString = itemElement.attributeValue(MAX_ATTRIBUTE_NAME);
			final int max;
			try {
				max = Integer.valueOf(maxString);
			} catch (NumberFormatException e) {
				log.error("Wrong type for max items, must be an integer... ignore!", e);
				continue;
			}

			final ItemType type = ItemType.fromString(itemElement.attributeValue(TYPE_ATTRIBUTE_NAME));
			items.add(new Item(min, max, type));
		}

		return new EventItemModify(steal, items.toArray(Item[]::new));
	}

	private static final EventShip getEventShipFromElement(Element shipElement) {
		final String shipName = shipElement.attributeValue(LOAD_ATTRIBUTE_NAME);
		final boolean hostile = Boolean.valueOf(shipElement.attributeValue(HOSTILE_ATTRIBUTE_NAME));

		return new EventShip(shipName, hostile);
	}

}
