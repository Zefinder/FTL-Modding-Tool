package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.Consts.*;

import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record WeaponBoost(WeaponBoostType weaponBoostType, int amount, int count) implements XmlObject {

	static final WeaponBoost EMPTY = new WeaponBoost(null, 0, 0);
	
	@Override
	public XmlTag<?> toXmlTag() {
		if (weaponBoostType == null) {
			return XmlTag.empty();
		}

		XmlTag<WeaponBoostType> weaponBoostTypeTag = new XmlTag<WeaponBoostType>(TYPE_TAG_NAME, weaponBoostType);
		XmlTag<Integer> amountTag = new XmlTag<Integer>(AMOUNT_TAG_NAME, amount);
		XmlTag<Integer> countTag = new XmlTag<Integer>(COUNT_TAG_NAME, count);

		return new XmlTag<List<XmlTag<?>>>(WEAPON_BOOST_TAG_NAME, List.of(weaponBoostTypeTag, amountTag, countTag));
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}

}
