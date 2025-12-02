package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import net.zefinder.ftlmod.game.Race;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventBoarders(boolean breach, int min, int max, Race race) implements XmlObject {



	public EventBoarders(boolean breach, int min, int max, Race race) {
		this.breach = breach;
		this.min = min;
		this.max = max;
		this.race = race == null ? Race.HUMAN : race;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(BOARDERS_TAG_NAME, new Attribute(BREACH_ATTRIBUTE_NAME, Boolean.toString(breach)),
				new Attribute(MIN_ATTRIBUTE_NAME, Integer.toString(min)),
				new Attribute(MAX_ATTRIBUTE_NAME, Integer.toString(max)),
				new Attribute(CLASS_ATTRIBUTE_NAME, race.raceName()));
	}

}
