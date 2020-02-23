package ca.mcgill.comp421.iilgenerator.generators;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BookGenerator extends Generator {
	public static class Book extends Generator.GeneratedElement{
		public String isbnNumber;
		public String title;
		public String category;
		public Date publicationDate;
		public String publisher;
		public String description;
		
		public String toString() {
			return String.format("%s;%s;%s;%s;%s;%s", isbnNumber, title, category, publicationDate, publisher, description);
		}

		@Override
		public String getFieldNames() {
			return "isbnNumber;title;category;publicationDate;publisher;description";
		}
	}
	
	public BookGenerator() {
		super();
	}
	
	public BookGenerator(long seed) {
		super(seed);
	}

	@Override
	public void generate(int num) {
		int numGenerated = 0;
		while(numGenerated < num) {
			Book b = new Book();
			b.isbnNumber = faker.numerify("###-#-##-######-#");
			if(!primaryKeyMap.contains(b.isbnNumber)) {
				primaryKeyMap.add(b.isbnNumber);
				b.title = faker.book().title().replaceAll("'", "");
				b.category = faker.book().genre();
				b.publisher = faker.book().publisher();
				b.description = faker.lorem().paragraph(3);
				b.publicationDate = new Date(faker.date().past(3650, TimeUnit.DAYS).getTime());
				elements.add(b);
				numGenerated += 1;
			}
		}	
	}
	
	public List<Book> getGeneratedBooks(){
		return elements.stream().map(ele -> (Book)ele).collect(Collectors.toList());
	}
}
