package app.tools.tools;

import app.Handler;

import java.awt.Color;

public class CursorAttributes {

    private Tool equippedTool;
    private Color color;
    private int strokeWidth;
    private float transparencyVal = 1.0f;

    public CursorAttributes(Handler handler)
    {
        equippedTool = new Brush(handler);
        color = Color.BLACK;
        strokeWidth = 5;
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
}
