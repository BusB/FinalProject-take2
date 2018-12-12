

public class GameShape{
    private static final double ELLIPSE_AREA = 0.78540;
    private static final double TRIANGLE_AREA = 0.5;
    private static final double RECTANGLE_AREA = 1.0;
    private static final double STAR_AREA = 0.32632;

    private double gsArea;
    private double xScale;
    private double yScale;
    private String shapeName;
    private int xPosition;
    private int yPosition;
    private boolean isSelected;
    private int location;
    private String filename;


    public GameShape(int shapeType, double xs, double ys){
        this.xScale = xs;
        this.yScale = ys;
        isSelected = false;
        yPosition = 500;
        xPosition = 225;
        location = 0;

        if(shapeType==1){
            this.gsArea = ELLIPSE_AREA * xs * ys;
            this.shapeName = "Ellipse";
            this.filename="circle.png";
        } else if(shapeType==2){
            this.gsArea = TRIANGLE_AREA * xs * ys;
            this.shapeName = "Triangle";
            this.xPosition+=100;
            this.filename="triangle.png";
        } else if(shapeType==3){
            this.gsArea = STAR_AREA * xs * ys;
            this.shapeName = "Star";
            this.xPosition+=200;
            this.filename="star.png";
        } else {
            this.gsArea = RECTANGLE_AREA * xs * ys;
            this.shapeName = "Rectangle";
            this.xPosition+=300;
            this.filename="square.png";
        }
    }

    public GameShape(String shapeName){
        //creates random shape of the specified type
        this.xScale = 0.2 + 0.8*Math.random();
        this.yScale = 0.2 + 0.8*Math.random();
        isSelected = false;
        yPosition = 500;
        xPosition = 225;
        location = 0;

        if(shapeName.equalsIgnoreCase("ellipse")){
            this.gsArea = ELLIPSE_AREA * xScale * yScale;
            this.shapeName = "Ellipse";
            this.filename="circle.png";
        } else if(shapeName.equalsIgnoreCase("triangle")){
            this.gsArea = TRIANGLE_AREA * xScale * yScale;
            this.shapeName = "Triangle";
            this.xPosition+=100;
            this.filename="triangle.png";
        } else if(shapeName.equalsIgnoreCase("star")){
            this.gsArea = STAR_AREA * xScale * yScale;
            this.shapeName = "Star";
            this.filename="star.png";
            this.xPosition+=200;
        } else {
            this.gsArea = RECTANGLE_AREA * xScale * yScale;
            this.shapeName = "Rectangle";
            this.xPosition+=300;
            this.filename="square.png";
        }
    }

    public GameShape(GameShape ellipse, GameShape triangle, GameShape star){
        //creates rectangle that makes sure shapes can balance
        int randomShape = (int) (Math.random()*3);
        double scale = 0.2 + 0.8*Math.random();

        if(randomShape==0){
            if(ellipse.getGsArea()<triangle.getGsArea()+star.getGsArea()-4){
                double area = (triangle.getGsArea()+star.getGsArea()-ellipse.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            } else if(ellipse.getGsArea()>triangle.getGsArea()+star.getGsArea()+4){
                double area = (ellipse.getGsArea()-triangle.getGsArea()-star.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            }else{
                randomShape = 1;
            }
        }
        if(randomShape==1){
            if(triangle.getGsArea()<ellipse.getGsArea()+star.getGsArea()-4.){
                double area = (ellipse.getGsArea()+star.getGsArea()-triangle.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            } else if(triangle.getGsArea()>ellipse.getGsArea()+star.getGsArea()+4.){
                double area = (triangle.getGsArea()-ellipse.getGsArea()-star.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            } else {
                randomShape = 2;
            }
        }
        if(randomShape==2){
            if(star.getGsArea()<ellipse.getGsArea()+triangle.getGsArea()-4.){
                double area = (ellipse.getGsArea()+triangle.getGsArea()-star.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            } else if(star.getGsArea()>ellipse.getGsArea()+triangle.getGsArea()+4.){
                double area = (star.getGsArea()-ellipse.getGsArea()-triangle.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            } else {
                randomShape = 3;
            }
        }
        if(randomShape==3){
            if (star.getGsArea()+triangle.getGsArea()+ellipse.getGsArea()<100.){
                double area = (star.getGsArea()+triangle.getGsArea()+ellipse.getGsArea())/100.;
                while (area/scale < 0.2 || area/scale > 1.0){
                    scale = 0.2 + 0.8*Math.random();
                }
                xScale = scale;
                yScale = area/scale;
            } else {
                double area = (star.getGsArea()+triangle.getGsArea()+ellipse.getGsArea())/100.;
                xScale = 1.0;
                yScale = 1.0;
                star.rescale(area);
                triangle.rescale(area);
                ellipse.rescale(area);
            }
        }

        this.shapeName = "Rectangle";
        this.gsArea = RECTANGLE_AREA* xScale * yScale;
        this.filename="square.png";
        this.isSelected = false;
        this.yPosition = 500;
        this.xPosition = 525;
        this.location = 0;

    }

    public double getGsArea(){
        return Math.round(this.gsArea*100);
    }

    public double getxScale() {
        return xScale;
    }

    public boolean getIsSelected(){
        return isSelected;
    }

    public int getLocation() {
        return location;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
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

    public void setLocation(int location) {
        this.location = location;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void selectShape(){
        this.isSelected = !isSelected;
    }

    public void rescale(double area){
        this.xScale = xScale/Math.sqrt(area);
        this.yScale = yScale/Math.sqrt(area);
    }

    public String toString(){
        return shapeName + "; x " + xScale + "; y " + yScale + "; Area " + this.getGsArea();
    }
}

