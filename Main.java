/*
 * This is the Main class which is used to run the program properly. It strictly uses the Display class with classes inside it and the Controller class with classes also inside it. This is crucial for running the program because this class allows the program to run on the console with all the other Java classes
 */
public class Main {
    /*
     * The main static void class to run the program and the different functions of it
     * 
     * @param String[]args - accepts the argument of strings to simulate state of the program
     */
    public static void main(String[] args) {
        /*
         * Creates a new Display instance that is necessary for the menu and map layout and game state display for the user to see
         */
        Display menu = new Display();
        /*
         * Creates a new Controller instance that is necessary for the gameplay of the player mainly chips movement and interaction with levels
         */
        Controller controller = new Controller();
        /*
         * Calls the controller method and shows the main menu to simulate the start of the program for the player
         */
        menu.showMainMenu(controller);
    }
}
