package net.zefinder.ftlmod.event;

public enum EventFleetType {

	REBEL("rebel"), BATTLE("battle"), FED("fed"), NONE("");

	private final String type;

	private EventFleetType(final String type) {
		this.type = type;
	}

	public String type() {
		return type;
	}

	public static final EventFleetType fromString(final String type) {
		if (type == null) {
			return NONE;
		}

		return switch (type) {
		case "rebel" -> REBEL;
		case "battle" -> BATTLE;
		case "fed" -> FED;
		default -> NONE;
		};
	}

}
