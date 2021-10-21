package Model;

import Menu.MainMenu;
import api.AdminResource;
import service.CustomerService;
import service.ReservationService;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws ParseException {
        while (true) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainmenu();
        }
    }
}
