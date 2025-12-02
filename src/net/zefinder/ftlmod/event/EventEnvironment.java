package net.zefinder.ftlmod.event;

import static net.zefinder.ftlmod.Consts.*;

import net.zefinder.ftlmod.game.EnvironmentType;
import net.zefinder.ftlmod.game.Target;
import net.zefinder.ftlmod.xml.XmlObject;
import net.zefinder.ftlmod.xml.XmlTag;
import net.zefinder.ftlmod.xml.XmlTag.Attribute;

public record EventEnvironment(EnvironmentType environmentType, Target target) implements XmlObject {

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
