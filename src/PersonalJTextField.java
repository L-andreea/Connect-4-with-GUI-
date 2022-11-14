package forza4;

import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * PersonalJTextFiled extends JTextFiled
 */
public class PersonalJTextField extends JTextField {

    /**
     * set a text filed with background equals with array 0
     * font array 4
     * and border with the color given
     * 
     * @param c the color given
     */
    public void setFildNamePlayer(Color c) {
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, c));
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setBackground(MainFrame.ar.get(0));
        this.setForeground(MainFrame.ar.get(4));
    }

}
