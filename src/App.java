import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    private ArrayList<Ramp> Ramps = new ArrayList<Ramp>();
    private Box box;//box object
    private Ramp ramp;// ramp object
    private int score;
    private int scene = 1;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(0);//black background
        box = new Box(this, Ramps);
        ramp = new Ramp(this, score);
        Ramps.add(new Ramp(this, score));//initial ramp
    }

    public void settings() {
        size(1000, 700);

    }

    public void draw() {
        if (scene == 1) {//start screen
            scene1();
        } else if (scene == 2) {//game
            scene2();
        } else if (scene == 3) {//end screen
            scene3();
        }
    }

    public void keyPressed() {
        if (keyCode == LEFT) {//smooth movement
            box.moveLeft();
        }
        if (keyCode == RIGHT) {
            box.moveRight();
        }
        if (key == ' ' && scene == 1) {//advance scene to game
            scene++;
            box.reset();
            removeRamps();//all ramps are removed before tou play again
        }
        if (key == ' ' && scene == 3) {//bring yourself back to start screen
            scene=1;
        }
    }

    public void keyReleased() {
        if (keyCode == LEFT) {//smooth movement
            box.stopLeft();
        }
        if (keyCode == RIGHT) {
            box.stopRight();
        }
    }

    public void scene1() {//start screen
        score=0;//resets score
        background(0);
        fill(0, 255, 0);
        textSize(80);
        textAlign(CENTER);
        text("SLOPE", 500, 100);
        textSize(60);
        text("Press Space To Continue", 500, 400);
    }

    public void scene2() {
        background(0);
        if (frameCount % 100 == 0) {//ramp generation (Framecount is moden code)
            Ramps.add(new Ramp(this, score));
            score++;
        }
        for (int i = 0; i < Ramps.size(); i++) {//goes through each ramp
            Ramp ramp = Ramps.get(i);//sets ramp so we dont have to use Ramps.get(i)
            ramp.display();//show each ramp
            ramp.move();//move each ramp
            if (ramp.offScreen()==true) {//removes ramps when they reach the top of the screen
                Ramps.remove(i);
            }
        }
        box.display();//show box
        box.move(Ramps);//move the box
        textSize(25);
        textAlign(CENTER);
        text(score, 100, 100);//shows score
        death();//advances scene when you die
    }

    public void scene3() {
        background(0);
        fill(0, 255, 0);
        textSize(80);
        textAlign(CENTER);
        text("SCORE", 500, 100);//shows your finale score
        textSize(100);
        text(score, 500, 200);
        int highscore=0;//set variable for when we display it later
        try (Scanner scanner = new Scanner(Paths.get("src/highscore.txt"))) {//taken from google drive
            highscore = Integer.valueOf(scanner.nextLine());
            if (score > highscore) {
                try { Files.write( // file writing from chatgpt
                            Paths.get("src/highscore.txt"),
                            String.valueOf(score).getBytes());
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        text("HIGHSCORE", 500, 400);
        text(highscore, 500, 500);//displays highscore
        textSize(80);
        text("Press Space To Play Again", 500, 600);
    }

    public void death() {
        if (box.getY() <= 0 || box.BottomY() >= height) {//senses when you hit the top or bottom meaning you die advancing the scen
            scene++;
        }
    }
    public void removeRamps(){//removes all ramps in the arraylist
        for(int i=0; i<Ramps.size(); i++){
            Ramps.remove(i);
        }
    }
}
