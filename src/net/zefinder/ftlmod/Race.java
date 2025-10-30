package net.zefinder.ftlmod;

public enum Race {
	HUMAN("human"), ENGI("engi"), MANTIS("mantis"), SLUG("slug"), ZOLTAN("energy"), ROCK("rock"), LANIUS("anaerobic"),
	CRYSTAL("crystal"), GHOST("ghost");

	private final String race;

	private Race(final String race) {
		this.race = race;
	}

	public String race() {
		return race;
	}

	public static final Race fromString(String race) {
		return switch (race) {
		case "human" -> HUMAN;
		case "engi" -> ENGI;
		case "mantis" -> MANTIS;
		case "slug" -> SLUG;
		case "energy" -> ZOLTAN;
		case "rock" -> ROCK;
		case "anaerobic" -> LANIUS;
		case "crystal" -> CRYSTAL;
		case "ghost" -> GHOST;
		default -> HUMAN;
		};
	}
}
