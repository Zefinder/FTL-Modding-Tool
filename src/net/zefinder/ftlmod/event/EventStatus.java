package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import net.zefinder.ftlmod.game.ShipSystem;
import net.zefinder.ftlmod.game.Target;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventStatus(int amount, StatusType type, ShipSystem system, Target target) implements XmlObject {

	public static enum StatusType {
		LOSS("loss"), LIMIT("limit"), CLEAR("clear"), DIVIDE("divide");

		private final String typeName;

		private StatusType(final String typeName) {
			this.typeName = typeName;
		}

		public String typeName() {
			return typeName;
		}

		public static final StatusType fromString(String name) {
			if (name == null) {
				return LOSS;
			}

			return switch (name) {
			case "loss" -> LOSS;
			case "limit" -> LIMIT;
			case "clear" -> CLEAR;
			case "divide" -> DIVIDE;
			default -> LOSS;
			};
		}
	}

	public EventStatus(int amount, StatusType type, ShipSystem system, Target target) {
		this.amount = amount < 0 ? 0 : amount;
		this.type = type == null ? StatusType.LOSS : type;
		this.system = system == null ? ShipSystem.ROOM : system;
		this.target = target == null ? Target.ALL : target;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(STATUS_TAG_NAME, new Attribute(AMOUNT_ATTRIBUTE_NAME, Integer.toString(amount)),
				new Attribute(SYSTEM_ATTRIBUTE_NAME, system.systemName()),
				new Attribute(TYPE_ATTRIBUTE_NAME, type.typeName()),
				new Attribute(TARGET_ATTRIBUTE_NAME, target.targetName()));
	}

}
