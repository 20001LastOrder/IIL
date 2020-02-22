package ca.mcgill.comp421.iilgenerator.generators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public abstract class Generator {

	public abstract static class GeneratedElement{
		public abstract String getFieldNames();
	}
	
	protected List<GeneratedElement> elements;
	private Random r;
	protected Faker faker;
	protected static HashSet<String> primaryKeyMap;

	protected Generator(long seed) {
		r = new Random(seed);
		elements = new ArrayList<GeneratedElement>();
		faker = new Faker(Utils.locale, r );
		primaryKeyMap = new HashSet<String>();
	}
	
	protected Generator() {
		r = new Random();
		elements = new ArrayList<GeneratedElement>();
		faker = new Faker(r);
		primaryKeyMap = new HashSet<String>();
	}
	
	public void generate(int min, int max) {
		int range = max - min;
		int numberToGenerate = r.nextInt() % range + min;
		generate(numberToGenerate);
	}
	
	abstract void generate(int num);
	
	public HashSet<String> getPrimaryKeySet(){
		return primaryKeyMap;
	}
	
	public List<GeneratedElement> getGeneratedElements(){
		return elements;
	}
	
	public void clearGeneratedElements() {
		elements.clear();
		primaryKeyMap.clear();
	}
}
