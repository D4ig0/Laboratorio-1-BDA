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

--- Tabla institucion_log
CREATE TABLE IF NOT EXISTS desastresdb.public.institucion_log (
    id_institucion INT,

    nombre_nuevo VARCHAR(50),
    nombre_anterior VARCHAR(50),

    fecha_modificacion TIMESTAMP,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
); 
--- Trigger para la tabla institucion
CREATE OR REPLACE FUNCTION desastresdb.public.institucion_trigger_function()
RETURNS TRIGGER AS $institucion_trigger_function$
BEGIN
    IF(TG_OP = 'INSERT') THEN 
        INSERT INTO desastresdb.public.institucion_log (id_institucion, nombre_nuevo, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_institucion, NEW.nombre, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF(TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.institucion_log (id_institucion, nombre_nuevo, nombre_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_institucion, NEW.nombre, OLD.nombre, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF(TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.institucion_log (id_institucion, nombre_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (OLD.id_institucion, OLD.nombre, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$institucion_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER institucion_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.institucion
FOR EACH ROW EXECUTE PROCEDURE desastresdb.public.institucion_trigger_function();

--- Tabla emergencia_log
CREATE TABLE IF NOT EXISTS desastresdb.public.emergencia_log (
    id_emergencia INT,

    id_institucion_nuevo INT,
    id_institucion_anterior INT,

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

    fecha_modificacion TIMESTAMP,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
);

--- Trigger para la tabla emergencia
CREATE OR REPLACE FUNCTION desastresdb.public.emergencia_trigger_function()
RETURNS TRIGGER AS $emergencia_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.emergencia_log (id_emergencia, id_institucion_nuevo, nombre_nuevo, descripcion_nuevo, fecha_inicio_nuevo, fecha_termino_nuevo, activo_nuevo, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_emergencia, NEW.id_institucion, NEW.nombre, NEW.descripcion, NEW.fecha_inicio, NEW.fecha_termino, NEW.activo, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.emergencia_log (id_emergencia, id_institucion_nuevo, id_institucion_anterior, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_termino_nuevo, fecha_termino_anterior, activo_nuevo, activo_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_emergencia, NEW.id_institucion, OLD.id_institucion, NEW.nombre, OLD.nombre, NEW.descripcion, OLD.descripcion, NEW.fecha_inicio, OLD.fecha_inicio, NEW.fecha_termino, OLD.fecha_termino, NEW.activo, OLD.activo, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.emergencia_log (id_emergencia, id_institucion_anterior, nombre_anterior, descripcion_anterior, fecha_inicio_anterior, fecha_termino_anterior, activo_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (OLD.id_emergencia, OLD.id_institucion, OLD.nombre, OLD.descripcion, OLD.fecha_inicio, OLD.fecha_termino, OLD.activo, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$emergencia_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER emergencia_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.emergencia
FOR EACH ROW EXECUTE PROCEDURE desastresdb.public.emergencia_trigger_function();

--- Tabla tarea_log
CREATE TABLE IF NOT EXISTS desastresdb.public.tarea_log (
    id_tarea INT,

    id_emergencia_nuevo INT,
    id_emergencia_anterior INT,

    nombre_nuevo VARCHAR(50),
    nombre_anterior VARCHAR(50),

    descripcion_nuevo VARCHAR(200),
    descripcion_anterior VARCHAR(200),

    cant_vol_requeridos_nuevo INT,
    cant_vol_requeridos_anterior INT,

    cant_vol_inscritos_nuevo INT,
    cant_vol_inscritos_anterior INT,

    fecha_inicio_nuevo DATE,
    fecha_inicio_anterior DATE,

    fecha_fin_nuevo DATE,
    fecha_fin_anterior DATE,

    estado_actual_nuevo VARCHAR(50),
    estado_actual_anterior VARCHAR(50),

    fecha_modificacion TIMESTAMP,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
);

--- Trigger para la tabla tarea
CREATE OR REPLACE FUNCTION desastresdb.public.tarea_trigger_function()
RETURNS TRIGGER AS $tarea_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.tarea_log (id_tarea, id_emergencia_nuevo, nombre_nuevo, descripcion_nuevo, cant_vol_requeridos_nuevo, cant_vol_inscritos_nuevo, fecha_inicio_nuevo, fecha_fin_nuevo, estado_actual_nuevo, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_tarea, NEW.id_emergencia, NEW.nombre, NEW.descripcion, NEW.cant_vol_requeridos, NEW.cant_vol_inscritos, NEW.fecha_inicio, NEW.fecha_fin, NEW.estado_actual, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.tarea_log (id_tarea, id_emergencia_nuevo, id_emergencia_anterior, nombre_nuevo, nombre_anterior, descripcion_nuevo, descripcion_anterior, cant_vol_requeridos_nuevo, cant_vol_requeridos_anterior, cant_vol_inscritos_nuevo, cant_vol_inscritos_anterior, fecha_inicio_nuevo, fecha_inicio_anterior, fecha_fin_nuevo, fecha_fin_anterior, estado_actual_nuevo, estado_actual_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_tarea, NEW.id_emergencia, OLD.id_emergencia, NEW.nombre, OLD.nombre, NEW.descripcion, OLD.descripcion, NEW.cant_vol_requeridos, OLD.cant_vol_requeridos, NEW.cant_vol_inscritos, OLD.cant_vol_inscritos, NEW.fecha_inicio, OLD.fecha_inicio, NEW.fecha_fin, OLD.fecha_fin, NEW.estado_actual, OLD.estado_actual, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.tarea_log (id_tarea, id_emergencia_anterior, nombre_anterior, descripcion_anterior, cant_vol_requeridos_anterior, cant_vol_inscritos_anterior, fecha_inicio_anterior, fecha_fin_anterior, estado_actual_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (OLD.id_tarea, OLD.id_emergencia, OLD.nombre, OLD.descripcion, OLD.cant_vol_requeridos, OLD.cant_vol_inscritos, OLD.fecha_inicio, OLD.fecha_fin, OLD.estado_actual, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$tarea_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER tarea_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.tarea
FOR EACH ROW EXECUTE PROCEDURE desastresdb.public.tarea_trigger_function();

--- Tabla voluntario_log
CREATE TABLE IF NOT EXISTS desastresdb.public.voluntario_log (
    id_voluntario INT,

    nombre_nuevo VARCHAR(50),
    nombre_anterior VARCHAR(50),

    correo_nuevo VARCHAR(50),
    correo_anterior VARCHAR(50),

    password_nuevo VARCHAR(50),
    password_anterior VARCHAR(50),

    fecha_modificacion TIMESTAMP,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
);

--- Trigger para la tabla voluntario
CREATE OR REPLACE FUNCTION desastresdb.public.voluntario_trigger_function()
RETURNS TRIGGER AS $voluntario_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.voluntario_log (id_voluntario, nombre_nuevo, correo_nuevo, password_nuevo, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_voluntario, NEW.nombre, NEW.correo, NEW.password, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.voluntario_log (id_voluntario, nombre_nuevo, nombre_anterior, correo_nuevo, correo_anterior, password_nuevo, password_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_voluntario, NEW.nombre, OLD.nombre, NEW.correo, OLD.correo, NEW.password, OLD.password, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.voluntario_log (id_voluntario, nombre_anterior, correo_anterior, password_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (OLD.id_voluntario, OLD.nombre, OLD.correo, OLD.password, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$voluntario_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER voluntario_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.voluntario
FOR EACH ROW EXECUTE PROCEDURE desastresdb.public.voluntario_trigger_function();

--- Tabla ranking_log
CREATE TABLE IF NOT EXISTS desastresdb.public.ranking_log (
    id_ranking INT,

    id_voluntario_nuevo INT,
    id_voluntario_anterior INT,

    id_tarea_nuevo INT,
    id_tarea_anterior INT,

    puntaje_nuevo INT,
    puntaje_anterior INT,

    flg_invitado_nuevo BOOLEAN,
    flg_invitado_anterior BOOLEAN,

    flg_participa_nuevo BOOLEAN,
    flg_participa_anterior BOOLEAN,

    fecha_modificacion TIMESTAMP,
    usuario_modificador VARCHAR(50),
    accion VARCHAR(50)
);

--- Trigger para la tabla ranking
CREATE OR REPLACE FUNCTION desastresdb.public.ranking_trigger_function()
RETURNS TRIGGER AS $ranking_trigger_function$
BEGIN
    IF(TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.ranking_log (id_ranking, id_voluntario_nuevo, id_tarea_nuevo, puntaje_nuevo, flg_invitado_nuevo, flg_participa_nuevo, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_ranking, NEW.id_voluntario, NEW.id_tarea, NEW.puntaje, NEW.flg_invitado, NEW.flg_participa, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.ranking_log (id_ranking, id_voluntario_nuevo, id_voluntario_anterior, id_tarea_nuevo, id_tarea_anterior, puntaje_nuevo, puntaje_anterior, flg_invitado_nuevo, flg_invitado_anterior, flg_participa_nuevo, flg_participa_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (NEW.id_ranking, NEW.id_voluntario, OLD.id_voluntario, NEW.id_tarea, OLD.id_tarea, NEW.puntaje, OLD.puntaje, NEW.flg_invitado, OLD.flg_invitado, NEW.flg_participa, OLD.flg_participa, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.ranking_log (id_ranking, id_voluntario_anterior, id_tarea_anterior, puntaje_anterior, flg_invitado_anterior, flg_participa_anterior, fecha_modificacion, usuario_modificador, accion)
        VALUES (OLD.id_ranking, OLD.id_voluntario, OLD.id_tarea, OLD.puntaje, OLD.flg_invitado, OLD.flg_participa, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$ranking_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER ranking_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.ranking
FOR EACH ROW EXECUTE PROCEDURE desastresdb.public.ranking_trigger_function();

--- Tabla trigger para la tabla habilidad

CREATE TABLE IF NOT EXISTS desastresdb.public.habilidad_log (
    id_habilidad INTEGER,
    descripcion_nuevo VARCHAR(100),
    descripcion_anterior VARCHAR(100),
    fecha TIMESTAMP,
    usuario_modificador VARCHAR(100),
    accion VARCHAR(100)
);

--- Trigger para la tabla habilidad

CREATE OR REPLACE FUNCTION desastresdb.public.habilidad_trigger_function()
RETURNS TRIGGER AS $habilidad_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.habilidad_log(id_habilidad, descripcion_nuevo, descripcion_anterior, fecha, usuario_modificador, accion)
        VALUES (NEW.id_habilidad, NEW.descripcion, NULL, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.habilidad_log(id_habilidad, descripcion_nuevo, descripcion_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_habilidad, NEW.descripcion, OLD.descripcion, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.habilidad_log(id_habilidad, descripcion_nuevo, descripcion_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_habilidad, NULL, OLD.descripcion, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$habilidad_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER habilidad_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.habilidad_trigger_function();


-- Tabla trigger para la tabla tarea_habilidad

CREATE TABLE IF NOT EXISTS desastresdb.public.tarea_habilidad_log (
    id_tarea_habilidad INTEGER,
    
    id_tarea_anterior INTEGER,
    id_tarea_nuevo INTEGER,

    id_habilidad_anterior INTEGER,
    id_habilidad_nuevo INTEGER,
    
    fecha TIMESTAMP,
    usuario_modificador VARCHAR(100),
    accion VARCHAR(100)

);

--- Trigger para la tabla tarea_habilidad

CREATE OR REPLACE FUNCTION desastresdb.public.tarea_habilidad_trigger_function()
RETURNS TRIGGER AS $tarea_habilidad_trigger_function$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.tarea_habilidad_log(id_tarea_habilidad, id_tarea_anterior, id_tarea_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (NEW.id_tarea_habilidad, NULL, NEW.id_tarea, NULL, NEW.id_habilidad, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.tarea_habilidad_log(id_tarea_habilidad, id_tarea_anterior, id_tarea_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (OLD.id_tarea_habilidad, OLD.id_tarea, NEW.id_tarea, OLD.id_habilidad, NEW.id_habilidad, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.tarea_habilidad_log(id_tarea_habilidad, id_tarea_anterior, id_tarea_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (OLD.id_tarea_habilidad, OLD.id_tarea, NULL, OLD.id_habilidad, NULL, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$tarea_habilidad_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER tarea_habilidad_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.tarea_habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.tarea_habilidad_trigger_function();


--- Tabla trigger para la tabla estado_tarea
CREATE TABLE IF NOT EXISTS desastresdb.public.estado_tarea_log (
    id_estado_tarea INTEGER,
    descripcion_nuevo VARCHAR(100),
    descripcion_anterior VARCHAR(100),
    fecha TIMESTAMP,
    usuario_modificador VARCHAR(100),
    accion VARCHAR(100)
);

--- Trigger para la tabla estado_tarea
CREATE OR REPLACE FUNCTION desastresdb.public.estado_tarea_trigger_function()
RETURNS TRIGGER AS $estado_tarea_trigger_function$
BEGIN
    IF(TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.estado_tarea_log(id_estado_tarea, descripcion_nuevo, descripcion_anterior, fecha, usuario_modificador, accion)
        VALUES (NEW.id_estado_tarea, NEW.descripcion, NULL, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF(TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.estado_tarea_log(id_estado_tarea, descripcion_nuevo, descripcion_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_estado_tarea, NEW.descripcion, OLD.descripcion, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF(TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.estado_tarea_log(id_estado_tarea, descripcion_nuevo, descripcion_anterior, fecha, usuario_modificador, accion)
        VALUES (OLD.id_estado_tarea, NULL, OLD.descripcion, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$estado_tarea_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER estado_tarea_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.estado_tarea
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.estado_tarea_trigger_function();


--- Tabla trigger para la vol_habilidad
CREATE TABLE IF NOT EXISTS desastresdb.public.vol_habilidad_log (
    id_vol_habilidad INTEGER,

    id_voluntario_anterior INTEGER,
    id_voluntario_nuevo INTEGER,

    id_habilidad_anterior INTEGER,
    id_habilidad_nuevo INTEGER,

    fecha TIMESTAMP,
    usuario_modificador VARCHAR(100),
    accion VARCHAR(100)
);

--- Trigger para la tabla vol_habilidad
CREATE OR REPLACE FUNCTION desastresdb.public.vol_habilidad_trigger_function()
RETURNS TRIGGER AS $vol_habilidad_trigger_function$
BEGIN
    IF(TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.vol_habilidad_log(id_vol_habilidad, id_voluntario_anterior, id_voluntario_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (NEW.id_vol_habilidad, NULL, NEW.id_voluntario, NULL, NEW.id_habilidad, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF(TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.vol_habilidad_log(id_vol_habilidad, id_voluntario_anterior, id_voluntario_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (OLD.id_vol_habilidad, OLD.id_voluntario, NEW.id_voluntario, OLD.id_habilidad, NEW.id_habilidad, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF(TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.vol_habilidad_log(id_vol_habilidad, id_voluntario_anterior, id_voluntario_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (OLD.id_vol_habilidad, OLD.id_voluntario, NULL, OLD.id_habilidad, NULL, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$vol_habilidad_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER vol_habilidad_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.vol_habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.vol_habilidad_trigger_function();

--- Tabla trigger para la tabla eme_habilidad
CREATE TABLE IF NOT EXISTS desastresdb.public.eme_habilidad_log (
    id_eme_habilidad INTEGER,

    id_emergencia_anterior INTEGER,
    id_emergencia_nuevo INTEGER,

    id_habilidad_anterior INTEGER,
    id_habilidad_nuevo INTEGER,

    fecha TIMESTAMP,
    usuario_modificador VARCHAR(100),
    accion VARCHAR(100)
);

--- Trigger para la tabla eme_habilidad
CREATE OR REPLACE FUNCTION desastresdb.public.eme_habilidad_trigger_function()
RETURNS TRIGGER AS $eme_habilidad_trigger_function$
BEGIN
    IF(TG_OP = 'INSERT') THEN
        INSERT INTO desastresdb.public.eme_habilidad_log(id_eme_habilidad, id_emergencia_anterior, id_emergencia_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (NEW.id_eme_habilidad, NULL, NEW.id_emergencia, NULL, NEW.id_habilidad, NOW(), CURRENT_USER, 'CREATE');
        RETURN NEW;
    ELSIF(TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.eme_habilidad_log(id_eme_habilidad, id_emergencia_anterior, id_emergencia_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (OLD.id_eme_habilidad, OLD.id_emergencia, NEW.id_emergencia, OLD.id_habilidad, NEW.id_habilidad, NOW(), CURRENT_USER, 'UPDATE');
        RETURN NEW;
    ELSIF(TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.eme_habilidad_log(id_eme_habilidad, id_emergencia_anterior, id_emergencia_nuevo, id_habilidad_anterior, id_habilidad_nuevo, fecha, usuario_modificador, accion)
        VALUES (OLD.id_eme_habilidad, OLD.id_emergencia, NULL, OLD.id_habilidad, NULL, NOW(), CURRENT_USER, 'DELETE');
        RETURN OLD;
    END IF;
END;
$eme_habilidad_trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER eme_habilidad_trigger_function_trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.eme_habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.eme_habilidad_trigger_function();

----------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS desastresdb.public.temp_table (
	id_user varchar(50),
	cantidad integer,
	accion varchar(50)
);

CREATE OR REPLACE PROCEDURE contar_accion(p_accion VARCHAR) LANGUAGE plpgsql AS $$

DECLARE
    c_list CURSOR FOR
SELECT usuario_modificador, COUNT(usuario_modificador) AS num 
FROM (
    SELECT usuario_modificador FROM desastresdb.public.institucion_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.emergencia_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.tarea_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.voluntario_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.ranking_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.habilidad_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.tarea_habilidad_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.estado_tarea_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.vol_habilidad_log WHERE accion = p_accion
    UNION ALL
    SELECT usuario_modificador FROM desastresdb.public.eme_habilidad_log WHERE accion = p_accion
    ) AS all_tables
GROUP BY usuario_modificador
ORDER BY num DESC;
    
    
BEGIN
	DELETE from temp_table;
	FOR rec_list IN c_list LOOP
			insert into desastresdb.public.temp_table values (rec_list.usuario_modificador, rec_list.num, p_accion);
	END LOOP;
END;
$$;	

--- call contar_accion('CREATE');
--- call contar_accion('UPDATE');
--- call contar_accion('DELETE');