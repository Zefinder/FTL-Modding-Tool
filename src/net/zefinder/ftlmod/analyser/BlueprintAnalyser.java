package net.zefinder.ftlmod.analyser;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.zefinder.ftlmod.project.Project;
import net.zefinder.ftlmod.project.ProjectCreationException;
import net.zefinder.ftlmod.project.ProjectManager;
import net.zefinder.ftlmod.weapon.WeaponCreationException;

/**
 * Used to analyse a blueprint file and gather their names.
 */
public class BlueprintAnalyser {

	private static final Logger log = LoggerFactory.getLogger(BlueprintAnalyser.class);

	private static final String USER_DEFINED_FILES_EXTENSION = ".append";
//	private static final String BLUEPRINT_PATTERN_FORMAT = "<%sBlueprint name=\"([^\"]+)\"";

//	private static final List<String> PATHS_TO_ANALYSE = List.of("blueprints.xml", "dlcBlueprints.xml");
//	private static final Set<String> WEAPON_FILE_NAMES = Set.of("blueprints.xml", "dlcBlueprints.xml");

//	private static enum BlueprintType {
//		WEAPON("weapon"), SHIP("ship"), AUG("aug"), SYSTEM("system"), CREW("crew"), DRONE("drone");
//
//		// Keep them local!
//		private final Pattern pattern;
//
//		private BlueprintType(final String typeName) {
//			this.pattern = Pattern.compile(BLUEPRINT_PATTERN_FORMAT.formatted(typeName));
//		}
//
//	}

	private BlueprintAnalyser() {
		// Nothing to see here
	}

	/**
	 * Analyses the blueprint file and returns an immutable set of all types and
	 * names.
	 * 
	 * @param blueprintFile the file to analyse
	 * @return an immutable set of type and names
	 * @throws DocumentException
	 */
	public static void analyse() throws DocumentException {
		SAXReader reader = new SAXReader();
		reader.setIgnoreComments(true);

		// Get the resource path from the project manager
		String resourcePath = ProjectManager.getInstance().getResourceDirectoryPath();
		if (!resourcePath.isEmpty()) {
			File dataDir = new File(resourcePath + Project.DATA_PATH);
			boolean isUser = false;

			// Should exist but we never know with users...
			if (dataDir.exists()) {
				// List all xml files
				File[] filesToAnalyse = dataDir.listFiles((dir, name) -> name.endsWith(".xml"));

				for (File fileToAnalyse : filesToAnalyse) {
					log.info("Analying file " + fileToAnalyse.getName());
					Document doc = reader.read(fileToAnalyse);
					Element root = doc.getRootElement();

					TextAnalyser.analyse(root.elements("text"), isUser);

					try {
						WeaponAnalyser.analyse(root.elements("weaponBlueprint"), isUser);
					} catch (WeaponCreationException e) {
						log.error(
								"Error when reading a weapon, skip weapon analysis for file " + fileToAnalyse.getName(),
								e);
					}
					
					// Analyse events from root and from event lists
					EventAnalyser.analyse(root.elements("event"), isUser);
//					EventAnalyser.analyse(root.elements("eventList"), isUser);
					
					// Events
//					ElementAnalyser.analyse(root.elements("event"), isUser);
//					ElementAnalyser.analyse(root.elements("eventList").stream().map(t -> t.elements("event"))
//							.<Element>flatMap(t -> t.stream()).toList(), isUser);
//					ElementAnalyser.analyse(root.elements("event").stream().map(t -> t.elements("choice"))
//							.<Element>flatMap(t -> t.stream()).map(t -> t.elements("event"))
//							.<Element>flatMap(t -> t.stream()).toList(), isUser);
					
					ElementAnalyser.analyse(root.elements("event").stream().map(t -> t.elements("item_modify"))
					.<Element>flatMap(t -> t.stream()).toList(), isUser);
					
					// Choices
//					ElementAnalyser.analyse(root.elements("event").stream().map(t -> t.elements("choice"))
//							.<Element>flatMap(t -> t.stream()).toList(), isUser);

				}
				ElementAnalyser.showResults();
			} else {
				log.info("Data directory do not exist... Why user? whyyyyyyy?");
			}

		} else {
			log.info("Game resource directory not set, ignore...");
		}

		// Get the project path from the project manager
		String projectPath = ProjectManager.getInstance().getProjectDirectoryPath();
		if (!projectPath.isEmpty()) {

		}

	}

	public static void main(String[] args) throws IOException, DocumentException, ProjectCreationException {
//		ProjectManager.getInstance().createProject("AAA", "C:\\Users\\adric\\Desktop\\Travail\\mod",
//				"C:\\Users\\adric\\Desktop\\Travail\\FTL_dat");
//		ProjectManager.getInstance().openProject("AAA", "C:\\Users\\adric\\Desktop\\Travail\\mod");
		ProjectManager.getInstance().createProject("AAA", "C:\\Users\\Jakub\\Desktop\\Travail\\FTL_Mod\\esc_room_1",
				"C:\\Users\\Jakub\\Desktop\\Travail\\FTL_Mod\\res");
		ProjectManager.getInstance().openProject("AAA", "C:\\Users\\Jakub\\Desktop\\Travail\\FTL_Mod\\esc_room_1");
		BlueprintAnalyser.analyse();
	}

}
