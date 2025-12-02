package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import net.zefinder.ftlmod.game.DamageEffect;
import net.zefinder.ftlmod.game.ShipSystem;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventDamage(int amount, ShipSystem system, DamageEffect effect) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(DAMAGE_TAG_NAME, new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(amount)),
				new Attribute(SYSTEM_ATTRIBUTE_NAME, system.systemName()),
				new Attribute(EFFECT_ATTRIBUTE_NAME, effect.effectName()));
	}

}
