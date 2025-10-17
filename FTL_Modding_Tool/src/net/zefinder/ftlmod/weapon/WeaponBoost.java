package net.zefinder.ftlmod.weapon;

import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record WeaponBoost(WeaponBoostType weaponBoostType, int amount, int count) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		XmlTag<WeaponBoostType> weaponBoostTypeTag = new XmlTag<WeaponBoostType>("type", weaponBoostType);
		XmlTag<Integer> amountTag = new XmlTag<Integer>("amount", amount);
		XmlTag<Integer> countTag = new XmlTag<Integer>("count", count);

		return new XmlTag<List<XmlTag<?>>>("boost", List.of(weaponBoostTypeTag, amountTag, countTag));
	}
	
	@Override
	public final String toString() {
		return toXmlTag().toString();
	}

}
