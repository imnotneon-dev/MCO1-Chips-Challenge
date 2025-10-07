public class Maps {
    
    private char[][] tiles;
    private int chips;
    private int startX;
    private int startY;

    public Maps(char[][] tiles, int requiredChips, int startX, int startY) {
        this.tiles = tiles;
        this.chips = requiredChips;
        this.startX = startX;
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
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
        return chips;
    }
}