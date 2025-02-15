CREATE DATABASE IF NOT EXISTS chemistryDB CHARACTER SET utf8;

USE chemistryDB;

CREATE TABLE IF NOT EXISTS element_categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25) NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS elements(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25) NOT NULL,
	atomic_number INT NOT NULL,
	atomic_mass DECIMAL NOT NULL,
	fusion_point DOUBLE NOT NULL,
	boiling_point DOUBLE NOT NULL,
	freezing_point DOUBLE NOT NULL,
	measuring_unit VARCHAR(25) NOT NULL,
	category INT NOT NULL,
	FOREIGN KEY (category) REFERENCES element_categories(id)
);

CREATE TABLE IF NOT EXISTS compositions(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS chemical_bondings(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25) NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS chemical_properties(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25) NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS compounds(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	iupac_name VARCHAR(100) NOT NULL,
	formula VARCHAR(100) NOT NULL,
	molar_mass DOUBLE NOT NULL,
	composition INT NOT NULL,
	chemical_bonding INT NOT NULL,
	chemical_property INT NOT NULL,
	FOREIGN KEY (composition) REFERENCES compositions(id),
	FOREIGN KEY (chemical_bonding) REFERENCES chemical_bondings(id),
	FOREIGN KEY (chemical_property) REFERENCES chemical_properties(id)
);

INSERT IGNORE INTO element_categories (name, description) VALUES
('Metales Alcalinos','Son elementos del grupo 1 de la tabla periodica, son muy reactivos'),
('Metaloides','Son elementos que tienen propiedades intermedias entre los metales y los no metales, son semiconductores'),
('Actinoides','Son elementos del bloque f de la tabla periodica, ubicados en la fila 7, son conocidos por su alta radiactividad'),
('Metales Alcalinoterreos','Son elementos del grupo 2 de la tabla periodica, son menos reactivos que los metales alcalinos'),
('No Metales Reactivos','Son elementos altamente reactivos, especialmente con metales, formando compuestos como oxidos y sales'),
('Metales Transicionales','Son elementos de los grupos 3 a 12 de la tabla periodica, son buenos conductores de electricidad y tienen la capacidad de formar varios estados de oxidacion'),
('Gases Nobles','Son elementos del grupo 18, son gases inodoros, incoloros y no reactivos debido a que tienen una capa de electrones completa'),
('Metales Postransicionales','Son metales ubicados en la parte inferior de la tabla periodica, son menos ductiles que los metales transicionales'),
('Lantanidos','Son elementos del bloque f, ubicados en la fila 6 de la tabla periodica, son metales raros y utilizados en aplicaciones como imanes y catalizadores');


INSERT IGNORE INTO elements (name, atomic_number, atomic_mass, fusion_point, boiling_point, freezing_point, measuring_unit, category) VALUES
('Hidrogeno',1,1.008,-259.16,-252.87,-259.16,'°C',5),
('Helio',2,4.0026,-272.2,-268.93,null,'°C',7),
('Litio',3,6.94,180.5,1590,null,'°C',1),
('Berilio',4,9.0122,1287,2471,null,'°C',4),
('Boro',5,10.81,2076,4000,2076,'°C',2);

INSERT IGNORE INTO chemical_bondings (name, description) VALUES
('Enlace Ionico','Transferencia de electrones entre un metal y un no metal'),
('Enlace Covalente','Comparticion de electrones entre dos no metales (puede ser no polar o polar)'),
('Enlace Metalico','Comparticion de electrones entre atomos de metales en una "nube" de electrones');

INSERT IGNORE INTO chemical_properties (name, description) VALUES
('Acidos','Compuestos que liberan iones de hidrogeno (H+) o protones cuando se disuelven en agua, tienen un PH menor que 7, son corrosivos'),
('Bases','Compuestos que liberan iones de hidroxido (OH-) cuando se disuelven en agua, tienen un php mayor que 7'),
('Sales','Compuestos ionicos que suelen tener una estructura cristalina, se disuelven en agua para liberar iones positivos e iones negativos'),
('Oxidos','Compuestos que contienen oxigeno combinado con otro elemento, pueden ser de naturaleza acida o basica'),
('Hidruros','Compuestos formados por hidrogeno y otro elemento, suelen ser ionicos o covalentes'),
('Peroxidos','Compuestos que contienen el grupo -O-O- (un enlace entre dos atomos de oxigeno, son oxidantes y pueden liberar oxigeno de forma activa)'),
('Compuestos Organicos','Compuestos que contienen carbono y generalmente estan asociados con hidrogeno y a veces con oxigeno, nitrogeno y otros elementos, contienen enlaces covalentes');

INSERT IGNORE INTO compositions (name, description) VALUES
('Organicos','Compuestos que contienen carbono y generalmente hidrogeno'),
('Inorganicos','Compuestos que no contienen carbono, excepto algunas excepciones');

INSERT IGNORE INTO compounds (name, iupac_name, formula, molar_mass, composition, chemical_bonding, chemical_property) VALUES
('Cloruro de sodio','Sodium chloride','NaCl',58.44,2,1,3),
('Acido sulfurico','Sulfuric acid','H₂SO₄',98.08,2,2,1),
('Metano','Methane','CH₄',16.04,1,2,5),
('Oxido de calcio','Calcium oxide','CaO',56.08,2,1,4),
('Acido acético','Acetic acid','CH₃COOH',60.05,1,2,1);



