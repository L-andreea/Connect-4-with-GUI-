package forza4;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * this class implements the method that set the different panel that was used
 * in MainFrame
 */
public class CreatePanel extends JPanel implements PanelGame {

    /** set the panel that contein the image */
    @Override
    public void setImgPanel() {
        this.setSize(1500, 1000);
        this.setBackground(new Color(0, 0, 0, 250));
    }

    /** the panel that coteine a grid */
    @Override
    public void setPanelGrid() {
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, MainFrame.ar.get(3)));
        this.setBackground(MainFrame.ar.get(3));

    }

    // *the look of a center panel */
    @Override
    public void setPanelCenter() {

        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    /** set panel north */
    @Override
    public void setPanelNoth() {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(80, 30, 80, 30));

    }

    /** set panel weast */
    @Override
    public void setPanelWest() {
        this.setBackground(new Color(0, 0, 0, 0));

        this.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 20));

    }

    /** set panel east */
    @Override
    public void setPanelEast() {
        this.setBackground(new Color(0, 0, 0, 250));
        this.setBorder(BorderFactory.createMatteBorder(10, 20, 20, 20, MainFrame.ar.get(3)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    /** set panel south */
    @Override
    public void setPanelSouth() {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 100));

    }

}
