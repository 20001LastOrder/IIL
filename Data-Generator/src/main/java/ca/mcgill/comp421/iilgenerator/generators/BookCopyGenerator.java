package ca.mcgill.comp421.iilgenerator.generators;

import java.util.List;
import java.util.stream.Collectors;

public class BookCopyGenerator extends Generator {
	public static class BookCopy extends Generator.GeneratedElement{
		public String barCode;
		public String lName;
		public String iName;
		public String isbnNumber;
		
		@Override
		public String getFieldNames() {
			return "barCode;lName;iName;isbnNumber";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s;%s", barCode, lName, iName,isbnNumber);
		}
	}
	
	private LibraryGenerator libraryGenerator;
	private BookGenerator bookGenerator;

	public BookCopyGenerator(long seed, LibraryGenerator libraryGenerator, BookGenerator bookGenerator) {
		super(seed);
		this.libraryGenerator = libraryGenerator;
		this.bookGenerator = bookGenerator;
	}
	
	public BookCopyGenerator(LibraryGenerator libraryGenerator, BookGenerator bookGenerator) {
		super();
		this.libraryGenerator = libraryGenerator;
		this.bookGenerator = bookGenerator;
	}
	
	
	@Override
	public void generate(int num) {
		if(libraryGenerator.getGeneratedElements().size() == 0) {
			libraryGenerator.generate(num / 10);
		}
		
		if(bookGenerator.getGeneratedElements().size()==0) {
			bookGenerator.generate(num/2);
		}
		
		List<LibraryGenerator.Library> libraries = libraryGenerator.getGeneratedLibraries();
		List<BookGenerator.Book> books = bookGenerator.getGeneratedBooks();
		int numGenerated = 0;
		
		while(numGenerated < num) {
			BookCopy bCopy = new BookCopy();
			bCopy.barCode = faker.numerify("#-###-###-###-#");
			if(!primaryKeyMap.contains(bCopy.barCode)) {
				// new bar code, generate and add new book copy
				primaryKeyMap.add(bCopy.barCode);
				LibraryGenerator.Library library = libraries.get(faker.random().nextInt(libraries.size()));
				BookGenerator.Book book = books.get(faker.random().nextInt(books.size()));
				bCopy.iName = library.iName;
				bCopy.lName = library.lName;
				bCopy.isbnNumber = book.isbnNumber;
				elements.add(bCopy);
				numGenerated++;
			}
		}
	}
	
	public List<BookCopy> getGeneratedBookCopies(){
		return elements.stream().map(ele -> (BookCopy)ele).collect(Collectors.toList());
	}
}
