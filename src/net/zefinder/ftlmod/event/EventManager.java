package net.zefinder.ftlmod.event;

import net.zefinder.ftlmod.ObjectManager;

public final class EventManager extends ObjectManager<Event> {

	private static final EventManager instance = new EventManager();
	
	public static final EventManager getInstance() {
		return instance;
	}
	
}
