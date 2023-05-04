import entity.HomePageHeader;
import services.FlightService;
import utils.RandomEverything;
import utils.display.MainMenu;


public class MainWebView {
    public static void main(String[] args) {
        System.out.println("\n" + HomePageHeader.TITLE.getTitleValue() + "\n");
        while (true) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.displayMainMenu();
        }
    }
}
