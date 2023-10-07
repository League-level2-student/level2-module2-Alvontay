package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
    int foodX;
    int foodY;
    int direction = UP;
    int foodEaten = 0;
    int size = 20;
    ArrayList<Segment> tail = new ArrayList<>();
    
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(WIDTH,HEIGHT);
    }

    @Override
    public void setup() {
        head = new Segment(size*5,size*5);
        frameRate(10);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
        foodX = ((int)random(WIDTH/size)*size);
        foodY = ((int)random(HEIGHT/size)*size);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(0,0,0);
        drawFood();
        move();
        drawSnake();
        eat();
        textSize(20);
        text("Score " + foodEaten, 420, 20);
    }

    void drawFood() {
        // Draw the food
    	fill(180,0,0);
        rect(foodX,foodY,size,size);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0,255,0);
    	rect(head.x,head.y,size,size);
    	drawTail();
    	manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
//    	int color = (foodEaten * 20);
    	fill(0,200,0);
    	for (Segment i: tail) {
    		rect(i.x,i.y,size,size);
    	}
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
        checkTailCollision();
        drawTail();
        tail.add(new Segment(head.x, head.y));
        tail.remove(0);
    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
    	for (Segment i: tail) {
    		if (i.x == head.x && i.y == head.y) {
    			foodEaten = 1;
    			tail = new ArrayList<Segment>();
    	        tail.add(new Segment(head.x, head.y));
    		}
    	}
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed    	
    	if ((direction == UP && keyCode != DOWN) || 
    		(direction == DOWN && keyCode != UP) ||
    		(direction == RIGHT && keyCode != LEFT) ||
    		(direction == LEFT && keyCode != RIGHT)) {
    		
    		if (keyCode == UP || keyCode == DOWN || keyCode == RIGHT || keyCode == LEFT) {
        		direction = keyCode;	
    		}
    	}
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        if (direction == UP) {
            head.y = head.y - size;
        } else if (direction == DOWN) {
            head.y = head.y + size;                
        } else if (direction == LEFT) {
            head.x = head.x - size;
        } else if (direction == RIGHT) {
            head.x = head.x + size;
        }
        
        checkBoundaries();
 
    }

    void checkBoundaries() {
        if (head.x == 500) {
        	head.x = 0;
        } else if (head.x == -size) {
        	head.x = 500 - size;
        }
        if (head.y == 500) {
        	head.y = 0;
        } else if (head.y == -size) {
        	head.y = 500 - size;
        }
        
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if (head.x == foodX && head.y == foodY) {
    		foodEaten++;
    		dropFood();
    		tail.add(new Segment(head.x, head.y));
    	}
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
