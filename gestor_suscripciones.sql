-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-09-2025 a las 08:45:46
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
-- Base de datos: `gestor_suscripciones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

CREATE TABLE `suscripcion` (
  `id` bigint(20) NOT NULL,
  `Servicio` varchar(100) NOT NULL,
  `Tipo_plan` varchar(50) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `Duracion_meses` int(11) NOT NULL,
  `Pecio` decimal(10,2) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `tipo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `suscripcion`
--

INSERT INTO `suscripcion` (`id`, `Servicio`, `Tipo_plan`, `estado`, `Duracion_meses`, `Pecio`, `nombre`, `precio`, `tipo`) VALUES
(1, 'Netflix', 'Estándart', 'Activo', 1, 10.99, '', 0.00, ''),
(2, 'Spotify', 'Premium', 'Activo', 1, 6.49, '', 0.00, ''),
(3, 'Prime video', 'Membresia', 'Activo', 1, 5.99, '', 0.00, ''),
(4, 'Disney+', 'Premium', 'Activo', 1, 15.99, '', 0.00, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripciones_usuarios`
--

CREATE TABLE `suscripciones_usuarios` (
  `id` bigint(20) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `id_suscripcion` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `suscripciones_usuarios`
--

INSERT INTO `suscripciones_usuarios` (`id`, `estado`, `fecha_fin`, `fecha_inicio`, `id_suscripcion`, `id_usuario`) VALUES
(1, 'ACTIVA', '2025-10-23', '2025-09-23', 3, 1),
(2, 'ACTIVA', '2025-09-26', '2025-08-26', 1, 2),
(3, 'ACTIVA', '2025-07-10', '2025-06-10', 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fechanac` date DEFAULT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `apellido`, `email`, `fechanac`, `nombre`) VALUES
(1, 'Fuentes', 'Leviacker98@gmail.com', '1998-05-10', 'Levi'),
(2, 'Gomez', 'sofia.gomez@gmail.com', '2000-03-10', 'Sofia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID` int(11) NOT NULL,
  `Nombre` varchar(254) NOT NULL,
  `Apellido` varchar(254) NOT NULL,
  `Fecha_nac` date NOT NULL,
  `Email` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID`, `Nombre`, `Apellido`, `Fecha_nac`, `Email`) VALUES
(1, 'Juan', 'Lopez', '1999-07-10', 'juanloop34@gmail.com'),
(2, 'Ricardo', 'Palacios', '2002-01-22', 'palacios2201@gmail.com'),
(3, 'Levi', 'Fuentes', '1998-12-25', 'Leviacker98@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `suscripciones_usuarios`
--
ALTER TABLE `suscripciones_usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr884notgeanu7v6fvscnkif01` (`id_usuario`),
  ADD KEY `FKcwhee3c6wytyxqn162x16dxnj` (`id_suscripcion`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `suscripciones_usuarios`
--
ALTER TABLE `suscripciones_usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `suscripciones_usuarios`
--
ALTER TABLE `suscripciones_usuarios`
  ADD CONSTRAINT `FKcwhee3c6wytyxqn162x16dxnj` FOREIGN KEY (`id_suscripcion`) REFERENCES `suscripcion` (`id`),
  ADD CONSTRAINT `FKr884notgeanu7v6fvscnkif01` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
