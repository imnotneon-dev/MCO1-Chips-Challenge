public class Tiles {
    
    public static final char BLANK = ' ';
    public static final char WATER = 'W';
    public static final char FIRE = 'F';
    public static final char WALL = 'X';
    public static final char EXIT = 'E';
    public static final char FORCE_UP = '^';
    public static final char FORCE_DOWN = 'v';
    public static final char FORCE_LEFT = '<';
    public static final char FORCE_RIGHT = '>';
    private char type;

    public Tiles(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public void setType(char newType) {
        this.type = newType;
    }

    public static boolean isWalkable(char tile, Inventory inv, int requiredChips) {
        switch(tile) {
            case BLANK:
            case Inventory.CHIP:
            case FORCE_UP:
            case FORCE_DOWN:
            case FORCE_LEFT:
            case FORCE_RIGHT:
            case Inventory.RED_KEY:
            case Inventory.BLUE_KEY:
            case Inventory.FLIPPERS:
            case Inventory.FIRE_BOOTS:
            case Doors.RED_DOOR:
            case Doors.BLUE_DOOR:
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
        return tile == Inventory.CHIP || tile == Inventory.RED_KEY || tile == Inventory.BLUE_KEY || tile == Inventory.FLIPPERS || tile == Inventory.FIRE_BOOTS;
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

    public static void applyForce(Chip chip, Maps map) { //implemented change regarding file/water tile not destoryed also force tiles
        while (true) {
            char tileUnder = map.getTile(chip.getX(), chip.getY());
            if (!isForceTile(tileUnder)) {
                tileUnder = chip.getCurrentTileBelow();
                if (!isForceTile(tileUnder)) 
                    break;
            }

            int oldX = chip.getX();
            int oldY = chip.getY();
            int newX = oldX;
            int newY = oldY;

            switch (tileUnder) {
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

            char nextTile = map.getTile(newX, newY);

            if (!isWalkable(nextTile, chip.getInventory(), map.getRequiredChips()))
                break;

            map.setTile(oldX, oldY, tileUnder);

            chip.setX(newX);
            chip.setY(newY);

            chip.setCurrentTileBelow(nextTile);

            if (isCollectible(nextTile)) { //collects anything if forced
                switch (nextTile) {
                    case Inventory.CHIP:
                        chip.getInventory().addChips();
                        break;
                    case Inventory.RED_KEY:
                        chip.getInventory().addRedKey();
                        break;
                    case Inventory.BLUE_KEY:
                        chip.getInventory().addBlueKey();
                        break;
                    case Inventory.FLIPPERS:
                        chip.getInventory().addFlippers();
                        break;
                    case Inventory.FIRE_BOOTS:
                        chip.getInventory().addFireBoots();
                        break;
                }
                chip.setCurrentTileBelow(BLANK);
                map.setTile(newX, newY, BLANK);
            }

            if (nextTile == WATER && !chip.getInventory().hasFlippers()) {
                chip.die();
                map.setTile(newX, newY, WATER);
                return;
            }
            if (nextTile == FIRE && !chip.getInventory().hasFireBoots()) {
                chip.die();
                map.setTile(newX, newY, FIRE);
                return;
            }

            map.setTile(newX, newY, Chip.CHIP);

            // continue sliding only if the tile we just moved onto is a force tile
            if (!isForceTile(nextTile)) 
                break;
        }
    }
}
