CREATE TABLE development.Notes (
	noteId INT NOT NULL AUTO_INCREMENT,
	title varchar(50) NOT NULL,
	noteDate DATE NOT NULL,
	description VARCHAR(1000) NULL,
	tag varchar(50) NULL,
	CONSTRAINT Notes_PK PRIMARY KEY (noteId),
	CONSTRAINT Notes_UN UNIQUE KEY (noteDate,title)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

