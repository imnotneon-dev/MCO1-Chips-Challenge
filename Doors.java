public class Doors {

    private final char RED = 'R';
    private final char BLUE = 'B';
    private boolean locked;
    private char color;

    public Doors(char color) {
        this.locked = true;
        this.color = color;
    }

    public boolean isDoor(char tile) {
        return tile == RED || tile == BLUE;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean unlockDoor(Inventory inv, char tile) {
        if (tile == RED && inv.hasRedKey()) {
            inv.useRedKey();
            locked = false;
            return true;
        }
        else if (tile == BLUE && inv.hasBlueKey()) {
            inv.useBlueKey();
            locked = false;
            return true;            
        }
        return false;
    }

}