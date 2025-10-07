public class Main {
    public static void main(String[] args) {
        Display menu = new Display();
        Controller controller = new Controller();
        menu.showMainMenu(controller);
    }
}