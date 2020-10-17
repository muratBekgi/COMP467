package app;

import app.input.KeyManager;
import app.input.MouseManager;
import app.tools.Manager;

public class Handler {
    private PaintApp app;
    private Manager manager;

    public Handler(PaintApp app)
    {
        this.app = app;
        manager = new Manager(this);
    }

    public PaintApp getApp()
    {
        return app;
    }

    public void setApp(PaintApp app)
    {
        this.app = app;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public KeyManager getKeymanager()
    {
        return app.getKeyManager();
    }

    public MouseManager getMouseManager()
    {
        return app.getMouseManager();
    }
}
