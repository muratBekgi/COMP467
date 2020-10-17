package app.tools.tools;

import app.Handler;

import java.awt.event.ActionEvent;

public abstract class Tool {
    Handler handler;
    public Tool(Handler handler)
    {
        this.handler = handler;
    }

    public void performUIClick(ActionEvent e)
    {
        handler.getManager().getCursor().setEquippedTool(this);
    }
}
