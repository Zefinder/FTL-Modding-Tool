package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.weapon.Weapon.PROJECTILE_TAG_NAME_STRING;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record WeaponProjectile(int count, boolean fake, String projectileImageReference) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		Attribute countAttribute = new Attribute("count", String.valueOf(count));
		Attribute fakeAttribute = new Attribute("fake", String.valueOf(fake));

		return new XmlTag<String>(PROJECTILE_TAG_NAME_STRING, projectileImageReference, countAttribute, fakeAttribute);
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}
	
}
