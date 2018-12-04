#select * from airport;
#select * from flight;
#select * from ticket;
#select * from user;


use Airline;


#User:

#p123
INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked)
	VALUES('pekip', '46bf36a7193438f81fccc9c4bcc8343e', 'Petar', 'Petrović', '2018-12-03', 'ADMIN', 0);
#s456
INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked)
	VALUES('savas', '10f96fb0511f1cc86e10d4d7fe024a03', 'Sava', 'Savanović', '2018-12-04', 'REGULAR', 0);
#m789
INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked)
	VALUES('mitarm', 'bee55b534c4f3d9204a4d0ee271a9440', 'Mitar', 'Mirić', '2018-12-05', 'REGULAR', 0);


#Airline:
INSERT INTO Airport (name)
	VALUES ("Aerodrom Nikola Tesla");
INSERT INTO Airport (name)
	VALUES ("Heathrow Airport");
INSERT INTO Airport (name)
	VALUES ("Charles de Gaulle Airport");
INSERT INTO Airport (name)
	VALUES ("Amsterdam Airport Schiphol");
INSERT INTO Airport (name)
	VALUES ("Frankfurt am Main Airport");

#Flight:
#DATETIME format: 'YYYY-MM-DD HH:MM:SS'

#Past:
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('a1', '2018-12-04 20:45:00', '2018-12-05 06:00:00', 1, 2, 190, 1090.50);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('a2', '2018-11-04 20:45:00', '2018-11-05 06:00:00', 1, 3, 190, 1200.50);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('a3', '2018-10-04 20:45:00', '2018-10-05 06:00:00', 2, 1, 190, 900.00);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('a4', '2018-09-04 20:45:00', '2018-09-05 06:00:00', 3, 1, 190, 350.49);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('a5', '2018-08-04 20:45:00', '2018-08-05 06:00:00', 4, 6, 78, 599.99);

#Future:
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('b1', '2019-01-01 20:45:00', '2019-01-02 06:00:00', 1, 2, 190, 1090.50);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('b2', '2019-01-04 20:45:00', '2019-01-05 06:00:00', 1, 3, 190, 1200.50);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('b3', '2019-02-04 20:45:00', '2019-02-05 06:00:00', 2, 1, 190, 900.00);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('b4', '2019-03-04 20:45:00', '2019-03-05 06:00:00', 3, 1, 190, 350.49);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price)
	VALUES('b5', '2019-04-04 20:45:00', '2019-04-05 06:00:00', 4, 6, 78, 599.99);
    

#Ticket

#(opciono) se popunjava povratni let
#Potrebno je ponuditi samo odabir letova čiji je polazni aerodrom isti kao dolazni aerodrom polaznog leta, čiji je
#dolazni aerodrom isti kao polazni aerodrom polaznog leta i čiji je datum (vreme) polaska nakon datuma
#(vremena) dolaska polaznog leta

INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id)
	VALUES (1, 3, 12, 49, '2018-11-04 15:00:00', '2018-12-04 20:00:00', 3);
INSERT INTO Ticket (departure_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id)
	VALUES (1, 5, 49, '2018-11-04 15:00:00', '2018-12-04 20:00:00', 3); #nije povratni
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id)
	VALUES (2, 4, 12, 49, '2018-11-04 15:00:00', '2018-12-04 20:00:00', 3);
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id)
	VALUES (1, 3, 12, 49, '2018-11-04 15:00:00', '2018-12-04 20:00:00', 2);
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id)
	VALUES (2, 4, 12, 49, '2018-11-04 15:00:00', '2018-12-04 20:00:00', 2);





