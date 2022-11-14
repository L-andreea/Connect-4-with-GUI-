package forza4;

/*
 * Classname: MainFrame 
 * this Class create the frame of the game "forza4"
 * implement the action listener of the button in the frame 
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * the classe implmente the look used for the game "forza4"
 */

public class MainFrame extends JFrame implements ActionListener {

    /** used for set the color in the grid */
    public static final ArrayList<Color> ar = new ArrayList<Color>();
    /** the button used in the frame */
    private PersonalJButton newgame, oldgame, start, savegame;
    /** the panel used in the frame */
    private CreatePanel north, west, south, east, grid, center, imagePanel;
    /** the JText area used in the frame */
    private PersonalJTextField nameplayer1, nameplayer2;
    /** the JLabel used in the frame */
    private PersonalJLabel player1, player2, img, turnPlayer;
    /** the Game used in the frame */
    private Game p;

    public MainFrame() {
        /** add color that was used for the element in the frame */
        ar.add(new Color(0, 0, 0)); // general background color
        ar.add(Color.CYAN);// color of playe1
        ar.add(Color.MAGENTA); // color of player2
        ar.add(new Color(123, 104, 238)); // color borad of grid
        ar.add(new Color(255, 255, 255)); // color of text

        // create a frame
        JFrame mainFrame = new JFrame("partita");

        // lebel with img
        img = new PersonalJLabel();
        img.setImg();

        // create the panel
        north = new CreatePanel();
        west = new CreatePanel();
        south = new CreatePanel();
        east = new CreatePanel();
        center = new CreatePanel();
        grid = new CreatePanel();
        imagePanel = new CreatePanel();

        // make difference
        center.setPanelCenter();
        grid.setPanelGrid();
        east.setPanelEast();
        north.setPanelNoth();
        south.setPanelSouth();
        west.setPanelWest();

        imagePanel.setImgPanel();

        // create e set button
        savegame = new PersonalJButton();
        savegame.setSaveGameB("Salva Partita");

        newgame = new PersonalJButton();
        newgame.setNewGame("Nuova Partita");
        newgame.addActionListener(this);

        oldgame = new PersonalJButton();
        oldgame.setOldGameB("Vecchia Partita");
        oldgame.addActionListener(this);

        start = new PersonalJButton();
        start.setStartB("Start");

        // create e set name-text filed
        nameplayer1 = new PersonalJTextField();
        nameplayer1.setFildNamePlayer(ar.get(1));

        nameplayer2 = new PersonalJTextField();
        nameplayer2.setFildNamePlayer(ar.get(2));

        // create e set JLabel

        turnPlayer = new PersonalJLabel();
        turnPlayer.setTurnPlayer("TURNO GIOCATORE", ar.get(3), ar.get(0));

        player1 = new PersonalJLabel();
        player1.setJLabelPlayer("Player1", ar.get(1));

        player2 = new PersonalJLabel();
        player2.setJLabelPlayer("player2", ar.get(2));

        /** add component at north panel */
        north.setLayout(new BorderLayout());
        north.add(turnPlayer, BorderLayout.EAST);

        /** add componet of west panel */
        west.add(savegame);

        /** add component at east */
        east.add(newgame);
        east.add(oldgame);
        east.add(player1);
        east.add(nameplayer1);
        east.add(player2);
        east.add(nameplayer2);
        east.add(start);
        /** check if the image is in the path */
        File imgPath = new File("img//Forza4Sfondo.png");
        if (!(imgPath.exists())) {
            System.out.print("immagine vuota");
            imagePanel.setLayout(new BorderLayout());
            imagePanel.add(east, BorderLayout.EAST);
            imagePanel.add(west, BorderLayout.WEST);
            imagePanel.add(center, BorderLayout.CENTER);
            imagePanel.add(north, BorderLayout.NORTH);
            imagePanel.add(south, BorderLayout.SOUTH);

        }
        // **add component under img */
        else {
            imagePanel.add(img);

            img.setLayout(new BorderLayout());
            img.add(east, BorderLayout.EAST);

            img.add(west, BorderLayout.WEST);
            img.add(center, BorderLayout.CENTER);
            img.add(north, BorderLayout.NORTH);
            img.add(south, BorderLayout.SOUTH);
        }

        mainFrame.add(imagePanel);
        mainFrame.setSize(new Dimension(1500, 1000));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }

    /**
     * create the grid and add it at center panel
     */
    public void update_grid() {
        grid.setLayout(new GridLayout(Game.HEIGHT_GRID, Game.WIDTH_GRID, 12, 12));
        for (int i = 0; i < Game.HEIGHT_GRID; i++) {
            for (int j = 0; j < Game.WIDTH_GRID; j++) {

                PersonalJButton jb = new PersonalJButton();
                jb.setButtonGrid();
                jb.setBackground(MainFrame.ar.get(p.getGridIndex(i, j)));
                jb.setName("" + i * Game.WIDTH_GRID + j);
                jb.addActionListener(this);

                grid.add(jb);
            }
        }

        center.add(grid);
        grid.updateUI();
    }

    /**
     * update the grid the game is not finish
     * 
     * @param s sting of the number of the grid that was push
     */
    public void updateButton(String s) {
        int rForC = Integer.valueOf(s); // integer of row * WIDTH_GRID + colom of clicked button
        int j = rForC % Game.WIDTH_GRID; // colom of clicked button

        if (p.getState() == Game.ONGOING || p.getState() == Game.INVALID) {
            if (p.addPawnGame(j)) {
                int index_bTm = p.getPosIndex(j) * Game.WIDTH_GRID + j; // calcol the index of the button that need to
                                                                        // be change
                JButton jb = ((JButton) (grid.getComponent(index_bTm))); // button of index_bTm
                jb.setBackground(MainFrame.ar.get(p.getTurn())); // set the coloro of button
                grid.updateUI();
            }
        }
        switch (p.getState()) {
            case Game.FINISH_FULL_GRID:
                new PersonalJFrame("paritia finita", "la partita è patta", EXIT_ON_CLOSE, ar.get(4));

                break;
            case Game.FINISH_WINNER:

                new PersonalJFrame("partita finita", "il player " + p.getTurn() + " è il vincitore", EXIT_ON_CLOSE,
                        ar.get(p.getTurn()));
                break;
            case Game.INVALID:
                new PersonalJFrame("mossa non valida", "selezionare un'altra colonna", DISPOSE_ON_CLOSE, ar.get(4));

                break;

            case Game.ONGOING:

                setActivePlayer(p.getTurn());
                break;

        }

    }

    /**
     * set the background color of the label turnGiocatore
     * 
     * @param i the turn of the player
     */
    private void setActivePlayer(int i) {
        if (i == 1) {
            i = 2;
        } else {
            i = 1;
        }
        turnPlayer.setBackground(ar.get(i));
    }

    @Override
    /**
     * method that define the action intercetat of all button
     */
    public void actionPerformed(ActionEvent e) {
        /** fonud the button that was push */
        String name_button = ((JButton) e.getSource()).getName();

        /** difene action for every button */
        switch (name_button) {
            case "start":
                String pattern = " {0,}";
                // *chack if a name is emapty or two name equal*/
                if (Pattern.matches(pattern, nameplayer1.getText())
                        || Pattern.matches(nameplayer1.getText().trim(), nameplayer2.getText().trim())
                        || Pattern.matches(pattern, nameplayer2.getText())) {
                    /** lunch the frame invalid name */
                    new PersonalJFrame("errore nomi giocatori", "inserire nomi diversi", DISPOSE_ON_CLOSE, ar.get(4));
                    return;

                }

                // * start the game */
                p = new Game(nameplayer1.getText(), nameplayer2.getText());
                savegame.addActionListener(this);
                newgame.removeActionListener(this);
                oldgame.removeActionListener(this);
                this.update_grid();
                nameplayer1.setEnabled(false);
                nameplayer2.setEnabled(false);
                setActivePlayer(p.getTurn());

                break;
            case "newgame":
                start.addActionListener(this);
                break;

            case "oldgame":
                try {

                    JFileChooser c = new JFileChooser();
                    c.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int result = c.showDialog(this, "carica partita");

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectFile = c.getSelectedFile();
                        ReadFile rf = new ReadFile();
                        p = rf.reader(selectFile);
                        nameplayer1.setText(p.getPlayer1());
                        nameplayer2.setText(p.getPlayer2());
                        update_grid();
                        nameplayer1.setEnabled(false);
                        nameplayer2.setEnabled(false);
                        setActivePlayer(p.getTurn());
                        savegame.addActionListener(this);
                        newgame.removeActionListener(this);
                        oldgame.removeActionListener(this);
                        start.removeActionListener(this);
                    }

                } catch (NullPointerException es) {
                    new PersonalJFrame("errore lettura file", "impossibile caricare vecchia partita", DISPOSE_ON_CLOSE,
                            ar.get(4));

                }

                break;
            case "savegame":
                JFileChooser sg = new JFileChooser();
                sg.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = sg.showDialog(this, "salva");

                if (result == JFileChooser.APPROVE_OPTION) {

                    File selectFile = sg.getSelectedFile();
                    boolean stateSaveFile = p.saveGame(selectFile.getName());
                    if (stateSaveFile) {
                        newgame.removeActionListener(this);
                        start.removeActionListener(this);
                        new PersonalJFrame("partita salvata", "partita salvata corretamente", EXIT_ON_CLOSE,
                                ar.get(4));
                        return;
                    } else {
                        new PersonalJFrame("partita non salvata", "la partita non è stata salvata",
                                DISPOSE_ON_CLOSE,
                                ar.get(4));

                    }

                } else {
                    new PersonalJFrame("partita non salvata", "la partita non è stata salvata", DISPOSE_ON_CLOSE,
                            ar.get(4));

                }

                break;

            default:

                updateButton(name_button);

                break;
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
