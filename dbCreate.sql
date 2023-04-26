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
    nombre VARCHAR(50),
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
