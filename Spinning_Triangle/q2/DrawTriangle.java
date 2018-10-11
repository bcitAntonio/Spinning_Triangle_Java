package q2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * <p>
 * In this class, we have created a program that when you click your mouse at
 * any random point, the program will use that point as the reference of the
 * center of an equilateral triangle. Then as we drag our mouse closer or
 * further the size of equilateral triangle also increases or increases.
 * </p>
 *
 * @author Antonio Cao Shen
 * @version 1.0
 */
public class DrawTriangle extends Application {

    /** declaring static final for the number three in integer. */
    private static final int THREE = 3;

    /** Circle for point one after the drag. */
    private Circle pt1;

    /** declaring circle as center of the triangle. */
    private Circle center1;

    /** The contents of the application scene. */
    private Group root;

    /** center point. */
    private Circle atCenter = new Circle(0, 0, THREE);

    /**
     * Displays an initially empty scene, 
     * waiting for the user to draw lines with
     * the mouse.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        root = new Group(atCenter);
        atCenter.setFill(Color.RED);

        final int appWidth = 800;
        final int appHeight = 500;
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);

        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDrag);

        primaryStage.setTitle("Equilateral Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * method that create a circle for the center of the triangle when mouse is
     * clicked.
     * 
     * @param event
     *            MouseEvent
     */
    public void processMousePress(MouseEvent event) {
        root.getChildren().clear();
        center1 = new Circle(event.getX(), event.getY(), THREE);
        center1.setFill(Color.WHITE);

    }

    /**
     * method that increase or decrease the size of the triangle when mouse is
     * dragged from one point to another.
     * 
     * @param event
     *            MouseEvent
     */
    public void processMouseDrag(MouseEvent event) {
        pt1 = new Circle(event.getX(), event.getY(), THREE);
        triangle();

    }

    /**
     * method that calculate one point is 
     * 120 degree from the next point and connect
     * all three points to form a triangle using polygon.
     */
    public void triangle() {
        // clears the old triangle when mouse is clicked again.
        root.getChildren().clear();

        // get the point that the mouse is dragged to and call it pt1
        double x1 = pt1.getCenterX() - center1.getCenterX();
        double y1 = pt1.getCenterY() - center1.getCenterY();

        /*
         * calculate pt2 by using the rotation 
         * law, rotating point1 by 120 degree.
         */
        double x2 = (x1 * Math.cos(Math.PI * 2 / THREE) 
            - y1 * Math.sin(Math.PI * 2 / THREE)) + center1.getCenterX();
        double y2 = (x1 * Math.sin(Math.PI * 2 / THREE) 
            + y1 * Math.cos(Math.PI * 2 / THREE)) + center1.getCenterY();

        // create a circle for pt2 using x2 and y2 we calculated above
        Circle pt2 = new Circle(x2, y2, THREE);
        double x4 = pt2.getCenterX() - center1.getCenterX();
        double y4 = pt2.getCenterY() - center1.getCenterY();

        // use rotation law to calculate and make a circle for point 3
        double x3 = (x4 * Math.cos(Math.PI * 2 / THREE) 
            - y4 * Math.sin(Math.PI * 2 / THREE)) + center1.getCenterX();
        double y3 = (x4 * Math.sin(Math.PI * 2 / THREE) 
            + y4 * Math.cos(Math.PI * 2 / THREE)) + center1.getCenterY();
        Circle pt3 = new Circle(x3, y3, THREE);

        // connect 3 points to form triangle using polygon
        Polygon polygon = new Polygon(pt1.getCenterX(), 
            pt1.getCenterY(), pt2.getCenterX(), pt2.getCenterY(),
                pt3.getCenterX(), pt3.getCenterY());

        // set stroke to white, stroke width to 5
        polygon.setStroke(Color.WHITE);
        final int strokeWidth = 5;
        polygon.setStrokeWidth(strokeWidth);
        root.getChildren().add(polygon);
        root.getChildren().add(center1);

    }

    /**
     * Launches the JavaFX application.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
