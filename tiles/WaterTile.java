package tiles;

public class WaterTile extends Tiles {
    
    public WaterTile() {
        super('W');
        // will add sprite when GUI is implemented
    }

    @Override
    public boolean isWalkable(Inventory inv, int requiredChips) {
        return inv.hasFlippers();
    }

    @Override
    public void onStep(Chip chip, Maps map) {
        chip.die();
    }

}