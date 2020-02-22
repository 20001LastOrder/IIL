package ca.mcgill.comp421.iilgenerator.generators;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AdministratorGenerator extends Generator {
	public static class Administrator extends GeneratedUser{
		String iName;
		@Override
		public String getFieldNames() {
			return "email;uName;phoneNumber;uAddress;iName";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s;%s;%s", email, uName, phoneNumber, uAddress, iName);
		}
	}
	
	private InstitutionGenerator dependentGenerator;
	
	public AdministratorGenerator(long i, InstitutionGenerator dependentGenerator) {
		super(i);
		this.dependentGenerator = dependentGenerator;
	}
	
	public AdministratorGenerator(InstitutionGenerator dependentGenerator) {
		super();
		this.dependentGenerator = dependentGenerator;
	}
	
	public void loadOccupiedPrimaryKeys(HashSet<String> set) {
		primaryKeyMap.addAll(set);
	}

	@Override
	public void generate(int num) {
		if(dependentGenerator.getGeneratedElements().size() == 0) {
			dependentGenerator.generate(num / 2);
		}
		
		List<InstitutionGenerator.Institution> institutions = dependentGenerator.getGeneratedInstitutions();
		
		int numGenerated = 0;
		while(numGenerated < num) {
			Administrator a = new Administrator();
			a.uName = Utils.randomName(faker);
			a.email = Utils.randomEmail(faker, a.uName);
			if(!primaryKeyMap.contains(a.email)) {
				primaryKeyMap.add(a.email);
				a.uAddress = Utils.randomAddress(faker);
				a.phoneNumber = Utils.formatPhoneNumber(faker.phoneNumber().cellPhone());
				a.iName = institutions.get(faker.random().nextInt(institutions.size())).iName;
				elements.add(a);
				numGenerated += 1;
			}
		}	
	}
	
	public List<Administrator> getGeneratedPatrons(){
		return elements.stream().map(ele -> (Administrator)ele).collect(Collectors.toList());
	}
}
