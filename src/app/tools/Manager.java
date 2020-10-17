package app.tools;

import app.Handler;
import app.tools.tools.CursorAttributes;
import app.tools.tools.Tool;
import app.tools.ui.UIManager;

import java.util.ArrayList;

public class Manager {
    Handler handler;

    //cursor
    CursorAttributes cursor;

    //ui
    UIManager uiManager;

    //tools
    ArrayList<Tool> tools;

    public Manager(Handler handler)
    {
        this.handler = handler;
        cursor = new CursorAttributes(handler);
        uiManager = new UIManager(handler);

        handler.getMouseManager().setUiManager(uiManager);
    }

    public CursorAttributes getCursor() {
        return cursor;
    }

    public void setCursor(CursorAttributes cursor) {
        this.cursor = cursor;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }
}
