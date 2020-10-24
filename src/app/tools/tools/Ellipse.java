package app.tools.tools;

import app.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Tool{
    public Ellipse(Handler handler)
    {
        super(handler);
    }

    @Override
    public void onPress(MouseEvent e) {

    }

    @Override
    public void onRelease(MouseEvent e) {
        CursorAttributes cursor = handler.getManager().getCursor();
        Point drawStart = cursor.getDrawStart();
        Shape shape = drawShape(drawStart.x, drawStart.y, e.getX(), e.getY());

        handler.getManager().getUiManager().getCanvas().addStroke(shape, cursor.getColor(), cursor.getColor(), cursor.getTransparencyVal());
    }

    @Override
    public void onDrag(MouseEvent e) {
        CursorAttributes cursor = handler.getManager().getCursor();
        Shape shape = drawShape(cursor.getDrawStart().x, cursor.getDrawStart().y, cursor.getDrawEnd().x, cursor.getDrawEnd().y);

        handler.getManager().getUiManager().getCanvas().getGraphics().draw(shape);
    }

    @Override
    public Shape drawShape(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);

        return new Ellipse2D.Float(
                x, y, width, height);    }
}
