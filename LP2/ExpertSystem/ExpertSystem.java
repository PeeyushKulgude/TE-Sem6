package ExpertSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Flight {
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private List<Cargo> cargoList;

    public Flight(String flightNumber, Date departureTime, Date arrivalTime) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cargoList = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void addCargo(Cargo cargo) {
        cargoList.add(cargo);
    }
}

class Cargo {
    private String cargoId;
    private String description;

    public Cargo(String cargoId, String description) {
        this.cargoId = cargoId;
        this.description = description;
    }

    public String getCargoId() {
        return cargoId;
    }

    public String getDescription() {
        return description;
    }
}

class FlightManager {
    private List<Flight> flights;

    public FlightManager() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean checkConflict(Flight newFlight) {
        for (Flight existingFlight : flights) {
            if (newFlight.getDepartureTime().before(existingFlight.getArrivalTime()) &&
                    newFlight.getArrivalTime().after(existingFlight.getDepartureTime())) {
                return true;
            }
        }
        return false; 
    }
}

public class ExpertSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightManager flightManager = new FlightManager();

        System.out.print("Enter the number of flights: ");
        int numFlights = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numFlights; i++) {
            System.out.println("\nEnter information for Flight " + (i + 1) + ":");
            System.out.print("Flight number: ");
            String flightNumber = scanner.nextLine();
            System.out.print("Departure time (YYYY-MM-DD HH:MM): ");
            String departureTimeString = scanner.nextLine();
            Date departureTime = getDate(departureTimeString);
            System.out.print("Arrival time (YYYY-MM-DD HH:MM): ");
            String arrivalTimeString = scanner.nextLine();
            Date arrivalTime = getDate(arrivalTimeString);

            Flight newFlight = new Flight(flightNumber, departureTime, arrivalTime);

            if (flightManager.checkConflict(newFlight)) {
                System.out.println("Conflict detected for Flight " + (i + 1) + "!");
            } else {
                System.out.println("No conflict detected for Flight " + (i + 1) + ".");
            }

            flightManager.addFlight(newFlight);
        }
        scanner.close();
    }

    private static Date getDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
