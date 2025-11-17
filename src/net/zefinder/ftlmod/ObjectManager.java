package net.zefinder.ftlmod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ObjectManager<T extends NamedObject> {

	private final Map<String, UsedObject<T>> originalObjects;
	private final Map<String, UsedObject<T>> userObjects;

	protected ObjectManager() {
		originalObjects = new HashMap<String, UsedObject<T>>();
		userObjects = new HashMap<String, UsedObject<T>>();
	}

	public final Optional<T> getOriginalObject(final String name) {
		if (originalObjects.containsKey(name)) {
			return Optional.ofNullable(originalObjects.get(name).getObject());
		}

		return Optional.empty();
	}

	/**
	 * Tries to get an object from the user objects. If it does not exist, then tries
	 * to get it from the original game. This throws a
	 * {@link NullPointerException} if the object has been redefined by the user BUT
	 * is null.
	 * 
	 * @param name the object name
	 * @return a defined object, or {@link Optional#empty()}
	 */
	public final Optional<T> getObject(final String name) {
		if (userObjects.containsKey(name)) {
			return Optional.of(userObjects.get(name).getObject()); // User weapon cannot be null
		}

		return getOriginalObject(name);
	}

	/**
	 * Removes an object from the users object.
	 * 
	 * @param objectName the object's name to remove
	 */
	public final void removeObject(final String objectName) {
		userObjects.remove(objectName);
	}

	/**
	 * Adds a object to either the user or the original game.
	 * 
	 * @param object the object to add
	 * @param isUser true if the object is a user defined object
	 */
	public final void addObject(final T object, final boolean isUser) {
		String objectName = object.name();
		UsedObject<T> usedObject = UsedObject.of(object);

		if (isUser) {
			if (originalObjects.containsKey(objectName) && originalObjects.get(objectName).isUsed()) {
				usedObject.use();
			}

			userObjects.put(object.name(), usedObject);
		} else {
			originalObjects.put(object.name(), UsedObject.of(object));
		}
	}

	public final void useObject(final String objectName) {
		if (userObjects.containsKey(objectName)) {
			userObjects.get(objectName).use();
		} else if (originalObjects.containsKey(objectName)) {
			originalObjects.get(objectName).use();
		} else {
			originalObjects.put(objectName, UsedObject.empty());
		}
	}

	public final boolean isObjectUsed(final String objectName) {
		if (userObjects.containsKey(objectName)) {
			return userObjects.get(objectName).isUsed();
		} else if (originalObjects.containsKey(objectName)) {
			return originalObjects.get(objectName).isUsed();
		}

		return false;
	}

	public final void resetUsage() {
		userObjects.forEach((name, usedObject) -> usedObject.reset());
	}

	public final List<T> getUnusedObjects() {
		Map<String, UsedObject<T>> test = new HashMap<String, UsedObject<T>>();
		test.putAll(originalObjects);
		test.putAll(userObjects);

		return test.values().stream().filter(usedObject -> !usedObject.isUsed())
				.map(unusedObject -> unusedObject.getObject()).toList();
	}

	public final void clearOriginalObjects() {
		originalObjects.clear();
	}

	public final void clearUserObjects() {
		userObjects.clear();
	}
	
}
