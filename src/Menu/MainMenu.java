package Menu;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import api.AdminResource;
import api.HotelResource;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();

    public void mainmenu() throws ParseException {
        System.out.println("Welcome to the MainMenu");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        try {
            switch (i) {
                case 1:
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Please enter your checkInDate:(dd-mm-yyyy)");
                    String s1 = sc3.nextLine();
                    Date checkIn = new SimpleDateFormat("dd-mm-yyyy").parse(s1);
                    System.out.println("Please enter your checkOutDate:(mm-dd-yyyy)");
                    String s2 = sc3.nextLine();
                    Date checkOut = new SimpleDateFormat("dd-mm-yyyy").parse(s2);
                    Collection<IRoom> rooms = hotelResource.findRooms(checkIn, checkOut);
                    System.out.println(rooms);
                    if (!rooms.isEmpty()) {
                        System.out.println("Do you want to reserve a room?y/n");
                        String choice = sc3.nextLine();
                        if (choice.equals("y")) {
                            System.out.println("Do you have an account with us?y/n");
                            String has_account = sc3.nextLine();
                            if (has_account.equals("y")) {
                                System.out.println("Please enter your email in name@domain.com format:");
                                String email = sc3.nextLine();
                                Customer customer = hotelResource.getCustomer(email);
                                System.out.println("Which room number you would like to reserve?");
                                Integer roomnumber = sc3.nextInt();
                                IRoom aRoom = hotelResource.getARoom(roomnumber.toString());
                                hotelResource.reserveARoom(customer, aRoom, checkIn, checkOut);
                                System.out.println("Congratulations! Reservation was created!");
                            } else if (has_account.equals("n")) {
                                while (true) {
                                    try {
                                        System.out.println("Please enter your email:name@domain.com");
                                        String email2 = sc3.nextLine();
                                        System.out.println("Please enter your firstname:");
                                        String firstname = sc3.nextLine();
                                        System.out.println("Please enter your lastname:");
                                        String lastname = sc3.nextLine();
                                        hotelResource.addCustomer(email2, firstname, lastname);
                                        Customer customer2 = hotelResource.getCustomer(email2);
                                        System.out.println("Which room number you would like to reserve?");
                                        Integer roomnumber2 = sc3.nextInt();
                                        IRoom aRoom2 = HotelResource.getARoom(roomnumber2.toString());
                                        Reservation reservation = hotelResource.reserveARoom(customer2, aRoom2, checkIn, checkOut);
                                        System.out.println("Congratulations! Reservation was created!");
                                        break;
                                    } catch (IllegalArgumentException ex) {
                                        System.out.println(ex.getLocalizedMessage());
                                        System.out.println("Please enter a valid email.");
                                    }
                                }
                            } else {
                                System.out.println("You have entered an invalid answer.");
                            }
                        } else if (choice.equals("n")) {
                            System.out.println("OK.");
                        } else {
                            System.out.println("You have entered an invalid answer.");
                        }
                    } else {
                        System.out.println("Sorry there are no rooms available.");
                    }
                    break;
                case 2:
                    Scanner sc4 = new Scanner(System.in);
                    System.out.println("Please enter your email:name@domain.com");
                    String email3 = sc4.nextLine();
                    Customer customer3 = hotelResource.getCustomer(email3);
                    System.out.println(hotelResource.getCustomersReservation(email3));
                    break;
                case 3:
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Please enter your email in name@domain.com format:");
                    String s3 = sc2.nextLine();
                    System.out.println("Please enter your firstname:");
                    String s4 = sc2.nextLine();
                    System.out.println("Please enter your lastname:");
                    String s5 = sc2.nextLine();
                    try {
                        hotelResource.addCustomer(s3, s4, s5);
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case 4:
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.adminmenu();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        }
    }
}
