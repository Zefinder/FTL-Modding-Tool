package net.zefinder.ftlmod.event;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;

public class EventChoice implements XmlObject {

	/*
	 * True => don't show the rewards
	 */
	private final boolean hidden;
	private final int level;
	private final int minLevel;
	private final int maxLevel;
	private final boolean blue;
	private final int maxGroup;
	private final String req;

	private Event event;
	private EventText text;
	
	public EventChoice(final boolean hidden, final int level, final int minLevel, final int maxLevel,
			final int maxGroup, final boolean blue, final String req) {
		this.hidden = hidden;
		this.level = level;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.maxGroup = maxGroup;
		this.blue = blue;
		this.req = req;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		
		
		return null;
	}
	
}
