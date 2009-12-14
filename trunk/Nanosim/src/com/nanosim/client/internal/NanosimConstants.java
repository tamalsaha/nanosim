package com.nanosim.client.internal;

public interface NanosimConstants {

	/**
	 * The path to source code for examples, raw files, and style definitions.
	 */
	String DST_SOURCE = "gwtShowcaseSource/";

	/**
	 * The destination folder for parsed source code from Showcase examples.
	 */
	String DST_SOURCE_EXAMPLE = DST_SOURCE + "java/";

	/**
	 * The destination folder for raw files that are included in entirety.
	 */
	String DST_SOURCE_RAW = DST_SOURCE + "raw/";

	/**
	 * The destination folder for parsed CSS styles used in Showcase examples.
	 */
	String DST_SOURCE_STYLE = DST_SOURCE + "css/";

	/**
	 * Link to homepage.
	 */
	String HOMEPAGE = "http://localhost:8080/Nanosim.html";

	/**
	 * The available style themes that the user can select.
	 */
	String STYLE_THEME = "chrome";

	String categoryOther();

	String categoryTables();

	String categoryMail();

	/**
	 * @return the title of the main menu
	 */
	String mainMenuTitle();

	/**
	 * @return the sub title of the application
	 */
	String mainSubTitle();

	/**
	 * @return the welcome message
	 */
	String welcome();

	/**
	 * @return the title of the application
	 */
	String mainTitle();

	/**
	 * @return the title of the about box
	 */
	String cwAboutBoxTitle();

	/**
	 * @return the message of the about box
	 */
	String cwAboutBoxMessage();
}
