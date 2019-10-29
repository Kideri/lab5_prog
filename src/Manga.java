import java.util.ArrayList;

public class Manga extends Book {
	private String translateName;
	private String paintStyle;
//	private ArrayList<Human.Ability> abilities;

	public Manga () {
		super ();
		this.translateName = null;
		this.paintStyle = null;
//		this.abilities = new ArrayList<Human.Ability> ();
		System.out.println ("Created manga!");
	}
	public Manga (String name, String translateName) {
		super (name);
		this.translateName = translateName;
		this.paintStyle = null;
//		this.abilities = new ArrayList<Human.Ability> ();
		System.out.println ("Created manga!");
	}
	public Manga (String name, String translateName, int sheetCount) {
		super (name, sheetCount);
		this.translateName = translateName;
		this.paintStyle = null;
//		this.abilities = new ArrayList<Human.Ability> ();
		System.out.println ("Created manga!");
	}
	public Manga (String name, String translateName, int sheetCount, String paintStyle) {
		super (name, sheetCount);
		this.translateName = translateName;
		this.paintStyle = paintStyle;
//		this.abilities = new ArrayList<Human.Ability> ();
		System.out.println ("Created manga!");
	}

	public Manga getEmpty () {
		return new Manga ("emptyName", "emptyTranslateName", 0, "emptypaintStyle");
	}

	public void setAbility (Human.Ability ability) {
//		this.abilities.add (ability);
		System.out.println (this.getName () + " " + ability.toString () + ".");
	}

	public String getStyle () {
		return this.paintStyle;
	}

	public String getTranslateName () {
		return this.translateName;
	}

	@Override
	public int hashCode () {
		final long base = 257;
		final long mod = (long) (1e9) + 7;
		long result = 0;
		String name = this.getName ();
		if (name != null) {
			for (int i = 0; i < name.length (); i++) {
				char c = name.charAt (i);
				result *= base;
				result %= mod;
				result += (long) c;
				result %= mod;
			}
			for (int i = 0; i < this.translateName.length (); i++) {
				char c = this.translateName.charAt (i);
				result *= base;
				result %= mod;
				result += (long) c;
				result %= mod;
			}
		}
		if (this.paintStyle != null) {
			for (int i = 0; i < this.paintStyle.length (); i++) {
				char c = this.paintStyle.charAt (i);
				result *= base;
				result %= mod;
				result += (long) c;
				result %= mod;
			}
		}
		result *= 10000;
		result += this.getSheetCount ();
		result %= mod;
		return (int) result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass () != obj.getClass ())
			return false;
		Manga toCompare = (Manga) obj;
		return this.hashCode () == toCompare.hashCode ();
	}

	@Override
	public String toString () {
		String result = "========================================" + "\n" +
						"Object info:" + "\n" +
						"Name: " + (this.getName () == null ? "null" : this.getName ()) + "\n" +
						"Traslate name: " + (this.translateName == null ? "null" : this.translateName) + "\n" +
						"Sheet count: " + this.getSheetCount ()  + "\n" +
						"Paint style: " + (this.paintStyle == null ? "null" : this.paintStyle) + "\n" +
						"========================================" + "\n";
		return result;
	}

	public int compareTo (Manga toCompare) {
		if (this.equals(toCompare)) return 0;
		return this.getName().compareTo(toCompare.getName());
	}
}