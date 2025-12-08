package net.zefinder.ftlmod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.event.Event;

public abstract class ObjectManager<T extends NamedObject> {

	private final Logger log;
	private final Map<String, UsedObject<T>> originalObjects;
	private final Map<String, UsedObject<T>> userObjects;

	protected ObjectManager(final String name) {
		originalObjects = new HashMap<String, UsedObject<T>>();
		userObjects = new HashMap<String, UsedObject<T>>();
		
		final String loggerName;
		if (name == null || name.isBlank()) {
			loggerName = getClass().getName();
		} else {
			loggerName = "%s (%s)".formatted(getClass().getName(), name);
		}
		
		log = LoggerFactory.getLogger(loggerName);
	}
	
	protected ObjectManager() {
		originalObjects = new HashMap<String, UsedObject<T>>();
		userObjects = new HashMap<String, UsedObject<T>>();
		log = LoggerFactory.getLogger(getClass());
	}
	
	public final Optional<T> getOriginalObject(final String name) {
		if (originalObjects.containsKey(name)) {
			// TODO Remove when finished testing
			T a = originalObjects.get(name).getObject();
			if (a instanceof Event event) {				
				log.info("Retrun %s. Used: %b\nValue: %s".formatted(name, originalObjects.get(name).isUsed(), event.toXmlTag().toString() ));
			}
			return Optional.ofNullable(originalObjects.get(name).getObject());
		}

		log.warn("Object not found, return empty...");
		return Optional.empty();
	}

	/**
	 * Tries to get an object from the user objects. If it does not exist, then
	 * tries to get it from the original game. This throws a
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
		final String objectName = object.name();
		final UsedObject<T> usedObject = UsedObject.of(object);

		if (isUser) {
			// If already registered, check if already used before adding it
			if (originalObjects.containsKey(objectName) && originalObjects.get(objectName).isUsed()
					|| userObjects.containsKey(objectName) && userObjects.get(objectName).isUsed()) {
				log.info("Object exists and is used, replace!");
				usedObject.use();
			}

			userObjects.put(objectName, usedObject);
		} else {
			// If already registered, then check usage (should not happen but we never know)
			if (originalObjects.containsKey(objectName) && originalObjects.get(objectName).isUsed()) {
				log.info("Object exists and is used, replace!");
				usedObject.use();
			}

			originalObjects.put(objectName, usedObject);
		}
	}

	public final void useObject(final String objectName) {
		if (userObjects.containsKey(objectName)) {
			userObjects.get(objectName).use();
		} else if (originalObjects.containsKey(objectName)) {
			originalObjects.get(objectName).use();
		} else {
			log.info("Unknown object %s... Creating used placeholder!".formatted(objectName));

			// Create a holder in the original objects
			UsedObject<T> usedObject = UsedObject.empty();
			usedObject.use();
			originalObjects.put(objectName, usedObject);
		}
	}

	public final boolean isObjectUsed(final String objectName) {
		UsedObject<T> object = null;
		if (userObjects.containsKey(objectName)) {
			object = userObjects.get(objectName);
		} else if (originalObjects.containsKey(objectName)) {
			object = originalObjects.get(objectName);
		}

		return object == null ? false : object.isUsed();
	}

	public final void resetUsage() {
		log.info("Reset all object usage flag...");
		userObjects.forEach((name, usedObject) -> usedObject.reset());
		log.info("Done!");
	}

	public final List<T> getUnusedObjects() {
		Map<String, UsedObject<T>> test = new HashMap<String, UsedObject<T>>();
		test.putAll(originalObjects);
		test.putAll(userObjects);

		return test.values().stream().filter(usedObject -> !usedObject.isUsed())
				.map(unusedObject -> unusedObject.getObject()).toList();
	}

	public final void clearOriginalObjects() {
		log.info("Clearing all original game objects...");
		originalObjects.clear();
		log.info("Done!");
	}

	public final void clearUserObjects() {
		log.info("Clearing all user defined objects...");
		userObjects.clear();
		log.info("Done!");
	}

}
