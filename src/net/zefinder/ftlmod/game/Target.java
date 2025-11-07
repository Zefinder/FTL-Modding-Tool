package net.zefinder.ftlmod.game;

public enum Target {

	ALL("all"), ENEMY("enemy"), PLAYER("player");

	private final String targetName;

	private Target(final String targetName) {
		this.targetName = targetName;
	}

	public String targetName() {
		return targetName;
	}

	public static final Target fromString(final String name) {
		if (name == null) {
			return ALL;
		}

		return switch (name) {
		case "all" -> ALL;
		case "enemy" -> ENEMY;
		case "player" -> PLAYER;
		default -> ALL;
		};
	}

}
