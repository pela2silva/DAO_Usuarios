CREATE DATABASE Base_Caso;
USE Base_Caso;

CREATE TABLE Usuarios (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(45) NOT NULL,
Apellido VARCHAR(45) NOT NULL,
Email VARCHAR (45) NOT NULL,
Telefono VARCHAR(45) NOT NULL);

	SELECT * FROM Usuarios;

INSERT INTO Usuarios (Nombre,Apellido,Email,Telefono)
VALUES
('ALE','SILVA','ale@gmail.com','3513280106');

UPDATE Usuarios SET Telefono = '3511111111' WHERE (Nombre = 'ALE');

