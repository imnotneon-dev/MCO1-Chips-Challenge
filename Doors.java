public class Doors {

    private boolean locked;
    private char color;
    public static final char RED_DOOR = 'R';
    public static final char BLUE_DOOR = 'B';

    public Doors(char color) {
        this.locked = true;
        this.color = color;
    }

    public boolean isDoor(char tile) {
        return tile == RED_DOOR || tile == BLUE_DOOR;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean unlockDoor(Inventory inv, char tile) {
        if (tile == RED_DOOR && inv.hasRedKey()) {
            inv.useRedKey();
            locked = false;
            return true;
        }
        else if (tile == BLUE_DOOR && inv.hasBlueKey()) {
            inv.useBlueKey();
            locked = false;
            return true;            
        }
        return false;
    }

}