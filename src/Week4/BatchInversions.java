package Week4;

import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            int invRed = (255 - inPixel.getRed());
            int invBlue = (255 - inPixel.getBlue());
            int invGreen = (255 - inPixel.getGreen());

            pixel.setRed(invRed);
            pixel.setBlue(invBlue);
            pixel.setGreen(invGreen);
        }
        return outImage;
    }

    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeInversion(inImage);
            gray.draw();
            String newName = "inverted-" + f.getName();
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
    public static void main(String args[])
    {
        BatchInversions b=new BatchInversions();
        b.selectAndConvert();
    }
}
