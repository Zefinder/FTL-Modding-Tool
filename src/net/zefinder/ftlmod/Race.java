package net.zefinder.ftlmod;

public enum Race {
	NONE(""),
	
	HUMAN("human"), ENGI("engi"), MANTIS("mantis"), SLUG("slug"), ZOLTAN("energy"), ROCK("rock"), LANIUS("anaerobic"),
	CRYSTAL("crystal"), GHOST("ghost"), 
	/** This one is used when one crew becomes hostile */
	TRAITOR("traitor");

	private final String race;

	private Race(final String race) {
		this.race = race;
	}

	public String raceName() {
		return race;
	}

	public static final Race fromString(String race) {
		if (race == null) {
			return HUMAN;
		}
		
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
		case "traitor" -> TRAITOR;
		default -> NONE;
		};
	}
}
