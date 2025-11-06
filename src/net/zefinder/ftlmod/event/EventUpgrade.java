package net.zefinder.ftlmod.event;

import net.zefinder.ftlmod.ShipSystem;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventUpgrade(int amount, ShipSystem system) implements XmlObject {

	public static final String UPGRADE_TAG_NAME = "upgrade";
	public static final String AMOUNT_ATTRIBUTE_NAME = "amount";
	public static final String SYSTEM_ATTRIBUTE_NAME = "system";

	public EventUpgrade(int amount, ShipSystem system) {
		this.amount = amount < 1 ? 1 : amount;
		this.system = system == null || system == ShipSystem.ROOM ? ShipSystem.REACTOR : system;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(UPGRADE_TAG_NAME, new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(amount)),
				new Attribute(SYSTEM_ATTRIBUTE_NAME, system.systemName()));
	}
	
}
