# mrbs - Meeting Room Booking System 
## Overview
The Meeting Room Booking Service Application is a robust system that enables users to book meeting rooms, manage amenities, and handle various administrative tasks. This system is built with a focus on scalability, flexibility, and ease of use.
## Features
* ### Admin Functions:
  * Create, update, and manage meeting rooms.
  * View all available meeting rooms along with their amenities.
  * Add users to the system via an XML input.
* ### Manager Functios:
  * Book meeting rooms.
  * View all managers and their details.
  * Reset manager credits to 2000 every Monday.
## Getting Started
### Prerequisistes
* **Java 8+**: Ensure that you have JDK 8 or higher installed.
* **Maven**: Make sure Maven is installed to manage dependencies.
### Installation
1. Clone the Repository:
   ```bash
   https://github.com/BajajPratyush/mrbs.git
   cd mrbs
   ```
2. Build the Project:
   ```
   mvn clean install
   ```
3.  Run the UI and Backend Separately
## Usage
### Running the application (Backend)
Once the application is running, you will be greeted with a menu depending upon the user. For example when you login as an admin it will look like:
> Menu:
> 1. Create Meeting Room
> 2. Update Meeting Room
> 3. View All Meeting Rooms
> 4. Add Users
> 5. Exit
> Enter your choice:
## User Interface
The application provides a simple console-based user interface where admins can manage meeting rooms and users, and managers can book rooms. The UI is designed to be intuitive and straightforward, ensuring ease of use for all users.
## Custom Exception Handling
The application includes custom exceptions like **MeetingRoomAlreadyPresentException**, **InvalidMeetingRoomException**, and **ManagerNotFound**  etc. to handle specific error scenarios gracefully.
## Acknowledgments
Special thanks to the development team for their dedication and hard work in bringing this project to life.
## Team Broken Code:
1. Pratyush Bajaj
2. Rajshree Bansal
3. Riya Sharma
4. Rohith Surapuraju
5. Shreya Lal
6. Vansh Sharma
7. Yash Singhal
