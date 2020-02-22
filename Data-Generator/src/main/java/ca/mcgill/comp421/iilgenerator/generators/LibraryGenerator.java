package ca.mcgill.comp421.iilgenerator.generators;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryGenerator extends Generator {
	public static class Library extends Generator.GeneratedElement{
		public String lName;
		public String iName;
		public String lAddress;
		
		@Override
		public String getFieldNames() {
			return "lName; iName; lAddress";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s", lName, iName, lAddress);
		}
	}
	
	private InstitutionGenerator dependentGenerator;

	public LibraryGenerator(long seed, InstitutionGenerator dependentGenerator) {
		super(seed);
		this.dependentGenerator = dependentGenerator;
	}
	
	public LibraryGenerator(InstitutionGenerator dependentGenerator) {
		super();
		this.dependentGenerator = dependentGenerator;
	}
	
	
	@Override
	public void generate(int num) {
		if(dependentGenerator.getGeneratedElements().size() == 0) {
			dependentGenerator.generate(num / 2);
		}
		
		List<InstitutionGenerator.Institution> institutions = dependentGenerator.getGeneratedInstitutions();
		int numGenerated = 0;
		
		//a institution must have at least one library
		for(int i = 0; i < institutions.size(); i++) {
			Library l = new Library();
			l.lName = faker.artist().name() + " Library";
			l.iName = institutions.get(i).iName;
			l.lAddress = faker.address().streetAddress() + ", Montreal, QC, " + faker.bothify("H#?#?#").toUpperCase();
			primaryKeyMap.add(l.lName + l.iName);
			elements.add(l);
			numGenerated++;
		}
		
		
		while(numGenerated < num) {
			Library l = new Library();
			l.lName = faker.artist().name() + " Library";
			l.iName = institutions.get(faker.random().nextInt(institutions.size())).iName;
			if(!primaryKeyMap.contains(l.lName + l.iName)) {
				primaryKeyMap.add(l.lName + l.iName);
				l.lAddress = faker.address().streetAddress() + ", Montreal, QC, " + faker.bothify("H#?#?#").toUpperCase();
				elements.add(l);
				numGenerated += 1;
			}
		}
	}
	
	public List<Library> getGeneratedLibraries(){
		return elements.stream().map(ele -> (Library)ele).collect(Collectors.toList());
	}

}
