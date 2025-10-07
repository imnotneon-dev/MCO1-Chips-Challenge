public class NextLevel {

    private int currentLevel;
    private Maps[] levels;
    private int[] requiredChips;

    public NextLevel(Maps[] levels, int[] requiredChips) {
        this.levels = levels;
        this.requiredChips = requiredChips;
        currentLevel = 0;
    }

    public Maps getCurrentMap() {
        return levels[currentLevel];
    }

    public boolean canAdvance(int collectedChips) {
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