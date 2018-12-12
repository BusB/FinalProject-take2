import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class AreaTest{
    public static void main(String[] args) {
        int[] xList = {0,18,25,31,49,34,39,24,10,15};
        int[] yList = {19,19,0,19,19,31,49,39,49,31};
        int[] xListTriangle = {0,25,49};
        int[] yListTriangle = {49,0,49};
        Polygon star = new Polygon(xList, yList, 10);
        Ellipse2D ellipse = new Ellipse2D.Double(0,0,50,50);
        Rectangle2D rectangle = new Rectangle2D.Double(0,0,50,50);
        Polygon triangle = new Polygon(xListTriangle,yListTriangle,3);

        Area starArea = new Area(star);
        Area ellipseArea = new Area(ellipse);
        Area rectangleArea = new Area(rectangle);
        Area triangleArea = new Area(triangle);

    }
}
