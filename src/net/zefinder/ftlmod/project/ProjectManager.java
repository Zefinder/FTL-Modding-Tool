package net.zefinder.ftlmod.project;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ProjectManager {

	private static final Logger log = LoggerFactory.getLogger(ProjectManager.class);

	private static final ProjectManager instance = new ProjectManager();

	private static final LinkedHashMap<Project, Project> PROJECTS = new LinkedHashMap<Project, Project>();
	private static Project currentProject;

	private ProjectManager() {
	}

	private static final Project createEmptyProject(String name, String projectDirectoryPath)
			throws ProjectCreationException {
		return new Project(name, projectDirectoryPath, null);
	}

	public final boolean createProject(String name, String projectDirectoryPath, String resourceDirectoryPath) {
		Project project = null;
		try {
			project = createEmptyProject(name, projectDirectoryPath);
		} catch (ProjectCreationException e) {
			e.printStackTrace();
			log.error("An error occurred when opening the project", e);
			return false;
		}
		
		if (PROJECTS.containsKey(project)) {
			log.error("The project already exist...");
			return false;
		}
		
		// We know that project is not null
		project.setResourceDirectoryPath(resourceDirectoryPath);
		PROJECTS.put(project, project);
		return true;
	}
	
	public final boolean createProject(String name, String projectDirectoryPath) {
		return createProject(name, projectDirectoryPath, null);
	}
	
	/**
	 * Opens a new project. Returns true if open was successful.
	 * 
	 * @param name                 the project name
	 * @param projectDirectoryPath the project directory path
	 * @return true if a project has been successfully opened
	 */
	public final boolean openProject(String name, String projectDirectoryPath) {
		// Create an empty project that will be used to search in the set
		Project project = null;
		try {
			project = createEmptyProject(name, projectDirectoryPath);
		} catch (ProjectCreationException e) {
			e.printStackTrace();
			log.error("An error occurred when opening the project", e);
			return false;
		}

		// From here project can only be set
		project = PROJECTS.get(project);
		if (project == null) {
			// This should never happen unless the user decides to
			log.error("The specified project does not exist");
		} else {
			currentProject = project;
			currentProject.init();
		}

		return project != null;
	}

	public final void closeProject() {
		currentProject = null;
	}
	
	/**
	 * Returns the project directory path of the current project, or an empty string
	 * if a project has not been opened.
	 * 
	 * @return the project directory path (or empty string if no projects opened)
	 */
	public final String getProjectDirectoryPath() {
		return currentProject == null ? "" : currentProject.projectDirectoryPath();
	}

	/**
	 * Returns the project's resource directory path of the current project, or an
	 * empty string if a project has not been opened.
	 * 
	 * @return the resource directory path used for this project (or empty string if
	 *         no projects opened)
	 */
	public final String getResourceDirectoryPath() {
		return currentProject == null ? "" : currentProject.getResourceDirectoryPath();
	}

	public static final ProjectManager getInstance() {
		return instance;
	}

}
