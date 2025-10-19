import java.util.Scanner;

public class Display {

    private Scanner sc = new Scanner(System.in);

    public void showMainMenu(Controller controller) {
        while (true) {
            System.out.println("=== CHIP'S CHALLENGE ===");
            System.out.println("1. Start Game");
            System.out.println("2. Instructions");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    controller.startGame(); 
                    break;
                case 2:
                    showInstructions();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void showInstructions() {
        System.out.println("\nHOW TO PLAY:");
        System.out.println("W/A/S/D - move");
        System.out.println("Collect all chips to unlock the exit!");
        System.out.println("Collect colored keys to unlock colored doors!"); //added instruction
        System.out.println("Avoid fire and water unless you have items!");
        System.out.println();
    }

    public void displayMap(char[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                    System.out.print(map[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayInventory(Inventory inventory, Maps map) {
        System.out.println("Inventory:");
        System.out.print("Chips: " + (map.getRequiredChips()-inventory.getChips()));
        if (inventory.hasFireBoots())
            System.out.print(" Fire Boots: YES");
        else
            System.out.print(" Fire Boots: NO");
        if (inventory.hasFlippers())
            System.out.print(" Flippers: YES");
        else
            System.out.print(" Flippers: NO");
        System.out.print(" Red Key: " + inventory.getRedKeys());
        System.out.print(" Blue Key: " + inventory.getBlueKeys());
        System.out.println();
        System.out.println();
    }
}
