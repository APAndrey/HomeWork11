package com.petrusandrey;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int BOARD_WIDTH = 900;
    private static final int BOARD_HEIGT = 700;
    private static final int FPS = 60;

    private boolean closed;
    private Board board;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        Logger.log("Game started");

        GraphicsContext gc = canvas.getGraphicsContext2D();
        board = new Board(gc);
        board.draw();

        new Thread(this::runMainGameLoopInThread).start();


    }
    @Override
    public void stop(){

        closed = true;
    }
    private void runMainGameLoopInThread(){
        while (!closed) {
            Platform.runLater(this::drawFrame);
            try {
                int pauseBetweenFramesMillis = 1000 / FPS;
                Thread.sleep(pauseBetweenFramesMillis);
            } catch (InterruptedException e){
                break;
            }
        }
    }
    private void drawFrame() {
        board.draw();
        board.move();
    }
}


