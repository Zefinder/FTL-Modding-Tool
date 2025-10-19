package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.weapon.Weapon.BOOST_AMOUNT_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.BOOST_COUNT_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.BOOST_TYPE_TAG_NAME_STRING;
import static net.zefinder.ftlmod.weapon.Weapon.WEAPON_BOOST_TAG_NAME_STRING;

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

		XmlTag<WeaponBoostType> weaponBoostTypeTag = new XmlTag<WeaponBoostType>(BOOST_TYPE_TAG_NAME_STRING, weaponBoostType);
		XmlTag<Integer> amountTag = new XmlTag<Integer>(BOOST_AMOUNT_TAG_NAME_STRING, amount);
		XmlTag<Integer> countTag = new XmlTag<Integer>(BOOST_COUNT_TAG_NAME_STRING, count);

		return new XmlTag<List<XmlTag<?>>>(WEAPON_BOOST_TAG_NAME_STRING, List.of(weaponBoostTypeTag, amountTag, countTag));
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}

}
