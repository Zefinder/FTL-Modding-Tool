package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

/**
 * Auto rewards in an event.
 */
public record EventAutoReward(RewardLevel level, RewardType type) implements XmlObject {

	public static enum RewardLevel {
		RANDOM, LOW, MED, HIGH;

		public static final RewardLevel fromString(String level) {
			try {
				return RewardLevel.valueOf(level);
			} catch (Exception e) {
				return RANDOM;
			}
		}
	}

	/**
	 * Reward type, comes from the explanations in "event.xml" (plus scrap_only). I
	 * do not guarantee that all are still implemented (item for instance is never
	 * used...).
	 */
	public static enum RewardType {
		STANDARD("standard"), STUFF("stuff"), FUEL("fuel"), MISSILES("missiles"), DRONEPARTS("droneparts"),
		FUEL_ONLY("fuel_only"), MISSILES_ONLY("missiles_only"), DRONEPARTS_ONLY("droneparts_only"),
		SCRAP_ONLY("scrap_only"), WEAPON("weapon"), AUGMENT("augment"), DRONE("drone"), ITEM("item");

		private final String rewardType;

		private RewardType(String rewardType) {
			this.rewardType = rewardType;
		}

		public final String rewardType() {
			return rewardType;
		}

		public static final RewardType fromString(String type) {
			try {
				return RewardType.valueOf(type);
			} catch (Exception e) {
				return STANDARD;
			}
		}
	}

	public EventAutoReward(RewardLevel level, RewardType type) {
		this.level = level == null ? RewardLevel.LOW : level;
		this.type = type == null ? RewardType.STANDARD : type;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<String>(LEVEL_ATTRIBUTE_NAME, type.rewardType(),
				new Attribute(LEVEL_ATTRIBUTE_NAME, level.name()));
	}

}
