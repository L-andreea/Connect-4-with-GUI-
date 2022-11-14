package forza4;

import java.io.File;
import java.util.ArrayList;

/**
 * the class that is interess in read a file and load a game implement this
 * interface
 */

public interface LoadGame {

    /**
     * reader read a file and return an Object Game
     * 
     * @param file the file
     * @return a Game
     */
    public Game reader(File file);

    /**
     * chackGame chack the elementi in array and costuct an Object Game
     * 
     * @param array of string
     * @return Game
     */
    public Game checkGame(ArrayList<String> array);

}
