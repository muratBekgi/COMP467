package app.tools.ui;

import app.Handler;

import app.tools.canvas.Canvas;
import app.tools.tools.Brush;
import app.tools.tools.Ellipse;
import app.tools.tools.Line;
import app.tools.tools.Rectangle;

import javax.swing.Box;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private Handler handler;
    private Canvas canvas;

    private ArrayList<Button> buttonList;
    private TransparencySlider tSlider;

    private
    Box box;
    JPanel buttonPanel;

    public  UIManager(Handler handler)
    {
        this.handler = handler;
        box = Box.createHorizontalBox();

        buttonList = new ArrayList<Button>();
        buttonPanel = new JPanel();

        setupToolMenu();
        createButtons();
        setupTransparencySlider();

        buttonPanel.add(box);

        canvas = new Canvas(handler, 400, 500);
    }

    public void setupToolMenu()
    {
        buttonList.add(new Button(handler, "./res/icons/brush.png", new Brush(handler)));
        buttonList.add(new Button(handler, "./res/icons/Line.png", new Line(handler)));
        buttonList.add(new Button(handler, "./res/icons/Ellipse.png", new Ellipse(handler)));
        buttonList.add(new Button(handler, "./res/icons/Rectangle.png", new Rectangle(handler)));

        buttonList.add(new ColorPicker(handler, "./res/icons/Fill.png"));

        //createButtons();
    }

    public void setupTransparencySlider()
    {
        tSlider = new TransparencySlider(handler);

        box.add(tSlider.getTransLabel());
        box.add(tSlider.getTransSlider());
    }

    public void createButtons()
    {
        for(Button tool : buttonList)
        {
            tool.createButton();
            box.add(tool.getButton());
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }


    public void mouseMoved(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {

    }
}