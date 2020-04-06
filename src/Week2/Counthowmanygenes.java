package Week2;

public class Counthowmanygenes {
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
    int countGenes(String dna)
    {
        int startIndex=0,count=0;
        while(true)
        {

            String currGene=findGene(dna,startIndex);
            if(currGene.isEmpty())
            {
                break;
            }
            count++;
            startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
        }
        return count;
    }
    void testcountGenes()
    {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
    public static void main(String args[]) {
        Counthowmanygenes m = new Counthowmanygenes();
        m.testcountGenes();
    }
}
