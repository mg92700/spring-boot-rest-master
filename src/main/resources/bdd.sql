
CREATE SCHEMA `meteo` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `meteo`.`user` (
  `id` INT NOT NULL,
  `nom` VARCHAR(100) NOT NULL ,
  `prenom` VARCHAR(100) NOT NULL ,
  `salaire` INT NOT NULL ,
  `adresse` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`iduser`));

INSERT INTO `meteo`.`user` (`id`,`nom`, `prenom`, `salaire`, `adresse`) VALUES (1,'bg', 'bg', 1, 'bg');

CREATE TABLE `meteo`.`meteo` (
  `id` INT NOT NULL,
  `probarain` VARCHAR(45) NOT NULL DEFAULT 'Probabilité de pluie entre 0 et 100%',
  `probafrost` VARCHAR(45) NOT NULL DEFAULT 'Probabilité de gel entre 0 et 100%',
  `probafog` VARCHAR(45) NOT NULL DEFAULT 'Probabilité de brouillard entre 0 et 100%',
  `probawind70` VARCHAR(45) NOT NULL DEFAULT 'Probabilité de vent >70 km/h entre 0 et 100%',
  `probawind100` VARCHAR(45) NOT NULL DEFAULT 'Probabilité de vent >100 km/h entre 0 et 100%',
  `tsoil1` VARCHAR(45) NOT NULL DEFAULT 'Température du sol entre 0 et 10 cm en °C',
  `temp2m` VARCHAR(45) NOT NULL DEFAULT 'Température à 2 mètres en °C',
  PRIMARY KEY (`id`));

