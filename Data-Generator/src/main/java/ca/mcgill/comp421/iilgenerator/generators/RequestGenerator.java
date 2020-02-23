package ca.mcgill.comp421.iilgenerator.generators;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import ca.mcgill.comp421.iilgenerator.generators.WriteGenerator.Write;

public class RequestGenerator extends Generator {
	public static class Request extends Generator.GeneratedElement{
		public String email;
		public String barCode;
		public Date date;
		public String status;
		
		@Override
		public String getFieldNames() {
			return "email;barCode;date;Status";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s;%s", email, barCode, date,status);
		}
	}
	
	private BookCopyGenerator bookCopyGenerator;
	private PatronGenerator patronGenerator;
	public static final String[] STATUS = {"in review", "declined", "approved"};

	public RequestGenerator(long seed, BookCopyGenerator bookCopyGenerator, PatronGenerator patronGenerator) {
		super(seed);
		this.bookCopyGenerator = bookCopyGenerator;
		this.patronGenerator = patronGenerator;
	}
	
	public RequestGenerator(BookCopyGenerator bookCopyGenerator, PatronGenerator patronGenerator) {
		super();
		this.bookCopyGenerator = bookCopyGenerator;
		this.patronGenerator = patronGenerator;
	}
	
	
	@Override
	public void generate(int num) {
		if(bookCopyGenerator.getGeneratedElements().size() == 0) {
			bookCopyGenerator.generate(num / 10);
		}
		
		if(patronGenerator.getGeneratedElements().size()==0) {
			patronGenerator.generate(num/2);
		}
		
		List<BookCopyGenerator.BookCopy> bookCopies = bookCopyGenerator.getGeneratedBookCopies();
		List<PatronGenerator.Patron> patrons = patronGenerator.getGeneratedPatrons();
		
		int numGenerated = 0;
		while(numGenerated < num) {
			Request r = new Request();
			r.barCode = bookCopies.get(faker.random().nextInt(bookCopies.size())).barCode;
			r.email = patrons.get(faker.random().nextInt(patrons.size())).email;
			
			if(!primaryKeyMap.contains(r.barCode + r.email)) {
				// new bar code, generate and add new book copy
				primaryKeyMap.add(r.barCode + r.email);
				r.date = new Date(faker.date().past(1460  ,TimeUnit.DAYS).getTime());
				r.status = randomStatus();
				elements.add(r);
				numGenerated++;
			}
		}
	}
	
	private String randomStatus() {
		double draw = faker.random().nextDouble();
		if(draw < 0.6) {
			return STATUS[2];
		}else if(draw < 0.8) {
			return STATUS[0];
		}else {
			return STATUS[1];
		}
	}
	
	public List<Request> getGeneratedRequest(){
		return elements.stream().map(ele -> (Request)ele).collect(Collectors.toList());
	}
}
