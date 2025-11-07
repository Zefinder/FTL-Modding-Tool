package net.zefinder.ftlmod.event;

import net.zefinder.ftlmod.game.Background;
import net.zefinder.ftlmod.game.Planet;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventImg(Planet planet, Background back) implements XmlObject {

	public static final String IMG_TAG_NAME = "img";
	public static final String PLANET_ATTRIBUTE_NAME = "planet";
	public static final String BACKGROUND_ATTRIBUTE_NAME = "back";

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(IMG_TAG_NAME, new Attribute(PLANET_ATTRIBUTE_NAME, planet.name()),
				new Attribute(BACKGROUND_ATTRIBUTE_NAME, back.name()));
	}

}
