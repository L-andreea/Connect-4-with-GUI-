package forza4;

/*
 * the Class Grid costuct a grid the grid 
 * can be a grid with int 0 at element or 
 * a copy of a given grid
 */
public class Grid {

    /** grid */
    private int[][] grid;
    /** the height of the grid */
    private int heightGrid;
    /** the width of the grid */
    private int widthGrid;

    /**
     * costruct a grid with givene grid
     * 
     * @param gridArray the grid that be copy
     */
    public Grid(int[][] gridArray) {
        this.heightGrid = gridArray.length;
        this.widthGrid = gridArray[0].length;
        this.grid = gridArray;

    }

    /**
     * costruct a grid with dimension at the given
     * params and every element 0
     * 
     * @param heightGrid the height of the grid
     * @param widthGrid  the width of the grid
     */

    public Grid(int heightGrid, int widthGrid) {
        this.heightGrid = heightGrid;
        this.widthGrid = widthGrid;
        this.grid = new int[heightGrid][widthGrid];
        for (int i = 0; i < heightGrid; i++) {
            for (int j = 0; j < widthGrid; j++) {
                this.grid[i][j] = 0;
            }
        }

    }

    /**
     * gets the elemente of the grid in row and colom
     * 
     * @param row   the row of the grid
     * @param colom the colom of the grid
     * @return int
     */
    public int getElemnt(int row, int colom) {
        try {
            return this.grid[row][colom];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }

    }

    /**
     * Gets the grid in string
     * 
     * @return String
     */
    @Override
    public String toString() {
        String s = "\ngrid\n";
        for (int i = 0; i < heightGrid; i++) {
            for (int j = 0; j < widthGrid; j++) {
                s += this.grid[i][j] + ";";
            }
            s += "\n";
        }

        return s;
    }

    /**
     * addPawn set the position of the grid row and colom with the player
     * 
     * @param row    the row of the grid
     * @param colom  the colom of the grid
     * @param player the element with be modify at the grid
     * @return true if the element is change
     */
    public boolean addPawn(int row, int colom, int player) {
        try {
            grid[row][colom] = player;
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }

}
