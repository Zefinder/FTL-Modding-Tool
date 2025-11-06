package net.zefinder.ftlmod.event;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.Crew;
import net.zefinder.ftlmod.Race;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventCrewMember(int amount, Crew crew) implements XmlObject {

	public static final String CREW_MEMBER_TAG_NAME = "crewMember";
	public static final String AMOUNT_ATTRIBUTE_NAME = "amount";
	public static final String ID_ATTRIBUTE_NAME = "id";
	public static final String CLASS_ATTRIBUTE_NAME = "class";

	public static final String ALL_SKILLS_ATTRIBUTE_NAME = "all_skills";
	public static final String PILOT_ATTRIBUTE_NAME = "pilot";
	public static final String SHIELDS_ATTRIBUTE_NAME = "shields";
	public static final String ENGINES_ATTRIBUTE_NAME = "engines";
	public static final String COMBAT_ATTRIBUTE_NAME = "combat";
	public static final String REPAIR_ATTRIBUTE_NAME = "repair";
	public static final String WEAPONS_ATTRIBUTE_NAME = "weapons";

	public EventCrewMember(int amount, Crew crew) {
		this.amount = amount;
		this.crew = crew == null ? new Crew() : crew;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(amount)));

		if (!crew.nameRef().isBlank()) {
			attributes.add(new Attribute(ID_ATTRIBUTE_NAME, crew.nameRef()));
		}

		if (crew.race() != Race.NONE) {
			attributes.add(new Attribute(CLASS_ATTRIBUTE_NAME, crew.race().raceName()));
		}

		if (crew.allSkills() != 0) {
			attributes.add(new Attribute(ALL_SKILLS_ATTRIBUTE_NAME, Integer.toString(crew.allSkills())));
		} else {
			if (crew.pilot() != 0) {
				attributes.add(new Attribute(PILOT_ATTRIBUTE_NAME, Integer.toString(crew.pilot())));
			}

			if (crew.shields() != 0) {
				attributes.add(new Attribute(SHIELDS_ATTRIBUTE_NAME, Integer.toString(crew.shields())));
			}

			if (crew.engines() != 0) {
				attributes.add(new Attribute(ENGINES_ATTRIBUTE_NAME, Integer.toString(crew.engines())));
			}

			if (crew.combat() != 0) {
				attributes.add(new Attribute(COMBAT_ATTRIBUTE_NAME, Integer.toString(crew.combat())));
			}

			if (crew.repair() != 0) {
				attributes.add(new Attribute(REPAIR_ATTRIBUTE_NAME, Integer.toString(crew.repair())));
			}

			if (crew.weapons() != 0) {
				attributes.add(new Attribute(WEAPONS_ATTRIBUTE_NAME, Integer.toString(crew.weapons())));
			}
		}

		return new XmlTag<Void>(CREW_MEMBER_TAG_NAME, attributes.toArray(Attribute[]::new));
	}

}
