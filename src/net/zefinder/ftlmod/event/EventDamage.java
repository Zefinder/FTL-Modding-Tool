package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.game.DamageEffect;
import net.zefinder.ftlmod.game.ShipSystem;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventDamage(int amount, ShipSystem system, DamageEffect effect) implements XmlObject {

	public EventDamage(int amount, ShipSystem system, DamageEffect effect) {
		this.amount = amount;
		this.system = system == null ? ShipSystem.NONE : system;

		// If the ship system is none, then effect must be none
		if (system == ShipSystem.NONE) {
			this.effect = DamageEffect.NONE;
		} else {
			this.effect = effect == null ? DamageEffect.NONE : effect;
		}
	}

	public EventDamage(int amount) {
		this(amount, ShipSystem.NONE, DamageEffect.NONE);
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(amount)));
		if (system != ShipSystem.NONE) {
			attributes.add(new Attribute(SYSTEM_ATTRIBUTE_NAME, system.systemName()));

			if (effect != DamageEffect.NONE) {
				attributes.add(new Attribute(EFFECT_ATTRIBUTE_NAME, effect.effectName()));
			}
		}

		return new XmlTag<Void>(DAMAGE_TAG_NAME, attributes.toArray(Attribute[]::new));
	}

}
