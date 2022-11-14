package forza4;

import java.util.Random;
/*
 * the Class Game costruct a game with two player 
 * and a grid when the player put their id untile 
 * state is ONGOING 
 * whene the grid is full no more 0 the state is finish 
 * if the game state is SAVED_GAME the game finish 
 */

public class Game {

    /** chack winner */
    public static final int WIN_CHACK = 4;
    /** the height of the grid */
    public static final int HEIGHT_GRID = 7;
    /** the width of the grrid */
    public static final int WIDTH_GRID = 6;
    /** id first player */
    public static final int ID_PLAYER1 = 1;
    /** id second palyer */
    public static final int ID_PLAYER2 = 2;

    /** possibile state of the game */
    /** the game is playing */
    public static final int ONGOING = 0;
    /** the game is saved the program finish */

    public static final int SAVED_GAME = 1;
    /** when the grid is full the program finish */
    public static final int FINISH_WINNER = 2;
    /** state of move not valid */
    public static final int INVALID = 3;
    /** when the grid is full */
    public static final int FINISH_FULL_GRID = 4;

    /** the first player of the game */
    private Player player1;
    /** the second player of the game */
    private Player player2;
    /** the grid of the game */
    private Grid gridGame;

    /** rapresent by the id of the player */
    private int turn;

    /** state of the grid */
    private int state;

    /** arry with the position of last element add */
    private int[] pos;

    /**
     * costruct a new game with the name of palyer player1_name, player2_name
     * 
     * @param player1_name name of first player
     * @param player2_name name of second player
     */
    public Game(String player1_name, String player2_name) {
        this.player1 = new Player(player1_name, ID_PLAYER1);
        this.player2 = new Player(player2_name, ID_PLAYER2);
        this.gridGame = new Grid(HEIGHT_GRID, WIDTH_GRID);
        this.pos = new int[WIDTH_GRID];

        // *random choice of witch player start */
        turn = new Random().nextInt(1, 3);
        /** initialize pos with the last position of the grid */
        for (int i = 0; i < WIDTH_GRID; i++) {
            pos[i] = HEIGHT_GRID;
        }
    }

    /**
     * costurct of an old game
     * 
     * @param player1_name name of the player1
     * @param player2_name name of the player2
     * @param pos          array of the last postion taken in the grid
     * @param grid         grid of the old game
     * @param turn         the last player tha has move
     */
    public Game(String player1_name, String player2_name, int[] pos, int[][] grid, int turn) {
        this.player1 = new Player(player1_name, ID_PLAYER1);
        this.player2 = new Player(player2_name, ID_PLAYER2);
        this.gridGame = new Grid(grid);
        this.turn = turn;
        this.pos = pos;
    }

    /**
     * get the neme of the first player
     * 
     * @return name of the first player
     */
    public String getPlayer1() {
        return player1.getName();
    }

    /**
     * get the name of the second player
     * 
     * @return name of the second player
     */
    public String getPlayer2() {
        return player2.getName();
    }

    /**
     * get the turn of the player
     * 
     * @return the turn of the player
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * costurct an WriteFile and save the game
     * 
     * @return true if the operation was esecute
     */
    public boolean saveGame(String nameFile) {
        boolean result = false;
        if (state == ONGOING) {
            WriteFile sf = new WriteFile();
            result = sf.saveGame(this.toString(), nameFile);
            if (result) {
                state = SAVED_GAME;

            }

        }
        return result;

    }

    /**
     * get the string of pos array
     * 
     * @return a string of the pos array
     */
    public String posArray() {
        String s = "";
        for (int i = 0; i < WIDTH_GRID; i++) {
            s += pos[i] + ";";
        }
        return s;
    }

    /**
     * get the string of the Game
     * 
     * @return String the grid
     *         the turn
     *         the first player
     *         the second player
     */
    @Override
    public String toString() {
        return "Game " + gridGame.toString() + "pos\n" + posArray() + "\nstate\n" + this.state + "\nturn\n"
                + this.turn + "\nplayer1\n"
                + player1.toString() + "\nplayer2\n"
                + player2.toString();

    }

    /**
     * @return state
     */
    public int getState() {
        return this.state;
    }

    /**
     * gets the element of the grid at givene params
     * 
     * @param i the row of the grid
     * @param j the colom of the grid
     * @return the element of the grid at given params
     */
    public int getGridIndex(int i, int j) {
        return gridGame.getElemnt(i, j);
    }

    /**
     * if state game is equal to ongoing
     * swich the turn of the player
     */
    private void setTurn() {
        if (state == ONGOING) {
            if (turn == ID_PLAYER1) {
                turn = ID_PLAYER2;
            } else {
                turn = ID_PLAYER1;
            }
        }

    }

    /**
     * get the element in pos
     * 
     * @param j the position of the array pos
     * @return the element in postion j of the array pos
     */
    public int getPosIndex(int j) {
        return pos[j];
    }

    /**
     * chack if the grid is full
     * 
     * @return true if the grid is full
     */
    public boolean chackFullGrid() {
        boolean result = false;
        int fullGrid = 0;
        for (int i = 0; i < WIDTH_GRID; i++) {
            fullGrid += pos[i];
        }
        if (fullGrid == 0) {
            result = true;
        }
        return result;
    }

    /**
     * set the turn of the player
     * chack if the last pown of the colom is free and
     * change the pown of the grid at givene j and row is pos[j]-1
     * chack the game enter in a state of finish
     * otherwies set the state of invalid move
     * 
     * 
     * @param j the colom of the row
     * @return true if the grid was change
     */
    public boolean addPawnGame(int j) {
        setTurn();
        boolean result = false;
        if (state == ONGOING || state == INVALID) {
            try {
                /** if the colom is not full */
                if (pos[j] > 0) {
                    int row = pos[j] - 1;
                    if (gridGame.addPawn(row, j, turn)) {
                        pos[j] = row;
                        result = true;
                        state = ONGOING;

                        /** chack if the grid is full or a player has win */
                        if ((chackWinHorizontal(row, j) || (chackWinVertical(row, j))
                                || (chackWinMainDiagonal(row, j)) || chackWinSecondDiagonal(row, j))) {
                            state = FINISH_WINNER;
                            return result;

                        } else if (chackFullGrid()) {
                            state = FINISH_FULL_GRID;
                            return result;
                        }

                    }

                } else {

                    state = INVALID;

                }

            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                state = INVALID;
                return result;

            }

        }

        return result;

    }

    /**
     * chack if the last player has WIN_CHAKE paw in horizontal
     * 
     * @param i the row of the last element put in a grid
     * @param j the colom of the last element put in a grid
     * @return ture if there is a WIN_CHACK
     */
    private boolean chackWinHorizontal(int i, int j) {
        boolean result = false;
        /** chack that count the equal elementi near of the given position */
        int chack = 1;
        int x = 1;
        /** chack the laft side of the given position */
        while (chack <= WIN_CHACK && j + x < WIDTH_GRID) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i, x + j)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        x = 1;
        /** chack the right side of the grid */
        while (chack <= WIN_CHACK && j - x >= 0) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i, j - x)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        /** chacge result if are a horzontal alignament */
        if (chack == WIN_CHACK) {

            result = true;
        }

        return result;
    }

    /**
     * chack if there is a vertical Win of a given position
     * 
     * @param i row position
     * @param j colorm postion
     * @return ture if there is an alignment in vertical
     */
    private boolean chackWinVertical(int i, int j) {
        boolean result = false;
        /** var that count the equal elementi of the given position */
        int chack = 1;
        /** var that move in horiziontal */
        int x = 1;
        /** chack the bottom side of given position */
        while (chack <= WIN_CHACK && i + x < HEIGHT_GRID) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i + x, j)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        x = 1;
        /** chack the height side of given position */
        while (chack <= WIN_CHACK && i - x >= 0) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i - x, j)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        /** chacge result if are a horzontal alignament */
        if (chack == WIN_CHACK) {
            result = true;
        }

        return result;
    }

    /**
     * chack if there is an alignment at the main diagonal
     * start in a givene position
     * 
     * @param i row position
     * @param j colom position
     * @return true if there is an alignment at main diagonal
     */

    private boolean chackWinMainDiagonal(int i, int j) {
        boolean result = false;
        /** var that count the equal elementi of the given position */
        int chack = 1;
        int x = 1;
        /** chack the main diagonal in botton direction of a given position */
        while (chack <= WIN_CHACK && j + x < WIDTH_GRID && i + x < HEIGHT_GRID) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i + x, x + j)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        x = 1;
        /** chack the main diagonal in upper side of the given position */
        while (chack <= WIN_CHACK && j - x >= 0 && i - x >= 0) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i - x, j - x)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        /** chack result if are main diagonal alignament */
        if (chack == WIN_CHACK) {
            ;
            result = true;
        }

        return result;
    }

    /**
     * chack for an anlignament of paw at secondo diagonal
     * starte at the given position
     * 
     * @param i row position
     * @param j colom position
     * @return true if there is an anlignment
     */
    private boolean chackWinSecondDiagonal(int i, int j) {
        boolean result = false;
        /** var that count the equal elementi of the given position */
        int chack = 1;
        int x = 1;
        /** chack the upper side of second diagonal */

        while (chack <= WIN_CHACK && j + x < WIDTH_GRID && i - x >= 0) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i - x, x + j)) {
                chack++;
                x++;
            } else {
                break;
            }
        }
        x = 1;
        /** chack the bottom side of second diagonal */
        while (chack <= WIN_CHACK && i + x < HEIGHT_GRID && j - x >= 0) {
            if (gridGame.getElemnt(i, j) == gridGame.getElemnt(i + x, j - x)) {
                chack++;
                x++;
            } else {
                break;
            }
        }

        /** chack if there is an alignament on second diagonal */
        if (chack == WIN_CHACK) {
            // just for test
            result = true;

        }

        return result;
    }

}
