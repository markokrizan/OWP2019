#select * from airport;
#select * from flight;
#select * from ticket;
#select * from user;


use Airline;


#User:

#p123
INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked, deleted)
	VALUES('pekip', '46bf36a7193438f81fccc9c4bcc8343e', 'Petar', 'Petrović', '2018-12-03', 'ADMIN', 0, 0);
#s456
INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked, deleted)
	VALUES('savas', '10f96fb0511f1cc86e10d4d7fe024a03', 'Sava', 'Savanović', '2018-12-04', 'REGULAR', 0, 0);
#m789
INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked, deleted)
	VALUES('mitarm', 'bee55b534c4f3d9204a4d0ee271a9440', 'Mitar', 'Mirić', '2018-12-05', 'REGULAR', 0, 0);


#Airline:
INSERT INTO Airport (name, deleted)
	VALUES ("Aerodrom Nikola Tesla", 0);
INSERT INTO Airport (name, deleted)
	VALUES ("Heathrow Airport", 0);
INSERT INTO Airport (name, deleted)
	VALUES ("Charles de Gaulle Airport", 0);
INSERT INTO Airport (name, deleted)
	VALUES ("Amsterdam Airport Schiphol", 0);
INSERT INTO Airport (name, deleted)
	VALUES ("Frankfurt am Main Airport", 0);

#Flight:
#DATETIME format: 'YYYY-MM-DD HH:MM:SS'

#Past:
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('a1', '2018-12-04 20:45:00', '2018-12-05 06:00:00', 1, 2, 20, 1090.50, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('a2', '2018-11-04 20:45:00', '2018-11-05 06:00:00', 1, 3, 20, 1200.50, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('a3', '2018-10-04 20:45:00', '2018-10-05 06:00:00', 2, 1, 20, 900.00, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('a4', '2018-09-04 20:45:00', '2018-09-05 06:00:00', 3, 1, 20, 350.49, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('a5', '2018-08-04 20:45:00', '2018-08-05 06:00:00', 4, 5, 20, 599.99, 0);

#Future:
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('b1', '2019-03-01 20:45:00', '2019-04-02 06:00:00', 1, 2, 20, 1090.50, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('b2', '2019-04-03 20:45:00', '2019-04-04 06:00:00', 2, 1, 20, 1200.50, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('b3', '2019-03-04 20:45:00', '2019-04-05 06:00:00', 3, 4, 20, 900.00, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('b4', '2019-04-06 20:45:00', '2019-04-07 06:00:00', 4, 3, 20, 350.49, 0);
INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)
	VALUES('b5', '2019-04-04 20:45:00', '2019-04-05 06:00:00', 4, 5, 20, 599.99, 0);
    

#Ticket

#(opciono) se popunjava povratni let
#Potrebno je ponuditi samo odabir letova Ä�iji je polazni aerodrom isti kao dolazni aerodrom polaznog leta, Ä�iji je
#dolazni aerodrom isti kao polazni aerodrom polaznog leta i Ä�iji je datum (vreme) polaska nakon datuma
#(vremena) dolaska polaznog leta



INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)
	VALUES (1, 3, 2, 3, '2018-07-04 15:00:00', '2018-12-04 20:00:00', 3, 0);
INSERT INTO Ticket (departure_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)
	VALUES (1, 4, 5, '2018-07-04 15:00:00', '2018-12-04 20:00:00', 3, 0); #nije povratni
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)
	VALUES (2, 4, 6, 7, '2018-07-04 15:00:00', '2018-12-04 20:00:00', 3, 0);
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)
	VALUES (1, 3, 8, 9, '2018-07-04 15:00:00', '2018-12-04 20:00:00', 2, 0);
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)
	VALUES (2, 4, 10, 11, '2018-07-04 15:00:00', '2018-12-04 20:00:00', 2, 0);
	
	
#future flights b1 (id 6) and b2 (id 7) for seat testing:

INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, sale_date, user_id, deleted)
	VALUES (6, 7, 1, 2, '2019-01-17 15:00:00', 3, 0);
INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, sale_date, user_id, deleted)
	VALUES (6, 7, 3, 4, '2018-01-18 15:00:00', 3, 0);




