package forza4;

import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

/**
 * PersonalJLabel extend JLabel and implement the method to set the label of the
 * MainFrame
 */
public class PersonalJLabel extends JLabel {

    /** add in the label the img file */
    public void setImg() {

        ImageIcon img = new ImageIcon("img//Forza4Sfondo.png");
        this.setIcon(img);

    }

    /**
     * set a label with a string and two color used for the border and font
     * 
     * @param name String that is display inside
     * @param c    color of the border heilight and for the font
     * @param c2   color of the border shadow
     */
    public void setTurnPlayer(String name, Color c, Color c2) {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, c, c2));
        this.setBackground(MainFrame.ar.get(0));
        this.setOpaque(true);
        this.setText(name);
        this.setFont(new Font("Serif", Font.BOLD, 40));
        this.setForeground(c);

    }

    /**
     * set the look of a label with a string insiede and color of the font
     * 
     * @param name the string that is display
     * @param c    the color for the font
     */
    public void setJLabelPlayer(String name, Color c) {
        this.setText(name);
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setForeground(c);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));

    }

    /**
     * set a label with a string inside
     * 
     * @param mex the string that is display
     */
    public void setMexLabel(String mex) {
        this.setText(mex);
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setForeground(MainFrame.ar.get(0));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));

    }

}
