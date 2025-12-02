package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.game.Crew;
import net.zefinder.ftlmod.game.Race;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventCrewMember(int amount, Crew crew) implements XmlObject {

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
