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
    password VARCHAR(80) NOT NULL,
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

---------------------------------------------------------------------------------------------------
-- TRIGGERS

CREATE TABLE IF NOT EXISTS desastresdb.public.logs (
    id_log SERIAL NOT NULL,
    tabla VARCHAR(50),

    datos_anteriores TEXT,
    datos_nuevos TEXT,
   
    fecha_modificacion TIMESTAMP,
    usuario_modificador VARCHAR(50),
     accion VARCHAR(10),
    PRIMARY KEY(id_log)
);

CREATE OR REPLACE FUNCTION desastresdb.public.trigger_function()
RETURNS TRIGGER AS $trigger_function$
BEGIN
    IF(TG_OP = 'INSERT') THEN 
        INSERT INTO desastresdb.public.logs (tabla, datos_nuevos, fecha_modificacion, usuario_modificador, accion)
        VALUES (TG_TABLE_NAME, NEW, NOW(), current_setting('app.user.id'), 'CREATE');
        RETURN NEW;
    ELSIF(TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.logs (tabla, datos_nuevos, datos_anteriores, fecha_modificacion, usuario_modificador, accion)
        VALUES (TG_TABLE_NAME, NEW, OLD, NOW(), current_setting('app.user.id'), 'UPDATE');
        RETURN NEW;
    ELSIF(TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.logs (tabla, datos_anteriores, fecha_modificacion, usuario_modificador, accion)
        VALUES (TG_TABLE_NAME, OLD, NOW(), current_setting('app.user.id'), 'DELETE');
        RETURN OLD;
    END IF;
END;
$trigger_function$ LANGUAGE plpgsql;

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.institucion
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.emergencia
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.tarea
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER UPDATE OR DELETE ON desastresdb.public.voluntario
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.ranking
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.tarea_habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.estado_tarea
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.vol_habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();

CREATE TRIGGER trigger
AFTER INSERT OR UPDATE OR DELETE ON desastresdb.public.eme_habilidad
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_function();


CREATE OR REPLACE FUNCTION desastresdb.public.trigger_new_voluntario()
RETURNS TRIGGER AS $trigger_nvoluntario$

BEGIN
    INSERT INTO desastresdb.public.logs (tabla, datos_nuevos, fecha_modificacion, usuario_modificador, accion)
    VALUES (TG_TABLE_NAME, NEW, NOW(), NULL, 'CREATE');
    RETURN NEW;
END;
$trigger_nvoluntario$ LANGUAGE plpgsql;

CREATE TRIGGER new_voluntario
AFTER INSERT ON desastresdb.public.voluntario
FOR EACH ROW EXECUTE FUNCTION desastresdb.public.trigger_new_voluntario();


----------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS desastresdb.public.temp_table (
	id_user varchar(50),
	cantidad integer,
	accion varchar(50)
);

CREATE OR REPLACE PROCEDURE contar_accion(p_accion VARCHAR) LANGUAGE plpgsql AS $$
    
BEGIN
    DELETE FROM desastresdb.public.temp_table;
	INSERT INTO desastresdb.public.temp_table
    SELECT usuario_modificador, COUNT (accion), p_accion
    FROM desastresdb.public.logs
    WHERE accion = p_accion
    GROUP BY usuario_modificador
    ORDER BY usuario_modificador ASC;
END;
$$;	

--- call contar_accion('CREATE');
--- call contar_accion('UPDATE');
--- call contar_accion('DELETE');

----------------------------------------------------------------------------------------------------
CREATE FUNCTION total_tareas_activas_por_emergencia(id_e int) RETURNS int AS $$
BEGIN
	RETURN(
		SELECT count(ta) FROM tarea ta WHERE ta.id_emergencia = id_e and ta.estado_actual LIKE 'Activo'
		);
END;
$$ LANGUAGE plpgsql;
-----EN UNA QUERY DENTO DE LA DATA BASE de desastresdb---------------------------------------------------------------------------
CREATE EXTENSION postgis;

SELECT AddGeometryColumn('', 'emergencia', 'ubicacion', 4326, 'POINT', 2);
SELECT AddGeometryColumn('', 'voluntario', 'ubicacion', 4326, 'POINT', 2);