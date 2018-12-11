

public class GameShape{
    private static final double ELLIPSE_AREA = 0.78540;
    private static final double TRIANGLE_AREA = 0.5;
    private static final double RECTANGLE_AREA = 1.0;
    private static final double STAR_AREA = 0.32632;

    private double gsArea;
    private double xScale;
    private double yScale;
    private String shapeName;

    public GameShape(int shapeType, double xs, double ys){
        this.xScale = xs;
        this.yScale = ys;

        if(shapeType==1){
            this.gsArea = ELLIPSE_AREA * xs * ys;
            this.shapeName = "Ellipse";
        } else if(shapeType==2){
            this.gsArea = TRIANGLE_AREA * xs * ys;
            this.shapeName = "Triangle";
        } else if(shapeType==3){
            this.gsArea = STAR_AREA * xs * ys;
            this.shapeName = "Star";
        } else {
            this.gsArea = RECTANGLE_AREA * xs * ys;
            this.shapeName = "Rectangle";
        }
    }

    public GameShape(String shapeName){
        //creates random shape of the specified type
        this.xScale = 0.2 + 0.8*Math.random();
        this.yScale = 0.2 + 0.8*Math.random();
        if(shapeName.equalsIgnoreCase("ellipse")){
            this.gsArea = ELLIPSE_AREA * xScale * yScale;
            this.shapeName = "Ellipse";
        } else if(shapeName.equalsIgnoreCase("triangle")){
            this.gsArea = TRIANGLE_AREA * xScale * yScale;
            this.shapeName = "Triangle";
        } else if(shapeName.equalsIgnoreCase("star")){
            this.gsArea = STAR_AREA * xScale * yScale;
            this.shapeName = "Star";
        } else {
            this.gsArea = RECTANGLE_AREA * xScale * yScale;
            this.shapeName = "Rectangle";
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
    }

    public double getGsArea(){
        return Math.round(this.gsArea*100);
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

    public void rescale(double area){
        this.xScale = xScale/Math.sqrt(area);
        this.yScale = yScale/Math.sqrt(area);
    }

    public String toString(){
        return shapeName + "; x " + xScale + "; y " + yScale + "; Area " + this.getGsArea();
    }
}

