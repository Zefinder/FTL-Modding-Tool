package net.zefinder.ftlmod.event;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.text.Text;
import net.zefinder.ftlmod.text.TextType;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventChoice(boolean hidden, int level, int minLevel, int maxLevel, int maxGroup, boolean blue, String req,
		Event event, Text text) implements XmlObject {

	public static final String CHOICE_TAG_NAME = "choice";
	public static final String CHOICE_HIDDEN_ATTRIBUTE = "hidden";
	public static final String CHOICE_LEVEL_ATTRIBUTE = "lvl";
	public static final String CHOICE_MIN_LEVEL_ATTRIBUTE = "min_lvl";
	public static final String CHOICE_MAX_LEVEL_ATTRIBUTE = "max_lvl";
	public static final String CHOICE_BLUE_ATTRIBUTE = "blue";
	public static final String CHOICE_MAX_GROUP_ATTRIBUTE = "max_group";
	public static final String CHOICE_REQ_ATTRIBUTE = "req";

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
		attributes.add(new Attribute(CHOICE_HIDDEN_ATTRIBUTE, Boolean.toString(hidden)));

		if (level > 0) {
			attributes.add(new Attribute(CHOICE_LEVEL_ATTRIBUTE, Integer.toString(level)));
		}

		if (minLevel > 0) {
			attributes.add(new Attribute(CHOICE_MIN_LEVEL_ATTRIBUTE, Integer.toString(minLevel)));
		}

		if (maxLevel > 0) {
			attributes.add(new Attribute(CHOICE_MAX_LEVEL_ATTRIBUTE, Integer.toString(maxLevel)));
		}

		if (maxGroup > 0) {
			attributes.add(new Attribute(CHOICE_MAX_GROUP_ATTRIBUTE, Integer.toString(maxGroup)));
		}

		if (blue) {
			attributes.add(new Attribute(CHOICE_BLUE_ATTRIBUTE, Boolean.toString(blue)));
		}

		if (!req.isBlank()) {
			attributes.add(new Attribute(CHOICE_REQ_ATTRIBUTE, req));
		}

		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		tags.add(event.toXmlTag());
		tags.add(text.toXmlTag());

		return new XmlTag<List<XmlTag<?>>>(CHOICE_TAG_NAME, tags, attributes.toArray(Attribute[]::new));
	}

	public static void main(String[] args) throws EventCreationException {
		var a = new EventChoice(true, 3, 0, 0, 0, true, "clonebay", new Event(EventType.LOAD, "test", false, null),
				new Text(TextType.NAMED, "This is a test"));
		System.out.println(a.toXmlTag());
	}

}
