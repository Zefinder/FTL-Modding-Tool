package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventChoice(boolean hidden, int level, int minLevel, int maxLevel, int maxGroup, boolean blue, String req,
		Event event, Text text) implements XmlObject {

	/*
	 * hidden true => don't show the rewards
	 */

	public EventChoice(final boolean hidden, final int level, final int minLevel, final int maxLevel,
			final int maxGroup, final boolean blue, final String req, final Event event, final Text text) {
		this.hidden = hidden;
		this.level = level < 0 ? 0 : level;
		this.minLevel = minLevel < 0 ? 0 : minLevel;
		this.maxLevel = maxLevel < 0 ? 0 : maxLevel;
		this.maxGroup = maxGroup < 0 ? 0 : maxGroup > 1 ? 1 : maxGroup;
		this.blue = blue;
		this.req = req == null ? "" : req; // TODO More checks on req when systems and weapons ok

		if (event == null) {
			throw new EventChoiceCreationException("Event must not be null when creating an event choice");
		}
		this.event = event;

		if (text == null || text.text().isBlank()) {
			throw new EventChoiceCreationException(
					"Choice text must not be null (nor blank) when creating an event choice");
		}
		this.text = text;
	}

	public EventChoice(EventChoiceBuilder builder) {
		this(builder.isHidden(), builder.getLevel(), builder.getMinLevel(), builder.getMaxLevel(),
				builder.getMaxGroup(), builder.isBlue(), builder.getReq(), builder.getEvent(), builder.getText());
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute(HIDDEN_ATTRIBUTE_NAME, Boolean.toString(hidden)));

		if (level > 0) {
			attributes.add(new Attribute(LVL_ATTRIBUTE_NAME, Integer.toString(level)));
		}

		if (minLevel > 0) {
			attributes.add(new Attribute(MIN_LEVEL_ATTRIBUTE_NAME, Integer.toString(minLevel)));
		}

		if (maxLevel > 0) {
			attributes.add(new Attribute(MAX_LEVEL_ATTRIBUTE_NAME, Integer.toString(maxLevel)));
		}

		if (maxGroup > 0) {
			attributes.add(new Attribute(MAX_GROUP_ATTRIBUTE_NAME, Integer.toString(maxGroup)));
		}

		if (blue) {
			attributes.add(new Attribute(BLUE_ATTRIBUTE_NAME, Boolean.toString(blue)));
		}

		if (!req.isBlank()) {
			attributes.add(new Attribute(REQ_ATTRIBUTE_NAME, req));
		}

		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		tags.add(event.toXmlTag());
		tags.add(text.toXmlTag());

		return new XmlTag<List<XmlTag<?>>>(CHOICE_TAG_NAME, tags, attributes.toArray(Attribute[]::new));
	}

}
