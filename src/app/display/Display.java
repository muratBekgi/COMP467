package app.display;

import app.tools.ui.UIManager;

import javax.swing.*;

public class Display {
    private JFrame frame;
    private JComponent component;
    private int w;
    private int h;
    private String title;

    public Display(String title, int width, int height)
    {
        this.title = title;
        w = width;
        h = height;
        createDisplay();
    }

    public void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }
}
