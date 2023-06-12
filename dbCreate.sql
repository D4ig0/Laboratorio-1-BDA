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
    id_habilidad INT,
    id_tarea INT,
    PRIMARY KEY(id_tarea_habilidad),
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
DECLARE
    user_id TEXT;
BEGIN
    BEGIN
        SELECT current_setting('app.user.id') INTO user_id;
    EXCEPTION
        WHEN undefined_object THEN
            user_id := 'ADMIN';
    END;

    IF(TG_OP = 'INSERT') THEN 
        INSERT INTO desastresdb.public.logs (tabla, datos_nuevos, fecha_modificacion, usuario_modificador, accion)
        VALUES (TG_TABLE_NAME, NEW, NOW(), user_id, 'CREATE');
        RETURN NEW;
    ELSIF(TG_OP = 'UPDATE') THEN
        INSERT INTO desastresdb.public.logs (tabla, datos_nuevos, datos_anteriores, fecha_modificacion, usuario_modificador, accion)
        VALUES (TG_TABLE_NAME, NEW, OLD, NOW(), user_id, 'UPDATE');
        RETURN NEW;
    ELSIF(TG_OP = 'DELETE') THEN
        INSERT INTO desastresdb.public.logs (tabla, datos_anteriores, fecha_modificacion, usuario_modificador, accion)
        VALUES (TG_TABLE_NAME, OLD, NOW(), user_id, 'DELETE');
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
-----EN UNA QUERY DENTO DE LA DATA BASE de desastresdb-----------------------------------------------------------------------
CREATE EXTENSION postgis;

SELECT AddGeometryColumn('', 'emergencia', 'ubicacion', 4326, 'POINT', 2);
SELECT AddGeometryColumn('', 'voluntario', 'ubicacion', 4326, 'POINT', 2);

-----------------------------------------------------------------------------------------------------------------------------
----- 20 voluntarios con contraseña cifrada

INSERT INTO desastresdb.public.voluntario (nombre, correo, password, ubicacion) VALUES
('Usuario 01','usuario011@gmail.com','$2a$10$uKUBd3r6ckQTTaBiIHwT8uM9WMZ.uDAXVUai7jL9zJr3tv2VV805O','0101000020E6100000010080AC25AB51C01DF20E89DEBE40C0'),
('Usuario 02','usuario022@gmail.com','$2a$10$NtGqcfw7.LM/aHNN4s2C..SFxUjbW.IXaIebY4HTnt7ms/oSNJnoK','0101000020E6100000010000B385A651C02387182A15BA40C0'),
('Usuario 03','usuario033@gmail.com','$2a$10$jrW8NUZfKV0vcWacrZjhhuFx5RwQuvEnnOLKuFeBb6x0nCHj0bPpS','0101000020E61000000100003C65E351C0FDB14A209A8140C0'),
('Usuario 04','usuario044@gmail.com','$2a$10$DoCW.a13otAJAweOsy5KLOPOwbULN.TQF4u047huhp9/KbTyJZ1A2','0101000020E6100000010080B7C7D051C079EB2228C97040C0'),
('Usuario 05','usuario055@gmail.com','$2a$10$H0NH5pB2CqIP725SZtoo7ONjPmAUgXDjqtGueNpdtj6ALxRYCJ0/u','0101000020E6100000010080A88DCE51C04B37E1E4783940C0'),
('Usuario 06','usuario066@gmail.com','$2a$10$8Ej0FrKTm1Jz.3he9K3fOukCpUx/WUAHir7..S6zBRieAJqrjp8tO','0101000020E61000000100801942D651C0029BC97822F33DC0'),
('Usuario 07','usuario077@gmail.com','$2a$10$T6X13pDauXwYYf3/HmY5X.ct4LkH108jlqBHbKjI2/2zGXRJ1geGy','0101000020E6100000010080ECFBD051C00B45EFBA3DE63DC0'),
('Usuario 08','usuario088@gmail.com','$2a$10$jv28t9zRNoggzK0JQLuZA.OpmsUzdLNnLyeN3/YpQcTWI5GIm.PRu','0101000020E610000001008064C09451C0408C9D5E735B3BC0'),
('Usuario 09','usuario099@gmail.com','$2a$10$IxoP8spzETTQibI7IY//ZOvkYwXs34orq4ygl7Ntgdw4xgcSfy/Hy','0101000020E61000000100800DF59851C0292B607EA6A537C0'),
('Usuario 10','usuario100@gmail.com','$2a$10$n.XfIZqiBu9Z6so5DgSnieRxq31NMYchBiMwSfzDXTZYj9czKV/6O','0101000020E61000000100807BA28C51C088BB44CF841736C0'),
('Usuario 11','usuario111@gmail.com','$2a$10$V4LazJNW3V.lAtd6KHr50OYS4qhtxw9D0eNpVMpiL85RngJvyp0w2','0101000020E610000001000086DE8951C0FA137956123734C0'),
('Usuario 12','usuario122@gmail.com','$2a$10$ymFlaCrFby3WbzG5ecENQOKf5h/zRtvOFS1OZcRZkJj8y7qEBxUnW','0101000020E6100000010000C5F09251C08D75814DD27832C0'),
('Usuario 13','usuario133@gmail.com','$2a$10$z7KNJniVcum7hoaY1iSpTOrE0R7jHdTljVvZV3eZAhncpIbOeC8cm','0101000020E6100000010080822FE951C0E747D71217B641C0'),
('Usuario 14','usuario144@gmail.com','$2a$10$4CAg56XY/.ODqcTVOmezBOWIb4dzaRpyz2XvTeflutBz8Q5ycM112','0101000020E610000001008044994452C02C4901B8EA6742C0'),
('Usuario 15','usuario155@gmail.com','$2a$10$TdzC/oPRbFD4yPWeMo2if.osSuc6KgTD8QeLfyfrrcto.aerZizzy', '0101000020E61000000100804F7B4852C074E2B21B134A44C0'),
('Usuario 16','usuario166@gmail.com','$2a$10$LKQYDQ0c9SFfRBbzqiKSmOl2Ei.Ulzlllh1n1kiZy4CebL8eQ.kg6', '0101000020E6100000010080982F3C52C0AEE8B4654DBC44C0'),
('Usuario 17','usuario177@gmail.com','$2a$10$PEETS0LV6IOxwZQGt8RRKeCWaKIsiYKsiCdUPJ6DKpMllsapOuB4e','0101000020E6100000010080EDA37252C0ACA3339A3F3B45C0'),
('Usuario 18','usuario188@gmail.com','$2a$10$yzznwWf4Kp3lH0iVaiApYOOKxa.fyCGPGU8PQR6Ug7zVdibeGGvxO','0101000020E61000000100802DAE2852C0336F213EA45E43C0'),
('Usuario 19','usuario199@gmail.com','$2a$10$NzG5CMdJLiuWkQ7Q1iFca.YNGljx34WltEV90dkz0wEAJc1YgaBdK','0101000020E610000001000044874F52C03AB902E7DDE743C0'),
('Usuario 20','usuario200@gmail.com','$2a$10$zc7FFAib2wfu8CuI2pYJgeRcchxGdLbX/wezTLlJf6T8aWaFdwgLa','0101000020E6100000010000BF6F4652C0048BEFF53B6342C0');
