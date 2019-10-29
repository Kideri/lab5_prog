import java.util.ArrayList;

public interface IHuman {
	public int getAge ();
	public void setAge (int age) throws DownGrageAgeException;
	public String getName ();
	public void setName (String name);
	public Job getJob ();
	public void setJob (Job job);
	public void work () throws WorkException, RewardException;
	public void setChance ();
}