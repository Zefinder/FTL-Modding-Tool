package net.zefinder.ftlmod.weapon;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record WeaponProjectile(int count, boolean fake, String projectileImageReference) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		Attribute countAttribute = new Attribute("count", String.valueOf(count));
		Attribute fakeAttribute = new Attribute("fake", String.valueOf(fake));

		return new XmlTag<String>("projectile", projectileImageReference, countAttribute, fakeAttribute);
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}
	
}
