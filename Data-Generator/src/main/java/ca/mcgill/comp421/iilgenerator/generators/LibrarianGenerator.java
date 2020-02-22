package ca.mcgill.comp421.iilgenerator.generators;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LibrarianGenerator extends Generator {
	public static class Librarian extends GeneratedUser{
		String iName;
		String lName;
		String workingDays;
		@Override
		public String getFieldNames() {
			return "email;uName;phoneNumber;uAddress;workingDays;iName;lName";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s;%s;%s;%s;%s", email, uName, phoneNumber, uAddress, workingDays, iName, lName);
		}
	}
	
	private char[] workingDays = {'M', 'T', 'W', 'R', 'F'};
	private LibraryGenerator dependentGenerator;
	
	public LibrarianGenerator(long i, LibraryGenerator dependentGenerator) {
		super(i);
		this.dependentGenerator = dependentGenerator;
	}
	
	public LibrarianGenerator(LibraryGenerator dependentGenerator) {
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
		
		List<LibraryGenerator.Library> libraries = dependentGenerator.getGeneratedLibraries();
		int numGenerated = 0;
		
		//TODO: each library should have at least one librarian
		
		while(numGenerated < num) {
			Librarian l = new Librarian();
			
			l.uName = Utils.randomName(faker);
			l.email = Utils.randomEmail(faker, l.uName);
			if(primaryKeyMap.contains(l.email)) {
				continue;
			}
			
			LibraryGenerator.Library lib = libraries.get(faker.random().nextInt(libraries.size()));
			l.iName = lib.iName;
			l.lName = lib.lName;
			l.workingDays = this.randomWorkingDays();
			l.phoneNumber = Utils.formatPhoneNumber(faker.phoneNumber().cellPhone());
			l.uAddress = Utils.randomAddress(faker);
			primaryKeyMap.add(l.email);
			elements.add(l);
			numGenerated++;
		}	
	}
	
	private String randomWorkingDays() {
		String days = "";
		for(int i = 0; i < workingDays.length; i++) {
			if(faker.random().nextDouble() >= 0.5) {
				days += workingDays[i];
			}
		}
		if(days.length() == 0) {
			days += workingDays[faker.random().nextInt(workingDays.length)];
		}
		
		return days;
	}
	
	public List<Librarian> getGeneratedLibrarians(){
		return elements.stream().map(ele -> (Librarian)ele).collect(Collectors.toList());
	}
	
}
