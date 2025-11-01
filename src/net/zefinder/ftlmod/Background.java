package net.zefinder.ftlmod;

public enum Background {
	BACKGROUND, BG_DARK, BG_NEBULA;

	public static final Background fromString(String back) {
		try {
			return Background.valueOf(back);
		} catch (Exception e) {
			return BACKGROUND;
		}
	}
}
