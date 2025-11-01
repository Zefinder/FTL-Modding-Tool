package net.zefinder.ftlmod.event;

import net.zefinder.ftlmod.DamageEffect;
import net.zefinder.ftlmod.ShipSystem;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventDamage(int amount, ShipSystem system, DamageEffect effect) implements XmlObject {

	public static final String DAMAGE_TAG_NAME = "damage";
	public static final String AMOUNT_ATTRIBUTE_NAME = "amount";
	public static final String SYSTEM_ATTRIBUTE_NAME = "system";
	public static final String EFFECT_ATTRIBUTE_NAME = "effect";

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(DAMAGE_TAG_NAME, new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(amount)),
				new Attribute(SYSTEM_ATTRIBUTE_NAME, system.systemName()),
				new Attribute(EFFECT_ATTRIBUTE_NAME, effect.effectName()));
	}

}
