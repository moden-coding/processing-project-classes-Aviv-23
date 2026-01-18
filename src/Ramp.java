import processing.core.PApplet;

public class Ramp {
    PApplet canvas;
    private int X;
    private int SizeX = 300;// width
    private int Y;
    private int SizeY = 50;// height
    private double speed = 3;// startsing speed of ramps

    public Ramp(PApplet c, double score) {
        canvas = c;
        X = (int) canvas.random(0, 700);// chatGPT makes random x from 0 to 700 for the ramps
        Y = canvas.height;
        speed = speed + score * 0.2;
    }

    public void display() {
        canvas.fill(0, 255, 0);
        canvas.rect(X, Y, SizeX, SizeY);// draws the ramp
    }

    public int Y() {
        return Y;
    }

    public int X() {
        return X;
    }

    public int rightSide() {// right side x of ramp
        return X + SizeX;
    }

    public int Bottom() {// bottom y of ramp
        return Y + SizeY;
    }

    public boolean offScreen() {//checks of ramp is not on the screen anymore
        if (Bottom() < 0) {
            return true;
        } else {
            return false;
        }
    }

    public void move() {//moves the ramps up
        Y -= speed;
    }
}
