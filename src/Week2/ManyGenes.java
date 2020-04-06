package Week2;

public class ManyGenes {
    int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        int currIndex=dna.indexOf(stopCodon,startIndex);
        while(currIndex!=-1)
        {

            int diff=currIndex-startIndex;
            if((diff%3)==0)
                return currIndex;
            else
                currIndex=dna.indexOf(stopCodon,currIndex);
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

        int min=0;
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
    void printAllGenes(String dna)
    {
        int startIndex=0;
        while(true)
        {

            String currGene=findGene(dna,startIndex);
            if(currGene.isEmpty())
            {
                break;
            }
            System.out.println(currGene);
            startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
        }
    }
    void testFindStopCodon()
    {
        System.out.println(findStopCodon("ATGTAA",0,"TAA"));
        System.out.println(findStopCodon("ATGTAGTAAT",0,"AAA"));
        System.out.println(findStopCodon("ATGAAGTTATAGTAA",0,"TAA"));

    }
    void testFindGene()
    {
        System.out.println(findGene("TAATAGTAG",0));
        System.out.println(findGene("ATGGTATGA",0));
        System.out.println(findGene("ATGTAATAGTGA",0));
        System.out.println(findGene("ATGATGTG",0));
        System.out.println(findGene("ATGGGTG",0));

    }
    public static void main(String args[])
    {
        ManyGenes m=new ManyGenes();
       m.testFindStopCodon();
       m.testFindGene();
       m.printAllGenes("ATGGTCTAATTTATGCTGCAACGGTGAAGA");
    }
}
