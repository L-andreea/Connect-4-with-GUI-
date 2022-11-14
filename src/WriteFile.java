package forza4;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * the clase write a file
 */
public class WriteFile {

    private PrintWriter writer;

    /**
     * write a file with a givene text and given name
     * 
     * @param text the text of the file
     * @param name the name of the file
     * @return ture if no exception has occure
     */
    public boolean saveGame(String text, String name) {

        try {
            writer = new PrintWriter(name);
            writer.printf(text);
            writer.close();
            return true;
        } catch (IOException exception) {
            return false;
        }

    }

}
