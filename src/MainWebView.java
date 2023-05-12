import entity.HomePageHeader;
import services.FlightService;
import services.TicketAndReportService;
import utils.RandomEverything;
import utils.display.MainMenu;

import javax.swing.*;


public class MainWebView {
    public static void main(String[] args) {
        System.out.println("\n" + HomePageHeader.TITLE.getTitleValue() + "\n");
        FlightService.getFlightList();
        while (true) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.displayMainMenu();
        }
    }
}
