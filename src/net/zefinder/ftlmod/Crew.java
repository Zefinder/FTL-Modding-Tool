package net.zefinder.ftlmod;

public record Crew(String nameRef, Race race, int allSkills, int pilot, int shields, int engines, int combat,
		int repair, int weapons) {

	public Crew() {
		this("", Race.NONE, 0, 0, 0, 0, 0, 0, 0);
	}

	public Crew(String nameRef) {
		this(nameRef, Race.NONE, 0, 0, 0, 0, 0, 0, 0);
	}

	public Crew(int allSkills) {
		this("", Race.NONE, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills);
	}

	public Crew(int pilot, int shields, int engines, int combat, int repair, int weapons) {
		this("", Race.NONE, 0, pilot, shields, engines, combat, repair, weapons);
	}

	public Crew(String nameRef, int allSkills) {
		this(nameRef, Race.NONE, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills);
	}

	public Crew(String nameRef, int pilot, int shields, int engines, int combat, int repair, int weapons) {
		this(nameRef, Race.NONE, 0, pilot, shields, engines, combat, repair, weapons);
	}

	public Crew(String nameRef, Race race) {
		this(nameRef, race, 0, 0, 0, 0, 0, 0, 0);
	}

	public Crew(Race race, int allSkills) {
		this("", race, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills);
	}

	public Crew(Race race, int pilot, int shields, int engines, int combat, int repair, int weapons) {
		this("", race, 0, pilot, shields, engines, combat, repair, weapons);
	}

	public Crew(String nameRef, Race race, int pilot, int shields, int engines, int combat, int repair, int weapons) {
		this(nameRef, race, 0, pilot, shields, engines, combat, repair, weapons);
	}

	public Crew(String nameRef, Race race, int allSkills) {
		this(nameRef, race, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills, allSkills);
	}

	public Crew(String nameRef, Race race, int allSkills, int pilot, int shields, int engines, int combat, int repair,
			int weapons) {
		this.nameRef = nameRef == null ? "" : nameRef;
		this.race = race == null ? Race.NONE : race;
		this.allSkills = allSkills < 0 ? 0 : allSkills > 2 ? 2 : allSkills;
		if (allSkills == 0) {
			this.pilot = pilot < 0 ? 0 : pilot > 2 ? 2 : pilot;
			this.shields = shields < 0 ? 0 : shields > 2 ? 2 : shields;
			this.engines = engines < 0 ? 0 : engines > 2 ? 2 : engines;
			this.combat = combat < 0 ? 0 : combat > 2 ? 2 : combat;
			this.repair = repair < 0 ? 0 : repair > 2 ? 2 : repair;
			this.weapons = weapons < 0 ? 0 : weapons > 2 ? 2 : weapons;
		} else {
			this.pilot = allSkills;
			this.shields = allSkills;
			this.engines = allSkills;
			this.combat = allSkills;
			this.repair = allSkills;
			this.weapons = allSkills;
		}
	}

}
