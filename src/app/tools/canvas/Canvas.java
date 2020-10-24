package app.tools.canvas;

import app.Handler;

import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Canvas extends JComponent {
    Handler handler;
    Graphics2D graphics;
    BufferedImage bufferedImage;
    int w;
    int h;
    double scale = 1;

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    ArrayList<Color> shapeFill = new ArrayList<Color>();
    ArrayList<Color> shapeStroke = new ArrayList<Color>();
    ArrayList<Float> transPercent = new ArrayList<Float>();

    public Canvas(Handler handler, int w, int h) {
        this.handler = handler;

        if(w == 0)
            this.w = 500;
        if(h == 0)
            this.h = 400;

        bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        graphics = bufferedImage.createGraphics();

        graphics.translate(w / 2, h / 2);
        graphics.scale(scale, scale);

        graphics.setBackground(Color.white);

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

//                if (currentAction != 1) {
//
//                    // When the mouse is pressed get x & y position
//
//                    drawStart = new Point(e.getX(), e.getY());
//                    drawEnd = drawStart;
//                    repaint();
//
//                }

                handler.getManager().getCursor().onPress(e);
                repaint();
            }

            public void mouseReleased(MouseEvent e) {

//                if (currentAction != 1) {
//
//                    // Create a shape using the starting x & y
//                    // and finishing x & y positions
//
//                    Shape aShape = null;
//
//                    if (currentAction == 2) {
//                        aShape = drawLine(drawStart.x, drawStart.y, e.getX(), e.getY());
//                    } else if (currentAction == 3) {
//                        aShape = drawEllipse(drawStart.x, drawStart.y, e.getX(), e.getY());
//                    } else if (currentAction == 4) {
//
//                        // Create a new rectangle using x & y coordinates
//
//                        aShape = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
//                    }
//
//
//                    // Add shapes, fills and colors to there ArrayLists
//
//                    shapes.add(aShape);
//                    shapeFill.add(fillColor);
//                    shapeStroke.add(strokeColor);
//
//                    // Add transparency value to ArrayList
//
//                    transPercent.add(transparentVal);
//
//                    drawStart = null;
//                    drawEnd = null;

                    // repaint the drawing area
//
//                    repaint();
//
//                }

                handler.getManager().getCursor().onRelease(e);
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {

                // If this is a brush have shapes go on the screen quickly

//                if (currentAction == 1) {
//
//                    int x = e.getX();
//                    int y = e.getY();
//
//                    Shape aShape = null;
//
//                    // Make stroke and fill equal to eliminate the fact that this is an ellipse
//                    strokeColor = fillColor;
//
//                    aShape = drawBrush(x, y, 5, 5);
//
//                    shapes.add(aShape);
//                    shapeFill.add(fillColor);
//                    shapeStroke.add(strokeColor);
//
//                    // Add the transparency value
//                    transPercent.add(transparentVal);
//                }

                // Get the final x & y position after the mouse is dragged

//                drawEnd = new Point(e.getX(), e.getY());
                handler.getManager().getCursor().onDrag(e);
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
// Class used to define the shapes to be drawn

        this.graphics = (Graphics2D) g;

        // Antialiasing cleans up the jagged lines and defines rendering rules

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke

        graphics.setStroke(new BasicStroke(4));

        // Iterators created to cycle through strokes and fills
        Iterator<Color> strokeCounter = shapeStroke.iterator();
        Iterator<Color> fillCounter = shapeFill.iterator();

        // Iterator for transparency

        Iterator<Float> transCounter = transPercent.iterator();

        for (Shape s : shapes) {

            // Sets the shapes transparency value

            graphics.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, transCounter.next()));

            // Grabs the next stroke from the color arraylist
            graphics.setPaint(strokeCounter.next());

            graphics.draw(s);

            // Grabs the next fill from the color arraylist
            graphics.setPaint(fillCounter.next());

            graphics.fill(s);
        }

        // Guide shape used for drawing
//        if (drawStart != null && drawEnd != null) {
//            // Makes the guide shape transparent
//
//            graphics.setComposite(AlphaComposite.getInstance(
//                    AlphaComposite.SRC_OVER, 0.40f));
//
//            // Make guide shape gray for professional look
//
//            graphics.setPaint(Color.LIGHT_GRAY);
//
//            Shape aShape = null;

//            if (currentAction == 2) {
//                aShape = drawLine(drawStart.x, drawStart.y,
//                        drawEnd.x, drawEnd.y);
//            } else if (currentAction == 3) {
//                aShape = drawEllipse(drawStart.x, drawStart.y,
//                        drawEnd.x, drawEnd.y);
//            } else if (currentAction == 4) {
//
//                // Create a new rectangle using x & y coordinates
//
//                aShape = drawRectangle(drawStart.x, drawStart.y,
//                        drawEnd.x, drawEnd.y);
//            }

            //aShape = handler.getManager().getCursor().getEquippedTool().drawShape(x1, y1, x2, y2);


            //graphics.draw(aShape);
//        }
    }

    public Graphics2D getGraphics () {
        return graphics;
    }

    public void setGraphics (Graphics2D graphics){
        this.graphics = graphics;
    }

    public void addStroke(Shape shape, Color stroke, Color fill, Float transparency) {
        shapes.add(shape);
        shapeFill.add(fill);
        shapeStroke.add(stroke);
        transPercent.add(transparency);
    }
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<Color> getShapeFill() {
        return shapeFill;
    }

    public void setShapeFill(ArrayList<Color> shapeFill) {
        this.shapeFill = shapeFill;
    }

    public ArrayList<Color> getShapeStroke() {
        return shapeStroke;
    }

    public void setShapeStroke(ArrayList<Color> shapeStroke) {
        this.shapeStroke = shapeStroke;
    }

    public ArrayList<Float> getTransPercent() {
        return transPercent;
    }

    public void setTransPercent(ArrayList<Float> transPercent) {
        this.transPercent = transPercent;
    }

    /*
    // Contains all of the rules for drawing
    Graphics2D graphSettings;

    // Going to be used to monitor what shape to draw next
    int currentAction = 1;

    // ArrayLists that contain each shape drawn along with
    // that shapes stroke and fill

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    ArrayList<Color> shapeFill = new ArrayList<Color>();
    ArrayList<Color> shapeStroke = new ArrayList<Color>();
    ArrayList<Float> transPercent = new ArrayList<Float>();

    Point drawStart, drawEnd;

    // Monitors events on the drawing area of the frame

    public Canvas() {

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (currentAction != 1) {

                    // When the mouse is pressed get x & y position

                    drawStart = new Point(e.getX(), e.getY());
                    drawEnd = drawStart;
                    repaint();

                }


            }

            public void mouseReleased(MouseEvent e) {

                if (currentAction != 1) {

                    // Create a shape using the starting x & y
                    // and finishing x & y positions

                    Shape aShape = null;

                    if (currentAction == 2) {
                        aShape = drawLine(drawStart.x, drawStart.y, e.getX(), e.getY());
                    } else if (currentAction == 3) {
                        aShape = drawEllipse(drawStart.x, drawStart.y, e.getX(), e.getY());
                    } else if (currentAction == 4) {

                        // Create a new rectangle using x & y coordinates

                        aShape = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
                    }


                    // Add shapes, fills and colors to there ArrayLists

                    shapes.add(aShape);
                    shapeFill.add(fillColor);
                    shapeStroke.add(strokeColor);

                    // Add transparency value to ArrayList

                    transPercent.add(transparentVal);

                    drawStart = null;
                    drawEnd = null;

                    // repaint the drawing area

                    repaint();

                }

            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {

                // If this is a brush have shapes go on the screen quickly

                if (currentAction == 1) {

                    int x = e.getX();
                    int y = e.getY();

                    Shape aShape = null;

                    // Make stroke and fill equal to eliminate the fact that this is an ellipse
                    strokeColor = fillColor;

                    aShape = drawBrush(x, y, 5, 5);

                    shapes.add(aShape);
                    shapeFill.add(fillColor);
                    shapeStroke.add(strokeColor);

                    // Add the transparency value
                    transPercent.add(transparentVal);
                }

                // Get the final x & y position after the mouse is dragged

                drawEnd = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }


    public void paint(Graphics g) {
        // Class used to define the shapes to be drawn

        graphSettings = (Graphics2D) g;

        // Antialiasing cleans up the jagged lines and defines rendering rules

        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke

        graphSettings.setStroke(new BasicStroke(4));

        // Iterators created to cycle through strokes and fills
        Iterator<Color> strokeCounter = shapeStroke.iterator();
        Iterator<Color> fillCounter = shapeFill.iterator();

        // Iterator for transparency

        Iterator<Float> transCounter = transPercent.iterator();

        for (Shape s : shapes) {

            // Sets the shapes transparency value

            graphSettings.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, transCounter.next()));

            // Grabs the next stroke from the color arraylist
            graphSettings.setPaint(strokeCounter.next());

            graphSettings.draw(s);

            // Grabs the next fill from the color arraylist
            graphSettings.setPaint(fillCounter.next());

            graphSettings.fill(s);
        }

        // Guide shape used for drawing
        if (drawStart != null && drawEnd != null) {
            // Makes the guide shape transparent

            graphSettings.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));

            // Make guide shape gray for professional look

            graphSettings.setPaint(Color.LIGHT_GRAY);

            Shape aShape = null;

            if (currentAction == 2) {
                aShape = drawLine(drawStart.x, drawStart.y,
                        drawEnd.x, drawEnd.y);
            } else if (currentAction == 3) {
                aShape = drawEllipse(drawStart.x, drawStart.y,
                        drawEnd.x, drawEnd.y);
            } else if (currentAction == 4) {

                // Create a new rectangle using x & y coordinates

                aShape = drawRectangle(drawStart.x, drawStart.y,
                        drawEnd.x, drawEnd.y);
            }


            graphSettings.draw(aShape);
        }
    }

    private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2) {
        // Get the top left hand corner for the shape
        // Math.min returns the points closest to 0

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        // Gets the difference between the coordinates and

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);

        return new Rectangle2D.Float(
                x, y, width, height);
    }

    // The other shapes will work similarly
    // More on this in the next tutorial

    private Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);

        return new Ellipse2D.Float(
                x, y, width, height);
    }

    private Line2D.Float drawLine(int x1, int y1, int x2, int y2) {

        return new Line2D.Float(
                x1, y1, x2, y2);
    }

    private Ellipse2D.Float drawBrush(int x1, int y1, int brushStrokeWidth, int brushStrokeHeight) {

        return new Ellipse2D.Float(
                x1, y1, brushStrokeWidth, brushStrokeHeight);

    }
*/
}