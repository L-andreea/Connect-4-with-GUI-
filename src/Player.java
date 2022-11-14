package forza4;

/*
 * A Player has a name and an id 
 */
public class Player {

    /** the name of the player */
    private String name;
    /** the id of the player */
    private int id;

    /**
     * costurct a Player with a given
     * 
     * @param name the name of the player
     * @param id   the id of the player
     */
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the Id of the player
     * 
     * @return id player
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the name of the player
     * 
     * @return String name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name and id of the player
     * 
     * @return String name and id of the Player
     */

    @Override
    public String toString() {
        return "name \n" + this.name + "\nid\n" + this.id;
    }

}
