-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2025 a las 02:38:37
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `matriculadoss`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `codiMedi` int(11) NOT NULL,
  `ndniMedi` varchar(8) NOT NULL,
  `appaMedi` varchar(50) NOT NULL,
  `apmaMedi` varchar(50) NOT NULL,
  `nombMedi` varchar(50) NOT NULL,
  `fechNaciMedi` date NOT NULL,
  `logiMedi` varchar(100) NOT NULL,
  `passMedi` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`codiMedi`, `ndniMedi`, `appaMedi`, `apmaMedi`, `nombMedi`, `fechNaciMedi`, `logiMedi`, `passMedi`) VALUES
(1, '1234', 'santiago', 'ildefonso', 'ricky ', '2025-06-03', 'rickyyy', '$2a$12$k8GwbPIy3kPyjG936AMMbuEH56LpQK0BgE2SjAnYLZn/o5RSaUD2W'),
(2, '123', 'HOLA', 'HOLA', 'HOLA', '2025-05-29', 'hola', '$2a$12$H78/zl7SSjtPaNrQi5sYOOa.o4Z/AKDOIUUct/o/tX.dPtQrY4Uei'),
(3, '111', 'EDITADOO', 'EDITADOO', 'EDIT', '2025-06-05', 'holaaa', '$2a$12$UKpuTxk2dh8TCD1jAvxBFO5.tmkjFndUuYCn7RrNCRtLUE5loz2Xa'),
(4, '999', 'PRUEBA edit', 'PRUEBA edit', 'PRUEBA edit', '2025-06-13', 'PRUEBA edit', '$2a$12$f7hMp0tXjcYGXZviNI5uGe5d7Bc26jxnaIQzeQcga359lSbnlUkwS');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`codiMedi`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `codiMedi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
