package app.tools.ui;

import app.Handler;
import app.tools.tools.Tool;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button {

    protected Handler handler;

    protected String iconPath;
    private Tool tool;

    protected JButton button;

    public Button(Handler handler, String iconPath, Tool tool)
    {
        this.handler = handler;
        this.iconPath = iconPath;
        this.tool = tool;
    }

    // Spits out buttons based on the image supplied
    // actionNum represents each shape to be drawn
    public void createButton() {
        button = new JButton();
        Icon butIcon = new ImageIcon(iconPath);
        button.setIcon(butIcon);

        // Make the proper actionPerformed method execute when the
        // specific button is pressed
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tool.performUIClick(e);
            }
        });
    }

    public JButton getButton()
    {
        return button;
    }
}
