package app.tools.ui;

import app.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPicker extends Button{
    public ColorPicker(Handler handler, String iconPath)
    {
        super(handler, iconPath, null);
    }

    @Override
    public void createButton() {
        button = new JButton();
        Icon butIcon = new ImageIcon(iconPath);
        button.setIcon(butIcon);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handler.getManager().getCursor().setColor(JColorChooser.showDialog(null, "Pick a Color", handler.getManager().getCursor().getColor()));
            }
        });
    }
}
