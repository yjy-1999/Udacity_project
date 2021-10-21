package service;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import Model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService INSTANCE;
    private static Collection<Reservation> collection_reservation = new ArrayList<Reservation>();
    private static Collection<IRoom> collection_room = new ArrayList<IRoom>();

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }

    public static void addRoom(IRoom room) {
        collection_room.add(room);
    }

    public static IRoom getARoom(String roomId) {
        IRoom result = null;
        for (IRoom room : collection_room) {
            if (room.getRoomNumber().equals(roomId)) {
                result = room;
            }
        }
        return result;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservedRoom = new Reservation(customer, room, checkInDate, checkOutDate);
        collection_reservation.add(reservedRoom);
        System.out.println(reservedRoom.toString());
        return reservedRoom;
    }


    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        try {
            List<IRoom> reservedRooms = getReservedRooms(checkInDate, checkOutDate);
            for (IRoom room : collection_room) {
                if(!reservedRooms.contains(room)){
                    availableRooms.add(room);
                }
            }
        } catch (Exception e){
            if (availableRooms.isEmpty()) return null;
        }
        return availableRooms;
    }
//        Collection<IRoom> available_rooms = new ArrayList<IRoom>();
//        List<IRoom> reservedRooms = getReservedRooms(checkInDate, checkOutDate);
//
//        for (IRoom room : collection_room) {
//            if (!reservedRooms.contains(room)) {
//                available_rooms.add(room);
//            } else if (reservedRooms.contains(room)) {
//                Calendar c1 = Calendar.getInstance();
//                c1.setTime(checkInDate);
//                c1.add(Calendar.DAY_OF_MONTH, 7);
//
//                Calendar c2 = Calendar.getInstance();
//                c2.setTime(checkOutDate);
//                c2.add(Calendar.DAY_OF_MONTH, 7);
//
//                List<IRoom> reservedRooms2 = getReservedRooms(c1.getTime(), c2.getTime());
//                if (!reservedRooms2.contains(room)) {
//                    System.out.println("Recommanded room:");
//                    System.out.println("From" + c1.getTime() + "to" + c2.getTime());
//                    available_rooms.add(room);
//                } else if (reservedRooms2.contains(room)) {
//                    System.out.println("Sorry, there is no available room.");
//                }
//            }
//        }
//
//        return available_rooms;
//    }

    public List<IRoom> getReservedRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> reservedRooms = new ArrayList<>();
        try {
            for (Reservation reservation : collection_reservation) {
                if (reservation.getCheckInDate().getTime() <= checkInDate.getTime() &&
                        reservation.getCheckOutDate().getTime() >= checkOutDate.getTime()) {
                    reservedRooms.add(reservation.getRoom());
                }
            }
        } catch (Exception e){
            if (reservedRooms.isEmpty()) return null;
        }
        return reservedRooms;
    }


    public Collection<Reservation> getCustomersReservation(String customerEmail) {
        Collection<Reservation> get_reservation = new ArrayList<Reservation>();
        for (Reservation reservation : collection_reservation) {
            if (reservation.getCustomer().getEmail().equals(customerEmail)) {
                get_reservation.add(reservation);
            }
        }
        return get_reservation;
    }

    public void printAllReservation() {
        for (Reservation reservations : collection_reservation) {
            System.out.println(reservations);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return collection_room;
    }
}
