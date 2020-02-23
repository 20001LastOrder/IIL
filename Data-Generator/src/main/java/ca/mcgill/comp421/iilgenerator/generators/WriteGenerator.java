package ca.mcgill.comp421.iilgenerator.generators;

import java.util.List;
import java.util.stream.Collectors;

public class WriteGenerator extends Generator {
	public static class Write extends Generator.GeneratedElement{
		public int authorId;
		public String isbnNumber;
		
		@Override
		public String getFieldNames() {
			return "authorId;isbnNumber";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s", authorId, isbnNumber);
		}
	}
	
	private AuthorGenerator authorGenerator;
	private BookGenerator bookGenerator;

	public WriteGenerator(long seed, AuthorGenerator authorGenerator, BookGenerator bookGenerator) {
		super(seed);
		this.authorGenerator = authorGenerator;
		this.bookGenerator = bookGenerator;
	}
	
	public WriteGenerator(AuthorGenerator authorGenerator, BookGenerator bookGenerator) {
		super();
		this.authorGenerator = authorGenerator;
		this.bookGenerator = bookGenerator;
	}
	
	
	@Override
	public void generate(int num) {
		if(authorGenerator.getGeneratedElements().size() == 0) {
			authorGenerator.generate(num / 10);
		}
		
		if(bookGenerator.getGeneratedElements().size()==0) {
			bookGenerator.generate(num/2);
		}
		
		List<AuthorGenerator.Author> authors = authorGenerator.getGeneratedAuthors();
		List<BookGenerator.Book> books = bookGenerator.getGeneratedBooks();
		int numGenerated = 0;
		
		while(numGenerated < num) {
			Write write = new Write();
			write.authorId = authors.get(faker.random().nextInt(authors.size())).autherId;
			write.isbnNumber = books.get(faker.random().nextInt(books.size())).isbnNumber;
			if(!primaryKeyMap.contains(write.authorId + write.isbnNumber)) {
				// new bar code, generate and add new book copy
				primaryKeyMap.add(write.authorId + write.isbnNumber);
				
				elements.add(write);
				numGenerated++;
			}
		}
	}
	
	public List<Write> getGeneratedWrites(){
		return elements.stream().map(ele -> (Write)ele).collect(Collectors.toList());
	}
}
