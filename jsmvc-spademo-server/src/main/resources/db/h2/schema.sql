DROP TABLE EVENTS IF EXISTS;

CREATE TABLE EVENTS (
	ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	TITLE VARCHAR(40) NOT NULL,
	DESCRIPTION VARCHAR(400) NOT NULL,
	START TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	END TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	OWNER VARCHAR(20) NOT NULL,
	STATUS VARCHAR(10) NOT NULL,
	CONSTRAINT pk_EVENTS PRIMARY KEY (ID)
);