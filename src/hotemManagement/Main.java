package hotemManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

// Phase 1
class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);

        // Initializing shell to interact with
        System.out.println(Main.getPrompt());
        String command = "0";
        while (command.compareTo("9") != 0) {
            System.out.print("Enter your choice: ");
            command = sc.nextLine();
            if (command.compareTo("9") != 0) {
                Main.processCommand(hotel, command, sc);
                System.out.println("\n\n");
                System.out.println(Main.getPrompt());
            }
        }
        sc.close();
    }

    private static String getPrompt() {
        return "Welcome to the Hotel Management System. Please choose the operation that you would like to perform: \n1. Enter Room Details\n2. Show Room Details\n3. Show Rooms Available\n4. Book a Room\n5. Show Guest Details\n6. Show Menu Card\n7. Order Room Service\n8. Checkout\n9. Exit\n\n";
    }

    private static void processCommand(Hotel hotel, String command, Scanner sc) {
        if (command.compareTo("1") == 0) {
            // Adding new room details
            int roomNumber, price;
            String roomType, roomConfiguration;

            String newRoom = "Y";
            while (newRoom.compareTo("Y") == 0) {
                // Taking room details
                System.out.print("Room Number: ");
                roomNumber = sc.nextInt();
                sc.nextLine();
                System.out.print("Room Type: ");
                roomType = sc.nextLine();
                System.out.print("Room Configuration: ");
                roomConfiguration = sc.nextLine();
                System.out.print("Room Price: ");
                price = sc.nextInt();
                sc.nextLine();

                // Adding room
                Room roomInfo = new Room(roomNumber, price, roomType, roomConfiguration);
                hotel.addRoom(roomInfo);

                System.out.print("Would you like to add another room? (Y/N): ");
                newRoom = sc.nextLine();
            }
        } else if (command.compareTo("2") == 0) {
            // Displaying room details
            System.out.println("Room Information in the Hotel..");
            hotel.displayRoomDetails();
        } else if (command.compareTo("3") == 0) {
            hotel.showRoomsWithAvailability();
        } else if (command.compareTo("4") == 0) {
            String checkInDate, checkoutDate, toProceed;
            int roomNumber, cost;
            System.out.println("Please enter the dates...:");
            System.out.print("Check-In Date (DD/MM/YYYY): ");
            checkInDate = sc.nextLine();
            System.out.print("Check-Out Date (DD/MM/YYYY): ");
            checkoutDate = sc.nextLine();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate checkIn = LocalDate.parse(checkInDate, format);
            LocalDate checkOut = LocalDate.parse(checkoutDate, format);
            long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);

            System.out.println("Reservation request for " + daysBetween + " days..");
            System.out.println("Rooms Available during these dates:");
            hotel.showAvailableRooms(checkIn, checkOut);

            System.out.print("Enter the Room Number that you would like to book: ");
            roomNumber = sc.nextInt();
            sc.nextLine();

            Room currRoom = hotel.getRoom(roomNumber);

            while (currRoom == null) {
                System.out.print("Invalid Room Number. Enter a Room Number that exists: ");
                roomNumber = sc.nextInt();
                sc.nextLine();
                currRoom = hotel.getRoom(roomNumber);
            }

            cost = currRoom.getPrice() * (int) daysBetween;

            System.out.println("Total cost of the Room for " + daysBetween + " days: " + cost);
            System.out.print("Would you like to proceed[Y|N]: ");
            toProceed = sc.nextLine();

            if (toProceed.compareTo("Y") == 0) {
                String name, email, isDiscount, bookRoom, mobile;
                int discount;

                System.out.print("Enter Customer Name: ");
                name = sc.nextLine();
                System.out.print("Enter Customer Mobile: ");
                mobile = sc.nextLine();
                System.out.print("Enter Customer Email: ");
                email = sc.nextLine();
                System.out.print("Would you like to Offer any Discount[Y|N]: ");
                isDiscount = sc.nextLine();
                if (isDiscount.compareTo("Y") == 0) {
                    System.out.print("Enter Discount[%]: ");
                    discount = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.println(100 - discount + " " + (float) (100 - discount) / 100 + " " + cost);
                    float discountPrice = ((float) (100 - discount) / 100) * cost;
                    cost = (int) discountPrice;
                    System.out.println("Total Cost of the Room after Discount: " + cost);
                }
                System.out.print("Would you like to Book the Room[Y|N]: ");
                bookRoom = sc.nextLine();

                if (bookRoom.compareTo("Y") == 0) {
                    hotel.bookRoom(currRoom.getRoomNumber(), cost, checkIn, checkOut, name, mobile, email);
                    System.out.println("Room " + roomNumber + " has been booked...");
                    System.out.println("Confirmation number: ");
                }
            }
        } else if (command.compareTo("5") == 0) {
            int roomNumber;
            System.out.print("Enter the Room Number: ");
            roomNumber = sc.nextInt();
            sc.nextLine();

            Room currRoom = hotel.getRoom(roomNumber);
            if (currRoom.isBooked()) {
                System.out.println("Customer Name: " + currRoom.getCustomerName());
                System.out.println("Customer Mobile: " + currRoom.getCustomerMobile());
                System.out.println("Customer Email: " + currRoom.getCustomerEmail());
            } else {
                System.out.println("Room is Vacant");
            }
        } else if (command.compareTo("6") == 0) {
            for (String[] item : Main.getMenuCard()) {
                System.out.println(String.format("%s   %s   %s", item[0], item[1], item[2]));
            }
        } else if (command.compareTo("7") == 0) {
            ArrayList<Integer> items = new ArrayList<>();
            String itemNumber;
            int roomNumber;
            System.out.print("Enter the Room Number: ");
            roomNumber = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter the Item Number (Q to quit): ");
            itemNumber = sc.nextLine();

            while (itemNumber.compareTo("Q") != 0) {
                int item = Integer.valueOf(itemNumber);
                items.add(item);
                System.out.print("Enter the Item Number (Q to quit): ");
                itemNumber = sc.nextLine();
            }

            System.out.println("You Have Ordered...");
            int totalCost = 0;
            for (int item : items) {
                totalCost += Integer.valueOf(getMenuCard()[item - 1][2]);
                System.out.println(String.format("%s    %s", getMenuCard()[item - 1][1], getMenuCard()[item - 1][2]));
            }
            System.out.print("Would you like to Proceed [Y|N]: ");
            String toProceed = sc.nextLine();
            if (toProceed.compareTo("Y") == 0) {
                Room currRoom = hotel.getRoom(roomNumber);
                currRoom.addRoomService(totalCost);
                System.out.println("A total of " + totalCost + " will be charged to the Room");
                System.out.println("Ordered items will be delivered to the Room...");
            }

        } else if (command.compareTo("8") == 0) {
            String paymentMethod;
            int roomNumber;
            System.out.print("Enter the Room Number: ");
            roomNumber = sc.nextInt();
            sc.nextLine();

            Room currRoom = hotel.getRoom(roomNumber);
            System.out.println("Total Cost for the room: " + currRoom.getReservationCost());
            System.out.println("Total Cost of the Room Service Items: " + currRoom.getRoomServiceCost());
            System.out.println("Total Payable: " + currRoom.getReservationCost() + currRoom.getRoomServiceCost());

            System.out.println(
                    "Choose Payment Option:\n1. Credit Card\n2. Debit Card\n3. Net Banking\n4. Cash\n5. UPI\n6. Cancel");
            System.out.print("Select Payment Method: ");
            paymentMethod = sc.nextLine();

            if (paymentMethod.compareTo("6") != 0) {
                hotel.checkout(roomNumber);
                String[] paymentMethods = { "Credit Card", "Debit Card", "Net Baking", "Cash", "UPI" };
                System.out.println("Processing Payment Request...\nPayment via "
                        + paymentMethods[Integer.valueOf(paymentMethod) - 1] + " Successful..");
            }
        } else {
            System.out.println("Invalid command! Please select 1 through 9");
        }
    }

    private static String[][] getMenuCard() {
        String[][] menuCard = {
                { "1", "Sandwich", "50" }, { "2", "Burger", "75" }, { "3", "Pasta", "100" }, { "4", "Fries", "40" },
                { "5", "Pizza", "125" }
        };
        return menuCard;
    }
}
