package net.zefinder.ftlmod.analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to analyse a blueprint file and gather their names.
 */
public class BlueprintAnalyser {

	private static final Logger log = LoggerFactory.getLogger(BlueprintAnalyser.class);

	private static final String BLUEPRINT_PATTERN_FORMAT = "<%sBlueprint name=\"([^\"]+)\"";

	private static enum BlueprintType {
		WEAPON("weapon"), SHIP("ship"), AUG("aug"), SYSTEM("system"), CREW("crew"), DRONE("drone");

		// Keep them local!
		private final Pattern pattern;

		private BlueprintType(final String typeName) {
			this.pattern = Pattern.compile(BLUEPRINT_PATTERN_FORMAT.formatted(typeName));
		}

	}

	private BlueprintAnalyser() {
		// Nothing to see here
	}

	/**
	 * Analyses the blueprint file and returns an immutable set of all types and
	 * names.
	 * 
	 * @param blueprintFile the file to analyse
	 * @return an immutable set of type and names
	 */
	public static Set<Result> analyse(File blueprintFile) {
		if (!blueprintFile.exists()) {
			return Set.of();
		}

		final Set<Result> results = new HashSet<Result>();
		try (BufferedReader reader = new BufferedReader(new FileReader(blueprintFile))) {
			String line;
			Matcher matcher;

			while ((line = reader.readLine()) != null) {
				// Check for each type
				for (final BlueprintType type : BlueprintType.values()) {
					matcher = type.pattern.matcher(line);

					if (matcher.find()) {
						results.add(new Result(type, matcher.group(1)));

						// Exit the type loop
						break;
					}
				}
			}

		} catch (IOException e) {
			log.error("An error occured when analysing the following blueprint file: " + blueprintFile.getName(), e);

			// Return empty set, error means probably corrupted data
			return Set.of();
		}

		return Set.copyOf(results);
	}

	public static record Result(BlueprintType blueprintType, String name) {
	};

//	private static void analyse(HashMap<BlueprintType, Set<String>> results, File blueprintFile) throws FileNotFoundException, IOException {
//		Pattern sub = Pattern.compile("<(([^/>! ]+))");
//		Pattern close = Pattern.compile("</");
//
//		try (BufferedReader reader = new BufferedReader(new FileReader(blueprintFile))) {
//			String line;
//			Matcher matcher;
//			boolean in = false;
//			BlueprintType currentType = BlueprintType.WEAPON;
//
//			while ((line = reader.readLine()) != null) {
//				if (!in) {
//					for (final BlueprintType type : BlueprintType.values()) {
//						matcher = type.pattern.matcher(line);
//
//						if (matcher.find()) {
//							currentType = type;
//							in = true;
//							close = Pattern.compile("</%sBlueprint>".formatted(type.name().toLowerCase()));
//							
//							results.computeIfAbsent(type, t -> new HashSet<String>());
//							
//							// Exit the type loop
//							break;
//						}
//					}
//				} else {
//					matcher = sub.matcher(line);
//					if (matcher.find()) {
//						results.get(currentType).add(matcher.group(1));
//					} else {
//						matcher = close.matcher(line);
//						if (matcher.find()) {
//							in = false;
//						}
//					}
//				}
//			}
//		}
//	}
	
	public static void main(String[] args) throws IOException, DocumentException {
//		HashMap<BlueprintType, Set<String>> results = new HashMap<BlueprintAnalyser.BlueprintType, Set<String>>();
//		analyse(results, new File("C:\\Users\\Jakub\\Desktop\\Travail\\FTL_Mod\\res\\data\\blueprints.xml"));
//		analyse(results, new File("C:\\Users\\Jakub\\Desktop\\Travail\\FTL_Mod\\res\\data\\dlcBlueprints.xml"));
//		
//		System.out.println(results.toString());
//		
//		results.forEach((k, v) -> {
//			System.out.println(k.toString() + " -> " + v.contains("sound"));
//		});
//		
//		System.out.println();
//		System.out.println(String.join("\n", results.get(BlueprintType.WEAPON)));
		
		SAXReader reader = new SAXReader();
		reader.setIgnoreComments(true);
		var doc = reader.read(new File("C:\\Users\\Jakub\\Desktop\\Travail\\FTL_Mod\\res\\data\\blueprints.xml"));
		
		System.out.println();
		for (Element element : doc.getRootElement().elements("weaponBlueprint")) {
			System.out.println(element);
		}
	}

}
