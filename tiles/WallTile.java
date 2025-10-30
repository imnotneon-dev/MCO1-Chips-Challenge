package tiles;

public class WallTile extends Tiles {
    
    public WallTile() {
        super('X');
        // will add sprite when GUI is implemented
    }

    @Override
    public boolean isWalkable(Inventory inv, int requiredChips) {
        return false;
    }
}