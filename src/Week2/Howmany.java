package Week2;

public class Howmany {
    int howMany(String a,String b)
    {
        int c=0,d=0;
        while(b.indexOf(a,d)!=-1)
        {
            c++;
            d=b.indexOf(a,d)+a.length();

        }
        return c;
    }
    void testhowMany()
    {
        System.out.println(howMany("AA","ATAAAA"));
    }
    public static void main(String args[])
    {
        Howmany m=new Howmany();
        m.testhowMany();
    }
}
