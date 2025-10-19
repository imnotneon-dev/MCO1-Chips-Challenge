public class Maps {
    
    private char[][] tiles;
    private int requiredChips; //changed to required chips
    private int startX;
    private int startY;

    public Maps(char[][] tiles, int requiredChips) {
        this.tiles = copyMap(tiles);
        this.requiredChips = requiredChips; //changed to required chips
        findStartPosition();
    }

    private char[][] copyMap(char[][] source) { //copyMap new method for resetting level properly upon death with an existing copy
        char[][] copy = new char[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }
        return copy;
    }

    public int[] findStartPosition() {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (tiles[y][x] == '@') {
                    this.startX = x;
                    this.startY = y;
                }
            }
        }
        return new int[] {0, 0};
    }

    public Maps cloneMap() { // returning the map copy of an existing map
        char[][] newTiles = copyMap(this.tiles);
        return new Maps(newTiles, this.requiredChips);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public char[][] getMap() {
        return tiles;
    }
    public char getTile(int x, int y) {
        return tiles[y][x];
    }

    public void setTile(int x, int y, char tile) {
        tiles[y][x] = tile;
    }
    
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length;
    }

    public int getRequiredChips() { 
        return requiredChips; //changed to required chips
    }
}
