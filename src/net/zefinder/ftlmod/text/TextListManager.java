package net.zefinder.ftlmod.text;

import net.zefinder.ftlmod.ObjectManager;

public class TextListManager extends ObjectManager<TextList> {

	private static final TextListManager instance = new TextListManager();

	public static final TextListManager getInstance() {
		return instance;
	}

}
