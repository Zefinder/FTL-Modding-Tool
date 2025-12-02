package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public record EventRemoveCrew(boolean canReclone, Text cloneText) implements XmlObject {	
	
	public EventRemoveCrew(boolean canReclone, Text cloneText) {
		this.canReclone = canReclone;
		this.cloneText = cloneText == null ? Text.EMPTY : cloneText;
	}
	
	@Override
	public XmlTag<?> toXmlTag() {
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		tags.add(new XmlTag<Boolean>(CLONE_TAG_NAME, canReclone));
		tags.add(cloneText.toXmlTag());
		
		return new XmlTag<List<XmlTag<?>>>(REMOVE_CREW_TAG_NAME, tags);
	}
	
}
