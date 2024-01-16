package hotemManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
    HashMap<Integer, Room> rooms;
    ArrayList<Integer> roomIds;

    // Initialize roomIds to an empty ArrayList and rooms to a hashmap where the
    // keys are the room numbers that point to the specific room
    // Arraylist is used to preserve order of roomIds for output
    public Hotel() {
        this.rooms = new HashMap<>();
        this.roomIds = new ArrayList<>();
    }

    // Add a new room to the hotel
    public void addRoom(Room newRoom) {
        this.rooms.put(newRoom.getRoomNumber(), newRoom);
        for (int i = 0; i < this.roomIds.size(); i++) {
            if (this.roomIds.get(i) > newRoom.getRoomNumber()) {
                this.roomIds.add(i, newRoom.getRoomNumber());
                return;
            }
        }
        this.roomIds.add(newRoom.getRoomNumber());
    }

    // Book a Room
    public void bookRoom(int roomNumber, int reservationCost, LocalDate checkin, LocalDate checkout, String guestName,
            String guestMobile, String guestEmail) {
        Room currRoom = this.rooms.get(roomNumber);
        currRoom.bookRoom(reservationCost, guestName, guestMobile, guestEmail, checkin, checkout);
    }

    // Get Room Object
    public Room getRoom(int roomNumber) {
        return this.rooms.get(roomNumber);
    }

    // Checkout
    public void checkout(int roomNumber) {
        this.rooms.get(roomNumber).checkout();
    }


    // Only print Available Rooms
    public void showAvailableRooms(LocalDate checkinDate, LocalDate checkoutDate) {
        for (int roomId : this.roomIds) {
            Room currRoom = this.rooms.get(roomId);
            if (!currRoom.isBooked()) {
                System.out.println(String.format("%d    %s    %s    %d", currRoom.getRoomNumber(),
                        currRoom.getRoomType(), currRoom.getRoomConfiguration(), currRoom.getPrice()));
            }
        }
    }

    // Display room information
    public void displayRoomDetails() {
        for (int roomId : this.roomIds) {
            Room currRoom = this.rooms.get(roomId);
            System.out.println(String.format("%d    %s    %s    %d", currRoom.getRoomNumber(), currRoom.getRoomType(),
                    currRoom.getRoomConfiguration(), currRoom.getPrice()));
        }
    }

    // Show All Rooms with Availability Status
    public void showRoomsWithAvailability() {
        for (int i = 0; i < this.roomIds.size(); i++) {
            Room currRoom = this.rooms.get(this.roomIds.get(i));
            String availability = currRoom.isBooked() ? "Booked" : "Available";
            System.out.println(String.format("%d    %s    %s    %d    %s", currRoom.getRoomNumber(),
                    currRoom.getRoomType(), currRoom.getRoomConfiguration(), currRoom.getPrice(), availability));
        }
    }
}
