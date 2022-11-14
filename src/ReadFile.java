package forza4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ReadFile implements the interface LoadGame
 */
public class ReadFile implements LoadGame {

    /**
     * read the file in input and put the line in an arrayList
     * call the method chackGame
     * 
     * @param file the file that whast read
     * @return Game if the file exist and the chackGame return a Game Object
     */
    public Game reader(File file) {

        try {
            FileReader fr = new FileReader(file);
            Scanner in = new Scanner(fr);
            ArrayList<String> ar = new ArrayList<String>();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                ar.add(line);
            }
            in.close();
            return checkGame(ar);

        } catch (IOException e) {
            return null;

        }

    }

    /**
     * chack the array list is the format correct
     * 
     * @param ar the array list
     * @return Game if the array list is in the right format
     *         null if the element in the file is incorect
     */
    @Override
    public Game checkGame(ArrayList<String> ar) {
        int[][] grid = new int[Game.HEIGHT_GRID][Game.WIDTH_GRID];
        int start_grid = ar.indexOf("grid");
        int finish_grid = ar.indexOf("pos");
        int turn = ar.indexOf("turn") + 1;
        int player1 = ar.indexOf("player1") + 2;
        int player2 = ar.indexOf("player2") + 2;
        try {
            if (finish_grid == -1 || turn == -1 || player1 == -1 || player2 == -1) {

                return null;
            }

            int row = 0;
            String[] grid_s = new String[Game.WIDTH_GRID];

            for (int i = start_grid + 1; i < finish_grid; i++) {
                grid_s = ar.get(i).split(";");

                for (int j = 0; j < Game.WIDTH_GRID; j++) {
                    int pow = Integer.parseInt(grid_s[j]);
                    if (pow == Game.ONGOING || pow == Game.ID_PLAYER1 || pow == Game.ID_PLAYER2) {
                        grid[row][j] = pow;
                    } else {
                        return null;
                    }

                }
                row++;
            }

            grid_s = ar.get(finish_grid + 1).split(";");
            int[] pos = new int[Game.WIDTH_GRID];

            for (int i = 0; i < Game.WIDTH_GRID; i++) {
                int j = Integer.parseInt(grid_s[i]);

                if (j >= 0 && j <= Game.HEIGHT_GRID) {
                    pos[i] = j;
                } else {
                    return null;
                }

            }

            int turnN = Integer.parseInt(ar.get(turn));
            if (turnN == Game.ID_PLAYER1 || turnN == Game.ID_PLAYER2) {
                Game game = new Game(ar.get(player1), ar.get(player2), pos, grid, turnN);
                return game;
            }

        } catch (NumberFormatException e) {

            return null;
        }
        return null;

    }

}
