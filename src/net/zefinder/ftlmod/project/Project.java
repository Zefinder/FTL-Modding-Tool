package net.zefinder.ftlmod.project;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Project {

	private static final Logger log = LoggerFactory.getLogger(Project.class);

	public static final String AUDIO_PATH = File.separator + "audio";
	public static final String DATA_PATH = File.separator + "data";
	public static final String FONTS_PATH = File.separator + "fonts";
	public static final String IMG_PATH = File.separator + "img";

	private final String name;
	private final String projectDirectoryPath;
	private String resourceDirectoryPath;

	public Project(String name, String projectDirectoryPath, String resourceDirectoryPath)
			throws ProjectCreationException {
		// Check name
		if (name == null || name.isBlank()) {
			throw new ProjectCreationException("The project name must be at least one character");
		}
		this.name = name;

		// Check project directory path
		if (projectDirectoryPath == null) {
			throw new ProjectCreationException("The project directory path must not be null!");
		}
		File projectDirectory = new File(projectDirectoryPath);
		if (!projectDirectory.exists()) {
			throw new ProjectCreationException("The project directory must exist!");
		}
		if (!projectDirectory.isDirectory()) {
			throw new ProjectCreationException("The project directory must be a directory!");
		}
		this.projectDirectoryPath = projectDirectoryPath;

		// Check original game resource
		setResourceDirectoryPath(resourceDirectoryPath);
	}

	public String name() {
		return name;
	}

	public String projectDirectoryPath() {
		return projectDirectoryPath;
	}

	public String getResourceDirectoryPath() {
		return resourceDirectoryPath;
	}
	
	public void setResourceDirectoryPath(String resourceDirectoryPath) {
		// If the resources lead to a resource directory that does not exist, log and
		// set to null
		if (resourceDirectoryPath == null) {
			this.resourceDirectoryPath = null;
		} else {
			File resourceDirectory = new File(resourceDirectoryPath);
			if (!resourceDirectory.exists()) {
				log.info("Resource directory does not exist, ignore...");
				this.resourceDirectoryPath = null;

			} else if (!resourceDirectory.isDirectory()) {
				log.info("Resource directory is not a directory, ignore...");
				this.resourceDirectoryPath = null;
			} else {
				this.resourceDirectoryPath = resourceDirectoryPath;
			}
		}
	}

	private void init(String directoryPath) {
		File dir = new File(projectDirectoryPath + directoryPath);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	public void init() {
		init(AUDIO_PATH);
		init(DATA_PATH);
		init(FONTS_PATH);
		init(IMG_PATH);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Project other && other.name.equals(name)
				&& other.projectDirectoryPath.equals(projectDirectoryPath);
	}

	@Override
	public int hashCode() {
		return "Project[name=%s,projectDirectoryPath=%s]".formatted(name, projectDirectoryPath).hashCode();
	}

}
