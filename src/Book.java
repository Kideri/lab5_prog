import java.util.ArrayList;

public abstract class Book {
	private String name;
	private int sheetCount;
	private ArrayList<Human.Ability> abilities;

	public Book () {
		this.name = null;
		this.sheetCount = 0;	
	}
	public Book (String name) {
		this.name = name;
		this.sheetCount = 0;	
	}
	public Book (String name, int sheetCount) {
		this.name = name;
		this.sheetCount = sheetCount;	
	}

	public String getName () {
		return this.name;
	}

	public int getSheetCount () {
		return this.sheetCount;
	}

	public void setAbility (Human.Ability ability) {
		this.abilities.add (ability);
	}
}