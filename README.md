Java Bus Reservation System

This Java program simulates a bus reservation system where passengers can view available buses, book tickets, and check the status of booked seats for various bus routes.

Classes
Passenger

Represents a passenger with a name.
Bus

Represents a bus with attributes such as bus number, origin, destination, fare, and an array of Passenger objects representing seats.
Methods include booking seats, checking seat availability, and displaying booked and vacant seats.
ReservationSystem

Manages multiple Bus instances.
Allows users to view available buses, book tickets specifying seat numbers and passenger names, and check the status of booked seats.
Features
Object-oriented design using Java.
Utilizes arrays and classes to model buses and passengers.
Interactive user interface via console input/output.
Validates user inputs for bus selection, seat booking, and status checks.
Usage
View Available Buses

Displays a list of available buses including their bus number, origin, destination, and fare.
Book Tickets

Prompts users to enter the bus number and number of tickets to book.
Users specify seat numbers and passenger names.
Checks and confirms seat availability before booking.
Check Bus Status Board

Allows users to enter a bus number to view the status of booked seats.
Displays both booked and vacant seats for the selected bus.
Instructions
Compile the Java files using javac ReservationSystem.java.
Run the program using java ReservationSystem.
Follow the prompts to view buses, book tickets, and check seat status.
