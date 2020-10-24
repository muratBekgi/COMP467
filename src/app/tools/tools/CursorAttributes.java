package app.tools.tools;

import app.Handler;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class CursorAttributes {

    private Tool equippedTool;
    private Color color;
    private int strokeWidth;
    private float transparencyVal = 1.0f;
    private Point drawStart, drawEnd;


    public CursorAttributes(Handler handler)
    {
        equippedTool = new Brush(handler);
        color = Color.BLACK;
        strokeWidth = 5;
    }

    public void onPress(MouseEvent e)
    {
        drawStart = e.getPoint();
        getEquippedTool().onPress(e);
    }
    public void onRelease(MouseEvent e)
    {
        getEquippedTool().onRelease(e);
        drawStart = null;
        drawEnd = null;

    }
    public void onDrag(MouseEvent e)
    {
        drawEnd = e.getPoint();
        getEquippedTool().onDrag(e);
    }

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public Color getColor() {
        return color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setEquippedTool(Tool equippedTool) {
        this.equippedTool = equippedTool;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float getTransparencyVal() {
        return transparencyVal;
    }
    public void setTransparencyVal(float transparencyVal) {
        this.transparencyVal = transparencyVal;
    }

    public Point getDrawStart() {
        return drawStart;
    }

    public void setDrawStart(Point drawStart) {
        this.drawStart = drawStart;
    }

    public Point getDrawEnd() {
        return drawEnd;
    }

    public void setDrawEnd(Point drawEnd) {
        this.drawEnd = drawEnd;
    }
}
