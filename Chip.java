public class Chip {
    
    private int x;
    private int y;
    private final Inventory INVENTORY;  
    
    public Chip(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.INVENTORY = new Inventory();  // start empty
    }

    void move(char direction, Maps maps) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
   
    public Inventory getInventory() {
        return INVENTORY;
    }

}