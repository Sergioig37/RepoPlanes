insert into curso(nombre) values('1A');
insert into curso(nombre) values('2A');
insert into curso(nombre) values('3A');
insert into curso(nombre) values('2B');


insert into plan(nombre, FK_CURSO) values('Operación Triunfo', 1);
insert into plan(nombre, FK_CURSO) values('Operación Camarón', 2);
insert into plan(nombre) values('Operación Kandahar');
insert into plan(nombre) values('Operación Fortune');
insert into plan(nombre) values('Operación Salazar');
insert into plan(nombre) values('Operación Mechano');
insert into plan(nombre) values('Operación Fax');



insert into tutor(id, nombre, plan_id) values('243524', 'Mario', 1);
insert into tutor(id, nombre, plan_id) values('989345', 'Alejandro', 2);
insert into tutor(id, nombre, plan_id) values('563455', 'Pablo', 3);
insert into tutor(id, nombre, plan_id) values('8563747', 'Juan', 4);
insert into tutor(id, nombre, plan_id) values('857', 'David', 5);
insert into tutor(id, nombre) values('85735135', 'Marcos');


insert into actividad(obligatoria, nombre, descripcion) values(false, 'Futbol', 'futbol para benjamines');
insert into actividad(obligatoria, nombre, descripcion) values(true, 'Informática', 'informática para principiantes');
insert into actividad(obligatoria, nombre, descripcion) values(false, 'Pin Pong', 'pin pong para principiantes');

insert into enmarca(actividad_id, plan_id, fecha) values(1, 2, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(2, 2, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(1, 3, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(1, 4, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(2, 1, '14/09/2024');


