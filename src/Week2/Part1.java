package Week2;

import java.util.Scanner;

public class Part1 {
    static String findSimpleGene(String txt)
    {
        String res="";
        int start=txt.indexOf("ATG");
        if(start==-1)
        {
            return res;
        }
        int stop=txt.indexOf("TAA",start+3);
        if(stop==-1)
        {
            return res;
        }
        res+=txt.substring(start,stop+3);
        if((stop-start)%3!=0)
        {
            return "";
        }
        return res;
    }
    static void testSimpleGene()
    {
        String txt;
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<5;i++)
        {
            txt=sc.nextLine();
            System.out.println("given strand is "+txt);
            System.out.println("the gene is "+findSimpleGene(txt));
        }
    }
    public static void main(String[] args)
    {
        testSimpleGene();
    }
}