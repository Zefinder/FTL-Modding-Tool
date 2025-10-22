package net.zefinder.ftlmod.analyser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

/**
 * Only here for dev purpose. It records all possible tags and attributes of a
 * list of elements. It won't record the possible values taken inside since it
 * is not relevant.
 */
final class ElementAnalyser {

	public static final Map<String, Map<String, Set<String>>> components = new HashMap<String, Map<String, Set<String>>>();

	private ElementAnalyser() {
	}

	public static final void analyse(final List<Element> elements, final boolean isUser) {

		for (Element eventElement : elements) {
			for (Element el : eventElement.elements()) {
				components.computeIfAbsent(el.getName(), t -> new HashMap<String, Set<String>>());
				var elementMap = components.get(el.getName());
				
				el.attributes().forEach(
						attribute -> elementMap.computeIfAbsent(attribute.getName(), t -> new HashSet<String>())
								.add(el.attributeValue(attribute.getName())));
			}
		}
	}

	public static final void showResults() {
		ElementAnalyser.components.forEach(
				(name, attribute) -> System.out.println("%s ->[\n\t%s\n\t]".formatted(name, attribute.toString())));
	}

	public static final void clear() {
		components.clear();
	}
}
