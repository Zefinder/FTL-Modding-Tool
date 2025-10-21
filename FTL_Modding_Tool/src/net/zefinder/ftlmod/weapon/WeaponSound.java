package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.weapon.Weapon.SOUND_TAG_NAME;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record WeaponSound(String soundReference) implements XmlObject {
	
	public WeaponSound(String soundReference) {
		if (soundReference == null) {
			soundReference = "";
		}
		
		this.soundReference = soundReference;
	}
	
	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<String>(SOUND_TAG_NAME, soundReference);
	}
	
	@Override
	public final String toString() {
		return toXmlTag().toString();
	}
	
}
