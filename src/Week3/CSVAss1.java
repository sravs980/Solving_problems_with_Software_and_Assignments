package Week3;
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSVAss1 {
    void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser1 = fr.getCSVParser();
        System.out.println(countryInfo(parser1,"India"));
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold","diamonds");
        CSVParser parser2 = fr.getCSVParser();
        System.out.println(numberOfExporters(parser2,"gold"));
        CSVParser parser4 = fr.getCSVParser();
        bigExporters(parser4,"$999,999,999,");

    }
    String countryInfo(CSVParser parser,String country)
    {
        String sub = "";
        for (CSVRecord record : parser) {
            String cou = record.get("Country");
            if (cou.contains(country)) {
                sub += cou+":"+record.get("Exports")+":"+record.get("Value (dollars)");
                return sub;
            }
        }
        return "NOTFOUND";
    }
    void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2)
    {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1)&&export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    int numberOfExporters(CSVParser parser,String exportItem)
    {
        int c=0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem))
                c++;
        }
        return c;
    }
    void bigExporters(CSVParser parser,String amount)
    {
        for (CSVRecord record : parser) {
            String export = record.get("Value (dollars)");
            int l = amount.length();
            int k = export.length();
            if (k >= l) {
                String country = record.get("Country");
                System.out.println(country + " " + export);
            }
        }
    }
    public static void main(String args[])
    {
        CSVAss1 t=new CSVAss1();
        t.tester();
    }
}
