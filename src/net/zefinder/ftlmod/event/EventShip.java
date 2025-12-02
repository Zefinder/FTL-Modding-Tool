package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventShip(String name, boolean hostile) implements XmlObject {

	public EventShip(String name, boolean hostile) {
		this.name = name == null ? "" : name;
		this.hostile = hostile;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		if (!name.isBlank()) {
			attributes.add(new Attribute(LOAD_ATTRIBUTE_NAME, name));
		}
		attributes.add(new Attribute(HOSTILE_ATTRIBUTE_NAME, Boolean.toString(hostile)));

		return new XmlTag<Void>(SHIP_TAG_NAME, attributes.toArray(Attribute[]::new));
	}

}
