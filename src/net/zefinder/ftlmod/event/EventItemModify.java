package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import java.util.ArrayList;
import java.util.List;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventItemModify(boolean steal, Item... items) implements XmlObject {

	public static enum ItemType {
		SCRAP("scrap"), DRONES("drones"), FUEL("fuel"), MISSILES("missiles");

		private final String typeName;

		private ItemType(final String typeName) {
			this.typeName = typeName;
		}

		public String typeName() {
			return typeName;
		}

		public static final ItemType fromString(final String name) {
			if (name == null) {
				return SCRAP;
			}

			return switch (name) {
			case "scrap" -> SCRAP;
			case "drones" -> DRONES;
			case "fuel" -> FUEL;
			case "missiles" -> MISSILES;
			default -> SCRAP;
			};
		}
	}

	public static final record Item(int min, int max, ItemType type) implements XmlObject {

		public Item(int min, int max, ItemType type) {
			this.min = min;
			this.max = max;
			this.type = type == null ? ItemType.SCRAP : type;
		}

		@Override
		public XmlTag<?> toXmlTag() {
			return new XmlTag<Void>(ITEM_TAG_NAME, new Attribute(MIN_ATTRIBUTE_NAME, Integer.toString(min)),
					new Attribute(MAX_ATTRIBUTE_NAME, Integer.toString(max)),
					new Attribute(TYPE_ATTRIBUTE_NAME, type.typeName()));
		}

	}

	public EventItemModify(boolean steal, Item... items) {
		this.steal = steal;
		this.items = items == null ? new Item[0] : items;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		List<XmlTag<?>> tags = new ArrayList<XmlTag<?>>();
		for (Item item : items) {
			if (item != null) {
				tags.add(item.toXmlTag());
			}
		}

		Attribute[] attributes = steal
				? new Attribute[] { new Attribute(STEAL_ATTRIBUTE_NAME, Boolean.toString(steal)) }
				: new Attribute[0];

		return new XmlTag<List<XmlTag<?>>>(ITEM_MODIFY_TAG_NAME, tags, attributes);
	}

}
