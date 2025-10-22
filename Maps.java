public class Maps {
    
    private Tiles[][] tiles; 
    private int requiredChips; 
    private int startX;
    private int startY;

     public Maps(char[][] charTiles, int requiredChips) {
        this.tiles = createTileMap(charTiles); 
        this.requiredChips = requiredChips; 
        findStartPosition();
    }

    private Tiles[][] createTileMap(char[][] source) {
        Tiles[][] newTiles = new Tiles[source.length][source[0].length];
        for (int y = 0; y < source.length; y++) {
            for (int x = 0; x < source[y].length; x++) {
                newTiles[y][x] = new Tiles(source[y][x]); 
            }
        }
        return newTiles;
    }

    public int[] findStartPosition() {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (tiles[y][x].getType() == '@') { 
                    this.startX = x;
                    this.startY = y;
                }
            }
        }
        return new int[] {startX, startY};
    }

    public Maps cloneMap() { 
        char[][] newTiles = getMap(); 
        return new Maps(newTiles, this.requiredChips);
    }

    public char[][] getMap() {
        char[][] charMap = new char[tiles.length][tiles[0].length];
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                charMap[y][x] = tiles[y][x].getType(); 
            }
        }
        return charMap;
    }

    public char getTile(int x, int y) {
        return tiles[y][x].getType(); 
    }

    public void setTile(int x, int y, char tileType) {
        tiles[y][x].setType(tileType); 
    }
    
    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length;
    }

    public int getRequiredChips() { 
        return requiredChips;
    }
}