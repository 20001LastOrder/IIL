package ca.mcgill.comp421.iilgenerator.generators;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorGenerator extends Generator {
	public static class Author extends Generator.GeneratedElement{
		public int autherId;
		public String aName;
		
		public String toString() {
			return String.format("%s;%s", autherId, aName);
		}

		@Override
		public String getFieldNames() {
			return "autherId;aName";
		}
	}
	
	public AuthorGenerator() {
		super();
	}
	
	public AuthorGenerator(long seed) {
		super(seed);
	}

	@Override
	public void generate(int num) {
		int numGenerated = 0;
		while(numGenerated < num) {
			Author a = new Author();
			a.autherId = faker.random().nextInt(1000);
			if(!primaryKeyMap.contains(a.autherId+"")) {
				primaryKeyMap.add(a.autherId+"");
				// do not want any ' in the database
				a.aName = faker.book().author();
				elements.add(a);
				numGenerated += 1;
			}
		}	
	} 
	
	public List<Author> getGeneratedAuthors(){
		return elements.stream().map(ele -> (Author)ele).collect(Collectors.toList());
	}
}
