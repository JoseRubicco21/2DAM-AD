-- Script de inicialización para MySQL - Bolechas
-- =========================================

-- Crear la base de datos de bolechas
CREATE DATABASE IF NOT EXISTS bolechas_JoseRF
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

-- Crear usuario de acceso remoto
CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';

-- Conceder permisos sobre la base de datos
GRANT ALL PRIVILEGES ON bolechas_JoseRF.* TO 'usuario'@'%';

-- Activar los cambios
FLUSH PRIVILEGES;