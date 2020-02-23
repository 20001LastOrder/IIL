package ca.mcgill.comp421.iilgenerator.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

public final class Utils {
	public static Locale locale = Locale.CANADA;
	public static String[] emailSuffix = {"@gmail.com", "@icloud.com", "@outlook.com", "@mail.com"};

	public static String formatPhoneNumber(String phoneNumber) {
		return phoneNumber.replace(") ", "-").replace("(", "").replaceAll("\\.", "-");
	}
	
	public static String randomName(Faker faker) {
		return (faker.name().firstName() +" "+ faker.name().lastName()).replaceAll("'", "");
	}
	
	public static String randomEmail(Faker faker, String name) {
		return name.replace(" ", ".").toLowerCase() + faker.numerify("##") + emailSuffix[faker.random().nextInt(emailSuffix.length)];
	}
	
	public static String randomAddress(Faker faker) {
		return faker.address().streetAddress(true) + ", Montreal, QC, " + faker.bothify("H#?#?#").toUpperCase();
	}
	
	public static void toFile(String filename, List<Generator.GeneratedElement> elements) {
		File file = new File(filename);
		try {
			FileWriter writer = new FileWriter(file);
			StringBuilder builder = new StringBuilder();
			
			// print header first
			builder.append(elements.get(0).getFieldNames() + "\n");
			for(Generator.GeneratedElement element : elements) {
				builder.append(element.toString() + "\n");
			}
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
