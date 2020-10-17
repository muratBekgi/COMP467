package app;

import app.display.Display;
import app.input.KeyManager;
import app.input.MouseManager;
import app.tools.canvas.Canvas;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

public class PaintApp {

        Handler handler;

        Display display;

        private int width = 600, height = 500;
        public String title = "PaintApp";

        KeyManager keyManager;
        MouseManager mouseManager;

        public PaintApp() {
                keyManager = new KeyManager();
                mouseManager = new MouseManager();

                init();
        }

        public void init()
        {
                display = new Display(title, width, height);

                display.getFrame().addKeyListener(keyManager);
                display.getFrame().addMouseListener(mouseManager);
                display.getFrame().addMouseMotionListener(mouseManager);

                handler = new Handler(this);

                // Position the buttons in the bottom of the frame
                display.getFrame().add(handler.getManager().getUiManager().getButtonPanel(), BorderLayout.SOUTH);

                // Make the drawing area take up the rest of the frame
                //display.getFrame().add(new Canvas(), BorderLayout.CENTER);
        }

        public KeyManager getKeyManager() {
                return keyManager;
        }

        public void setKeyManager(KeyManager keyManager) {
                this.keyManager = keyManager;
        }

        public MouseManager getMouseManager() {
                return mouseManager;
        }

        public void setMouseManager(MouseManager mouseManager) {
                this.mouseManager = mouseManager;
        }
}