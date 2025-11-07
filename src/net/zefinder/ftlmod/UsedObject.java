package net.zefinder.ftlmod;

/**
 * This class stores an object and if it has been used somewhere
 * 
 * @param <T>
 */
public class UsedObject<T> {

	private T object;
	private boolean used;

	private UsedObject() {
		this.object = null;
		this.used = false;
	}

	private UsedObject(T object) {
		this.object = object;
		this.used = false;
	}

	/**
	 * Sets the object iff there is no present object.
	 * 
	 * @param object the object to set
	 */
	public void setObject(T object) {
		if (this.object == null) {
			this.object = object;
		}
	}

	public T getObject() {
		return object;
	}

	/**
	 * Returns true if the object has been used.
	 * 
	 * @return true if the object has been used
	 */
	public boolean isUsed() {
		return used;
	}

	public void use() {
		this.used = true;
	}

	public void reset() {
		this.used = false;
	}

	public static final <T> UsedObject<T> of(T object) {
		return new UsedObject<T>(object);
	}
	
	public static final <T> UsedObject<T> empty() {
		return new UsedObject<T>();
	}

}
