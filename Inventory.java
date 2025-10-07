public class Inventory {
    
    private int redKeys = 0;
    private int blueKeys = 0;
    private boolean hasFlippers;
    private boolean hasFireBoots;
    private int chips = 0;

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

}