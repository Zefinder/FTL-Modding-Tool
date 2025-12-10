package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.NamedObject;
import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.text.TextType;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record Event(EventType eventType, String name, boolean unique, Text eventText, boolean repair, EventFleetType fleet,
		List<EventDamage> eventDamages, EventImg eventImg, EventBoarders eventBoarders, boolean secretSector,
		EventItemModify eventItemModify, EventShip eventShip, String eventQuest, String remove, int modifyPursuit,
		String augmentName, String weaponName, boolean revealMap, EventAutoReward eventAutoReward,
		EventUpgrade eventUpgrade, int unlockShip, boolean store, String droneName, boolean distressBeacon,
		EventEnvironment eventEnvironment, EventRemoveCrew eventRemoveCrew, EventCrewMember eventCrewMember,
		EventStatus eventStatus, List<EventChoice> choices) implements NamedObject, XmlObject {

	public static final Event EMPTY = new EventBuilder().setEventType(EventType.NONE).build();

	public Event(EventType eventType, String name, boolean unique, Text eventText, boolean repair, EventFleetType fleet,
			List<EventDamage> eventDamages, EventImg eventImg, EventBoarders eventBoarders, boolean secretSector,
			EventItemModify eventItemModify, EventShip eventShip, String eventQuest, String remove, int modifyPursuit,
			String augmentName, String weaponName, boolean revealMap, EventAutoReward eventAutoReward,
			EventUpgrade eventUpgrade, int unlockShip, boolean store, String droneName, boolean distressBeacon,
			EventEnvironment eventEnvironment, EventRemoveCrew eventRemoveCrew, EventCrewMember eventCrewMember,
			EventStatus eventStatus, List<EventChoice> choices) {
		if (eventType == null) {
			throw new EventCreationException("Event type cannot be null or empty, error!");
		}

		if (eventType == EventType.LOAD) {
			// If loading an event, the name cannot be null!
			if (name == null || name.isBlank()) {
				throw new EventCreationException("Event load cannot be null or empty, error!");
			}
		}

		if (eventType == EventType.NAMED) {
			// If creating a named event, the name cannot be null!
			if (name == null || name.isBlank()) {
				throw new EventCreationException("Event name cannot be null or empty, error!");
			}
		}

		this.eventType = eventType;
		this.name = name == null ? "" : name;
		this.unique = unique;
		this.eventText = eventText == null ? Text.EMPTY : eventText;
		this.repair = repair;
		this.fleet = fleet == null ? EventFleetType.NONE : fleet;
		this.eventDamages = eventDamages;
		this.eventImg = eventImg;
		this.eventBoarders = eventBoarders;
		this.secretSector = secretSector;
		this.eventItemModify = eventItemModify;
		this.eventShip = eventShip;
		this.eventQuest = eventQuest == null ? "" : eventQuest;
		this.remove = remove == null ? "" : remove;
		this.modifyPursuit = modifyPursuit;
		this.augmentName = augmentName == null ? "" : augmentName;
		this.weaponName = weaponName == null ? "" : weaponName;
		this.revealMap = revealMap;
		this.eventAutoReward = eventAutoReward;
		this.eventUpgrade = eventUpgrade;
		this.unlockShip = unlockShip;
		this.store = store;
		this.droneName = droneName == null ? "" : droneName;
		this.distressBeacon = distressBeacon;
		this.eventEnvironment = eventEnvironment;
		this.eventRemoveCrew = eventRemoveCrew;
		this.eventCrewMember = eventCrewMember;
		this.eventStatus = eventStatus;
		this.choices = List.copyOf(choices);
	}

	public Event(EventBuilder builder) {
		this(builder.getEventType(), builder.getName(), builder.isUnique(), builder.getEventText(), builder.isRepair(),
				builder.getFleet(), builder.getEventDamages(), builder.getEventImg(), builder.getEventBoarders(),
				builder.isSecretSector(), builder.getEventItemModify(), builder.getEventShip(), builder.getEventQuest(),
				builder.getRemove(), builder.getModifyPursuit(), builder.getAugmentName(), builder.getWeaponName(),
				builder.isRevealMap(), builder.getEventAutoReward(), builder.getEventUpgrade(), builder.getUnlockShip(),
				builder.isStore(), builder.getDroneName(), builder.isDistressBeacon(), builder.getEventEnvironment(),
				builder.getEventRemoveCrew(), builder.getEventCrewMember(), builder.getEventStatus(),
				builder.getChoices());
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

		// From here there must be a non empty name
		if (eventType == EventType.NAMED) {
			attributes.add(new Attribute(NAME_ATTRIBUTE_NAME, name));			
		}

		if (unique) {
			attributes.add(new Attribute(UNIQUE_ATTRIBUTE_NAME, Boolean.toString(unique)));
		}

		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		tags.add(eventText.toXmlTag());

		if (repair) {
			tags.add(new XmlTag<Void>(REPAIR_TAG_NAME));
		}

		if (fleet != EventFleetType.NONE) {
			tags.add(new XmlTag<String>(FLEET_TAG_NAME, fleet.type()));
		}

		if (!eventDamages.isEmpty()) {
			eventDamages.forEach(eventDamage -> tags.add(eventDamage.toXmlTag()));
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

		if (eventItemModify != null) {
			tags.add(eventItemModify.toXmlTag());
		}

		if (!remove.isBlank()) {
			tags.add(new XmlTag<Void>(REMOVE_TAG_NAME, new Attribute(NAME_ATTRIBUTE_NAME, remove)));
		}

		if (eventShip != null) {
			tags.add(eventShip.toXmlTag());
		}

		if (!eventQuest.isBlank()) {
			tags.add(new XmlTag<Void>(QUEST_TAG_NAME, new Attribute(EVENT_ATTRIBUTE_NAME, eventQuest)));
		}

		if (modifyPursuit != 0) {
			tags.add(new XmlTag<Void>(MODIFY_PURSUIT_TAG_NAME,
					new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(modifyPursuit))));
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

		if (eventUpgrade != null) {
			tags.add(eventUpgrade.toXmlTag());
		}

		if (unlockShip >= 0) {
			tags.add(new XmlTag<Void>(UNLOCK_SHIP_TAG_NAME,
					new Attribute(ID_ATTRIBUTE_NAME, Integer.toString(unlockShip))));
		}

		if (store) {
			tags.add(new XmlTag<Void>(STORE_TAG_NAME));
		}

		if (!droneName.isBlank()) {
			tags.add(new XmlTag<Void>(DRONE_TAG_NAME, new Attribute(NAME_ATTRIBUTE_NAME, droneName)));
		}

		if (distressBeacon) {
			tags.add(new XmlTag<Void>(DISTRESS_BEACON_TAG_NAME));
		}

		if (eventEnvironment != null) {
			tags.add(eventEnvironment.toXmlTag());
		}

		if (eventRemoveCrew != null) {
			tags.add(eventRemoveCrew.toXmlTag());
		}

		if (eventCrewMember != null) {
			tags.add(eventCrewMember.toXmlTag());
		}

		if (eventStatus != null) {
			tags.add(eventStatus.toXmlTag());
		}

		if (!choices.isEmpty()) {
			tags.addAll(choices.stream().map(eventChoice -> eventChoice.toXmlTag()).toList());
		}

		return new XmlTag<List<XmlTag<?>>>(EVENT_TAG_NAME, tags, attributes.toArray(Attribute[]::new));
	}

	public static void main(String[] args) {
		System.out.println(new EventBuilder().setEventType(EventType.NAMED)
				.setName("Test")
				.setEventText(new Text(TextType.NORMAL, "This is a test event")).setDroneName("drone_COMBAT1").build()
				.toXmlTag().toString());
	}

}
