package Week4;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BBAss1 {
    public void printNames () {
        FileResource fr = new FileResource("/home/sravanthi/Documents/us_babynames_test/yob2012short.csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int c=0;
        int Boys=0;
        int Girls=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                Boys ++;
            } else {
                Girls ++;
            }
        }
        c=Girls+Boys;
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls= " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("Number of girl names = "+Girls);
        System.out.println("Number of Boys names = "+Boys);
        System.out.println("total names in the file = "+c);
    }
    int getRank(int year,String name,String gender)
    {

        FileResource fr=new FileResource("/home/sravanthi/Documents/us_babynames_test/yob"+year+"short.csv");
        int k=1,f=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (gender.equals("M") &&rec.get(1).equals("M")) {
                if (rec.get(0).contains(name)) {
                    f++;
                    break;
                }
                else {
                    k++;
                }
            }
            else if(gender.equals("F") && rec.get(1).equals("F")){
                if (rec.get(0).equals(name)) {
                    f++;
                    break;
                } else
                    k++;
            }
        }
        if(f!=1&&k>1)
            return -1;
        return k;
    }
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("/home/sravanthi/Downloads/data/yob2014.csv");
        totalBirths(fr);
    }
    String getName(int year,int rank,String gender)
    {
        FileResource fr=new FileResource("/home/sravanthi/Documents/us_babynames_test/yob"+year+"short.csv");
        int f=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (gender.equals("M") && rec.get(1).equals("M")) {
                f++;
                if (f == rank)
                return rec.get(0);
            }
            else if (gender.equals("F") && rec.get(1).equals("F")) {
                f++;
                if (f == rank)
                    return rec.get(0);
            }
        }
        return "NO NAME";
    }
    void whatIsNameInYear(String name,int year,int newYear,String gender )
    {
        int k=getRank(year,name,gender);
        String st=getName(newYear,k,gender);
        System.out.println(name+" born in "+year+" would be "+st+" if she was born in "+newYear+".");
    }
    int yearOfHighestRank(String name,String gender)
    {
        int rank=Integer.MAX_VALUE;
        String file="";
        String filename="";
        int year=-1;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int k = 1, h = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (gender.equals("M") && rec.get(1).equals("M")) {
                    if (rec.get(0).contains(name)) {
                        h++;

                        break;
                    } else {
                        k++;
                    }
                        file = f.getName();
                } else if (gender.equals("F") && rec.get(1).equals("F")) {
                    if (rec.get(0).equals(name)) {
                        h++;
                        break;
                    } else
                        k++;
                    file = f.getName();
                }
            }
            if (h != 1 && k > 1)
                k = -1;
            if(rank>k) {
                rank = k;
                filename=file;
            }
        }
        filename=filename.replaceAll("[^\\d]","");
        year=Integer.parseInt(filename);
        return year;
    }
    double getAverageRank(String name,String gender)
    {
        double rank=0.0;
        int c=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int k = 1, h = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (gender.equals("M") && rec.get(1).equals("M")) {
                    if (rec.get(0).contains(name)) {
                        h++;

                        break;
                    } else {
                        k++;
                    }
                } else if (gender.equals("F") && rec.get(1).equals("F")) {
                    if (rec.get(0).equals(name)) {
                        h++;
                        break;
                    } else
                        k++;
                }
            }
            if (h != 1 && k > 1)
                k = -1;
            rank = rank + k;
            c++;
        }
        return rank/c;
    }
    int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        FileResource fr=new FileResource("/home/sravanthi/Documents/us_babynames_test/yob"+year+"short.csv");
        int rank=getRank(year,name,gender);
        int f=0,Totalbirth=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (gender.equals("M") && rec.get(1).equals("M")) {
                f++;
                if (f != rank)
                    Totalbirth+=Integer.parseInt(rec.get(2));
                else
                    break;
            }
            else if (gender.equals("F") && rec.get(1).equals("F")) {
                f++;
                if (f != rank)
                    Totalbirth+=Integer.parseInt(rec.get(2));
                else
                    break;
            }
        }
        return Totalbirth;
    }
    public static void main(String args[])
    {
        BBAss1 b=new BBAss1();
        b.testTotalBirths();
        System.out.println("The rank is = "+b.getRank(2012,"Mason","M"));
        System.out.println("Name is :"+b.getName(2012,3,"F"));
        b.whatIsNameInYear("Isabella",2012,2014,"F");
        System.out.println("yearofHighestRank: "+b.yearOfHighestRank("Mason","M"));
        System.out.println("AverageRank: "+b.getAverageRank("Mason","M"));
        System.out.println("TotalBirthsRankedHigher: "+b.getTotalBirthsRankedHigher(2012,"Ethan","M"));

    }
}