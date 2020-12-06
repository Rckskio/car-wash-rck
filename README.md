# CS50 Final Project - Car Wash
## Created by Luiz Henrique Goncalves

# Project

This project is a simple application to help a car wash to organize bookings and register clients, after the client is registered, making a booking will provide name suggestions and autocomplete the name of the client, then you can have a look on the bookings with details like name of the client, date, time and what service is to be done.

## Structure

The project structure is based on 5 activities, the main activity, customer sign in activity, book a service, calendar and customers list.

**Main Activity**

On this activity the user can see the logo of the company on top and 4 buttons to start the others activities.

**Customer Sign in Activity**

This activity allows the user to register a new customer, with simple details as name and phone number. The button to save is disabled automatically if both input text is empty or the number provided does not match a valid number, in this case the number pattern I chose is from Brazil, where you have the pattern with 8 digits and some locations 9 digits, also if the user enter a character that is not a digit the color of the text will become red and the button will be disabled, after removing this character the color goes back to the original and after the number match 8 or 9 digits the button is enabled again, allowing the user to save the user on the database.
The database was implemented using Room library from android with SQLite, this activity is the one that creates the database for customers on the users phone or tablet.

**Book a Service Activity**

The activity to book an appointment, in this activity the user can enter the name of the customer, select a date, time and which service to provide. The services available are Simple wash, Complete without wax and Complete with was.
The input text of this activity users the database of customers to display autocomplete suggestions of customers name already registered, but it is only suggestions, if the user don't choose one of the names, he still can make the appointment for the name typed.
In this activity the button to confirm the booking is disable when the customer name is empty and no service was selected, the date and time if are not chosen will prompt the user with a toast to select the date and time.
This activity also create another database, this time for the bookings already made.

**Calendar Activity**

This activity will load from the database all scheduled bookings and services, and display on a recycler view each booking on a separated card view containing the customer name, date, time and which service to provide.

**Customers List** 

Like the calendar activity but this activity will display all the registered customers and phone numbers, also using an recycler view and card view for each customer.

# Allow Backup

This app is enabled to allow backup, so if the user has enabled auto backup to his Google account on his device, it will store all the data of the app on the users phone. The data like database, shared preferences etc. Google says it wont use the user space on Google drive, that's why it will store a maximum of 25MB per app on a hidden location on the user account, making it secure with encryption and not allowing the user or other app to access this data, it will only be used when backing up or restoring.

## That's It, I hope this app can help some small car wash business to keep organized with bookings.

**Still need to implement new functionalities and organize the code structure**

**Need to implement delete option for bookings and costumers**

# Made by Luiz Henrique Goncalves, AKA Rck.

**Light mode example**

![Car Wash Light mode](https://thumbs.gfycat.com/PopularEdibleAllosaurus-mobile.jpg)

**Dark mode example**

![Car Wash example dark mode](https://thumbs.gfycat.com/ZanyInfiniteCuckoo-size_restricted.gif)


