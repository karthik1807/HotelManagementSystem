package hotemManagement;

import java.time.LocalDate;

public class Room {
    private int roomNumber, price, reservationCost, roomServiceCost;
    private String roomType, roomConfiguration, guestName, guestMobile, guestEmail;
    private Boolean isBooked;
    private LocalDate checkIn, checkOut;

    public Room(int roomNumber, int price, String roomType, String roomConfiguration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.roomConfiguration = roomConfiguration;
        this.isBooked = false;
        this.roomServiceCost = 0;
    }

    public void bookRoom(int reservationCost, String guestName, String guestMobile, String guestEmail, LocalDate checkin,
            LocalDate checkout) {
        this.isBooked = true;
        this.guestName = guestName;
        this.guestMobile = guestMobile;
        this.guestEmail = guestEmail;
        this.reservationCost = reservationCost;
        this.checkIn = checkin;
        this.checkOut = checkout;
    }

    public void checkout() {
        this.isBooked = false;
        this.guestName = this.guestEmail = this.guestMobile = null;
        this.reservationCost = -1;
        this.checkIn = this.checkOut = null;
    }

    public void addRoomService(int cost) {
        this.roomServiceCost += cost;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public String getRoomConfiguration() {
        return this.roomConfiguration;
    }

    public int getPrice() {
        return this.price;
    }

    public LocalDate getCheckIn() {
        return this.checkIn;
    }

    public LocalDate getCheckOut() {
        return this.checkOut;
    }

    public void setCheckIn(LocalDate checkin) {
        this.checkIn = checkin;
    }

    public void setCheckOut(LocalDate checkout) {
        this.checkOut = checkout;
    }

    public Boolean isBooked() {
        return this.isBooked;
    }

    public String getCustomerName() {
        return this.guestName;
    }

    public String getCustomerMobile() {
        return this.guestMobile;
    }

    public String getCustomerEmail() {
        return this.guestEmail;
    }

    public int getReservationCost() {
        return this.reservationCost;
    }

    public int getRoomServiceCost() {
        return this.roomServiceCost;
    }
}

