package com.petrusandrey;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class Circle implements Shape{

   private static double diameter = 30;

   private Board board;
   private GraphicsContext gc;
   private double x;
   private double y;

   private double xSpeed;
   private double ySpeed;

   public Circle(Board board, GraphicsContext gc, double x, double y) {
       this.board = board;
       this.gc = gc;
       this.x = x;
       this.y = y;

       Random random = new Random();
       xSpeed = 2 + random.nextInt(4);
       ySpeed = 2 + random.nextInt(1);
    }

    @Override
    public void move() {

        x += xSpeed;
        y += ySpeed;

        if (handleСollision()) {
            return;
        }

        if (x + diameter > gc.getCanvas().getWidth()) {
            setDirectionLeft();
        }

        if (y + diameter > gc.getCanvas().getHeight()) {
            setDirectionTop();
        }

        if (x < 0) {
            setDirectionRight();
        }

        if (y < 0) {
            setDirectionBottom();
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
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.fillOval(x, y, diameter, diameter);
        gc.strokeOval(x, y, diameter, diameter);
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
