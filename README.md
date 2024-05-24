# ScanDash

ScanDash is a smart cart application that enhances the shopping experience by allowing customers and managers to easily scan products using RFID technology. Built with Java Swing for the user interface and integrated with a MySQL database, ScanDash simplifies inventory management for managers and provides a seamless shopping experience for customers.

## Features

### For Managers:
- Add, remove, and manage inventory items.
- Input item details such as price, quantity, and description.
- View and update the database of products.

### For Customers:
- Login with their membership credentials.
- Scan products to add them to their cart.
- View their cart and proceed to checkout.

## Technology Stack
- *Frontend:* Java Swing
- *Backend:* Java
- *Database:* MySQL
- *RFID Integration:* Arduino

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server
- Arduino IDE (for RFID setup)
- Maven (for dependency management)

### Installation

1. *Clone the Repository*

   git clone https://github.com/yourusername/scandash.git
   cd scandash

   Setup the MySQL Database

Create a database named user.
Import the SQL scripts located in Database-ScanDash to set up the required tables.
Configure Database Connection

Update the database connection details in the ViewCartWindow.java file.
java
Copy code
final String DB_URL = "jdbc:mysql://localhost:3306/user";
final String USERNAME = "root";
final String PASSWORD = "yourpassword";
Build and Run the Application

Usage
Start the Application

Run the Progress class to launch the initial loading screen and navigate to the FirstWindow for user selection (Manager or Customer).
Manager Functions

Login as a manager to add, remove, and manage inventory items.
Customer Functions

Login as a customer to scan items and manage their cart.
