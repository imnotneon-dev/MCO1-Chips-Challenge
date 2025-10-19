public class Inventory {
    
    private int redKeys = 0;
    private int blueKeys = 0;
    private boolean hasFlippers;
    private boolean hasFireBoots;
    private int chips = 0;
    public static final char CHIP = '#';
    public static final char RED_KEY = 'r';
    public static final char BLUE_KEY = 'b';
    public static final char FLIPPERS = '_'; //swapped flippers and fire_boots due to error before
    public static final char FIRE_BOOTS = 'L';

    public Inventory() {
        hasFireBoots = false;
        hasFlippers = false;
    }

    public void addRedKey() {
        redKeys++;
    }

    public void addBlueKey() {
        blueKeys++;
    }

    public void addFlippers() {
        hasFlippers = true;
    }

    public void addFireBoots() {
        hasFireBoots = true;
    }

    public void addChips() {
        chips++;
    }

    public boolean hasRedKey() {
        return redKeys > 0;
    }

    public boolean hasBlueKey() {
        return blueKeys > 0;
    }

    public boolean hasFlippers() {
        return hasFlippers;
    }

    public boolean hasFireBoots() {
        return hasFireBoots;
    }

    public int getChips() {
        return chips;
    }

    public void useRedKey() {
        redKeys--;
    }

    public void useBlueKey() {
        blueKeys--;
    }

    public int getRedKeys() {
        return redKeys;
    }

    public int getBlueKeys() {
        return blueKeys;
    }

    public void resetInventory() {
        redKeys = 0;
        blueKeys = 0;
        hasFlippers = false;
        hasFireBoots = false;
        chips = 0;
    }

}