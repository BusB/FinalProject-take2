import java.awt.*;

public class Box extends Rectangle {
    private Color color;
    private int width;
    private int xPosition;
    private int yPosition;
    protected String name;

    public Box(Color color, String name, int width, int xPosition, int yPosition) {
        this.color = color;
        this.name = name;
        this.width = width;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Color getColor() {
        return color;
    }

    public double getArea() {
        return width * width;
    }

    public int returnWidth() {
        return width;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void changeXPos(int changeBy) {
        xPosition += changeBy;
    }

    public void changeYPos(int changeBy) {
        yPosition += changeBy;
    }


    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public String getName() {
        return name;
    }
}
