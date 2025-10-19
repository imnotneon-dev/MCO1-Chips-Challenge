import java.util.Scanner;

public class Controller {

    private Maps[] levels;
    private Maps currentMap;
    private Display display = new Display();
    private Chip chip;
    private Scanner sc = new Scanner(System.in);
    private NextLevel nextLevel;

    public Controller() {
        levels = new Maps[] {
            new Maps(Levels.generateMap1().getMap(), Levels.generateMap1().getChips()),
            new Maps(Levels.generateMap2().getMap(), Levels.generateMap2().getChips()),
            new Maps(Levels.generateMap3().getMap(), Levels.generateMap3().getChips())
        };

        int[] requiredChips = {
            Levels.generateMap1().getChips(),
            Levels.generateMap2().getChips(),
            Levels.generateMap3().getChips()
        };

        nextLevel = new NextLevel(levels, requiredChips);
    }

    public void startGame() {
        currentMap = nextLevel.getCurrentMap();
        chip = new Chip(currentMap.getStartX(), currentMap.getStartY());
        System.out.println("Level " + (nextLevel.getCurrentLevel() + 1) + " Start!");
        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            display.displayMap(currentMap.getMap());
            display.displayInventory(chip.getInventory(), currentMap);
            System.out.print("Move (W/A/S/D, Q to quit): ");
            char move = sc.next().toUpperCase().charAt(0);

            if (move == 'Q') {
                System.out.println("Quitting to menu...");
                break;
            }

            String moved = chip.move(move, currentMap); //one causing blocks
            if (moved.equals("blocked")) {
                System.out.println("Cannot move there!");
            }

            char currentTile = currentMap.getTile(chip.getX(), chip.getY());

            if (moved.equals("exit") && nextLevel.canAdvance(chip.getInventory().getChips())) {
                System.out.println("Level Complete!");

                if (nextLevel.advance()) {
                    currentMap = nextLevel.getCurrentMap();
                    chip.setX(currentMap.getStartX());
                    chip.setY(currentMap.getStartY());
                    chip.getInventory().resetInventory();
                    System.out.println("Level " + (nextLevel.getCurrentLevel() + 1) + " Start!");
                } else {
                    System.out.println("You completed all levels!");
                    break; //removed emoji
                }
            }

            if (!chip.isAlive() && moved.equals("died")) {
                System.out.println("You died! Restarting level..."); 
                resetLevel(); //cannot move after revive
            }
        }
    }

    private void resetLevel() { //resetLevel fixed 
        System.out.println(); //"You died! Restarting level...");
        currentMap = nextLevel.getCurrentMap();
        
        chip.revive(); //moved to top
        chip.getInventory().resetInventory(); 
        currentMap = nextLevel.getCurrentMap();
        chip.setX(currentMap.getStartX()); //returns chip
        chip.setY(currentMap.getStartY());

        currentMap.setTile(chip.getX(), chip.getY(), Chip.CHIP); 
    }
}
