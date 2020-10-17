package app.input;

import app.tools.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {


    UIManager uiManager;

    private boolean leftPressed, rightPressed, leftReleased, rightReleased;
    private int mouseX, mouseY;

    public void setUiManager(UIManager uiManager)
    {
        this.uiManager = uiManager;
    }

    public boolean isLeftPressed()
    {
        return leftPressed;
    }

    public boolean isRightPressed()
    {
        return rightPressed;
    }

    public boolean isLeftReleased()
    {
        return leftReleased;
    }

    public boolean isRightReleased()
    {
        return rightReleased;
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftPressed = true;
            leftReleased = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON2)
        {
            rightPressed = true;
            rightReleased = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftPressed = false;
            leftReleased = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON2)
        {
            rightPressed = false;
            rightReleased = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null)
        {
            uiManager.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null)
        {
            uiManager.mouseMoved(e);
        }
    }
}
