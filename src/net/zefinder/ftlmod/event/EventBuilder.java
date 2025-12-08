package net.zefinder.ftlmod.event;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.Builder;
import net.zefinder.ftlmod.text.Text;

public class EventBuilder implements Builder {

	private EventType eventType;
	private String name;
	private boolean unique;

	private Text eventText;
	private boolean repair;
	private EventFleetType fleet;
	private List<EventDamage> eventDamages;
	private EventImg eventImg;
	private EventBoarders eventBoarders;
	private boolean secretSector;
	private EventItemModify eventItemModify;
	private EventShip eventShip;
	private Event eventQuest;
	private String remove;
	private int modifyPoursuit;
	private String augmentName;
	private String weaponName;
	private boolean revealMap;
	private EventAutoReward eventAutoReward;
	private EventUpgrade eventUpgrade;
	private int unlockShip;
	private boolean store;
	private String droneName;
	private boolean distressBeacon;
	private EventEnvironment eventEnvironment;
	private EventRemoveCrew eventRemoveCrew;
	private EventCrewMember eventCrewMember;
	private EventStatus eventStatus;
	private List<EventChoice> choices;

	public EventBuilder() {
		this.eventType = EventType.NONE;
		this.name = "";
		this.unique = false;
		this.eventText = Text.EMPTY;
		this.repair = false;
		this.fleet = EventFleetType.NONE;
		this.eventDamages = new ArrayList<EventDamage>();
		this.eventImg = null;
		this.eventBoarders = null;
		this.secretSector = false;
		this.eventItemModify = null;
		this.eventShip = null;
		this.eventQuest = null;
		this.remove = "";
		this.modifyPoursuit = 0;
		this.augmentName = "";
		this.weaponName = "";
		this.revealMap = false;
		this.eventAutoReward = null;
		this.eventUpgrade = null;
		this.unlockShip = -1;
		this.store = false;
		this.droneName = "";
		this.distressBeacon = false;
		this.eventEnvironment = null;
		this.eventRemoveCrew = null;
		this.eventCrewMember = null;
		this.eventStatus = null;
		this.choices = new ArrayList<EventChoice>();
	}

	public EventType getEventType() {
		return eventType;
	}

	public String getName() {
		return name;
	}

	public boolean isUnique() {
		return unique;
	}

	public Text getEventText() {
		return eventText;
	}

	public boolean isRepair() {
		return repair;
	}

	public EventFleetType getFleet() {
		return fleet;
	}

	public List<EventDamage> getEventDamages() {
		return eventDamages;
	}

	public EventImg getEventImg() {
		return eventImg;
	}

	public EventBoarders getEventBoarders() {
		return eventBoarders;
	}

	public boolean isSecretSector() {
		return secretSector;
	}

	public EventItemModify getEventItemModify() {
		return eventItemModify;
	}

	public EventShip getEventShip() {
		return eventShip;
	}

	public Event getEventQuest() {
		return eventQuest;
	}

	public String getRemove() {
		return remove;
	}

	public int getModifyPoursuit() {
		return modifyPoursuit;
	}

	public String getAugmentName() {
		return augmentName;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public boolean isRevealMap() {
		return revealMap;
	}

	public EventAutoReward getEventAutoReward() {
		return eventAutoReward;
	}

	public EventUpgrade getEventUpgrade() {
		return eventUpgrade;
	}

	public int getUnlockShip() {
		return unlockShip;
	}

	public boolean isStore() {
		return store;
	}

	public String getDroneName() {
		return droneName;
	}

	public boolean isDistressBeacon() {
		return distressBeacon;
	}

	public EventEnvironment getEventEnvironment() {
		return eventEnvironment;
	}

	public EventRemoveCrew getEventRemoveCrew() {
		return eventRemoveCrew;
	}

	public EventCrewMember getEventCrewMember() {
		return eventCrewMember;
	}

	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public List<EventChoice> getChoices() {
		return choices;
	}

	public EventBuilder setEventType(final EventType eventType) {
		this.eventType = eventType;
		return this;
	}

	public EventBuilder setName(final String name) {
		this.name = name;
		return this;
	}

	public EventBuilder setUnique(final boolean unique) {
		this.unique = unique;
		return this;
	}

	public EventBuilder setEventText(final Text eventText) {
		this.eventText = eventText;
		return this;
	}

	public EventBuilder setRepair(final boolean repair) {
		this.repair = repair;
		return this;
	}

	public EventBuilder setFleet(final EventFleetType fleet) {
		this.fleet = fleet;
		return this;
	}

	public EventBuilder addEventDamage(final EventDamage eventDamage) {
		this.eventDamages.add(eventDamage);
		return this;
	}

	public EventBuilder setEventImg(final EventImg eventImg) {
		this.eventImg = eventImg;
		return this;
	}

	public EventBuilder setEventBoarders(final EventBoarders eventBoarders) {
		this.eventBoarders = eventBoarders;
		return this;
	}

	public EventBuilder setSecretSector(final boolean secretSector) {
		this.secretSector = secretSector;
		return this;
	}

	public EventBuilder setEventItemModify(final EventItemModify eventItemModify) {
		this.eventItemModify = eventItemModify;
		return this;
	}

	public EventBuilder setEventShip(final EventShip eventShip) {
		this.eventShip = eventShip;
		return this;
	}

	public EventBuilder setEventQuest(final Event eventQuest) {
		this.eventQuest = eventQuest;
		return this;
	}

	public EventBuilder setRemove(final String remove) {
		this.remove = remove;
		return this;
	}

	public EventBuilder setModifyPoursuit(final int modifyPoursuit) {
		this.modifyPoursuit = modifyPoursuit;
		return this;
	}

	public EventBuilder setAugmentName(final String augmentName) {
		this.augmentName = augmentName;
		return this;
	}

	public EventBuilder setWeaponName(final String weaponName) {
		this.weaponName = weaponName;
		return this;
	}

	public EventBuilder setRevealMap(final boolean revealMap) {
		this.revealMap = revealMap;
		return this;
	}

	public EventBuilder setEventAutoReward(final EventAutoReward eventAutoReward) {
		this.eventAutoReward = eventAutoReward;
		return this;
	}

	public EventBuilder setEventUpgrade(final EventUpgrade eventUpgrade) {
		this.eventUpgrade = eventUpgrade;
		return this;
	}

	public EventBuilder setUnlockShip(final int unlockShip) {
		this.unlockShip = unlockShip;
		return this;
	}

	public EventBuilder setStore(final boolean store) {
		this.store = store;
		return this;
	}

	public EventBuilder setDroneName(final String droneName) {
		this.droneName = droneName;
		return this;
	}

	public EventBuilder setDistressBeacon(final boolean distressBeacon) {
		this.distressBeacon = distressBeacon;
		return this;
	}

	public EventBuilder setEventEnvironment(final EventEnvironment eventEnvironment) {
		this.eventEnvironment = eventEnvironment;
		return this;
	}

	public EventBuilder setEventRemoveCrew(final EventRemoveCrew eventRemoveCrew) {
		this.eventRemoveCrew = eventRemoveCrew;
		return this;
	}

	public EventBuilder setEventCrewMember(final EventCrewMember eventCrewMember) {
		this.eventCrewMember = eventCrewMember;
		return this;
	}

	public EventBuilder setEventStatus(final EventStatus eventStatus) {
		this.eventStatus = eventStatus;
		return this;
	}

	public EventBuilder setChoices(final List<EventChoice> choices) {
		this.choices = choices;
		return this;
	}

	@Override
	public Event build() throws EventCreationException {
		return new Event(this);
	}
	
}
