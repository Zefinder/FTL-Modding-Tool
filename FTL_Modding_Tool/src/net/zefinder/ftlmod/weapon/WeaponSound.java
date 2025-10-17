package net.zefinder.ftlmod.weapon;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record WeaponSound(String soundReference) implements XmlObject {
	
	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<String>("sound", soundReference);
	}
	
	@Override
	public final String toString() {
		return toXmlTag().toString();
	}
	
}
