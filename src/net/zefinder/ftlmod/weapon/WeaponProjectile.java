package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.Consts.*;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record WeaponProjectile(int count, boolean fake, String projectileImageReference) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		Attribute countAttribute = new Attribute(COUNT_ATTRIBUTE_NAME, String.valueOf(count));
		Attribute fakeAttribute = new Attribute(FAKE_ATTRIBUTE_NAME, String.valueOf(fake));

		return new XmlTag<String>(PROJECTILE_TAG_NAME, projectileImageReference, countAttribute, fakeAttribute);
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}
	
}
