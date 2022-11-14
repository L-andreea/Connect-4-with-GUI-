package forza4;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class PersonalJFrame extends JFrame {

    /** the label used in the frame */
    private PersonalJLabel label;
    /** the panel used in the frame */
    private JPanel panel;

    /**
     * set the frame with the paramentr given
     * 
     * @param title      the title of the frame
     * @param mex        the mex that was put in the jlabel and dispaly
     * @param closeFrame type of closing the frame
     * @param colorBack  color of the backgraund of the panel
     */
    public PersonalJFrame(String title, String mex, int closeFrame, Color colorBack) {
        this.setTitle(title);
        this.setSize(new Dimension(500, 400));
        panel = new JPanel();
        panel.setBackground(colorBack);
        label = new PersonalJLabel();
        label.setMexLabel(mex);
        panel.add(label);
        this.setLocationRelativeTo(null);
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(closeFrame);

    }

}
