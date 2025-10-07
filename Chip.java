public class Chip {
    
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

    void move(char direction, Maps map) {

    }

    void collect(char item, Maps map) {

    }

    void die(char tile, Maps map) {

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