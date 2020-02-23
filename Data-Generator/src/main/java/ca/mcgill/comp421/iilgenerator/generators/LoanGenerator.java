package ca.mcgill.comp421.iilgenerator.generators;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.mutable.MutableFloat;

public class LoanGenerator extends Generator {
	public static class Loan extends Generator.GeneratedElement{
		public int loanId;
		public Date startDate;
		public Date requiredReturnDate;
		public Date actualReturnDate;
		public float fine;
		public boolean hasRequest;
		public String email;
		public String barCode;
		
		@Override
		public String getFieldNames() {
			return "loanId;startDate;requiredReturnDate;actualReturnDate;fine;hasRequest;email;barCode";
		}
		
		@Override
		public String toString() {
			return String.format("%s;%s;%s;%s;%s;%s;%s;%s", loanId, startDate, requiredReturnDate,
								actualReturnDate, fine, hasRequest, email, barCode);
		}
	}
	
	private BookCopyGenerator bookCopyGenerator;
	private PatronGenerator patronGenerator;
	private static final float FINE_PER_DAY = 5;
	public LoanGenerator(long seed, BookCopyGenerator bookCopyGenerator, PatronGenerator patronGenerator) {
		super(seed);
		this.bookCopyGenerator = bookCopyGenerator;
		this.patronGenerator = patronGenerator;
	}
	
	public LoanGenerator(BookCopyGenerator bookCopyGenerator, PatronGenerator patronGenerator) {
		super();
		this.bookCopyGenerator = bookCopyGenerator;
		this.patronGenerator = patronGenerator;
	}
	
	
	@Override
	public void generate(int num) {
		if(bookCopyGenerator.getGeneratedElements().size() == 0) {
			bookCopyGenerator.generate(num / 2);
		}
		
		if(patronGenerator.getGeneratedElements().size()==0) {
			patronGenerator.generate(num/2);
		}
		
		List<BookCopyGenerator.BookCopy> bookCopies = bookCopyGenerator.getGeneratedBookCopies();
		List<PatronGenerator.Patron> patrons = patronGenerator.getGeneratedPatrons();
		int numGenerated = 0;
		
		while(numGenerated < num) {
			Loan loan = new Loan();
			loan.loanId = faker.random().nextInt(0, 1000000 );
			if(!primaryKeyMap.contains(loan.loanId+"")) {
				// new loan id, create a new loan
				primaryKeyMap.add(loan.loanId+"");
				loan.startDate = randomStartDate();
				loan.requiredReturnDate = randomRequiredReturnDate(loan.startDate);
				MutableFloat fine = new MutableFloat(0);
				loan.actualReturnDate = RandomActualReturnedDate(loan.startDate, loan.requiredReturnDate, fine);
				loan.fine = fine.floatValue();
				loan.hasRequest = randomBoolean();
				loan.barCode = bookCopies.get(faker.random().nextInt(bookCopies.size())).barCode;
				loan.email = patrons.get(faker.random().nextInt(patrons.size())).email;
				elements.add(loan);
				numGenerated++;
			}
		}
	}
	
	private Date randomStartDate() {
		double draw = faker.random().nextDouble();
		
		if(draw > 0.5) {
			// create potential on going loan
			return new Date(faker.date().past(30, TimeUnit.DAYS).getTime());
		}else {
			// create old start time
			return new Date(faker.date().past(1460, TimeUnit.DAYS).getTime());
		}
	}
	
	private Date randomRequiredReturnDate(Date startDate) {
		// convert for faker generation
		java.util.Date date = new java.util.Date(startDate.getTime());
		return new Date(faker.date().future(60, TimeUnit.DAYS, date).getTime());
	}
	
	private boolean randomBoolean() {
		double draw = faker.random().nextDouble();
		if(draw > 0.8) {
			return true;
		}else {
			return false;
		}
	}
	
	private Date RandomActualReturnedDate(Date startDate, Date RequiredEndDate, MutableFloat fine) {
		java.util.Date sDate = new java.util.Date(startDate.getTime());
		java.util.Date rDate = new java.util.Date(RequiredEndDate.getTime());
		
		// if required Return date is after current, return default date
		if(rDate.after(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))) {
			return new Date(0);
		}
		
		double draw = faker.random().nextDouble();
		if(draw > 0.8) {
			Date actual = new Date(faker.date().future(20, TimeUnit.DAYS, rDate).getTime());
			fine.setValue((actual.getTime() - rDate.getTime()) / TimeUnit.DAYS.toMillis(1)* FINE_PER_DAY);
			return actual;
		}else {
			return new Date(faker.date().between(sDate, rDate).getTime());
		}
	}
	
	public List<Loan> getGeneratedLoans(){
		return elements.stream().map(ele -> (Loan)ele).collect(Collectors.toList());
	}
}
