import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
	static Hashtable<String, Manga> elements = new Hashtable<String, Manga>();
	static Date date = new Date();
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		func.get_base();
		do {
			String input = in.nextLine();
			String command = func.parse_first(input), params = func.parse_other(input);
			boolean exit = false;
			switch (command) {
				case "remove_all":
					func.remove_all(params);
					break;
				case "remove":
					func.remove(params);
					break;
				case "show":
					func.show();
					break;
				case "add_if_max":
					func.add_if_max(params);
					break;
				case "info":
					func.info();
					break;
				case "remove_lower":
					func.remove_lower(params);
					break;
				case "insert":
					func.insert(params);
					break;
				case "show_json":
					func.show_json();
					break;
				case "save":
					func.save();
					break;
				case "exit":
					exit = true;
					func.remove_extra();
					break;
				default:
					break;
			}
			func.extra_save();
			if (exit) {
				break;
			}
		} while (true);
	}

	private static boolean isGood (String toCheck) {
		int cnt = 0;
		for (int i = 0; i < toCheck.length(); i++) {
			if (toCheck.charAt(i) == '{') {
				cnt++;
			} else if (toCheck.charAt(i) == '}') {
				cnt--;
			}
			if (cnt < 0) {
				return true;
			}
		}
		if (cnt == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static String jsonGetter (String toGet) {
		StringBuilder current = new StringBuilder(toGet);
		while (!isGood(current.toString())) {
			String inp = in.nextLine();
			current.append(inp);
		}
		return current.toString();
	}
}