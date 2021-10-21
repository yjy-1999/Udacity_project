package api;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static HotelResource hotelResource = null;

    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String customerEmail) {
        return customerService.getCustomer(customerEmail);
    }

    public void addCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getARoom(String roomId) {
        return ReservationService.getARoom(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservation(String customerEmail) {
        return reservationService.getCustomersReservation(customerEmail);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
};

