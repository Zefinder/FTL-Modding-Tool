package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.game.Background;
import net.zefinder.ftlmod.game.Planet;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventImg(Planet planet, Background back) implements XmlObject {

	public EventImg(Planet planet, Background back) {
		this.planet = planet == null ? Planet.NONE : planet;
		this.back = back == null ? Background.BACKGROUND : back;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		if (planet != Planet.NONE) {
			attributes.add(new Attribute(PLANET_ATTRIBUTE_NAME, planet.name()));
		}

		if (back != Background.BACKGROUND) {
			attributes.add(new Attribute(BACKGROUND_ATTRIBUTE_NAME, back.name()));
		}

		return new XmlTag<Void>(IMG_TAG_NAME, attributes.toArray(Attribute[]::new));
	}

}
