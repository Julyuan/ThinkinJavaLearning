package Chapter14.typeinfo;

import java.lang.reflect.*;
import java.util.regex.*;
import static net.mindview.util.Print.*;


public class ShowMethods {
    private static String usage =
            "usage:\n"+
            "ShowMethods qualified.class.name\n"+
            "To show all methods in class or:\n"+
            "ShowMethods qualified.class.name.word\n"+
            "To search for methods involving 'word'";

    private static Pattern p = Pattern.compile("\\w+\\.");
    public static void main(String[] args){
        if(args.length < 1){
            print(usage);
            System.exit(0);
        }

        int lines = 0;
        try{
            Class<?>c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();

        }catch (ClassNotFoundException e){
            print("No such class: " + e);
        }
    }
}
