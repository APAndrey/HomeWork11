package com.petrusandrey;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.Random;

public class Snowman implements Shape {

    private static double diameter = 30;

    private Board board;
    private GraphicsContext gc;
    private double x;
    private double y;

    private double xSpeed;
    private double ySpeed;

    public Snowman(Board board, GraphicsContext gc, double x, double y) {
        this.board = board;
        this.gc = gc;
        this.x = x;
        this.y = y;

        Random random = new Random();
        xSpeed = 2 + random.nextInt(5);
        ySpeed = 2 + random.nextInt(5);
    }

    @Override
    public void move() {
        x += xSpeed;
        y += ySpeed;

        if (x + diameter > gc.getCanvas().getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y + diameter > gc.getCanvas().getHeight()) {
            ySpeed = -ySpeed;
        }
        if (x < 0) {
            xSpeed = -xSpeed;
        }
        if (y < 0) {
            ySpeed = -ySpeed;
        }
    }

    /**
     * @return is collision
     */
    private boolean handleСollision() {
        for (Shape shape : board.getShapes()) {
            if (shape == this) {
                continue;
            }
            if (shape.getDistance(x, y) < diameter) {
                if (x < shape.getX()) {
                    setDirectionLeft();
                } else {
                    setDirectionRight();
                }

                if (y < shape.getY()) {
                    setDirectionTop();
                } else {
                    setDirectionBottom();
                }

                return true;
            }
        }
        return false;
    }

    @Override
    public void draw() {
        gc.setFill(Color.INDIANRED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);

        gc.strokeLine(240, 73, 270, 73);
        gc.strokeLine(180, 73, 210, 73);
        gc.strokeLine(170, 140, 270, 140);
        gc.strokeOval(210, 60, 30, 30);
        gc.strokeOval(215, 37, 20, 20);
        gc.strokeOval(200, 90, 50, 50);

    }

    @Override
    public double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
    private void setDirectionLeft() {
        xSpeed = -Math.abs(xSpeed);
    }

    private void setDirectionRight() {
        xSpeed = Math.abs(xSpeed);
    }

    private void setDirectionTop() {
        ySpeed = -Math.abs(ySpeed);
    }

    private void setDirectionBottom() {
        ySpeed = Math.abs(ySpeed);
    }

}