package net.zefinder.ftlmod;

public enum ShipSystem {
	// Random system, is not a true system BUT is used for some events
	RANDOM("random"),
	
	// Primary
	PILOT("pilot"), BATTERY("battery"), SENSORS("sensors"), DOORS("doors"),

	// Secondary
	REACTOR("reactor"), SHIELDS("shields"), ENGINES("engines"), OXYGEN("oxygen"), MEDBAY("medbay"),
	CLONEBAY("clonebay"), HACKING("hacking"), MIND("mind"), CLOAKING("cloaking"), TELEPORTER("teleporter"),
	WEAPONS("weapons"), DRONES("drones"),
	
	// Empty room
	ROOM("room");

	private final String systemName;

	private ShipSystem(final String systemName) {
		this.systemName = systemName;
	}

	public String systemName() {
		return systemName;
	}
	
	public static final ShipSystem fromString(String name) {
		if (name == null) {
			return ROOM;
		}
		
		return switch (name) {
		case "random" -> RANDOM;
		case "pilot" -> PILOT;
		case "battery" -> BATTERY;
		case "sensors" -> SENSORS;
		case "doors" -> DOORS;
		case "reactor" -> REACTOR;
		case "shields" -> SHIELDS;
		case "engines" -> ENGINES;
		case "oxygen" -> OXYGEN;
		case "medbay" -> MEDBAY;
		case "clonebay" -> CLONEBAY;
		case "hacking" -> HACKING;
		case "mind" -> MIND;
		case "cloaking" -> CLOAKING;
		case "teleporter" -> TELEPORTER;
		case "weapons" -> WEAPONS;
		case "drones" -> DRONES;
		case "room" -> ROOM;
		default -> ROOM; // Empty room as fallback
		};
	}
}
