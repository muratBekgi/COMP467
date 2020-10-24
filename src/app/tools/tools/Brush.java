package app.tools.tools;

import app.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Brush extends Tool {
    public Brush(Handler handler)
    {
        super(handler);
    }

    @Override
    public void onPress(MouseEvent e) {

    }

    @Override
    public void onRelease(MouseEvent e) {

    }

    @Override
    public void onDrag(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        CursorAttributes cursor = handler.getManager().getCursor();
        int width = cursor.getStrokeWidth();
        Shape aShape = drawShape(x, y, width, width);

        handler.getManager().getUiManager().getCanvas().addStroke(aShape, cursor.getColor(), cursor.getColor(), cursor.getTransparencyVal());
    }


    @Override
    public Shape drawShape(int x1, int y1, int x2, int y2) {
        int strokeWidth = handler.getManager().getCursor().getStrokeWidth();
        return new Ellipse2D.Float(x1, y1, strokeWidth, strokeWidth);
    }
}
