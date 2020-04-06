package Week2;

public class Part3 {
    boolean twoOccurrences(String a,String b)
    {
        int len1=a.length();
        if(b.contains(a))
        {
            return b.indexOf(a, b.indexOf(a) + len1) == -1;
        }
        return false;
    }
    String lastPart(String a,String b)
    {
        String st="";
        int len=a.length();
        if(b.contains(a))
        {
            st+=b.substring(b.indexOf(a)+len);
            return st;
        }
        return b;
    }
    void testing()
    {
        System.out.println(twoOccurrences("world","helloworldMyworld"));
        System.out.println(twoOccurrences("by","Book was written by me"));
        System.out.println(twoOccurrences("the","Bring the chair"));
        System.out.println(twoOccurrences("a","so many people are suffering from corona"));
        System.out.println("\n"+lastPart("aya","banana"));
        System.out.println(lastPart("or","corona"));
        System.out.println(lastPart("done","lockdown"));
        System.out.println(lastPart("so","isolation"));
    }
    public static void main(String[] args)
    {
        Part3 p=new Part3();
        p.testing();
    }
}
