import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.geom.Area;

/**
 * Created by yijunma on 9/27/15. Modified by John Erik "Buster" Bylander and Matt Zellman on 12/11/2018.
 */
public class Canvas extends Window implements KeyListener, Runnable {


    // Create the variables you need below
    private String instructions;
    private String congrats = "Congratulations!!";
    private String displayText;
    private Box box = new Box(Color.PINK, "Box1", 50, 325, 500);
    private Box box2 = new Box(Color.PINK, "Box2", 50, 425, 500);
    private Pan panLeft = new Pan(Color.ORANGE, 10, 300, 360, 30, 0, -180, 1);
    private Pan panRight = new Pan(Color.ORANGE, 430, 300, 360, 30, 0, -180, 1);

    private GameShape gameEllipse = new GameShape(Color.RED,"ellipse");
    private GameShape gameTriangle = new GameShape(Color.BLUE,"triangle");
    private GameShape gameStar = new GameShape(Color.GREEN,"star");
    private GameShape gameRectangle = new GameShape(Color.YELLOW, gameEllipse,gameTriangle,gameStar);

    // Don't delete this constructor!
    public Canvas() {
        super(sFrame); //include this line in your own constructor
        initialize();  //include this line in your own constructor
    }


    // Create your constructor below that take a string as input
    public Canvas(String instructions) {
        super(sFrame); //include this line in your own constructor
        initialize();  //include this line in your own constructor
        this.instructions = instructions;
    }


    // Create your drawBox method below
    public void drawBox(Box b) {
        drawSquare(b.getColor(), b.getxPosition(), b.getyPosition(), b.returnWidth());
    }

    public void drawPan(Pan p) {
        drawArc(p.getGradient(), (int) p.x, (int) p.y, (int) p.width, (int) p.height, (int) p.start, (int) p.extent);
    }

    public void drawGameShape(GameShape gs){
        drawGameShape(gs.getGsColor(), gs);
    };




    //////////////////// add your draw method in the update method  ////////////////////
    //////////////////// so the canvas can keep updating the canvas ////////////////////
    @Override
    public void update(Graphics g) {
        //This method will automatically reload every 0.05 seconds
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(buf, 0, 0, null);//Don't delete this code!

        if(gameEllipse.getLocation()*gameTriangle.getLocation()*gameRectangle.getLocation()*gameStar.getLocation() != 0&&panLeft.totalArea==panRight.totalArea){
            displayText = congrats;
        } else {
            displayText = instructions;
        }

        drawBackground();
        drawString(Color.ORANGE, displayText, 30, 10, 50);
        //drawString(Color.ORANGE, ct, 30, 10, 90);
        //drawBox(box);
        //drawBox(box2);
        drawPan(panLeft);
        drawPan(panRight);
        drawGameShape(gameEllipse);
        drawGameShape(gameTriangle);
        drawGameShape(gameStar);
        drawGameShape(gameRectangle);


        /*if (keyTyped == 'a' && panRight.shapes.size() > 0) {
            panLeft.shapes.add(box);
            panLeft.totalArea = box.getArea();
            box.setyPosition((int) panLeft.y - 35);
            box.setxPosition((int) panLeft.x + 5);
            box2.setyPosition((int) panRight.y - 35);
            box2.setxPosition((int) panRight.x + 5);
        } else if (keyTyped == 'a') {
            panLeft.shapes.add(box);
            panLeft.totalArea = box.getArea();
            box.setyPosition((int) panLeft.y - 35);
            box.setxPosition((int) panLeft.x + 5);
        } else if (keyTyped == 'd' && panLeft.shapes.size() > 0) {
            panRight.shapes.add(box2);
            panRight.totalArea = box2.getArea();
            box2.setyPosition((int) panRight.y - 35);
            box2.setxPosition((int) panRight.x + 5);
            box.setyPosition((int) panLeft.y - 35);
            box.setxPosition((int) panLeft.x + 5);
        } else if (keyTyped == 'd') {
            panRight.shapes.add(box2);
            panRight.totalArea = box2.getArea();
            box2.setyPosition((int) panRight.y - 35);
            box2.setxPosition((int) panRight.x + 5);
        } else if (keyTyped == 's') {
            //method for removing a box?
        } else if (keyPressed == 'W') {
            //method for jumping into the pans to select/remove shapes?
        }
        if (panLeft.totalArea < panRight.totalArea && panLeft.y > 250) {
            panLeft.changeY(-5);
            panRight.changeY(5);
            drawBox(box);
            drawBox(box2);
        } else if (panLeft.totalArea > panRight.totalArea && panRight.y > 250) {
            panLeft.changeY(5);
            panRight.changeY(-5);
            drawBox(box);
            drawBox(box2);
        } else if (panRight.totalArea == panLeft.totalArea && panRight.y > 300) {
            panLeft.changeY(5);
            panRight.changeY(-5);
            drawBox(box);
            drawBox(box2);
        } else if (panRight.totalArea == panLeft.totalArea && panLeft.y > 300) {
            panLeft.changeY(-5);
            panRight.changeY(5);
            drawBox(box);
            drawBox(box2);
        }*/



        //here's a suggestion for how to do movement with GameShapes.
        if(keyTyped=='1'){
            if(gameEllipse.getLocation()==1){
                gameEllipse.setLocation(-1);
                panLeft.totalArea+=gameEllipse.getGsArea();
                panRight.totalArea-=gameEllipse.getGsArea();
                gameEllipse.hybridMove(-425, panLeft.y-35+(50*(1-gameEllipse.getyScale())));
            } else if(gameEllipse.getLocation()==-1){
                gameEllipse.setLocation(1);
                panRight.totalArea+=gameEllipse.getGsArea();
                panLeft.totalArea-=gameEllipse.getGsArea();
                gameEllipse.hybridMove(425, panRight.y-35+(50*(1-gameEllipse.getyScale())));
            } else {
                gameEllipse.setLocation(-1);
                panLeft.totalArea+=gameEllipse.getGsArea();
                gameEllipse.hybridMove(-210, panLeft.y-35+(50*(1-gameEllipse.getyScale())));
            }
            keyTyped = '0';
        } else if(keyTyped=='2'){
            if(gameTriangle.getLocation()==1){
                gameTriangle.setLocation(-1);
                panLeft.totalArea+=gameTriangle.getGsArea();
                panRight.totalArea-=gameTriangle.getGsArea();
                gameTriangle.hybridMove(-425, panLeft.y-35+(50*(1-gameTriangle.getyScale())));
            } else if(gameTriangle.getLocation()==-1){
                gameTriangle.setLocation(1);
                panRight.totalArea+=gameTriangle.getGsArea();
                panLeft.totalArea-=gameTriangle.getGsArea();
                gameTriangle.hybridMove(425, panRight.y-35+(50*(1-gameTriangle.getyScale())));
            } else {
                gameTriangle.setLocation(-1);
                panLeft.totalArea+=gameTriangle.getGsArea();
                gameTriangle.hybridMove(-210, panLeft.y-35+(50*(1-gameTriangle.getyScale())));
            }
            keyTyped = '0';
        } else if(keyTyped=='3'){
            if(gameStar.getLocation()==1){
                gameStar.setLocation(-1);
                panLeft.totalArea+=gameStar.getGsArea();
                panRight.totalArea-=gameStar.getGsArea();
                gameStar.hybridMove(-425, panLeft.y-35+(50*(1-gameStar.getyScale())));
            } else if(gameStar.getLocation()==-1){
                gameStar.setLocation(1);
                panRight.totalArea+=gameStar.getGsArea();
                panLeft.totalArea-=gameStar.getGsArea();
                gameStar.hybridMove(425, panRight.y-35+(50*(1-gameStar.getyScale())));
            } else {
                gameStar.setLocation(-1);
                panLeft.totalArea+=gameStar.getGsArea();
                gameStar.hybridMove(-210, panLeft.y-35+(50*(1-gameStar.getyScale())));
            }
            keyTyped='0';
        } else if(keyTyped=='4'){
            if(gameRectangle.getLocation()==1){
                gameRectangle.setLocation(-1);
                panLeft.totalArea+=gameRectangle.getGsArea();
                panRight.totalArea-=gameRectangle.getGsArea();
                gameRectangle.hybridMove(-425, panLeft.y-35+(50*(1-gameRectangle.getyScale())));
            } else if(gameRectangle.getLocation()==-1){
                gameRectangle.setLocation(1);
                panRight.totalArea+=gameRectangle.getGsArea();
                panLeft.totalArea-=gameRectangle.getGsArea();
                gameRectangle.hybridMove(425, panRight.y-35+(50*(1-gameRectangle.getyScale())));
            } else {
                gameRectangle.setLocation(-1);
                panLeft.totalArea+=gameRectangle.getGsArea();
                gameRectangle.hybridMove(-210, panLeft.y-35+(50*(1-gameRectangle.getyScale())));
            }
            keyTyped = '0';
        }
        if (panLeft.totalArea < panRight.totalArea && panLeft.y > 250) {
            panLeft.changeY(-5);
            panRight.changeY(5);
            gameEllipse.movePosition(0,gameEllipse.getLocation()*5);
            gameTriangle.movePosition(0,gameTriangle.getLocation()*5);
            gameStar.movePosition(0,gameStar.getLocation()*5);
            gameRectangle.movePosition(0,gameRectangle.getLocation()*5);
            drawGameShape(gameEllipse);
            drawGameShape(gameTriangle);
            drawGameShape(gameStar);
            drawGameShape(gameRectangle);
        } else if (panLeft.totalArea > panRight.totalArea && panRight.y > 250) {
            panLeft.changeY(5);
            panRight.changeY(-5);
            gameEllipse.movePosition(0,gameEllipse.getLocation()*-5);
            gameTriangle.movePosition(0,gameTriangle.getLocation()*-5);
            gameStar.movePosition(0,gameStar.getLocation()*-5);
            gameRectangle.movePosition(0,gameRectangle.getLocation()*-5);
            drawGameShape(gameEllipse);
            drawGameShape(gameTriangle);
            drawGameShape(gameStar);
            drawGameShape(gameRectangle);
        } else if (panRight.totalArea == panLeft.totalArea && panRight.y > 300) {
            panLeft.changeY(5);
            panRight.changeY(-5);
            gameEllipse.movePosition(0,gameEllipse.getLocation()*-5);
            gameTriangle.movePosition(0,gameTriangle.getLocation()*-5);
            gameStar.movePosition(0,gameStar.getLocation()*-5);
            gameRectangle.movePosition(0,gameRectangle.getLocation()*-5);
            drawGameShape(gameEllipse);
            drawGameShape(gameTriangle);
            drawGameShape(gameStar);
            drawGameShape(gameRectangle);
        } else if (panRight.totalArea == panLeft.totalArea && panLeft.y > 300) {
            panLeft.changeY(-5);
            panRight.changeY(5);
            gameEllipse.movePosition(0,gameEllipse.getLocation()*5);
            gameTriangle.movePosition(0,gameTriangle.getLocation()*5);
            gameStar.movePosition(0,gameStar.getLocation()*5);
            gameRectangle.movePosition(0,gameRectangle.getLocation()*5);
            drawGameShape(gameEllipse);
            drawGameShape(gameTriangle);
            drawGameShape(gameStar);
            drawGameShape(gameRectangle);
        }

    }

    ///////////////////// Functions / Variables you may need to use //////////////////////////

    private int canvasWidth = 800;
    private int canvasHeight = 600;
    private char keyPressed;
    private char keyTyped;

    public void drawBackground() {
        gc2.setColor(backgroundColor);
        gc2.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    public void drawSquare(Color color, int xPosition, int yPosition, int width) {
        // Use gc.fillRect(...); //The arguments are xPosition, yPosition, width, length
        gc2.setColor(color);
        gc2.fillRect(xPosition, yPosition, width, width);
    }

    public void drawArc(GradientPaint color, int x, int y, int w, int h, int start, int extent) {
        //gc2.setColor(color);
        color = new GradientPaint(210, y, Color.ORANGE, 210, y + 30, Color.darkGray);
        gc2.setPaint(color);
        gc2.fillArc(x, y, w, h, start, extent);

    }

    public void drawString(Color color, String str, int fontSize, int xPosition, int yPosition) {
        Font font = new Font("Times New Roman", 0, fontSize);
        gc2.setFont(font);
        gc2.setColor(color);
        gc2.drawString(str, xPosition, yPosition);
    }

    public void drawGameShape(Color color, Area a){
        gc2.setColor(color);
        gc2.fill(a);
    };
    ///////////////////////// no need to understand what's happening below ///////////////////////////
    private BufferedImage buf;
    private Graphics gc;
    private Graphics2D gc2;
    private int canvasXPosition = 200;
    private int canvasYPosition = 80;

    private Color backgroundColor = Color.BLACK;

    private static JFrame sFrame = new JFrame("Canvas");

    static {
        sFrame.setVisible(true);
        sFrame.setDefaultCloseOperation(3);
    }

    private void initialize() {
        sFrame.addKeyListener(this);
        this.setBounds(canvasXPosition, canvasYPosition, canvasWidth, canvasHeight);
        this.buf = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        gc = buf.getGraphics();
        gc2 = (Graphics2D) gc;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc2.setRenderingHints(rh);

        this.setVisible(true);
        new Thread(this).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyTyped = e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //If you press "ESC", you quit the game
        if (e.getKeyCode() == 27) {
            this.dispose();
            System.exit(0);
        }

        keyPressed = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed = ' ';
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);

                repaint();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
}