package Week3;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVAss2 {
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp < largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    String fileWithColdestTemperature()
    {
        File temp=null;
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp < largestTemp) {
                    largestSoFar = currentRow;
                    temp=f;
                }
            }
        }
        return temp.getName();
    }
    CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowhumidity = null;
        for (CSVRecord currentRow : parser) {
            int currentTemp=0;
            int largestTemp=0;
            if (lowhumidity == null) {
                lowhumidity = currentRow;
            }
            else {
                String sh=String.valueOf(currentRow.get("Humidity"));
                String sh1=String.valueOf(currentRow.get("Humidity"));
                if(sh.contains("N/A")||sh1.contains("N/A")){}
                else {
                    currentTemp = Integer.parseInt(currentRow.get("Humidity"));
                    largestTemp = Integer.parseInt(lowhumidity.get("Humidity"));
                    if (currentTemp < largestTemp)
                        lowhumidity = currentRow;
                }
            }
        }
        return lowhumidity;
    }
    void lowestHumidityInManyFiles()
    {
        CSVRecord largestSoFar = null;
        CSVRecord currentRow=null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            currentRow = lowestHumidityInFile(fr.getCSVParser());
        }
        System.out.println("Lowest Humidity was "+currentRow.get("Humidity")+" at "+currentRow.get("DateUTC"));
    }
    double AverageTemperatureInFile(CSVParser parser)
    {
        double avg=0.0;
        int c=0;
        for (CSVRecord currentRow : parser) {
            avg+= Double.parseDouble(currentRow.get("TemperatureF"));
            c++;
        }
        return avg/c;
    }
    double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        double avg=0.0;
        int c=0;
        for (CSVRecord currentRow : parser) {
            int t=Integer.parseInt(currentRow.get("Humidity"));
            if(t>=value){
                avg += Double.parseDouble(currentRow.get("TemperatureF"));
                c++;
            }
        }
        return avg/c;
    }
    public void testcoldestHourInFile() {
        FileResource fr = new FileResource("/home/sravanthi/Downloads/data/2015/weather-2015-01-01.csv");
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST")+ "\n DewpointF: " +largest.get("Dew PointF")+"\tHumidity: "+largest.get("Humidity")
                +"\tSea Level PressureIn: "+largest.get("Sea Level PressureIn")+" \tVisibility: "+largest.get("VisibilityMPH")
                +" \tWind Direction: "+largest.get("Wind Direction")+" \tWind SpeedMPH: "+largest.get("Wind SpeedMPH")+" \tGust SpeedMPH: "+largest.get("Gust SpeedMPH")+
                " \tPrecipitationIn: "+largest.get("PrecipitationIn")+" \tConditions: "+largest.get("Conditions")+" \tDateUTC: "+largest.get("DateUTC"));
    }
    public void testFileWithColdestTemperature()
    {
        System.out.println("Coldest day was in file "+fileWithColdestTemperature());
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largest=null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            largest = coldestHourInFile(fr.getCSVParser());
        }
        System.out.println("Coldest temperature on that day was " + largest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        FileResource fr = new FileResource("/home/sravanthi/Downloads/data/2013/"+fileWithColdestTemperature());
        CSVParser parser1 = fr.getCSVParser();
        for (CSVRecord record : parser1) {
            System.out.println(record.get("DateUTC")+": "+record.get("TemperatureF"));
        }
    }
    void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));

    }
    void testLowestHumidityInManyFiles()
    {
        lowestHumidityInManyFiles();
    }
    void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser1 = fr.getCSVParser();
        System.out.println("Average temperature in file is "+AverageTemperatureInFile(parser1));
    }
    void testaverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double a=averageTemperatureWithHighHumidityInFile(parser,80);
        if(a==0.0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is "+a);

    }
    public static void main(String args[])
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVAss2 t=new CSVAss2();
        t.testcoldestHourInFile();
        t.testFileWithColdestTemperature();
        t.testLowestHumidityInFile();
        t.testLowestHumidityInManyFiles();
        t.testAverageTemperatureInFile();
        t.testaverageTemperatureWithHighHumidityInFile();
    }
}
