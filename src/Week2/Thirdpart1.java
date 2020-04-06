package Week2;

import edu.duke.*;

public class Thirdpart1 {
    int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        int currIndex=dna.indexOf(stopCodon,startIndex);
        while(currIndex!=-1)
        {

            int diff=currIndex-startIndex;
            if((diff%3)==0)
                return currIndex;
            else
                currIndex=dna.indexOf(stopCodon,currIndex+1);
        }
        return -1;
    }
    String findGene(String dna,int where)
    {
        int start=dna.indexOf("ATG",where);
        if(start==-1)
            return "";
        int index1=findStopCodon(dna,start,"TAA");
        int index2=findStopCodon(dna,start,"TAG");
        int index3=findStopCodon(dna,start,"TGA");
        int min;
        if(index1==-1||(index3!=-1&&index3<index1))
            min=index3;
        else
            min=index1;
        if(min==-1||(index2!=-1&&index2<min))
            min=index2      ;
        if(min==-1)
            return "";
        return dna.substring(start,min+3);
    }
    StorageResource getAllGenes(String dna)
    {
        StorageResource st=new StorageResource();
        int startIndex=0;
        while(true)
        {
            String currGene=findGene(dna,startIndex);
            if(currGene.isEmpty())
            {
                break;
            }
            st.add(currGene);
            startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
        }
        return st;
    }
    float cgRatio(String dna)
    {
        int countC=0,countG=0,d=0;
        while(dna.indexOf("C",d)!=-1)
        {
            countC++;
            d=dna.indexOf("C",d)+1;

        }
        d=0;
        while(dna.indexOf("G",d)!=-1)
        {
            countG++;
            d=dna.indexOf("G",d)+1;

        }
        return (float)(countC+countG)/dna.length();
    }
    int countCTG(String dna)
    {
        int c=0,d=0;
        while(dna.indexOf("CTG",d)!=-1)
        {
            c++;
            d=dna.indexOf("G",d)+3;

        }
        return c;
    }
    void processGenes(StorageResource sr)
    {
        int c=0,d=0,len=0;
        for(String g:sr.data())
        {
            if(g.length()>60) {
                System.out.println("Strings of length greater than 60: "+g);
                c++;
            }
            float a=cgRatio(g);
            if(a>0.35) {

                System.out.println("Strings whose cg ratio is > than 0.35: "+g);
                d++;
            }
            if(len<g.length())
                len=g.length();
        }
        System.out.println("No. of Strings greater than 60: "+c);
        System.out.println("No. of Strings cgratio is greater than 0.35: "+d);
        System.out.println("Length of longest gene: "+len);
    }
    public void testProcessGenes()
    {
        FileResource fr=new FileResource("/home/sravanthi/Documents/brca1line.fa");
        String dna=fr.asString();
        dna=dna.toUpperCase();
        StorageResource genes=getAllGenes(dna);
        processGenes(genes);
    }
    public static void main(String[] args)
    {
        Thirdpart1 t=new Thirdpart1();
        StorageResource genes=t.getAllGenes("ATGGTCTAATTTATGCTGCAACGGTGAAGA");
        for(String g:genes.data())
        {
            System.out.println(g);
        }
        System.out.println(t.cgRatio("ATGCCATAG"));
        System.out.println(t.countCTG("ATGCTGATACTGG"));
        t.testProcessGenes();
    }
}
