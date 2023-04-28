import utils.display.MainMenu;

public class MainWebView {
    public static void main(String[] args) {
        System.out.println("Welcome to Book vemaybay gia sieu dat");
        while (true) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.displayMainMenu();
        }
    }
}
