package net.zefinder.ftlmod.event;

import net.zefinder.ftlmod.EnvironmentType;
import net.zefinder.ftlmod.Target;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventEnvironment(EnvironmentType environmentType, Target target) implements XmlObject {

	public static final String ENVIRONMENT_TAG_NAME = "environment";
	public static final String TYPE_ATTRIBUTE_NAME = "type";
	public static final String TARGET_ATTRIBUTE_NAME = "target";

	public EventEnvironment(EnvironmentType environmentType, Target target) {
		this.environmentType = environmentType == null ? EnvironmentType.SUN : environmentType;
		this.target = target == null ? Target.ALL : target;
	}

	@Override
	public XmlTag<?> toXmlTag() {
		return new XmlTag<Void>(ENVIRONMENT_TAG_NAME, new Attribute(TYPE_ATTRIBUTE_NAME, environmentType.typeName()),
				new Attribute(TARGET_ATTRIBUTE_NAME, target.targetName()));
	}

}
