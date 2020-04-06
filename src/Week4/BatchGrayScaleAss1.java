package Week4;

import edu.duke.*;
import java.io.*;

public class BatchGrayScaleAss1 {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
            String newName = "gray" + f.getName();
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
    public static void main(String args[])
    {
        BatchGrayScaleAss1 b=new BatchGrayScaleAss1();
        b.selectAndConvert();
    }
}
