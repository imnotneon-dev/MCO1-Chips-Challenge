public class Tiles {
    
    public static final char BLANK = ' ';
    public static final char WATER = 'W';
    public static final char FIRE = 'F';
    public static final char WALL = 'X';
    public static final char CHIP = '#';
    public static final char EXIT = 'E';
    public static final char FORCE_UP = '^';
    public static final char FORCE_DOWN = 'v';
    public static final char FORCE_LEFT = '<';
    public static final char FORCE_RIGHT = '>';
    public static final char RED_DOOR = 'R';
    public static final char BLUE_DOOR = 'B';
    public static final char RED_KEY = 'r';
    public static final char BLUE_KEY = 'b';
    public static final char FLIPPERS = 'L';
    public static final char FIRE_BOOTS = '_';

    public static boolean isWalkable(char tile, Inventory inv, int requiredChips) {
        
        switch(tile) {
            case BLANK:
            case CHIP:
            case FORCE_UP:
            case FORCE_DOWN:
            case FORCE_LEFT:
            case FORCE_RIGHT:
            case RED_KEY:
            case BLUE_KEY:
            case FLIPPERS:
            case FIRE_BOOTS:
            case RED_DOOR:
            case BLUE_DOOR:
                return true;
            case WATER:
                return inv.hasFlippers();
            case FIRE:
                return inv.hasFireBoots();
            case WALL:
                return false;
            case EXIT:
                return inv.getChips() >= requiredChips;
            default:
                return false;
        }
    }

    public static boolean isForceTile(char tile) {
        return tile == FORCE_UP || tile == FORCE_DOWN || tile == FORCE_LEFT || tile == FORCE_RIGHT;
    }

    public static boolean isCollectible(char tile) {
        return tile == CHIP || tile == RED_KEY || tile == BLUE_KEY || tile == FLIPPERS || tile == FIRE_BOOTS;
    }

    public static char getForceDirection(char tile) {
        if (tile == FORCE_UP) 
            return FORCE_UP;
        if (tile == FORCE_DOWN)
            return FORCE_DOWN;
        if (tile == FORCE_LEFT)
            return FORCE_LEFT;
        if (tile == FORCE_RIGHT)
            return FORCE_RIGHT;
        return ' ';
    }

    public static void applyForce(Chip chip, Maps map) {
        while (true) {
            char tile = map.getTile(chip.getX(), chip.getY());
            if (!isForceTile(tile))
                break;

            int oldX = chip.getX();
            int oldY = chip.getY();
            int newX = oldX;
            int newY = oldY;

            char direction = getForceDirection(tile);

            switch (direction) {
                case FORCE_UP:
                    newY--;
                    break;
                case FORCE_DOWN:
                    newY++;
                    break;
                case FORCE_LEFT:
                    newX--;
                    break;
                case FORCE_RIGHT:
                    newX++;
                    break;
                default:
                    break;
            }

            if (!map.inBounds(newX, newY))
                break;

            char next = map.getTile(newX, newY);

            if (!isWalkable(next, chip.getInventory(), map.getRequiredChips()))
                break;

            map.setTile(oldX, oldY, Tiles.BLANK);
            chip.setX(newX);
            chip.setY(newY);
            map.setTile(newX, newY, Chip.CHIP);
        }
    }
}