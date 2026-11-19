package in.co.rays.proj4.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MessageSource class is used to manage and retrieve localized messages from
 * the message resource bundle. It supports English and Hindi languages.
 * 
 * @author Hema Singh
 * @version 1.0
 */
public final class MessageSource {

	/**
	 * Language code for English.
	 */
	public static String ENGLISH_LANG_CODE = "en";

	/**
	 * Language code for Hindi.
	 */
	public static String HINDI_LANG_CODE = "hi";

	/**
	 * Default language code.
	 */
	public static String DEFAULT_LANGUAGE = ENGLISH_LANG_CODE;

	/**
	 * Holds the singleton instance of MessageSource.
	 */
	private static MessageSource ms = null;

	/**
	 * ResourceBundle object used to retrieve localized messages.
	 */
	private ResourceBundle rb = null;

	/**
	 * Private default constructor that initializes MessageSource with the default
	 * language.
	 */
	private MessageSource() {
		this(DEFAULT_LANGUAGE);
	}

	/**
	 * Private constructor that initializes the resource bundle for the specified
	 * language.
	 * 
	 * @param lang language code
	 */
	private MessageSource(String lang) {
		rb = ResourceBundle.getBundle("message", new Locale(lang));
	}

	/**
	 * Private constructor that initializes MessageSource using the specified
	 * locale.
	 * 
	 * @param locale locale used to determine the language
	 */
	private MessageSource(Locale locale) {
		this(locale.getLanguage());
	}

	/**
	 * Returns the singleton instance of MessageSource using the default language.
	 * 
	 * @return MessageSource instance
	 */
	public static MessageSource getInstance() {
		return getInstance(DEFAULT_LANGUAGE);
	}

	/**
	 * Returns the singleton instance of MessageSource. Creates a new instance if
	 * one does not already exist.
	 * 
	 * @param languageCode language code for the resource bundle
	 * @return MessageSource instance
	 */
	public static MessageSource getInstance(String languageCode) {
		if (ms == null) {
			ms = new MessageSource(languageCode);
		}
		return ms;
	}

	/**
	 * Changes the current language of the resource bundle.
	 * 
	 * @param lang language code
	 */
	public void setLocale(String lang) {
		rb = ResourceBundle.getBundle("message", new Locale(lang));
	}

	/**
	 * Returns the current language code.
	 * 
	 * @return current language code
	 */
	public String getLanguage() {
		return rb.getLocale().getLanguage();
	}

	/**
	 * Retrieves a localized message for the specified key. Returns an empty string
	 * if the key is not found.
	 * 
	 * @param key message key
	 * @return localized message
	 */
	public String get(String key) {
		String val = "";
		try {
			val = rb.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	 * Main method to test the MessageSource functionality.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		MessageSource ms = MessageSource.getInstance();
		// ms.setLocale("en");
		String val = ms.get("login.userid");
		System.out.println("-->" + val);
	}
}