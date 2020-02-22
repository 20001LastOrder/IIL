package ca.mcgill.comp421.iilgenerator.generators;

import java.util.List;
import java.util.stream.Collectors;

public class InstitutionGenerator extends Generator {	
	public static class Institution extends Generator.GeneratedElement{
		public String iName;
		public String iAddress;
		
		public String toString() {
			return iName + ";" + iAddress;
		}

		@Override
		public String getFieldNames() {
			return "iName;iAddress";
		}
	}
	
	public InstitutionGenerator() {
		super();
	}
	
	public InstitutionGenerator(long seed) {
		super(seed);
	}

	@Override
	public void generate(int num) {
		int numGenerated = 0;
		while(numGenerated < num) {
			Institution i = new Institution();
			i.iName = faker.university().name();
			if(!primaryKeyMap.contains(i.iName)) {
				primaryKeyMap.add(i.iName);
				i.iAddress = Utils.randomAddress(faker);
				elements.add(i);
				numGenerated += 1;
			}
		}	
	}
	
	public List<Institution> getGeneratedInstitutions(){
		return elements.stream().map(ele -> (Institution)ele).collect(Collectors.toList());
	}
}
