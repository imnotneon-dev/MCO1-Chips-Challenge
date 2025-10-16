public class Chip {
    
    public static final char CHIP = '@';
    private int x;
    private int y;
    private boolean alive;
    private final Inventory INVENTORY;  
    
    public Chip(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.alive = true;
        this.INVENTORY = new Inventory(); 
    }

    public String move(char direction, Maps map) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case 'W':
                newY--;
                break;
            case 'A': 
                newX--;
                break;
            case 'S': 
                newY++; 
                break;
            case 'D': 
                newX++; 
                break;
            default: 
                return "invalid";
        }

        if (!map.inBounds(newX, newY))
            return "blocked";

        char tile = map.getTile(newX, newY);

        Doors door = new Doors(tile);
        if (door.isDoor(tile)) {
            if (door.unlockDoor(INVENTORY, tile)) {
                map.setTile(newX, newY, Tiles.BLANK);
            } else {
                return "blocked";
            }
        }
        
        if (tile == Tiles.EXIT) {
            if (INVENTORY.getChips() >= map.getRequiredChips()) {
                return "exit";
            } else {
                return "blocked";
            }
        }

        if (Tiles.isWalkable(tile, INVENTORY, map.getRequiredChips())) {
            map.setTile(x, y, Tiles.BLANK);
            x = newX;
            y = newY;

            if (Tiles.isCollectible(tile)) {
                collect(tile);
            }

            map.setTile(newX, newY, Chip.CHIP);
            Tiles.applyForce(this, map);

            return "moved";
        }

        if (tile == Tiles.WATER || tile == Tiles.FIRE) {
            die();
            return "died";
        }

        return "blocked";
    }

    public void collect(char item) {
        switch(item) {
            case Tiles.CHIP:
                INVENTORY.addChips();;
                break;
            case Tiles.RED_KEY:
                INVENTORY.addRedKey();;
                break;
            case Tiles.BLUE_KEY:
                INVENTORY.addBlueKey();
                break;
            case Tiles.FIRE_BOOTS:
                INVENTORY.addFireBoots();
                break;
            case Tiles.FLIPPERS:
                INVENTORY.addFlippers();
                break;
        }
    }

    public void die() {
        alive = false;
    }

    public void revive() {
        alive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return alive;
    }
   
    public Inventory getInventory() {
        return INVENTORY;
    }

}