-- Procedure example:

DELIMITER $$
CREATE PROCEDURE report_specific(IN dateLow DATETIME, IN dateHigh DATETIME)
report_specific:BEGIN

	DECLARE dl DATETIME;
    DECLARE dh DATETIME;
	IF dateLow IS NULL THEN 
		SET dl = '1000-01-01 00:00:00'; 
	ELSE
		SET dl = dateLow;
	END IF;
    IF dateHigh IS NULL THEN
		SET dh = '9999-12-31 23:59:59'; 
	ELSE 
		SET dh = dateHigh;
	END IF;
 
	SELECT a.airport_id, a.name, flight_id, count(ticket_id) as no_of_sold_tickets, price * count(ticket_id) AS total_revenue
	FROM 
		airport a join flight f on a.airport_id = f.departure_airport_id or a.airport_id = f.arrival_airport_id
			left outer join ticket t on (f.flight_id = t.departure_flight_id or f.flight_id = t.arrival_flight_id) and t.sale_date IS NOT NULL
	WHERE departure_date between dl and dh or sale_date between dl and dh
	GROUP BY a.airport_id, f.flight_id;
END $$