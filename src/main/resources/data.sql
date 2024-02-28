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



insert into tutor(id, nombre, plan_id) values(1, 'Mario', 1);
insert into tutor(id, nombre, plan_id) values(2, 'Alejandro', 2);
insert into tutor(id, nombre, plan_id) values(3, 'Pablo', 3);
insert into tutor(id, nombre, plan_id) values(4, 'Juan', 4);
insert into tutor(id, nombre, plan_id) values(5, 'David', 5);
insert into tutor(id, nombre) values(6, 'Marcos');


insert into actividad(obligatoria, nombre, descripcion) values(false, 'Futbol', 'futbol para benjamines');
insert into actividad(obligatoria, nombre, descripcion) values(true, 'Informática', 'informática para principiantes');
insert into actividad(obligatoria, nombre, descripcion) values(false, 'Pin Pong', 'pin pong para principiantes');

insert into enmarca(actividad_id, plan_id, fecha) values(1, 2, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(2, 2, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(1, 3, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(1, 4, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(2, 1, '14/09/2024');


insert into usuario(usuario, password) values('sergio', '$2a$12$6ish92kXsSu4NnsD0N0WneCB5Fuw9PvBtTYuIaS/eYRm7epJV1xdS');
insert into usuario(usuario, password) values('mario', '$2a$12$jP5Xkwm8.fsZjtMQkCj7YOjRg3dyTDPc8wwUA13/0DeuhXBsJ7R..');

