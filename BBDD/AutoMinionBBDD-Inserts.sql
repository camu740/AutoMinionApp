use autominionbbdd;
insert into concessionaires values (1, 'AutoMinion', 'Calle Mi Villano Favorito', '29001', 'Wisconsin');

insert into customers values (1, 'Pepe', 'Rodriguez', 'Perez', '654123432', 'pepe@gmail.com', null, 'Calle Malaga'),
(2, 'Juan', 'Lorenzo', 'Fernandez', '65896325', 'juan@gmail.com', null, 'Calle Matalascañas'),
(3, 'Maria', 'Pinto', 'Ruiz', '745896214', 'maria@gmail.com', null, 'Calle Ronda'),
(4, 'Laura', 'Díaz', 'Díaz', '745896214', 'laura@gmail.com', null, 'Calle Minion'),
(5, 'Antonio', 'Hernández', 'Hernández', '745896214', 'antonio@gmail.com', null, 'Calle Sociedad'),
(6, 'Daniel', 'León', 'Becerra', '745896214', 'daniel@gmail.com', null, 'Calle Labrador'),
(7, 'Cristina', 'Lopez', 'Wichita', '751247853', 'cristina@gmail.com', null, 'Calle Sevilla');

insert into employees values (1, 'Ramon', 'Flores', 'Machado', '659847216', 'ramon@gmail.com', 'minion', null, 'Calle Betis', 1),
(2, 'Jose', 'Sanchez', 'Lopez', '625987123', 'jose@gmail.com', 'minion', null, 'Calle Palo', 1),
(3, 'Isabel', 'Flores', 'Perez', '796210458', 'isabel@gmail.com', 'minion', null, 'Calle Polo', 1),
(4, 'Ignacio', 'Resta', 'Mucho', '748120401', 'ignacio@gmail.com', 'minion', null, 'Calle Coche', 1),
(5, 'Rodolfo', 'Remo', 'Rios', '652145214', 'rodolfo@gmail.com', 'minion', null, 'Calle Rio', 1),
(6, 'a', 'a', 'a', 'a', 'a', 'a', null, 'a', 1);

insert into director values(5);

insert into speciality values(1, 'Chasis'),
(2, 'Motor'),
(3, 'Electronica'),
(4, 'Ruedas');

insert into mechanics values (4, 4),(1, 4);

insert into mechanicspeciality values (1,1),(1,4),(4,2),(4,3),(4,4);

insert into salesemployees values (2), (3);

insert into vehicles values (1, 'Coche', 'Opel','Corsa', '1245FGV', 'Negro',2019, 85602.26, 5, 5, 'Diesel', 'Manual', '2020-05-12', null, 1),
(2, 'Coche', 'Reanult', 'Megane', '9586DSC', 'Rojo',2015, 128640.85, 3, 5, 'Gasolina', 'Automatico', '2018-09-25',null, 1),
(3, 'Coche', 'Tesla','Model S', '65214LJK', 'Blanco',2022, 26985.14, 5, 5, 'Electric', 'Automatico', '2022-08-06',null, 1),
(4, 'Motocicleta', 'Honda','CBR600', '6625JNG', 'Negro',2018, 36985.78, null, 1, 'Gasolina', 'Manual', '2022-12-09',null, 1),
(5, 'Coche', 'Toyota','CHR', '8521JTI', 'Blanco', 2021, 45968.52, 5, 5, 'Hybrid', 'Automatico', '2021-05-12',null, 1);

insert into salesproposal values 
(2,1,3,'2022-06-15',17695.00, 1, '2022-07-06'),
(3,2,2,'2022-06-20',20000.00, 1, '2022-07-06'),
(2,3,1,'2022-07-15',15678.00, 1, '2022-07-06'),
(3,1,3,'2022-11-15',12345.00, 1, '2022-07-06'),
(2,2,2,'2022-06-01',54321.00, 0, null),
(3,3,1,'2022-01-15',11223.00, 0, null),
(2,1,3,'2022-05-26',10293.00, 0, null),
(2,2,2,'2022-02-02',31246.00, 0, null),
(3,3,5,'2021-10-19',21295.00, 0, null);

insert into repairs values 
(4, 2, '2020-12-28', 4, 550.00, 7.0, 'Revision motor, cambiar neumaticos y arreglar aire acondicionado', 'Alta', 'Ruedas, filtros del aire y filtro de aceite', null, false),
(1, 3, '2023-01-10', 3, 350.00, 5.0, 'Pintar parachoques delantero', 'Baja', 'Pintura', null, false);


