package com.petrusandrey;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private GraphicsContext gc;

    private List<Shape> shapes = new ArrayList<>();

    public Board(GraphicsContext gc) {
        this.gc = gc;

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            shapes.add(new Circle(this, gc, random.nextInt(100), random.nextInt(100)));

        }
        Random random1 = new Random();
        for (int i = 0; i < 1; i++) {

            shapes.add(new Square(this, gc, random1.nextInt(200), random1.nextInt(200)));
            shapes.add(new Snowman(this, gc, random1.nextInt(300), random1.nextInt(300)));
        }
    }
    public void move() {
        for (Shape shape : shapes) {
            shape.move();
        }
    }
    public void draw() {
        clean();
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public List<Shape> getShapes() {

        return shapes;
    }

    private void clean() {

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
