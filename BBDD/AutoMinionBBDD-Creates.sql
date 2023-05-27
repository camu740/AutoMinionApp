drop schema if exists AutoMinionBBDD;
create schema AutoMinionBBDD;
use AutoMinionBBDD;

create table Concessionaires (
id bigint primary key auto_increment,
name varchar(30),
address varchar(50),
CP int,
City varchar(50)
);

create table Customers (
id bigint primary key auto_increment,
name varchar(20),
surname1 varchar(20),
surname2 varchar(20),
phone varchar(15),
email varchar(50),
profileImage text,
address varchar(50)
);

create table Vehicles (
id bigint primary key auto_increment,
vehicleType varchar(20) not null,
brand varchar(45),
model varchar(50),
registration varchar(9) unique, #matricula
color varchar(20),
fabricationYear int,
km decimal(16,2),
numberDoor int,
numberSeat int,
combustion varchar(20),
drivingType varchar(20),
entry_date date,
customerId bigint,
concessionaireId bigint, #un vehiculo está en un concesionario que tiene muchos vehículos
constraint check (vehicleType = 'Coche' OR vehicleType = 'Motocicleta' OR vehicleType = 'Ciclomotor' ),
constraint check (drivingType = 'Manual' OR drivingType = 'Automatico'),
constraint check (combustion = 'Gasolina' OR combustion = 'Diesel' OR combustion = 'Electric' OR combustion = 'Hybrid'),
constraint foreign key (concessionaireId) references Concessionaires(id) on update cascade,
constraint foreign key (customerId) references Customers(id) on update cascade
);

create table Employees (
id bigint primary key auto_increment,
name varchar(20),
surname1 varchar(20),
surname2 varchar(20),
phone varchar(15),
email varchar(50),
password text,
profileImage text,
address varchar(50),
concessionaireId bigint, #un empleado trabaja en un concesionario que tiene muchos empleados
constraint foreign key (concessionaireId) references Concessionaires(id) on update cascade
);

create table Mechanics(
id bigint primary key,
bossId bigint,
constraint foreign key (id) references Employees(id) on update cascade on delete cascade,
constraint foreign key (bossId) references Mechanics(id) on update cascade
);

create table Speciality(
id bigint primary key auto_increment,
name varchar(30) unique
);

create table MechanicSpeciality(
MechanicId bigint,
SpecialityId bigint,
primary key (MechanicId, SpecialityId),
constraint foreign key (MechanicId) references Mechanics(id) on update cascade,
constraint foreign key (SpecialityId) references Speciality(id) on update cascade
);

create table SalesEmployees(
id bigint primary key,
constraint foreign key (id) references Employees(id) on update cascade on delete cascade
);

create table Director(
id bigint primary key,
constraint foreign key (id) references Employees(id) on update cascade on delete cascade
);

create table SalesProposal(
salesEmployeeId bigint,
customerId bigint,
vehicleId bigint,
proposal_date date,
proposalPrice decimal(16,2),
sold boolean,
sold_date date,
primary key (salesEmployeeId, customerId, vehicleId, proposal_date),
constraint foreign key (salesEmployeeId) references SalesEmployees(id) on update cascade,
constraint foreign key (vehicleId) references Vehicles(id) on update cascade,
constraint foreign key (customerId) references Customers(id) on update cascade
);

create table Repairs(
mechanicId bigint,
vehicleToRepair bigint,
request_date date,
customerId bigint,
estimatedBudget decimal(16,2),
estimatedTime decimal(16,2),
Details text,
Priority varchar(20),
partsList text,
finalize_date date default null,
finalize boolean default false,
primary key (mechanicId, vehicleToRepair, request_date),
constraint check (Priority = 'Alta' OR Priority = 'Media' OR Priority = 'Baja'),
constraint foreign key (mechanicId) references Mechanics(id) on update cascade,
constraint foreign key (vehicleToRepair) references Vehicles(id) on update cascade,
constraint foreign key (customerId) references Customers(id) on update cascade
);

