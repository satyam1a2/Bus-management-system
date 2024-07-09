import java.util.Scanner;

class Passenger {
    private String name;

    public Passenger() {
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Bus {
    private int busNo;
    private String origin;
    private String destination;
    private float fare;
    private Passenger[] seats;
    private int bookedSeats;

    public Bus(int busNo, String origin, String destination, float fare) {
        this.busNo = busNo;
        this.origin = origin;
        this.destination = destination;
        this.fare = fare;
        this.seats = new Passenger[ReservationSystem.MAX_SEATS];
        for (int i = 0; i < ReservationSystem.MAX_SEATS; i++) {
            seats[i] = new Passenger();
        }
        this.bookedSeats = 0;
    }

    public int getBusNo() {
        return busNo;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public float getFare() {
        return fare;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void bookSeat(int seatNo, String passengerName) {
        seats[seatNo - 1].setName(passengerName);
        bookedSeats++;
    }

    public boolean isSeatAvailable(int seatNo) {
        return seats[seatNo - 1].getName().isEmpty();
    }

    public void displayBookedSeats() {
        boolean anyBooked = false;
        for (int i = 0; i < ReservationSystem.MAX_SEATS; i++) {
            if (!seats[i].getName().isEmpty()) {
                if (!anyBooked) {
                    System.out.println("Booked Seats:");
                    anyBooked = true;
                }
                System.out.println("Seat " + (i + 1) + ": " + seats[i].getName());
            }
        }
        if (!anyBooked) {
            System.out.println("All seats are vacant from seat number 1 to " + ReservationSystem.MAX_SEATS);
        }
    }

    public void displayVacantSeats() {
        boolean anyVacant = false;
        for (int i = 0; i < ReservationSystem.MAX_SEATS; i++) {
            if (seats[i].getName().isEmpty()) {
                if (!anyVacant) {
                    System.out.println("Vacant Seats: Please select from the following available seats:");
                    anyVacant = true;
                }
                System.out.print((i + 1) + " ");
            }
        }
        if (!anyVacant) {
            System.out.println("Sorry, all the seats have already been booked.");
        }
    }
}

public class ReservationSystem {
    public static final int MAX_BUSES = 5;
    public static final int MAX_SEATS = 20;
    private Bus[] buses;
    private Scanner scanner;

    public ReservationSystem() {
        buses = new Bus[MAX_BUSES];
        scanner = new Scanner(System.in);
        initializeBuses();
    }

    private void initializeBuses() {
        String[] origins = {"City_A", "City_B", "City_C", "City_D", "City_E"};
        String[] destinations = {"City_X", "City_Y", "City_Z", "City_W", "City_V"};
        float[] fares = {15.0f, 20.0f, 25.0f, 18.0f, 22.0f};
        int[] busNumbers = {4325, 2375, 9643, 6453, 8345};

        for (int i = 0; i < MAX_BUSES; i++) {
            buses[i] = new Bus(busNumbers[i], origins[i], destinations[i], fares[i]);
        }
    }

    private int findBusIndex(int busNo) {
        for (int i = 0; i < MAX_BUSES; i++) {
            if (buses[i].getBusNo() == busNo) {
                return i;
            }
        }
        return -1;
    }

    public void viewBusList() {
        System.out.println("Welcome to our Bus Selection!");
        System.out.println("Explore our Available Buses:");
        System.out.println("Bus No.\t Origin\t Destination\t Fare");
        for (int i = 0; i < MAX_BUSES; i++) {
            System.out.println(" " + buses[i].getBusNo() + "\t " + buses[i].getOrigin() + "\t " + buses[i].getDestination() + "\t " + buses[i].getFare());
        }
        System.out.println();
    }

    public void bookTickets() {
        System.out.print("Enter the bus number: ");
        int busNo = scanner.nextInt();
        int index = findBusIndex(busNo);
        if (index == -1) {
            System.out.println("Invalid bus number");
            return;
        }

        Bus bus = buses[index];
        System.out.println("\nWelcome to Ticket Booking!");
        System.out.println("Bus " + bus.getBusNo() + " Details:");
        System.out.println("Total seats Available: " + MAX_SEATS);
        System.out.println("Seats Already Booked: " + bus.getBookedSeats());

        bus.displayVacantSeats();
        System.out.println();

        System.out.print("Enter number of tickets to book: ");
        int numTickets = scanner.nextInt();

        if (numTickets > 0 && numTickets <= (MAX_SEATS - bus.getBookedSeats())) {
            for (int i = 0; i < numTickets; i++) {
                System.out.print("Enter the seat number for Passenger " + (i + 1) + " (Seat numbers range from 1 to " + MAX_SEATS + "): ");
                int seatNo = scanner.nextInt();
                if (seatNo >= 1 && seatNo <= MAX_SEATS) {
                    if (bus.isSeatAvailable(seatNo)) {
                        System.out.print("Enter name for passenger " + (i + 1) + ": ");
                        String passengerName = scanner.next();
                        bus.bookSeat(seatNo, passengerName);
                        System.out.println("Seat " + seatNo + " booked for passenger " + passengerName);
                    } else {
                        System.out.println("Seat " + seatNo + " is already booked");
                        i--;
                    }
                } else {
                    System.out.println("Invalid seat number");
                    i--;
                }
            }
        } else {
            System.out.println("Invalid number of tickets entered or insufficient seats available.");
        }
        System.out.println();
    }

    public void checkBusStatusBoard() {
        System.out.print("Enter the bus number to check status board: ");
        int busNo = scanner.nextInt();
        int index = findBusIndex(busNo);
        if (index == -1) {
            System.out.println("Invalid bus number");
            return;
        }

        Bus bus = buses[index];
        System.out.println("Bus No. " + bus.getBusNo() + " - Status Board:");
        bus.displayBookedSeats();
    }

    public void showMenu() {
        while (true) {
            System.out.println("Explore Your Options:");
            System.out.println("1. View Available Buses");
            System.out.println("2. Book Your Tickets");
            System.out.println("3. Check Bus Status Board");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBusList();
                    break;
                case 2:
                    bookTickets();
                    break;
                case 3:
                    checkBusStatusBoard();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter a valid option again.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\n******");
        System.out.println("* Welcome to Our Reservation System! *");
        System.out.println("* ...Let's Get Your Tickets Reserved... *");
        System.out.println("******\n");

        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.showMenu();
    }
}
