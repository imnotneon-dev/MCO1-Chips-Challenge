public class Tiles {
    
    private final char BLANK = ' ';
    private final char WATER = 'W';
    private final char FIRE = 'F';
    private final char WALL = 'X';
    private final char CHIP = '#';
    private final char EXIT = 'E';
    private final char FORCE_UP = '^';
    private final char FORCE_DOWN = 'v';
    private final char FORCE_LEFT = '<';
    private final char FORCE_RIGHT = '>';

    public boolean isWalkable(char tile, Inventory inv, int requiredChips) {
        
        switch(tile) {
            case BLANK:
                return true;
            case WATER:
                return inv.hasFlippers();
            case FIRE:
                return inv.hasFireBoots();
            case WALL:
                return false;
            case CHIP:
                return true;
            case EXIT:
                return inv.getChips() >= requiredChips;
            case FORCE_UP:
            case FORCE_DOWN:
            case FORCE_LEFT:
            case FORCE_RIGHT:
                return true;
            default:
                return false;
        }
    }

    public boolean isForceTile(char tile) {
        return tile == FORCE_UP || tile == FORCE_DOWN || tile == FORCE_LEFT || tile == FORCE_RIGHT;
    }

    public char getForceDirection(char tile) {
        if (tile == FORCE_UP) 
            return FORCE_UP;
        if (tile == FORCE_DOWN)
            return FORCE_DOWN;
        if (tile == FORCE_LEFT)
            return FORCE_LEFT;
        if (tile == FORCE_RIGHT)
            return FORCE_RIGHT;
        return '-';
    }
}