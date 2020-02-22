package ca.mcgill.comp421.iilgenerator.generators;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PatronGenerator extends Generator {
	public static class Patron extends GeneratedUser{
		String iName;
		int maxBooks;
		@Override
		public String getFieldNames() {
			return "email;uName;phoneNumber;uAddress;maxBooks;iName";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s;%s;%s;%s", email, uName, phoneNumber, uAddress, maxBooks, iName);
		}
	}
	
	private InstitutionGenerator dependentGenerator;
	
	public PatronGenerator(long i, InstitutionGenerator dependentGenerator) {
		super(i);
		this.dependentGenerator = dependentGenerator;
	}
	
	public PatronGenerator(InstitutionGenerator dependentGenerator) {
		super();
		this.dependentGenerator = dependentGenerator;
	}
	
	public void loadOccupiedPrimaryKeys(HashSet<String> set) {
		primaryKeyMap.addAll(set);
	}

	@Override
	public void generate(int num) {
		if(dependentGenerator.getGeneratedElements().size() == 0) {
			dependentGenerator.generate(num / 10);
		}
		
		List<InstitutionGenerator.Institution> institutions = dependentGenerator.getGeneratedInstitutions();
		
		int numGenerated = 0;
		while(numGenerated < num) {
			Patron p = new Patron();
			p.uName = Utils.randomName(faker);
			p.email = Utils.randomEmail(faker, p.uName);
			if(!primaryKeyMap.contains(p.email)) {
				primaryKeyMap.add(p.email);
				p.uAddress = Utils.randomAddress(faker);
				p.phoneNumber = Utils.formatPhoneNumber(faker.phoneNumber().cellPhone());
				p.iName = institutions.get(faker.random().nextInt(institutions.size())).iName;
				p.maxBooks = 5 + faker.random().nextInt(10);
				elements.add(p);
				numGenerated += 1;
			}
		}	
	}
	
	public List<Patron> getGeneratedPatrons(){
		return elements.stream().map(ele -> (Patron)ele).collect(Collectors.toList());
	}
}
