DELIMITER $$
CREATE 
	TRIGGER insert_trigger BEFORE INSERT
    ON customers
    FOR EACH ROW 
    BEGIN
		INSERT INTO logs(message) VALUES('New customer added.');
	END$$
DELIMITER ;