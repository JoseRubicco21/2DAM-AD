-- Script de inicialización para MySQL - Bolechas
-- =========================================

-- Crear la base de datos de bolechas
CREATE DATABASE IF NOT EXISTS 2dam
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;


-- Also ensure the usuario user exists as specified
CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';
GRANT ALL PRIVILEGES ON 2dam.* TO 'usuario'@'%';

-- Activate the changes
FLUSH PRIVILEGES;