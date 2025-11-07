package net.zefinder.ftlmod.event;

public final class EventManager {

	private static final EventManager instance = new EventManager();
	
	
	private EventManager() {
	}
	
	public static final EventManager getInstance() {
		return instance;
	}

}
