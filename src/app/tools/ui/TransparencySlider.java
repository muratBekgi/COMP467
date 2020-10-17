package app.tools.ui;

import app.Handler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.DecimalFormat;

public class TransparencySlider {

    Handler handler;

    // Slider used to change the transparency
    JSlider transSlider;
    JLabel transLabel;

    // Makes sure the float for transparency only shows 2 digits
    DecimalFormat dec = new DecimalFormat("#.##");

    // Transparency of the shape
    float transparentVal = 1.0f;

    public TransparencySlider(Handler handler)
    {
        this.handler = handler;
        createSlider();
    }

    public void createSlider()
    {
        // Add the transparent label and slider
        transLabel = new JLabel("Transparent: 1");

        // Min value, Max value and starting value for slider
        transSlider = new JSlider(1, 99, 99);

        // Create an instance of ListenForEvents to handle events
        ListenForSlider lForSlider = new ListenForSlider();

        // Tell Java that you want to be alerted when an event
        // occurs on the slider
        transSlider.addChangeListener(lForSlider);
    }

    public JSlider getTransSlider() {
        return transSlider;
    }

    public void setTransSlider(JSlider transSlider) {
        this.transSlider = transSlider;
    }

    public JLabel getTransLabel() {
        return transLabel;
    }

    public void setTransLabel(JLabel transLabel) {
        this.transLabel = transLabel;
    }

    // Implements ActionListener so it can react to events on components
    // Called when the spinner is changed
    public class ListenForSlider implements ChangeListener {
        public void stateChanged(ChangeEvent e) {

            // Check if the source of the event was the button
            if (e.getSource() == transSlider) {

                // Change the value for the label next to the spinner
                // Use decimal format to make sure only 2 decimals are ever displayed
                transLabel.setText("Transparent: " + dec.format(transSlider.getValue() * .01));

                // Set the value for transparency for every shape drawn after
                handler.getManager().getCursor().setTransparencyVal((float) (transSlider.getValue() * .01));
            }
        }
    }
}
