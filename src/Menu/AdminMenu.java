package Menu;

import Model.Customer;
import Model.IRoom;
import Model.Room;
import Model.RoomType;
import api.AdminResource;

import java.text.ParseException;
import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {

    public static AdminResource adminResource = AdminResource.getInstance();
    public void adminmenu() throws ParseException {
        System.out.println("Welcome to the AdminMenu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        switch (i) {
            case 1:
                Collection<Customer> allCustomers = adminResource.getAllCustomers();
                System.out.println(allCustomers);
                break;
            case 2:
                Collection<IRoom> allRooms = adminResource.getAllRooms();
                System.out.println(allRooms);
                break;
            case 3:
                adminResource.printAllReservation();
                break;
            case 4:
                Room room = new Room();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter room number");
                room.setRoomNumber(scanner.nextLine());
                System.out.println("Please enter price per night");
                room.setPrice(scanner.nextDouble());
                System.out.println("Please enter room type: 1 for single,  2 for double bed");
                int type = scanner.nextInt();
                if (type == 1) {
                    room.setEnumeration(RoomType.Single);
                } else {
                    room.setEnumeration(RoomType.Double);
                }
                adminResource.addRoom(room);
                break;
            case 5:
                MainMenu mainMenu = new MainMenu();
                mainMenu.mainmenu();

        }
    }
}
