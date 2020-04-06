package Week2;
import edu.duke.*;
public class Part4 {
    public static void main(String args[]) {
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"", pos);
                int end = item.indexOf("\"", pos + 1);
                System.out.println(item.substring(beg + 1, end));
            }
        }
    }
}
