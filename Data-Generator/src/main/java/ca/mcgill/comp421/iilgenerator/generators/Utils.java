package ca.mcgill.comp421.iilgenerator.generators;

import java.util.Locale;

public final class Utils {
	public static Locale locale = Locale.CANADA;
	
	public static String formatPhoneNumber(String phoneNumber) {
		return phoneNumber.replace(") ", "-").replace("(", "").replaceAll("\\.", "-");
	}
}
