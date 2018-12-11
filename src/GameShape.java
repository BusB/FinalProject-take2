import java.awt.image.BufferedImage;

public class GameShape{
    private static final double CIRCLE_AREA = 0.78540;
    private static final double TRIANGLE_AREA = 0.5;
    private static final double SQUARE_AREA = 1.0;
    private static final double STAR_AREA = 0.32632;

    private double gsArea;
    private double xScale;
    private double yScale;
    private String shapeName;

    public GameShape(int shapeType, double xs, double ys){
        this.xScale = xs;
        this.yScale = ys;

        if(shapeType==1){
            this.gsArea = CIRCLE_AREA * xs * ys;
            this.shapeName = "Circle";
        } else if(shapeType==2){
            this.gsArea = TRIANGLE_AREA * xs * ys;
            this.shapeName = "Triangle";
        } else if(shapeType==3){
            this.gsArea = STAR_AREA * xs * ys;
            this.shapeName = "Star";
        } else {
            this.gsArea = SQUARE_AREA * xs * ys;
            this.shapeName = "Square";
        }
    }

    public double getGsArea(){
        return this.gsArea;
    }

    public double getxScale() {
        return xScale;
    }

    public double getyScale() {
        return yScale;
    }
    public String getShapeName() {
        return shapeName;
    }

    public void setxScale(double xScale) {
        this.xScale = xScale;
    }

    public void setyScale(double yScale){
        this.yScale = yScale;
    }
}
