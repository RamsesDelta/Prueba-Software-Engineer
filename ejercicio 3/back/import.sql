insert into generos (id_genero,nombre_genero) values (1,'Novela');
insert into generos (id_genero,nombre_genero) values (2,'Terror');
insert into generos (id_genero,nombre_genero) values (3,'Suspenso');
insert into generos (id_genero,nombre_genero) values (4,'Romance');
insert into generos (id_genero,nombre_genero) values (5,'Ciencia Ficcion');

insert into roles (id_rol,nombre) values (1,'ROLE_USER');
insert into roles (id_rol,nombre) values (2,'ROLE_ADMIN');

insert into status (id_status,nombre_status) values (1,'solicitado');
insert into status (id_status,nombre_status) values (2,'prestado');
insert into status (id_status,nombre_status) values (4,'cancelado');
insert into status (id_status,nombre_status) values (3,'devuelto');

insert into libros (id_libro,autor,cantidad,fecha_publicacion,titulo,id_genero) values (1,'Edgar Gonzales Castillo',2,'1995-01-29','La superviviente',1);
insert into libros (id_libro,autor,cantidad,fecha_publicacion,titulo,id_genero) values (2,'Jose Lopez Sanchez',2,'2017-03-23','No soy un monstruo',2);
insert into libros (id_libro,autor,cantidad,fecha_publicacion,titulo,id_genero) values (3,'Perla Beatriz Olvedo',1,'2020-04-01','Cartas del Diablo a su Sobrino',3);
insert into libros (id_libro,autor,cantidad,fecha_publicacion,titulo,id_genero) values (4,'Estefania Campos Ochoa',5,'2013-05-09','Princesa de cenizas',4);
insert into libros (id_libro,autor,cantidad,fecha_publicacion,titulo,id_genero) values (5,'Ramon Pozo Lopez',7,'2015-02-25','Secretos imperfectos',5);

insert into usuarios (id_usuario,correo,fecha_nacimiento,nombre,password,enable) values (1,'chirs.gc23@gmial.com','1995-03-29','Christofer Mencho Diaz','12345',1);
insert into usuarios (id_usuario,correo,fecha_nacimiento,nombre,password,enable) values (2,'mich.garcia@gmial.com','1980-04-08','Sara Ortiz Torres','12345',1);
insert into usuarios (id_usuario,correo,fecha_nacimiento,nombre,password,enable) values (3,'ferrari.24@gmial.com','1975-06-19','Fernando Blas Pacheco','12345',1);
insert into usuarios (id_usuario,correo,fecha_nacimiento,nombre,password,enable) values (4,'gc34@gmial.com','1976-09-25', 'Hugo Cruz Medina','12345',1);
insert into usuarios (id_usuario,correo,fecha_nacimiento,nombre,password,enable) values (5,'mago.contreras@gmial.com','1988-11-10','Margarita Contreras Vazquez','12345',1);

insert into solicitudes (id_solicitud,id_libro,id_status,id_usuario) values (1,2,1,3);
insert into solicitudes (id_solicitud,id_libro,id_status,id_usuario) values (2,4,1,5);


insert into usuarios_rol(id_usuario_rol,id_rol,id_usuario)  values  (1,1,1);
insert into usuarios_rol(id_usuario_rol,id_rol,id_usuario)  values  (2,1,2);
insert into usuarios_rol(id_usuario_rol,id_rol,id_usuario)  values  (3,2,3);
insert into usuarios_rol(id_usuario_rol,id_rol,id_usuario)  values  (4,1,4);