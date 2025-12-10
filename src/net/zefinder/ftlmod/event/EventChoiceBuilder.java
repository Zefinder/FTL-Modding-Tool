package net.zefinder.ftlmod.event;

import net.zefinder.ftlmod.Builder;
import net.zefinder.ftlmod.text.Text;

public class EventChoiceBuilder implements Builder {

	private boolean hidden;
	private int level;
	private int minLevel;
	private int maxLevel;
	private boolean blue;
	private int maxGroup;
	private String req;

	private Event event;
	private Text text;

	public EventChoiceBuilder() {
		hidden = false;
		level = 0;
		minLevel = 0;
		maxLevel = 0;
		maxGroup = 0;
		req = "";

		event = Event.EMPTY;
		text = Text.EMPTY;
	}

	public boolean isHidden() {
		return hidden;
	}

	public int getLevel() {
		return level;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public boolean isBlue() {
		return blue;
	}

	public int getMaxGroup() {
		return maxGroup;
	}

	public String getReq() {
		return req;
	}

	public Event getEvent() {
		return event;
	}

	public Text getText() {
		return text;
	}

	public EventChoiceBuilder setHidden(final boolean hidden) {
		this.hidden = hidden;
		return this;
	}

	public EventChoiceBuilder setLevel(final int level) {
		this.level = level;
		return this;
	}

	public EventChoiceBuilder setMinLevel(final int minLevel) {
		this.minLevel = minLevel;
		return this;
	}

	public EventChoiceBuilder setMaxLevel(final int maxLevel) {
		this.maxLevel = maxLevel;
		return this;
	}

	public EventChoiceBuilder setBlue(final boolean blue) {
		this.blue = blue;
		return this;
	}

	public EventChoiceBuilder setMaxGroup(final int maxGroup) {
		this.maxGroup = maxGroup;
		return this;
	}

	public EventChoiceBuilder setReq(final String req) {
		this.req = req;
		return this;
	}

	public EventChoiceBuilder setEvent(final Event event) {
		this.event = event;
		return this;
	}

	public EventChoiceBuilder setText(final Text text) {
		this.text = text;
		return this;
	}

	@Override
	public EventChoice build() {
		return new EventChoice(this);
	}

}
