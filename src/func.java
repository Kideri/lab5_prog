import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jdk.nashorn.internal.parser.Scanner;

import java.io.*;
import java.util.Hashtable;
import java.util.Set;

public class func {
    private static Gson gson = new Gson();
    private static OutputStreamWriter writer = new OutputStreamWriter(System.out);
    private static File xmlFile = new File (System.getenv("inputFile"));
    private static XStream xStream = new XStream(new StaxDriver());
    private static File extra_xmlFile = new File ("extra_save.xml");

    static void get_base() {
        if (!xmlFile.exists()) {
            try {
                xmlFile.createNewFile();
                System.out.println("File created");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        if (extra_xmlFile.exists()) {
            System.out.println("Detected extra save of objects!\nDo you want to load it?");
            String ans = Main.in.nextLine();
            if (ans.equals("yes")) {
                try {
                    Main.elements = (Hashtable<String, Manga>)xStream.fromXML(extra_xmlFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }
        try {
            Main.elements = (Hashtable<String, Manga>)xStream.fromXML(xmlFile);
        } catch (Exception ex) {
            try {
                writer.write("No objects detected\nAddind default");
                writer.flush();
                Main.elements.put("default", new Manga ().getEmpty());
                writer.write("Default empty object added");
                writer.flush();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    /**
     * Removing all elements equals to params
     * @param params
     */
    public static void remove_all (String params) {
        params = Main.jsonGetter (params);
        Manga element;
        try {
            element = gson.fromJson(params, Manga.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        Main.elements.values().removeIf(element::equals);
    }

    /**
     * Removing params element
     * @param params
     */
    public static void remove (String params) {
        Main.elements.remove(params);
    }

    /**
     * Show info about all elements
     */
    public static void show () {
        Set<String> keys = Main.elements.keySet();
        for (String key : keys) {
            try {
                writer.write("--------------\n" +
                                "Key : " + key + "\n" +
                                "Value:\n" + Main.elements.get(key).toString());
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void show_json () {
        Set<String> keys = Main.elements.keySet();
        for (String key : keys) {
            try {
                writer.write("--------------\n" +
                        "Key : " + key + "\n" +
                        "Value:\n" + gson.toJson (Main.elements.get(key)) + "\n");
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Adding element if it will be the biggest
     * @param params
     */
    public static void add_if_max (String params) {
        String element_key = parse_first(params);
        params = parse_other(params);
        Manga element;
        params = Main.jsonGetter(params);try {
            element = gson.fromJson(params, Manga.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        boolean ok = true;
        Set<String> keys = Main.elements.keySet();
        for (String key : keys) {
            if (Main.elements.get(key).compareTo(element) <= 0) {
                ok = false;
                break;
            }
        }
        if (ok) {
            Main.elements.put(element_key, element);
        }
    }

    /**
     * Show info about Hashtable <br />
     * Objects type, date of creation and object count
     */
    public static void info () {
        try {
            writer.write("Objects type: " + Manga.class.toString() + "\n" +
                            "Initialization date: " + Main.date.toString() + "\n" +
                            "Objects count: " + Main.elements.size() + "\n");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Removing all elements with key lower then params
     * @param params
     */
    public static void remove_lower (String params) {
        Set<String> keys = Main.elements.keySet();
        for (String key : keys) {
            if (key.compareTo(params) > 0) {
                Main.elements.remove(key);
            }
        }
    }


    /**
     * Adding params element
     * @param params
     */
    public static void insert (String params) {
        String element_key = parse_first(params);
        params = parse_other(params);
        params = Main.jsonGetter(params);
        Manga element;
        try {
            element = gson.fromJson(params, Manga.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        Main.elements.put(element_key, element);
    }

    static void save () {
        try {
            OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(xmlFile));
            fileWriter.write(xStream.toXML(Main.elements));
            fileWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    static void extra_save () {
        try {
            OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(extra_xmlFile));
            fileWriter.write(xStream.toXML(Main.elements));
            fileWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    static String parse_first (String input) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                break;
            }
            tmp.append(input.charAt(i));
        }
        return tmp.toString();
    }

    static String parse_other (String input) {
        StringBuilder tmp = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                if (!flag) {
                    flag = true;
                    continue;
                }
            }
            if (flag) {
                tmp.append(input.charAt(i));
            }
        }
        return tmp.toString();
    }

    static void remove_extra () {
        if (extra_xmlFile.exists()) {
            extra_xmlFile.delete();
        }
    }
}
