CREATE SEQUENCE entry_id_seq;

CREATE TABLE eodentry(
	p_id 	int NOT NULL DEFAULT nextval('entry_id_seq'),
	symbol 	varchar(45) NOT NULL,
	date 	date NOT NULL,
	open 	decimal(16, 4) NOT NULL,
	high 	decimal(16, 4) NOT NULL,
	low 	decimal(16, 4) NOT NULL,
	close 	decimal(16, 4) NOT NULL,
	volume 	int NOT NULL,
	PRIMARY KEY (p_id)
);
