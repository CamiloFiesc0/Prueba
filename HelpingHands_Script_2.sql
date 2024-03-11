create database HelpingHands; 

use HelpingHands;

CREATE TABLE Usuario
(
Id int primary key not null,
Nombre varchar (30),
Telefono int (10),
Direccion varchar (40),
Correo varchar (40),
Contraseña varchar (25),
Edad int (2),
Habilidad varchar (200),
Calificacion int (1),
Rol Varchar (30)
);




CREATE TABLE Solicitud
(
Codigo int primary key not null,
Descripcion varchar (200),
Ubicacion varchar (100),
Fecha date,
Prioridad varchar (20),
Pago_Ofrecido int (6),
idsolicitud int references Usuario(Id)
);

alter table solicitud add foreign key(idsolicitud) references Usuario(Id);


CREATE TABLE Servicio
(
ID_Asignado int primary key not null,
Tipo_Servicio varchar (100),
Costo int (6),
Distancia Varchar (100),
Tipo_Hora date,
idservicio int references Solicitud(Codigo)
);

alter table servicio add foreign key (idservicio) references Solicitud(codigo);

CREATE TABLE Metodo_Pago
(
Num_Factura int primary key not null,
Tarjeta int (12),
Efectivo int (6),
idMetodo int references Servicio(ID_Asignado)
);

alter table Metodo_Pago add foreign key (idMetodo) references Servicio(ID_Asignado);


