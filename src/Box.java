import java.util.ArrayList;

import processing.core.*;

public class Box extends PApplet {

    private int x;
    private int y;
    private int sizex = 75;//the width of the box
    private int sizey = 75;//the height of the box
    private int downspeed=5;//how fast the box moves down
    private int speed=7;//left right move speed
    private PApplet canvas;
    private boolean left = false;//smooth movement
    private boolean right = false;
    private boolean down = true;

    public Box(PApplet c, ArrayList<Ramp> r) {//box constructor
        canvas=c;
        x=canvas.width/2-sizex/2; //center of screen
        canvas = c;
        y=0;//top of screen

    }

    public void display() {
        canvas.rect(x, y, sizex, sizey);//show the box
    }

    public void move(ArrayList<Ramp> r) {
        down = true;//sets moving down as true unless said otherwise
        if (left && x > 0) {//smooth movemt
                moveLeft(r);
       

            x -= speed;
        }
        if (right && x < 1000 - sizex) {
            moveRight(r);
            x += speed;
        }

        if (down && y + sizey < 700) {
            moveUp(r);
            y += downspeed;
        }
    }
    public void moveLeft() {//smooth movement
        left = true;
    }

    public void moveRight() {
        right = true;
    }

    public void stopLeft() {
        left = false;
    }

    public void stopRight() {
        right = false;
    }

    public int getY() {//gets the y value
        return y;
    } 

    public int BottomY() {//gets y value of bottom of the box
        return y + sizey;
    }

    public int rightEdge() {//gets the x alue of the right side of the box
        return x + sizex;
    }

    public void moveUp(ArrayList<Ramp> Ramps) {
        for(int i=0; i<Ramps.size(); i++){
            Ramp ramp=Ramps.get(i);
        if (down && BottomY() >= ramp.Y() && y < ramp.Y() && rightEdge() > ramp.X() && x < ramp.rightSide()) {// chatGPT helped me edit my existing code to make it work
            y = ramp.Y() - sizey;//this code senses when the box touches the top of the ramp and teleports it so they dont clip into eachother
        }
    }
    }

    public void moveLeft(ArrayList<Ramp> Ramps) {
        for(int i=0; i<Ramps.size(); i++){
            Ramp ramp=Ramps.get(i);
        if (x < ramp.rightSide() && rightEdge() > ramp.rightSide() && BottomY() > ramp.Y() && y < ramp.Bottom()) {// chatGPT helped me edit my existing code to make it work
            y = ramp.Y() - sizey;
            x = ramp.rightSide();//this code senses when the box touches the side of the ramp and teleports it so they dont clip into eachother
        }
    }
    }

    public void moveRight(ArrayList<Ramp> Ramps) {
        for(int i=0; i<Ramps.size(); i++){
            Ramp ramp=Ramps.get(i);
        if (rightEdge() > ramp.X() && x < ramp.X() && BottomY() > ramp.Y() && y < ramp.Bottom()) {// chatGPT helped me edit my existing code to make it work
            x = ramp.X() - sizex;//this code senses when the box touches the side of the ramp and teleports it so they dont clip into eachother
        }
    }
}
public void reset(){//resets box to original position when you start a new game after loosing
 x=canvas.width/2-sizex/2; 
 y=0;  
}
}
