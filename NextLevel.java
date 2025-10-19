public class NextLevel {

    private int currentLevel;
    private Maps[] levels;
    private int[] requiredChips;

    public NextLevel(Maps[] levels, int[] requiredChips) {
        this.levels = levels;
        this.requiredChips = requiredChips;
        this.currentLevel = 0;
    }

    public Maps getCurrentMap() { //added the copyMap method
        return levels[currentLevel].cloneMap();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean canAdvance(int collectedChips) {
        if (currentLevel >= requiredChips.length) 
            return false;
        return collectedChips >= requiredChips[currentLevel];
    }

    public boolean advance() {
        if (currentLevel + 1 < levels.length) {
            currentLevel++;
            return true;
        }
        return false; 
    }
}
