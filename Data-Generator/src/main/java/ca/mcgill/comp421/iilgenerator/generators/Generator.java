package ca.mcgill.comp421.iilgenerator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public abstract class Generator {
	public abstract static class GeneratedElement{
		
	}
	
	protected List<GeneratedElement> elements;
	protected Random r;
	protected Faker faker;
	
	protected Generator(long seed) {
		r = new Random(seed);
		elements = new ArrayList<GeneratedElement>();
		faker = new Faker(Utils.locale, r);
	}
	
	protected Generator() {
		r = new Random();
		elements = new ArrayList<GeneratedElement>();
		faker = new Faker(r);
	}
	
	public void generate(int min, int max) {
		int range = max - min;
		int numberToGenerate = r.nextInt() % range + min;
		generate(numberToGenerate);
	}
	
	abstract void generate(int num);
	
	public List<GeneratedElement> getGeneratedElements(){
		return elements;
	}
	
	public void clearGeneratedElements() {
		elements.clear();
	}
}
