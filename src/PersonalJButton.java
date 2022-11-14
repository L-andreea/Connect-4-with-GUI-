package forza4;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * the class extend a JButtno and implement metohd to set the button in the
 * frame
 */
public class PersonalJButton extends JButton {

    /**
     * set the look of the button in the grid
     */
    public void setButtonGrid() {
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

    }

    /**
     * set the look of the button and add the thex tha was dispaly on it
     * 
     * @param name the text that was display on the button
     */
    public void setNewGame(String name) {
        this.setText(name);
        this.setName("newgame");
        this.setBorder(BorderFactory.createMatteBorder(10, 30, 10, 30, MainFrame.ar.get(0)));
        this.setBackground(MainFrame.ar.get(0));
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setForeground(MainFrame.ar.get(4));

    }

    /**
     * set ne look of the button savegame
     * 
     * @param name the string tha was display in the button
     */
    public void setSaveGameB(String name) {
        this.setText(name);
        this.setName("savegame");
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, MainFrame.ar.get(0)));
        this.setBackground(MainFrame.ar.get(0));
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setForeground(MainFrame.ar.get(4));
    }

    /**
     * set the look of the button old game
     * 
     * @param name the string that was display in the button
     */
    public void setOldGameB(String name) {
        this.setText(name);
        this.setName("oldgame");
        this.setBackground(MainFrame.ar.get(0));
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setForeground(MainFrame.ar.get(4));
        this.setBorder(BorderFactory.createMatteBorder(10, 30, 10, 30, MainFrame.ar.get(0)));

    }

    /**
     * set the look of the button start
     * 
     * @param name the string that was display on the button
     */
    public void setStartB(String name) {
        this.setText(name);
        this.setName("start");
        this.setBorder(BorderFactory.createMatteBorder(10, 30, 10, 30, MainFrame.ar.get(0)));
        this.setBackground(MainFrame.ar.get(0));
        this.setFont(new Font("Serif", Font.BOLD, 30));
        this.setForeground(MainFrame.ar.get(4));
    }

}
