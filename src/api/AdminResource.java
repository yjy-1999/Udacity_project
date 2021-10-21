package api;

import Model.Customer;
import Model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResource {

    private static AdminResource adminResource = null;

    public static AdminResource getInstance() {
        if (null == adminResource) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    CustomerService customerService = CustomerService.getInstance();;
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String customerEmail) {
        return customerService.getCustomer(customerEmail);
    }

    public void addCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void printAllReservation() {
        reservationService.printAllReservation();
    }

    public void addRoom(IRoom room) {
        reservationService.addRoom(room);
    }
}
