package net.zefinder.ftlmod.weapon;

import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record WeaponBeamColor(int r, int g, int b) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		XmlTag<Integer> rTag = new XmlTag<Integer>("r", r);
		XmlTag<Integer> gTag = new XmlTag<Integer>("g", g);
		XmlTag<Integer> bTag = new XmlTag<Integer>("b", b);

		return new XmlTag<List<XmlTag<Integer>>>("color", List.of(rTag, gTag, bTag));
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}

}
