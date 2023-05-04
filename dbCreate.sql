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

--- Tabla institucion_trigger_table
CREATE TABLE IF NOT EXISTS desastresdb.public.institucion_trigger_table (
    id_institucion INT,

    nombre_nuevo VARCHAR(50),
    nombre_anterior VARCHAR(50),

    fecha DATE,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
); 
--- Trigger para la tabla institucion
CREATE OR REPLACE FUNCTION desastresdb.public.institucion_trigger_function()
RETURNS TRIGGER AS $institucion_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.institucion_trigger_table(id_institucion, nombre_nuevo, nombre_anterior, fecha, usuario_modificador, accion)
        VALUES (NEW.id_institucion, NEW.nombre, NULL, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.institucion_trigger_table(id_institucion, nombre_nuevo, nombre_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_institucion, NEW.nombre, OLD.nombre, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.institucion_trigger_table(id_institucion, nombre_nuevo, nombre_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_institucion, NULL, OLD.nombre, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$institucion_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER institucion_trigger_function_trigger 
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.institucion
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.institucion_trigger_function();

--- Tabla emergencia_trigger_table
CREATE TABLE IF NOT EXISTS desastresdb.public.emergencia_trigger_table (
    id_emergencia INT,
    id_institucion INT,

    nombre_nuevo VARCHAR(50),
    nombre_anterior VARCHAR(50),

    descripcion_nuevo VARCHAR(200),
    descripcion_anterior VARCHAR(200),

    fecha_inicio_nuevo DATE,
    fecha_inicio_anterior DATE,

    fecha_termino_nuevo DATE,
    fecha_termino_anterior DATE,

    activo_nuevo BOOLEAN,
    activo_anterior BOOLEAN,

    fecha DATE,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
);

--- Trigger para la tabla emergencia
CREATE OR REPLACE FUNCTION desastresdb.public.emergencia_trigger_function()
RETURNS TRIGGER AS $emergencia_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.emergencia_trigger_table(id_emergencia, id_institucion, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, activo_nuevo, activo_anterior, fecha, usuario_modificador, accion)
        VALUES (NEW.id_emergencia, NEW.id_institucion, NEW.nombre, NULL, NEW.descripcion, NULL, NEW.fecha_inicio, NULL, NEW.fecha_termino, NULL, NEW.activo, NULL, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.emergencia_trigger_table(id_emergencia, id_institucion, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, activo_nuevo, activo_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_emergencia, NEW.id_institucion, NEW.nombre, OLD.nombre, NEW.descripcion, OLD.descripcion, NEW.fecha_inicio, OLD.fecha_inicio, NEW.fecha_termino, OLD.fecha_termino, NEW.activo, OLD.activo, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.emergencia_trigger_table(id_emergencia, id_institucion, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, activo_nuevo, activo_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_emergencia, OLD.id_institucion, NULL, OLD.nombre, NULL, OLD.descripcion, NULL, OLD.fecha_inicio, NULL, OLD.fecha_termino, NULL, OLD.activo, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$emergencia_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER emergencia_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.emergencia
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.emergencia_trigger_function();

--- Trigger para la tabla tarea
CREATE OR REPLACE FUNCTION desastresdb.public.tarea_trigger_function()
RETURNS TRIGGER AS $tarea_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.tarea_trigger_table(id_tarea, id_emergencia, id_estado_tarea, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, fecha_limite_nuevo, fecha_limite_anterior, fecha, usuario_modificador, accion)
        VALUES (NEW.id_tarea, NEW.id_emergencia, NEW.id_estado_tarea, NEW.nombre, NULL, NEW.descripcion, NULL, NEW.fecha_inicio, NULL, NEW.fecha_termino, NULL, NEW.fecha_limite, NULL, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.tarea_trigger_table(id_tarea, id_emergencia, id_estado_tarea, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, fecha_limite_nuevo, fecha_limite_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_tarea, NEW.id_emergencia, NEW.id_estado_tarea, NEW.nombre, OLD.nombre, NEW.descripcion, OLD.descripcion, NEW.fecha_inicio, OLD.fecha_inicio, NEW.fecha_termino, OLD.fecha_termino, NEW.fecha_limite, OLD.fecha_limite, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.tarea_trigger_table(id_tarea, id_emergencia, id_estado_tarea, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, fecha_limite_nuevo, fecha_limite_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_tarea, OLD.id_emergencia, OLD.id_estado_tarea, NULL, OLD.nombre, NULL, OLD.descripcion, NULL, OLD.fecha_inicio, NULL, OLD.fecha_termino, NULL, OLD.fecha_limite, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$tarea_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER tarea_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.tarea
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.tarea_trigger_function();

--- Trigger para la tabla voluntario
CREATE OR REPLACE FUNCTION desastresdb.public.voluntario_trigger_function()
RETURNS TRIGGER AS $voluntario_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.voluntario_trigger_table(id_voluntario, id_institucion, id_estado_voluntario, nombre_nuevo, nombre_anterior, apellido_paterno_nuevo, apellido_paterno_anterior, apellido_materno_nuevo, apellido_materno_anterior, fecha_nacimiento_nuevo, fecha_nacimiento_anterior, fecha_ingreso_nuevo, fecha_ingreso_anterior, fecha_salida_nuevo, fecha_salida_anterior, fecha, usuario_modificador, accion)
        VALUES (NEW.id_voluntario, NEW.id_institucion, NEW.id_estado_voluntario, NEW.nombre, NULL, NEW.apellido_paterno, NULL, NEW.apellido_materno, NULL, NEW.fecha_nacimiento, NULL, NEW.fecha_ingreso, NULL, NEW.fecha_salida, NULL, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.voluntario_trigger_table(id_voluntario, id_institucion, id_estado_voluntario, nombre_nuevo, nombre_anterior, apellido_paterno_nuevo, apellido_paterno_anterior, apellido_materno_nuevo, apellido_materno_anterior, fecha_nacimiento_nuevo, fecha_nacimiento_anterior, fecha_ingreso_nuevo, fecha_ingreso_anterior, fecha_salida_nuevo, fecha_salida_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_voluntario, NEW.id_institucion, NEW.id_estado_voluntario, NEW.nombre, OLD.nombre, NEW.apellido_paterno, OLD.apellido_paterno, NEW.apellido_materno, OLD.apellido_materno, NEW.fecha_nacimiento, OLD.fecha_nacimiento, NEW.fecha_ingreso, OLD.fecha_ingreso, NEW.fecha_salida, OLD.fecha_salida, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.voluntario_trigger_table(id_voluntario, id_institucion, id_estado_voluntario, nombre_nuevo, nombre_anterior, apellido_paterno_nuevo, apellido_paterno_anterior, apellido_materno_nuevo, apellido_materno_anterior, fecha_nacimiento_nuevo, fecha_nacimiento_anterior, fecha_ingreso_nuevo, fecha_ingreso_anterior, fecha_salida_nuevo, fecha_salida_anterior, fecha, usuario_modificador, accion)
        VALUES(OLD.id_voluntario, OLD.id_institucion, OLD.id_estado_voluntario, NULL, OLD.nombre, NULL, OLD.apellido_paterno, NULL, OLD.apellido_materno, NULL, OLD.fecha_nacimiento, NULL, OLD.fecha_ingreso, NULL, OLD.fecha_salida, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$voluntario_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER voluntario_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.voluntario
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.voluntario_trigger_function();
