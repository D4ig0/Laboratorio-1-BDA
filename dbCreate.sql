DROP DATABASE IF EXISTS desastresdb;

-- Crear database
CREATE DATABASE desastresdb
	WITH
	OWNER = postgres
	ENCODING = 'UTF8'
	TABLESPACE = pg_default
	CONNECTION LIMIT = -1
	IS_TEMPLATE = False;

COMMENT ON DATABASE desastresdb 
	IS 'laboratorio 1 - base de datos avanzadas';

--- Escribir el siguiente comando en el SQL Shell, para utilizar la base de datos creada:
---  \c desastresdb
--- O ingresar una query en pgAdmin para la base de datos desastresdb

CREATE TABLE IF NOT EXISTS desastresdb.public.institucion (
    id_institucion SERIAL NOT NULL,
    nombre VARCHAR(50),
    PRIMARY KEY (id_institucion)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.emergencia (
    id_emergencia SERIAL NOT NULL,
    id_institucion INT,
    nombre VARCHAR(50),
    descripcion VARCHAR(200),
    fecha_inicio DATE,
    fecha_termino DATE,
    activo BOOLEAN,
    PRIMARY KEY (id_emergencia),
    FOREIGN KEY (id_institucion) REFERENCES desastresdb.public.institucion(id_institucion)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.tarea(
    id_tarea SERIAL NOT NULL,
    id_emergencia INT,
    nombre VARCHAR(50),
    descripcion VARCHAR(200),
    cant_vol_requeridos INT,
    cant_vol_inscritos INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado_actual VARCHAR(100),
    PRIMARY KEY (id_tarea),
    FOREIGN KEY (id_emergencia) REFERENCES desastresdb.public.emergencia(id_emergencia)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.voluntario (
    id_voluntario SERIAL NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_voluntario)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.ranking (
    id_ranking SERIAL NOT NULL,
    id_voluntario INT,
    id_tarea INT,
    puntaje INT,
    flg_invitado BOOLEAN,
    flg_participa BOOLEAN,
    PRIMARY KEY (id_ranking),
    FOREIGN KEY (id_voluntario) REFERENCES desastresdb.public.voluntario(id_voluntario),
    FOREIGN KEY (id_tarea) REFERENCES desastresdb.public.tarea(id_tarea)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.habilidad (
    id_habilidad SERIAL NOT NULL,
    descripcion VARCHAR(200),
    PRIMARY KEY(id_habilidad)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.tarea_habilidad (
    id_tarea_habilidad SERIAL NOT NULL,
    id_emergencia INT,
    id_habilidad INT,
    id_tarea INT,
    PRIMARY KEY(id_tarea_habilidad),
    FOREIGN KEY (id_emergencia) REFERENCES desastresdb.public.emergencia(id_emergencia),
    FOREIGN KEY (id_habilidad) REFERENCES desastresdb.public.habilidad(id_habilidad),
    FOREIGN KEY (id_tarea) REFERENCES desastresdb.public.tarea(id_tarea)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.estado_tarea (
    id_estado_tarea SERIAL NOT NULL,
    descripcion VARCHAR(200),
    PRIMARY KEY(id_estado_tarea)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.vol_habilidad(
    id_vol_habilidad SERIAL NOT NULL,
    id_voluntario INT,
    id_habilidad INT,
    PRIMARY KEY(id_vol_habilidad),
    FOREIGN KEY (id_voluntario) REFERENCES desastresdb.public.voluntario(id_voluntario),
    FOREIGN KEY (id_habilidad) REFERENCES desastresdb.public.habilidad(id_habilidad)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.eme_habilidad(
    id_eme_habilidad SERIAL NOT NULL,
    id_emergencia INT,
    id_habilidad INT,
    PRIMARY KEY(id_eme_habilidad),
    FOREIGN KEY (id_emergencia) REFERENCES desastresdb.public.emergencia(id_emergencia),
    FOREIGN KEY (id_habilidad) REFERENCES desastresdb.public.habilidad(id_habilidad)
);

CREATE EXTENSION pgcrypto;

--- Creacion de tablas para almacenar datos obtenidos por los triggers

CREATE TABLE IF NOT EXISTS desastresdb.public.institucion_trigger (
    id_institucion INT,
    nombre_nuevo VARCHAR(50),
    nombre VARCHAR(50),
    nombre_anterior VARCHAR(50),
    fecha DATE,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50),
    FOREIGN KEY (id_institucion) REFERENCES desastresdb.public.institucion(id_institucion)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.emergencia_trigger (
    id_emergencia INT,
    id_institucion_nuevo INT,
    id_institucion INT,
    id_institucion_anterior INT,
    nombre_nuevo VARCHAR(50),
    nombre VARCHAR(50),
    nombre_anterior VARCHAR(50),
    descripcion_nuevo VARCHAR(200),
    descripcion VARCHAR(200),
    descripcion_anterior VARCHAR(200),
    fecha_inicio_nuevo DATE,
    fecha_inicio DATE,
    fecha_inicio_anterior DATE,
    fecha_termino_nuevo DATE,
    fecha_termino DATE,
    fecha_termino_anterior DATE,
    activo_nuevo BOOLEAN,
    activo BOOLEAN,
    activo_anterior BOOLEAN,
    fecha DATE,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50),
    FOREIGN KEY (id_emergencia) REFERENCES desastresdb.public.emergencia(id_emergencia),
    FOREIGN KEY (id_institucion) REFERENCES desastresdb.public.institucion(id_institucion),
    FOREIGN KEY (id_institucion_nuevo) REFERENCES desastresdb.public.institucion(id_institucion),
    FOREIGN KEY (id_institucion_anterior) REFERENCES desastresdb.public.institucion(id_institucion)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.tarea_trigger (
    id_tarea INT,
    id_emergencia_nuevo INT,
    id_emergencia INT,
    id_emergencia_anterior INT,
    nombre_nuevo VARCHAR(50),
    nombre VARCHAR(50),
    nombre_anterior VARCHAR(50),
    descripcion_nuevo VARCHAR(200),
    descripcion VARCHAR(200),
    descripcion_anterior VARCHAR(200),
    cant_vol_requeridos_nuevo INT,
    cant_vol_requeridos INT,
    cant_vol_requeridos_anterior INT,
    cant_vol_inscritos_nuevo INT,
    cant_vol_inscritos INT,
    cant_vol_inscritos_anterior INT,
    fecha_inicio_nuevo DATE,
    fecha_inicio DATE,
    fecha_inicio_anterior DATE,
    fecha_fin_nuevo DATE,
    fecha_fin DATE,
    fecha_fin_anterior DATE,
    estado_actual_nuevo VARCHAR(100),
    estado_actual VARCHAR(100),
    estado_actual_anterior VARCHAR(100),
    FOREIGN KEY (id_tarea) REFERENCES desastresdb.public.tarea(id_tarea),
    FOREIGN KEY (id_emergencia_nuevo) REFERENCES desastresdb.public.emergencia(id_emergencia),
    FOREIGN KEY (id_emergencia) REFERENCES desastresdb.public.emergencia(id_emergencia),
    FOREIGN KEY (id_emergencia_anterior) REFERENCES desastresdb.public.emergencia(id_emergencia)   
)

CREATE TABLE IF NOT EXISTS desastresdb.public.voluntario_trigger (
    id_voluntario INT,
    id_institucion_nuevo INT,
    id_institucion INT,
    id_institucion_anterior INT,
    nombre_nuevo VARCHAR(50),
    nombre VARCHAR(50),
    nombre_anterior VARCHAR(50),
    apellido_nuevo VARCHAR(50),
    apellido VARCHAR(50),
    apellido_anterior VARCHAR(50),
    rut_nuevo VARCHAR(50),
    rut VARCHAR(50),
    rut_anterior VARCHAR(50),
    fecha_nacimiento_nuevo DATE,
    fecha_nacimiento DATE,
    fecha_nacimiento_anterior DATE,
    correo_nuevo VARCHAR(50),
    correo VARCHAR(50),
    correo_anterior VARCHAR(50),
    telefono_nuevo VARCHAR(50),
    telefono VARCHAR(50),
    telefono_anterior VARCHAR(50),
    direccion_nuevo VARCHAR(50),
    direccion VARCHAR(50),
    direccion_anterior VARCHAR(50),
    comuna_nuevo VARCHAR(50),
    comuna VARCHAR(50),
    comuna_anterior VARCHAR(50),
    ciudad_nuevo VARCHAR(50),
    ciudad VARCHAR(50),
    ciudad_anterior VARCHAR(50),
    activo_nuevo BOOLEAN,
    activo BOOLEAN,
    activo_anterior BOOLEAN,
    fecha DATE,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50),
    FOREIGN KEY (id_voluntario) REFERENCES desastresdb.public.voluntario(id_voluntario),
    FOREIGN KEY (id_institucion) REFERENCES desastresdb.public.institucion(id_institucion),
    FOREIGN KEY (id_institucion_nuevo) REFERENCES desastresdb.public.institucion(id_institucion),
    FOREIGN KEY (id_institucion_anterior) REFERENCES desastresdb.public.institucion(id_institucion)
);

CREATE TABLE IF NOT EXISTS desastresdb.public.voluntario_habilidad_trigger (
    id_voluntario_habilidad INT,
    id_voluntario_nuevo INT,
    id_voluntario INT,
    id_voluntario_anterior INT,
    id_habilidad_nuevo INT,
    id_habilidad INT,
    id_habilidad_anterior INT,
    FOREIGN KEY (id_voluntario_habilidad) REFERENCES desastresdb.public.voluntario_habilidad(id_voluntario_habilidad),
    FOREIGN KEY (id_voluntario) REFERENCES desastresdb.public.voluntario(id_voluntario),
    FOREIGN KEY (id_voluntario_nuevo) REFERENCES desastresdb.public.voluntario(id_voluntario),
    FOREIGN KEY (id_voluntario_anterior) REFERENCES desastresdb.public.voluntario(id_voluntario),
    FOREIGN KEY (id_habilidad) REFERENCES desastresdb.public.habilidad(id_habilidad),
    FOREIGN KEY (id_habilidad_nuevo) REFERENCES desastresdb.public.habilidad(id_habilidad),
    FOREIGN KEY (id_habilidad_anterior) REFERENCES desastresdb.public.habilidad(id_habilidad)
);