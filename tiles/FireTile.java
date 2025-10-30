package tiles;

public class FireTile extends Tiles {
    
    public FireTile() {
        super('F');
        // will add sprite when GUI is implemented
    }

    @Override
    public boolean isWalkable(Inventory inv, int requiredChips) {
        return inv.hasFireBoots();
    }

    @Override
    public void onStep(Chip chip, Maps map) {
        chip.die();
    }

}