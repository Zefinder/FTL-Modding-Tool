package net.zefinder.ftlmod;

public enum System {
	// Primary
	PILOT("pilot"), BATTERY("battery"), SENSORS("sensors"), DOORS("doors"),

	// Secondary
	REACTOR("reactor"), SHIELDS("shields"), ENGINES("engines"), OXYGEN("oxygen"), MEDBAY("medbay"),
	CLONEBAY("clonebay"), HACKING("hacking"), MIND("mind"), CLOAKING("cloaking"), TELEPORTER("teleporter"),
	WEAPONS("weapons"), DRONES("drones");

	private final String systemName;

	private System(final String systemName) {
		this.systemName = systemName;
	}

	public String systemName() {
		return systemName;
	}
	
	public static final System fromString(String name) {
		return switch (name) {
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
		default -> REACTOR; // Reactor seems to be a reasonable fallback
		};
	}
}
