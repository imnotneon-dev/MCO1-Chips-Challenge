public class Chip {
    
    public static final char CHIP = '@';
    private int x;
    private int y;
    private boolean alive;
    private final Inventory INVENTORY;
    private char currentTileBelow = Tiles.BLANK; // added this to keep water and fire tiles as well as force tiles     
    
    public Chip(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.alive = true;
        this.INVENTORY = new Inventory(); 
    }

    public String move(char direction, Maps map) { //implemented new code to help simulate movement with special tiles
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

        Doors door = new Doors(tile); //door method unchanged in order to simulate removing doors upon entering with a key
        if (door.isDoor(tile)) {
            if (door.unlockDoor(INVENTORY, tile)) {
                map.setTile(newX, newY, Tiles.BLANK);
                tile = Tiles.BLANK;
            } else {
                return "blocked";
            }
        }

        if (tile == Tiles.EXIT) {
            if (INVENTORY.getChips() >= map.getRequiredChips()) return "exit";
            else return "blocked";
        }

        if (Tiles.isWalkable(tile, INVENTORY, map.getRequiredChips())) {
            map.setTile(x, y, getCurrentTileBelow());

            x = newX;
            y = newY;

            setCurrentTileBelow(tile);

            if (Tiles.isCollectible(tile)) {
                switch (tile) {
                    case Inventory.CHIP: 
                        INVENTORY.addChips(); 
                        break;
                    case Inventory.RED_KEY: 
                        INVENTORY.addRedKey(); 
                        break;
                    case Inventory.BLUE_KEY: 
                        INVENTORY.addBlueKey();
                        break;
                    case Inventory.FIRE_BOOTS:
                        INVENTORY.addFireBoots(); 
                        break;
                    case Inventory.FLIPPERS:
                        INVENTORY.addFlippers();
                        break;
                }
                setCurrentTileBelow(Tiles.BLANK);
                map.setTile(x, y, Tiles.BLANK);
            }

            map.setTile(x, y, Chip.CHIP);

            if (Tiles.isForceTile(tile)) {
                Tiles.applyForce(this, map);
            }

            if (isAlive())
                return "moved";
            else
                return "died";
        }

    //implemented to not destroy water and fire tiles upon steps

        if (tile == Tiles.WATER) {
            if (INVENTORY.hasFlippers()) {
                map.setTile(x, y, getCurrentTileBelow());
                x = newX; 
                y = newY;
                setCurrentTileBelow(tile);
                map.setTile(x, y, Chip.CHIP);
                return "moved";
            } else {
                map.setTile(x, y, getCurrentTileBelow());
                x = newX; 
                y = newY;
                setCurrentTileBelow(tile);
                map.setTile(x, y, Chip.CHIP);
                die();
                map.setTile(x, y, tile); //restoring water tile
                return "died";
            }
        }

        if (tile == Tiles.FIRE) {
            if (INVENTORY.hasFireBoots()) {
                map.setTile(x, y, getCurrentTileBelow());
                x = newX; 
                y = newY;
                setCurrentTileBelow(tile);
                map.setTile(x, y, Chip.CHIP);
                return "moved";
            } else {
                map.setTile(x, y, getCurrentTileBelow());
                x = newX; 
                y = newY;
                setCurrentTileBelow(tile);
                map.setTile(x, y, Chip.CHIP);
                die();
                map.setTile(x, y, tile); //restoring fire tile
                return "died";
            }
        }

        return "blocked";
    }


    public void collect(char item) { // collect method
        switch(item) {
            case Inventory.CHIP:
                INVENTORY.addChips();;
                break;
            case Inventory.RED_KEY:
                INVENTORY.addRedKey();;
                break;
            case Inventory.BLUE_KEY:
                INVENTORY.addBlueKey();
                break;
            case Inventory.FIRE_BOOTS:
                INVENTORY.addFireBoots();
                break;
            case Inventory.FLIPPERS:
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

    public char getCurrentTileBelow() {
        return currentTileBelow;
    }

    public void setCurrentTileBelow(char tile) {
        this.currentTileBelow = tile;
    }

}
