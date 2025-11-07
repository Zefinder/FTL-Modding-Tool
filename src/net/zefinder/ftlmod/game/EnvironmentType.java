package net.zefinder.ftlmod.game;

public enum EnvironmentType {

	STORM("storm"), SUN("sun"), NEBULA("nebula"), PDS("PDS"), ASTEROID("asteroid"), PULSAR("pulsar");

	private final String typeName;

	private EnvironmentType(final String typeName) {
		this.typeName = typeName;
	}

	public String typeName() {
		return typeName;
	}

	public static final EnvironmentType fromString(final String type) {
		if (type == null) {
			return SUN;
		}
		
		return switch (type) {
		case "storm" -> STORM;
		case "sun" -> SUN;
		case "nebula" -> NEBULA;
		case "PDS" -> PDS;
		case "asteroid" -> ASTEROID;
		case "pulsar" -> PULSAR;
		default -> SUN;
		};
	}

}
