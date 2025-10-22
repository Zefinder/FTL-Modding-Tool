package net.zefinder.ftlmod;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

public class FtlModdingTool {

	private static final Logger log = LoggerFactory.getLogger(FtlModdingTool.class);

	private static final Charset UTF_8_CHARSET = Charset.forName("UTF-8");
	private static final String LOG_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";
	private static final String LOG_FILE_NAME = "LogFile";
	private static final String LOG_FILE_PATH = "./ftlmoddingtool-log.txt";

	private static final String TOOL_APPNAME = "FTL Modding Tool";
	private static final String TOOL_APPVERSION = "FTL Modding Tool";

	public static void main(String[] args) {
		// Init logger and redirect to file
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(lc);
		encoder.setCharset(UTF_8_CHARSET);
		encoder.setPattern(LOG_PATTERN);
		encoder.start();

		FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
		fileAppender.setContext(lc);
		fileAppender.setName(LOG_FILE_NAME);
		fileAppender.setFile(new File(LOG_FILE_PATH).getAbsolutePath());
		fileAppender.setAppend(false);
		fileAppender.setEncoder(encoder);
		fileAppender.start();

		lc.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(fileAppender);

		// Print debug info
		log.debug("Started: {}", new Date());
		log.debug("{} v{}", TOOL_APPNAME, TOOL_APPVERSION);
		log.debug("OS: {} {}", System.getProperty("os.name"), System.getProperty("os.version"));
		log.debug("VM: {}, {}, {}", System.getProperty("java.vm.name"), System.getProperty("java.version"),
				System.getProperty("os.arch"));

		// Set the uncaught exception handler
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				log.error("Uncaught exception in thread: " + t.toString(), e);
			}
		});
		
		// Call SwingUtilities.invokeLater so we are sure that everything is ready for the GUI
		SwingUtilities.invokeLater(() -> init());
	}
	
	private static void init() {
		
	}

}
