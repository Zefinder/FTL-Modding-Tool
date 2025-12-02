package net.zefinder.ftlmod.text;

public enum TextType {
	/**
	 * Most basic text: no name, just text
	 */
	NORMAL,

	/**
	 * Like {@link #NORMAL}, but with a name with the attribute <code>name</code>
	 */
	NAMED,

	/**
	 * Text loaded from a text list using the attribute <code>load</code>. In this
	 * case, one text is selected at random from the list.
	 */
	LOAD,

	/**
	 * Text referring to an already existing text using the attribute
	 * <code>id</code>. In this case, the in-game text will be the one with the same
	 * name as specified.
	 */
	REFERENCE;
}
