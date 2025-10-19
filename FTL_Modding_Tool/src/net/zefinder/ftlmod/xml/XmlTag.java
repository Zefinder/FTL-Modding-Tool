package net.zefinder.ftlmod.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
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
	
	private static final XmlTag<?> EMPTY = new XmlTag<>("");
	public static final <T> XmlTag<T> empty() {
        @SuppressWarnings("unchecked")
        XmlTag<T> t = (XmlTag<T>) EMPTY;
        return t;
	};

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

	/**
	 * Creates a clone of this object. Because the element is not known before
	 * runtime, it is impossible to create a clone of it. Thus, if the XmlTag
	 * contains mutable objects (such as a {@link Collection}), then you need to
	 * clone it by hand!
	 */
	@Override
	public XmlTag<T> clone() {
		return new XmlTag<T>(tagName, element, attributes.toArray(Attribute[]::new));
	}

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

}
