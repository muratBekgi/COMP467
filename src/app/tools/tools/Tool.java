package app.tools.tools;

import app.Handler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public abstract class Tool {
    Handler handler;
    public int type; //0 = shape //1 = brush
    public Tool(Handler handler)
    {
        this.handler = handler;
    }

    public void performUIClick(ActionEvent e)
    {
        handler.getManager().getCursor().setEquippedTool(this);
    }

    public abstract void onPress(MouseEvent e);
    public abstract void onRelease(MouseEvent e);
    public abstract void onDrag(MouseEvent e);
    public abstract Shape drawShape(int x1, int y1, int x2, int y2);

    public int getType() {
        return type;
    }
}
