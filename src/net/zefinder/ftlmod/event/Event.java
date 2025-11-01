package net.zefinder.ftlmod.event;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.weapon.Weapon;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public class Event implements XmlObject {

	public static final String EVENT_TAG_NAME = "event";
	public static final String REPAIR_TAG_NAME = "repair";
	public static final String FLEET_TAG_NAME = "fleet";
	public static final String SECRET_SECTOR_TAG_NAME = "secretSector";
	public static final String ITEM_MODIFY_TAG_NAME = "item_modify";
	public static final String QUEST_TAG_NAME = "quest";
	public static final String REMOVE_TAG_NAME = "remove";
	public static final String MODIFY_POURSUIT_TAG_NAME = "modifyPoursuit";
	public static final String AUGMENT_TAG_NAME = "augment";
	public static final String WEAPON_TAG_NAME = "weapon";
	public static final String REVEAL_MAP_TAG_NAME = "reveal_map";
	
	public static final String LOAD_ATTRIBUTE_NAME = "load";
	public static final String NAME_ATTRIBUTE_NAME = "name";
	public static final String UNIQUE_ATTRIBUTE_NAME = "unique";
	public static final String ID_ATTRIBUTE_NAME = "id";
	public static final String STEAL_ATTRIBUTE_NAME = "steal";
	public static final String EVENT_ATTRIBUTE_NAME = "event";
	public static final String AMOUNT_ATTRIBUTE_NAME = "amount";

	public static final Event EMPTY = new Event();

	private final EventType eventType;
	private final String name;
	private final boolean unique;

	private final Text eventText;
	private final boolean repair;
	private final boolean fleet;
	private final EventDamage eventDamage;
	private final EventImg eventImg;
	private final EventBoarders eventBoarders;
	private final boolean secretSector;
	private final boolean itemModify;
	private final EventShip eventShip;
	private final Event eventQuest;
	private final String remove;
	private final int modifyPoursuit;
	private final String augmentName;
	private final String weaponName;
	private final boolean revealMap;
	private final EventAutoReward eventAutoReward;
	private final List<EventChoice> choices;
	private final Weapon weapon;

	public Event(EventType eventType, String name, boolean unique, Text eventText, boolean repair, boolean fleet,
			EventDamage eventDamage, EventImg eventImg, EventBoarders eventBoarders, boolean secretSector,
			boolean itemModify, EventShip eventShip, Event eventQuest, String remove, int modifyPoursuit, String augmentName, String weaponName, boolean revealMap, EventAutoReward eventAutoReward, List<EventChoice> choices, Weapon weapon)
			throws EventCreationException {
		if (eventType == null) {
			throw new EventCreationException("Event type cannot be null or empty, error!");
		}

		if (eventType == EventType.LOAD) {
			// If loading an event, the name cannot be null!
			if (name == null || name.isBlank()) {
				throw new EventCreationException("Event load cannot be null or empty, error!");
			}
		}

		this.eventType = eventType;
		this.name = name == null ? "" : name;
		this.unique = unique;

		// Event text is mandatory
		this.eventText = eventText == null ? Text.EMPTY : eventText;
		this.repair = repair;
		this.fleet = fleet;
		this.eventDamage = eventDamage;
		this.eventImg = eventImg;
		this.eventBoarders = eventBoarders;
		this.secretSector = secretSector;
		this.itemModify = itemModify;
		this.eventShip = eventShip;
		this.eventQuest = eventQuest;
		this.remove = remove == null ? "" : remove;
		this.modifyPoursuit = modifyPoursuit;
		this.augmentName = augmentName == null ? "" : augmentName;
		this.weaponName = weaponName == null ? "" : weaponName;
		this.revealMap = revealMap;
		this.eventAutoReward = eventAutoReward;
		this.choices = List.copyOf(choices);
		this.weapon = weapon;
	}

	private Event() {
		this(EventType.NONE, "", false, Text.EMPTY, false, false, null, null, null, false, false, null, null, "",0, "", "", false, null,
				List.<EventChoice>of(), Weapon.DEFAULT_WEAPON);
	}

	@Override
	public XmlTag<?> toXmlTag() {
		if (eventType == EventType.NONE) {
			return new XmlTag<Void>(EVENT_TAG_NAME);
		}

		if (eventType == EventType.LOAD) {
			return new XmlTag<Void>(EVENT_TAG_NAME, new Attribute(LOAD_ATTRIBUTE_NAME, name));
		}

		// From here normal event
		List<Attribute> attributes = new ArrayList<Attribute>();
		if (!name.isBlank()) {
			attributes.add(new Attribute(NAME_ATTRIBUTE_NAME, name));
		}

		if (unique) {
			attributes.add(new Attribute(UNIQUE_ATTRIBUTE_NAME, Boolean.toString(unique)));
		}

		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		tags.add(eventText.toXmlTag());

		if (!repair) {
			tags.add(new XmlTag<Void>(REPAIR_TAG_NAME));
		}

		if (!fleet) {
			tags.add(new XmlTag<Void>(FLEET_TAG_NAME));
		}

		if (eventDamage != null) {
			tags.add(eventDamage.toXmlTag());
		}

		if (eventImg != null) {
			tags.add(eventImg.toXmlTag());
		}

		if (eventBoarders != null) {
			tags.add(eventBoarders.toXmlTag());
		}

		if (secretSector) {
			tags.add(new XmlTag<Void>(SECRET_SECTOR_TAG_NAME));
		}

		if (itemModify) {
			tags.add(new XmlTag<Void>(ITEM_MODIFY_TAG_NAME,
					new Attribute(STEAL_ATTRIBUTE_NAME, Boolean.toString(itemModify))));
		}
		
		if (!remove.isBlank()) {
			tags.add(new XmlTag<Void>(REMOVE_TAG_NAME, new Attribute(NAME_ATTRIBUTE_NAME, remove)));
		}

		if (eventShip != null) {
			tags.add(eventShip.toXmlTag());
		}

		if (eventQuest != null) {
			tags.add(new XmlTag<Void>(QUEST_TAG_NAME, new Attribute(EVENT_ATTRIBUTE_NAME, eventQuest.name)));
		}
		
		if (modifyPoursuit != 0) {
			tags.add(new XmlTag<Void>(MODIFY_POURSUIT_TAG_NAME, new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(modifyPoursuit))));
		}
		
		if (!augmentName.isBlank()) {
			tags.add(new XmlTag<Void>(AUGMENT_TAG_NAME, new Attribute(NAME_ATTRIBUTE_NAME, augmentName)));
		}
		
		if (!weaponName.isBlank()) {
			tags.add(new XmlTag<Void>(WEAPON_TAG_NAME, new Attribute(NAME_ATTRIBUTE_NAME, weaponName)));
		}
		
		if (revealMap) {
			tags.add(new XmlTag<Void>(REVEAL_MAP_TAG_NAME));
		}
		
		if (eventAutoReward != null) {
			tags.add(eventAutoReward.toXmlTag());
		}

		if (!choices.isEmpty()) {
			tags.addAll(choices.stream().map(eventChoice -> eventChoice.toXmlTag()).toList());
		}

		if (weapon != null) {
			tags.add(weapon.toXmlTag());
		}

		return new XmlTag<List<XmlTag<?>>>(EVENT_TAG_NAME, tags, attributes.toArray(Attribute[]::new));
	}

}
