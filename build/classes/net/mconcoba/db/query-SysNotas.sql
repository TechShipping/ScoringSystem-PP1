

CREATE DATABASE IF NOT EXISTS SysNotas;


use SysNotas;


CREATE TABLE Alumnos(
	alumno_id integer auto_increment not null,
	nombres varchar(50) not null,
	apellidos varchar(50) not null,
	bimestre_uno decimal(10, 2) not null default 0.00,
	bimestre_dos decimal(10, 2) not null default 0.00,
	bimestre_tres decimal(10, 2) not null default 0.00,
	bimestre_cuatro decimal(10, 2) not null default 0.00,
	promedio decimal(10, 2) default 0.00,
	fecha_creacion timestamp default CURRENT_TIMESTAMP(),
	fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(),
	primary key PK_alumno_id (alumno_id)
);


create function fn_CalcPromedio(nota1 decimal(10, 2), nota2 decimal(10, 2), nota3 decimal(10, 2), nota4 decimal(10, 2)) returns decimal(10, 2)
	reads sql data deterministic
	begin
		declare pro decimal(10, 2);
		declare suma decimal(10, 2);
		set suma = nota1 + nota2 + nota3 + nota4;
        set pro = suma / 4;
		return pro;
	end;

create trigger tr_InsertPromedio
	before insert on Alumnos
	for each row
		begin
			set new.promedio = fn_CalcPromedio(new.bimestre_uno, new.bimestre_dos, new.bimestre_tres, new.bimestre_cuatro);
		end;
		
	
create trigger tr_UpdatePromedio
	before update on Alumnos
	for each row
		begin
			set new.promedio = fn_CalcPromedio(new.bimestre_uno, new.bimestre_dos, new.bimestre_tres, new.bimestre_cuatro);
		end;
	
	insert into Alumnos(nombres, apellidos, bimestre_uno, bimestre_dos, bimestre_tres, bimestre_cuatro)
		values('Manuel', 'Concoba', 80, 20, 50, 100);
	
	update Alumnos set nombres = ?, apellidos = ?, bimestre_uno = ?, bimestre_dos = ?, bimestre_tres = ?, bimestre_cuatro = ?  WHERE alumno_id = ?;
		
	SELECT fn_CalcPromedio(80.00, 20.00, 50.00, 10.00);
	

SELECT * from Alumnos a;

DELETE from Alumnos WHERE alumno_id = ?;
	
	
	
