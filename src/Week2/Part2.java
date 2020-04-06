package Week2;

import java.util.Scanner;

public class Part2 {
    static String findSimpleGene(String txt,int startCodon,int stopCodon)
    {
        String res="";
        if(startCodon==-1)
        {
            return res;
        }
        if(stopCodon==-1)
        {
            return res;
        }
        res+=txt.substring(startCodon,stopCodon+3);
        if((stopCodon-startCodon)%3!=0)
        {
            return "";
        }
        return res;
    }
    static void testSimpleGene()
    {
        String txt;
        Scanner sc=new Scanner(System.in);
        int start,stop;
        for(int i=0;i<5;i++)
        {
            txt=sc.nextLine();
            txt=txt.toUpperCase();
            System.out.println("given strand is "+txt);
            start=txt.indexOf("ATG");
            stop=txt.indexOf("TAA",start+3);
            System.out.println("the gene is "+findSimpleGene(txt,start,stop));

        }
    }
    public static void main(String[] args)
    {
        testSimpleGene();
    }
}