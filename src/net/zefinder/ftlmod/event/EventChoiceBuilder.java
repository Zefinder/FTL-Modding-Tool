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

	public void setHidden(final boolean hidden) {
		this.hidden = hidden;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public void setMinLevel(final int minLevel) {
		this.minLevel = minLevel;
	}

	public void setMaxLevel(final int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public void setBlue(final boolean blue) {
		this.blue = blue;
	}

	public void setMaxGroup(final int maxGroup) {
		this.maxGroup = maxGroup;
	}

	public void setReq(final String req) {
		this.req = req;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}

	public void setText(final Text text) {
		this.text = text;
	}
	
	@Override
	public EventChoice build() {
		return new EventChoice(this);
	}
	
}
