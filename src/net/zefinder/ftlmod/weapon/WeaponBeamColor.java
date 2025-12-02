package net.zefinder.ftlmod.weapon;

import static net.zefinder.ftlmod.Consts.*;
import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record WeaponBeamColor(int r, int g, int b) implements XmlObject {

	@Override
	public XmlTag<?> toXmlTag() {
		XmlTag<Integer> rTag = new XmlTag<Integer>(R_TAG_NAME, r);
		XmlTag<Integer> gTag = new XmlTag<Integer>(G_TAG_NAME, g);
		XmlTag<Integer> bTag = new XmlTag<Integer>(B_TAG_NAME, b);

		return new XmlTag<List<XmlTag<Integer>>>(COLOR_TAG_NAME, List.of(rTag, gTag, bTag));
	}

	@Override
	public final String toString() {
		return toXmlTag().toString();
	}

}
