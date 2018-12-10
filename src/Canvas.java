import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by yijunma on 9/27/15.
 */
public class Canvas extends Window implements KeyListener, Runnable {


    // Create the variables you need below
    private String instructions;
    private int count = 0;
    private String ct;
    private Box box = new Box(Color.PINK, 50, 325, 500);
    private Box box2 = new Box(Color.PINK, 50, 425, 500);
    private Pan panLeft = new Pan(Color.ORANGE, 60, 300, 300, 30, 0, -180, 1);
    private Pan panRight = new Pan(Color.ORANGE, 440, 300, 300, 30, 0, -180, 1);


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
        //ct = "Count: ";
    }


    // Create your drawBox method below
    public void drawBox(Box b) {
        drawSquare(b.getColor(), b.getxPosition(), b.getyPosition(), b.returnWidth());
    }

    public void drawPan(Pan p) {
        drawArc(p.getGradient(), (int) p.x, (int) p.y, (int) p.width, (int) p.height, (int) p.start, (int) p.extent);
    }


    //////////////////// add your draw method in the update method  ////////////////////
    //////////////////// so the canvas can keep updating the canvas ////////////////////
    @Override
    public void update(Graphics g) {
        //This method will automatically reload every 0.05 seconds
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(buf, 0, 0, null);//Don't delete this code!

        drawBackground();
        drawString(Color.ORANGE, instructions, 30, 10, 50);
        //drawString(Color.ORANGE, ct, 30, 10, 90);
        drawBox(box);
        drawBox(box2);
        drawPan(panLeft);
        drawPan(panRight);
        if (panLeft.totalArea < panRight.totalArea) {
            for (int i = 0; panLeft.y > 200; i--) {
                panLeft.changeY(i);
                panRight.changeY(i * -1);
            }
        } else if (panRight.totalArea < panLeft.totalArea) {
            for (int i = 0; panRight.y > 200; i--) {
                panRight.changeY(i);
                panLeft.changeY(i * -1);
            }
        } else if (panRight.totalArea == panLeft.totalArea) {
            panLeft.y = 300;
            panRight.y = 300;
        }


        if (keyTyped == 'a' && panRight.shapes.size() > 0) {
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
            panRight.shapes.add(box);
            panRight.totalArea = box.getArea();
            box.setyPosition((int) panRight.y - 35);
            box.setxPosition((int) panRight.x + 5);
        } else if (keyPressed == 'w') {
            box.changeYPos(-5);
        } else if (keyPressed == 's') {
            box.changeYPos(5);
        }


//        if (box.getxPosition() <= 0) {
//            box.setxPosition(canvasWidth);
//            count++;
//            ct = "Count: " + count;
//        } else if (box.getxPosition() >= canvasWidth) {
//            box.setxPosition(0);
//            count++;
//            ct = "Count: " + count;
//        }

//        if (box.getyPosition() <= 0) {
//            box.setyPosition(canvasHeight);
//            count++;
//            ct = "Count: " + count;
//        } else if (box.getyPosition() >= canvasHeight) {
//            box.setyPosition(0);
//            count++;
//            ct = "Count: " + count;
//        }


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

    public void drawArc(GradientPaint platecolor, int x, int y, int w, int h, int start, int extent) {
        //gc2.setColor(color);
        platecolor = new GradientPaint(210,y,Color.ORANGE,210,y+30,Color.darkGray);
        gc2.setPaint(platecolor);
        gc2.fillArc(x, y, w, h, start, extent);

    }

    public void drawString(Color color, String str, int fontSize, int xPosition, int yPosition) {
        Font font = new Font("Times New Roman", 0, fontSize);
        gc2.setFont(font);
        gc2.setColor(color);
        gc2.drawString(str, xPosition, yPosition);
    }

    ///////////////////////// no need to understand what's happening below ///////////////////////////
    private BufferedImage buf;
    private Graphics gc;
    private Graphics2D gc2;
    private int canvasXPosition = 0;
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
                Thread.sleep(40);
                repaint();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}