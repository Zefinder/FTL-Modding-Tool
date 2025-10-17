package net.zefinder.ftlmod.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Represents an XML Tag. Fields are immutable, calling to change, add or edit
 * the element or an attribute will create a new tag.
 */
public class XmlTag<T> {

	private static final OutputFormat format = OutputFormat.createPrettyPrint();

	static {
		format.setIndentSize(4);
		format.setSuppressDeclaration(true);
		format.setEncoding("UTF-8");
	}

	@SuppressWarnings("rawtypes")
	public static final XmlTag EMPTY = new XmlTag<>("");
	
	private final String tagName;
	private final T element;
	private final Set<Attribute> attributes;

	/**
	 * <p>
	 * Creates an XML tag with a name, an element and attributes. If the element is
	 * null, then the tag will be considered element-less.
	 * </p>
	 * 
	 * <p>
	 * Note that you cannot clone an {@link Object} without knowing its type. This
	 * means that you cannot protect the user from modifying the tag's element.
	 * <p>
	 */
	public XmlTag(final String tagName, final T element, final Attribute... attributes) {
		this.tagName = tagName;
		this.element = element;
		this.attributes = Arrays.stream(attributes).collect(Collectors.toSet());
	}

	public XmlTag(final String tagName, final Attribute... attributes) {
		this(tagName, null, attributes);
	}

	public final boolean hasElement() {
		return element != null;
	}

	/**
	 * Returns the element in the XML tag, or an empty Optional if no element.
	 */
	public final Optional<T> getElement() {
		return Optional.ofNullable(element);
	}

//	public final XmlTag<T> setElement(T element) {
//		return new XmlTag<T>(tagName, element, attributes.toArray(Attribute[]::new));
//	}

	@Override
	public String toString() {
		String str = "<" + tagName;

		for (Attribute attribute : attributes) {
			str += " " + attribute.toString();
		}

		if (hasElement()) {
			str += ">";
			if (element instanceof Collection<?> collection) {
				str += String.join("", collection.stream().map(t -> t.toString()).toList());
			} else {
				str += getElement().get().toString();
			}

			str += "</" + tagName + ">";
		} else {
			str += "/>";
		}

		try {
			Document document = DocumentHelper.parseText(str);
			StringWriter sw = new StringWriter();
			XMLWriter writer = new XMLWriter(sw, format);
			writer.write(document);

			return sw.toString();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static record Attribute(String name, String value) {
		@Override
		public final String toString() {
			return "%s=\"%s\"".formatted(name, value);
		}
	}

	public static void main(String[] args) {
		XmlTag<String> systemType = new XmlTag<>("type", "battery");
		XmlTag<?> systemTitle = new XmlTag<>("title", new Attribute("id", "system_battery_title"));
		XmlTag<?> systemDesc = new XmlTag<>("desc", new Attribute("id", "system_battery_desc"));
		XmlTag<Integer> systemStartPower = new XmlTag<>("startPower", 1);
		XmlTag<Integer> systemMaxPower = new XmlTag<>("maxPower", 2);
		XmlTag<Integer> systemRarity = new XmlTag<>("rarity", 1);
		XmlTag<Integer> level = new XmlTag<>("level", 50);
		XmlTag<Set<XmlTag<?>>> systemUpgradeCost = new XmlTag<>("startPower", Set.of(level));
		XmlTag<Integer> systemCost = new XmlTag<>("cost", 35);

		List<XmlTag<?>> tags = List.of(systemType, systemTitle, systemDesc, systemStartPower, systemMaxPower,
				systemRarity, systemUpgradeCost, systemCost);
		XmlTag<List<XmlTag<?>>> systemBlueprint = new XmlTag<>("systemBlueprint", tags,
				new Attribute("name", "battery"));

		XmlTag<?> FTL = new XmlTag<>("FTL", Set.of(systemBlueprint));

		System.out.println(FTL.toString().strip());

	}

}
