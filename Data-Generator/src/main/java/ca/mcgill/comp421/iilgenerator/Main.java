package ca.mcgill.comp421.iilgenerator;

import ca.mcgill.comp421.iilgenerator.generators.PatronGenerator;

public class Main {
	public static void main(String[] args) {
		PatronGenerator g = new PatronGenerator(2019);
		g.generate(10);
		for(PatronGenerator.Patron p : g.getGeneratedPatrons()) {
			System.out.println("----------------------------------");
			System.out.println(p.uName);
			System.out.println(p.email);
			System.out.println(p.uAddress);
			System.out.println(p.phoneNumber);
		}
	}
}
