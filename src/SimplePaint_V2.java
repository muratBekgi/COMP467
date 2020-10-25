import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

@SuppressWarnings("serial")
public class SimplePaint_V2 extends JFrame {
    JMenuBar menubar;
    JMenu File, Exit;
    JMenuItem New, Open;
    JComponent DrawingBoard;

    JButton brushBut, lineBut, ellipseBut, rectBut, strokeBut, fillBut, eraserBut;

    // Slider used to change the transparency
    JSlider transSlider;

    JLabel transLabel;

    // Makes sure the float for transparency only shows 2 digits
    DecimalFormat dec = new DecimalFormat("#.##");

    // Contains all of the rules for drawing
    Graphics2D graphSettings;

    // Change the stroke dynamically with a component
    //graphSettings.setStroke(new BasicStroke(5F));

    // used to monitor what shape to draw next
    int currentAction = 1;

    // Transparency of the shape
    //1.0f transparency off
    float transparentVal = 1.0f;

    // Default stroke and fill colors
    Color strokeColor = Color.BLACK, fillColor = Color.BLACK;

    public static void main(String [] args) {
        new SimplePaint_V2();
    }

    public SimplePaint_V2() {

        // Define the defaults for the JFrame
        this.setSize(800, 600);
        this.setTitle("Java Paint");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menubar);

        JMenuBar menuBar = new JMenuBar();

        // Add the menubar to the frame
        setJMenuBar(menuBar);

        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New Project");
        //JMenuItem openAction = new JMenuItem("Open File");
        JMenuItem exportAction = new JMenuItem("Export");

        fileMenu.add(newAction);
        //fileMenu.add(openAction);
        fileMenu.add(exportAction);

        // Creating New Project
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                new SimplePaint_V2().setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();

        // Swing box that will hold all the buttons horizontally
        Box theBox = Box.createHorizontalBox();

        // Make all the buttons in makeMeButtons by passing the
        // button icon.
        brushBut = makeMeButtons("/brush.png", 1);
        lineBut = makeMeButtons("./src/Line.png", 2);
        ellipseBut = makeMeButtons("./src/Ellipse.png", 3);
        rectBut = makeMeButtons("./src/Rectangle.png", 4);
        //eraserBut = makeMeButtons("./src/Rectangle.png", 7);

        // Make all the buttons in makeMeColorButton by passing the
        // button icon and true for stroke color or false for fill

        strokeBut = makeMeColorButton("./src/Stroke.png", 5, true);
        fillBut = makeMeColorButton("./src/Fill.png", 6, false);

        // Add the buttons to the box
        theBox.add(brushBut);
        theBox.add(lineBut);
        theBox.add(ellipseBut);
        theBox.add(rectBut);
        theBox.add(strokeBut);
        theBox.add(fillBut);
        //theBox.add(eraserBut);

        // Add the transparent label and slider
        transLabel = new JLabel("Transparent: 1");

        // Min value, Max value and starting value for slider
        transSlider = new JSlider(1, 99, 99);

        // Create an instance of ListenForEvents to handle events
        ListenForSlider lForSlider = new ListenForSlider();

        // creating class listenForSlider that will make an alert when an event
        // occurs on the slider
        transSlider.addChangeListener(lForSlider);

        theBox.add(transLabel);
        theBox.add(transSlider);

        // Add the box of buttons to the panel
        buttonPanel.add(theBox);

        // Position the buttons in the bottom of the frame
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Make the drawing area take up the rest of the frame
        //this.add(new DrawingBoard(), BorderLayout.CENTER);
        final DrawingBoard drawPanel = new DrawingBoard();
        this.add(drawPanel, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.WHITE);

        // exporting image
        exportAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                BufferedImage image = new BufferedImage(drawPanel.getWidth(),
                        drawPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = image.createGraphics();
                drawPanel.paint(g);
                g.dispose();

                JFileChooser fileChooser = new JFileChooser();
                java.io.File theDirectory = new File("C:/Users/Wenda/Desktop");
                fileChooser.setCurrentDirectory(theDirectory);
                FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(
                        "PNG file (*.png)", "png");
                fileChooser.addChoosableFileFilter(pngFilter);
                fileChooser.setFileFilter(pngFilter);

                int status = fileChooser.showSaveDialog(SimplePaint_V2.this);

                if (status == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(image, "png",
                                fileChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(null, "Image saved to "
                                + fileChooser.getSelectedFile().getName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        // Show the frame
        this.setVisible(true);
    }

    // Spits out buttons based on the image supplied
    // actionNum represents each shape to be drawn

    public JButton makeMeButtons(String iconFile, final int actionNum) {

        JButton theBut = new JButton();
        Icon butIcon = new ImageIcon(iconFile);
        theBut.setIcon(butIcon);

        // Make the proper actionPerformed method execute when the
        // specific button is pressed
        theBut.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentAction = actionNum;
            }
        });

        return theBut;
    }

    // Spits out buttons based on the image supplied and
    // whether a stroke or fill is to be defined
    public JButton makeMeColorButton(String iconFile, final int actionNum, final boolean stroke) {

        JButton theBut = new JButton();
        Icon butIcon = new ImageIcon(iconFile);
        theBut.setIcon(butIcon);

        theBut.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (stroke) {

                    // JColorChooser is a popup that lets you pick a color
                    strokeColor = JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);

                } else {

                    fillColor = JColorChooser.showDialog(null,  "Pick a Fill", Color.BLACK);
                }
            }
        });

        return theBut;
    }

    private class DrawingBoard extends JComponent {

        // ArrayLists that contain each shape drawn along with
        // that shapes stroke and fill
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        ArrayList<Color> shapeFill = new ArrayList<Color>();
        ArrayList<Color> shapeStroke = new ArrayList<Color>();
        ArrayList<Float> transPercent = new ArrayList<Float>();

        Point drawStart, drawEnd;

        // Monitors events on the drawing area of the frame
        public DrawingBoard() {

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

                        // Add shapes, fills, colors, and transparency to the ArrayLists
                        shapes.add(aShape);
                        shapeFill.add(fillColor);
                        shapeStroke.add(strokeColor);
                        transPercent.add(transparentVal);

                        drawStart = null;
                        drawEnd = null;

                        // repaint the drawing area
                        repaint();

                    }
                }
            } ); // end of addMouseListener

            this.addMouseMotionListener(new MouseMotionAdapter() {

                public void mouseDragged(MouseEvent e) {

                    // If this is a brush have shapes go on the screen quickly
                    // x and y will get the end position of the shape
                    if (currentAction == 1) {

                        int x = e.getX();
                        int y = e.getY();

                        Shape aShape = null;

                        // Make stroke and fill equal
                        // brush strokes are really ellipses
                        strokeColor = fillColor;

                        aShape = drawBrush(x,y,5,5);

                        shapes.add(aShape);
                        shapeFill.add(fillColor);
                        shapeStroke.add(strokeColor);
                        transPercent.add(transparentVal);
                    }


                    // Get the final x & y position after the mouse is dragged
                    drawEnd = new Point(e.getX(), e.getY());
                    repaint();
                }
            } ); // end of addMouseMotionListener
        }


        public void paint(Graphics g) {

            // Class used to define the shapes to be drawn and rendered on screen
            graphSettings = (Graphics2D)g;

            // Antialiasing cleans up the jagged lines and defines rendering rules
            graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Defines the line width of the stroke
            graphSettings.setStroke(new BasicStroke(5));

            // Iterators created to cycle through arraylist of strokes and fills
            Iterator<Color> strokeCounter = shapeStroke.iterator();
            Iterator<Color> fillCounter = shapeFill.iterator();

            // Iterator for transparency
            Iterator<Float> transCounter = transPercent.iterator();

            // cycling through shapes that were created
            for (Shape s : shapes) {

                // Sets the shapes transparency value
                graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transCounter.next()));

                // Grabs the next stroke from the color arraylist
                graphSettings.setPaint(strokeCounter.next());
                // draws shape on the screen
                graphSettings.draw(s);

                // Grabs the next fill from the color arraylist
                graphSettings.setPaint(fillCounter.next());
                graphSettings.fill(s);
            }

            // Guide shape used for drawing
            if (drawStart != null && drawEnd != null) {

                // Makes the guide shape transparent
                //1.0f means transparency off
                graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));

                // Make guide shape gray for professional look
                graphSettings.setPaint(Color.LIGHT_GRAY);

                Shape aShape = null;

                if (currentAction == 2) {

                    aShape = drawLine(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);

                } else if (currentAction == 3) {

                    aShape = drawEllipse(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);

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
            // Math.min returns the points closest to (0,0)
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);

            // Gets the difference between the coordinates and
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            return new Rectangle2D.Float(x, y, width, height);
        }

        // The other shapes will work similarly
        // More on this in the next tutorial
        private Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2) {
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            return new Ellipse2D.Float(x, y, width, height);
        }

        private Line2D.Float drawLine(int x1, int y1, int x2, int y2) {

            return new Line2D.Float(x1, y1, x2, y2);
        }

        private Ellipse2D.Float drawBrush(int x1, int y1, int brushStrokeWidth, int brushStrokeHeight) {

            return new Ellipse2D.Float(x1, y1, brushStrokeWidth, brushStrokeHeight);
        }
    }

    // Implements ActionListener so it can react to events on components
    private class ListenForSlider implements ChangeListener {

        // Called when the slider is changed
        public void stateChanged(ChangeEvent e) {

            // Check if the source of the event was the slider
            if (e.getSource() == transSlider) {

                // Change the value for the label next to the slider
                // Use decimal format to make sure only 2 decimals are displayed
                transLabel.setText("Transparent: " + dec.format(transSlider.getValue() * .01) );

                // Set the value for transparency for every shape drawn after
                transparentVal = (float) (transSlider.getValue() * .01);

            }

        }

    }
}
