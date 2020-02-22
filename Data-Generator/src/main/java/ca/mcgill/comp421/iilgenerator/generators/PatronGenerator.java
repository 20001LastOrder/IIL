package ca.mcgill.comp421.iilgenerator.generators;

import java.util.List;
import java.util.stream.Collectors;

public class PatronGenerator extends Generator {
	public static class Patron extends GeneratedUser{
		
	}
	
	public PatronGenerator(long i) {
		super(i);
	}
	
	public PatronGenerator() {
		super();
	}

	@Override
	public void generate(int number) {
		for(int i = 0; i < number; i++) {
			Patron p = new Patron();
			p.uName = faker.name().fullName();
			p.email = p.uName.toLowerCase().replaceAll(" ", ".") + "@gmail.com";
			p.uAddress = faker.address().fullAddress();
			p.phoneNumber = Utils.formatPhoneNumber(faker.phoneNumber().cellPhone());
			elements.add(p);
		}
	}
	
	
	public List<Patron> getGeneratedPatrons(){
		return elements.stream().map(ele -> (Patron)ele).collect(Collectors.toList());
	}
}
