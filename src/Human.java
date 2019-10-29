import java.util.ArrayList;

public class Human implements IHuman {
	public static class Ability implements IAbility {
		private String name;
		private ArrayList<Сharacteristic> types;

		public Ability (String name) {
			this.name = name;
			this.types = new ArrayList<> ();
		}

		public Ability (String name, Сharacteristic type) {
			this.name = name;
			this.types = new ArrayList<> ();
			addType (type);
		}

		public Ability (String name, ArrayList<Сharacteristic> types) {
			this.name = name;
			this.types = new ArrayList<> ();
			for (Сharacteristic type : types) {
				addType (type);
			}
		}

		public void addType (Сharacteristic type) {
			this.types.add (type);
		}

		public String getName () {
			return this.name;
		}

		public ArrayList<Сharacteristic> getType () {
			return this.types;
		}

		@Override
		public int hashCode () {
			final long base = 257;
			final long mod = (long) (1e9) + 7;
			long result = 0;
			if (this.name != null) {
				for (int i = 0; i < this.name.length (); i++) {
					char c = this.name.charAt (i);
					result *= base;
					result %= mod;
					result += (long) c;
					result %= mod;
				}
			}
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
			Ability toCompare = (Ability) obj;
			ArrayList<Сharacteristic> compTypes = this.getType ();
			return (this.hashCode () == toCompare.hashCode () && compTypes.equals (toCompare.getType ()));
		}

		@Override
		public String toString () {
			StringBuilder result = new StringBuilder ();
			for (int i = 0; i < types.size (); i++) {
				result.append (types.get (i));
				if (i + 1 < types.size ()) result.append (", ");
				else result.append (' ');
			}
			result.append (this.name);
			return result.toString ();
		}
	}

	private int age;
	protected int workChance, rewardChance;
	private String name;
	private ArrayList<Ability> abilities;
	private Job job;

	public Human (int age, String name) {
		this.age = age;
		this.name = name;
		this.abilities = new ArrayList<Ability> ();
		System.out.println ("Created Human's object with name: " + name + ", and age is: " + age + ".");
	}

	public int getAge () {
		return this.age;
	}

	public void setChance () {
		this.workChance = 50;
		this.rewardChance = 10;
	}

	public void setAge (int age) throws DownGrageAgeException {
		if (age < 0 || age > 150) return;
		if (age < this.age) {
			throw new DownGrageAgeException("New age is lower then current.", this, this.age, age);
		}
		this.age = age;
		System.out.println (this.name + "'s new age is: " + age + ".");
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		System.out.println (this.name + "'s new name is: " + name + ".");
		this.name = name;
	}

	public Job getJob () {
		return this.job;
	}

	public void setJob (Job job) {
		this.job = job;
		System.out.println (this.name + "'s new job is: " + job + ".");
	}

	public ArrayList<Ability> getAbilities () {
		return this.abilities;
	}

	public void setAbility (Ability ability) {
		this.abilities.add (ability);
		System.out.println (this.name + " " + ability.toString () + ".");
	}

	public void setAbility (ArrayList<Ability> abilities) {
		for (Ability ability : abilities) {
			this.setAbility (ability);
		}
	}

	@Override
	public int hashCode () {
		final long base = 257;
		final long mod = (long) (1e9) + 7;
		long result = 0;
		if (this.name != null) {
			for (int i = 0; i < this.name.length (); i++) {
				char c = this.name.charAt (i);
				result *= base;
				result %= mod;
				result += (long) c;
				result %= mod;
			}
		}
		result *= 1000;
		result += this.age;
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
		Human toCompare = (Human) obj;
		return (this.hashCode () == toCompare.hashCode () && this.abilities.equals (toCompare.abilities) && this.job == toCompare.job);
	}

	@Override
	public String toString () {
		String result = "Human name: " + this.name + ", " +
						"age: " + this.age;
		return result;
	}

	public void work () throws WorkException, RewardException {
		double success = Math.random() * 100;
		if (success <= workChance) {
			System.out.println("Work complete success!\nTrying to get reward, chance " + rewardChance +"%.");
			success = Math.random() * 100;
			if (success <= rewardChance) {
				System.out.println("Reward get success!");
			} else {
				throw new RewardException("You didn't get any reward.", this, success, this.rewardChance);
			}

		} else {
			throw new WorkException("Work didn't complete.", this, success, this.workChance);
		}
	}
}