package Week1;
import edu.duke.*;
import java.io.File;

    public class PerimeterAssignmentRunner {
        public double getPerimeter (Shape s) {
            double totalPerim = 0.0;
            Point prevPt = s.getLastPoint();
            for (Point currPt : s.getPoints()) {
                double currDist = prevPt.distance(currPt);
                totalPerim = totalPerim + currDist;
                prevPt = currPt;
            }
            return totalPerim;
        }

        public int getNumPoints (Shape s) {
            int c=0;
            for (Point dt:s.getPoints())
                c++;
            return c;
        }

        public double getAverageLength(Shape s) {
            double totalPerim = 0.0;
            int c=0;
            for (Point dt:s.getPoints())
                c++;
            Point prevPt = s.getLastPoint();
            for (Point currPt : s.getPoints()) {
                double currDist = prevPt.distance(currPt);
                totalPerim = totalPerim + currDist;
                prevPt = currPt;
            }
            return totalPerim/c;
        }

        public double getLargestSide(Shape s) {
            double totalPerim = 0.0;
            Point prevPt = s.getLastPoint();
            for (Point currPt : s.getPoints()) {
                double currDist = prevPt.distance(currPt);
                if(totalPerim < currDist)
                    totalPerim = currDist;
                prevPt = currPt;
            }
            return totalPerim;
        }

        public double getLargestX(Shape s) {
            double largestX = 0.0;
            for(Point curr:s.getPoints()) {
                if (curr.getX() > largestX)
                    largestX = curr.getX();
            }
            return largestX;
        }

        public double getLargestPerimeterMultipleFiles() {
            DirectoryResource dr = new DirectoryResource();
            double largestp=0.0;
            for (File f : dr.selectedFiles()) {
                FileResource fr = new FileResource(f);
                Shape s = new Shape(fr);
                double totalPerim = getPerimeter(s);
                if (largestp < totalPerim)
                    largestp = totalPerim;
            }
            return largestp;
        }

        public String getFileWithLargestPerimeter() {
            File temp = null;
            DirectoryResource dr = new DirectoryResource();
            double largestp=0.0;
            for (File f : dr.selectedFiles()) {
                FileResource fr = new FileResource(f);
                Shape s = new Shape(fr);
                double totalPerim = getPerimeter(s);
                if (largestp < totalPerim) {
                    largestp = totalPerim;
                    temp = f;
                }
            }
            return temp.getName();
        }

        public void testPerimeter () {
            FileResource fr = new FileResource();
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            System.out.println("perimeter = " + length);
            System.out.println("No. of points "+getNumPoints(s));
            System.out.println("Average Length "+getAverageLength(s));
            System.out.println("Largest side "+getLargestSide(s));
            System.out.println("Largest PointX "+getLargestX(s));
        }

        public void testPerimeterMultipleFiles() {
            System.out.println("LargestPerimeter "+getLargestPerimeterMultipleFiles());
        }

        public void testFileWithLargestPerimeter() {

            System.out.println(getFileWithLargestPerimeter());
        }
        public static void main (String[] args) {
            PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
            pr.testPerimeter();
            pr.testPerimeterMultipleFiles();
            pr.testFileWithLargestPerimeter();
        }
    }

