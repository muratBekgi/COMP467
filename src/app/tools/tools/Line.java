package app.tools.tools;

import app.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Line extends Tool {
    public Line(Handler handler)
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
        return new Line2D.Float(x1, y1, x2, y2);
    }
}
