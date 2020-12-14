INSERT INTO public.guardians (email)
VALUES ('jaimito@gmail.com');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'fulanito@gmail.com', 'Fulanito', 'De Tal',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'toto@gmail.com', 'Toto', 'Toto',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'FINISHED');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'tomas.j.pais@gmail.com', 'Tomás', 'Pais',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ezequiel.allio@gmail.com', 'Ezequiel', 'Allio',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ferezecarr@gmail.com', 'Fernando', 'Carreño',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ezeq.quevedo@gmail.com', 'Ezequiel', 'Quevedo',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'damiangayoso@gmail.com', 'Damián', 'Gayoso',
        'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (1, 1);

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (2, 1);

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (3, 1);

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (4, 1);

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (5, 1);

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (6, 1);

INSERT INTO public.patient_guardian (patient_id, guardian_id)
VALUES (7, 1);

INSERT INTO public.games (category, name, description, is_test_game)
VALUES ('ORIENTATION', 'Orientación', 'Responda las siguientes preguntas', true),
       ('FIXATION', 'Fijación', null, true),
       ('CALCULATION', 'Cálculo', null, true),
       ('ATTENTION', 'Atención', null, true),
       ('MEMORY', 'Memoria', null, true),
       ('VISUALIZATION', 'Visualización', null, true),
       ('REPETITION', 'Repetición', null, true),
       ('COMPREHENSION', 'Comprensión', null, true),
       ('READING', 'Lectura', 'Lea la consigna atentamente', true),
       ('WRITING', 'Escritura', null, true),
       ('DRAWING', 'Dibujo', null, true),
       ('ATTENTION', 'Atención', null, false),
       ('FIXATION', 'Fijación', null, false),
       ('WRITING', 'Escritura', null, false),
       ('VISUALIZATION', 'Visualización', null, false),
       ('READING', 'Lectura', 'Lea la consigna atentamente', false),
       ('READING', 'Lectura', 'Lea la consigna atentamente', false),
       ('WRITING', 'Escritura', null, false),
       ('VISUALIZATION', 'Visualización', null, false); --19

INSERT INTO public.tasks (game_id, description)
VALUES (1, '¿En qué año estamos?'),
       (1, '¿En qué estación del año?'),
       (1, '¿Qué día del mes es hoy?'),
       (1, '¿Qué día de la semana es hoy?'),
       (1, '¿En qué mes del año estamos?'),
       (1, '¿En qué país estamos?'),
       (1, '¿En qué provincia estamos?'),
       (1, '¿En qué ciudad estamos?'),
       (2, 'Escuche atentamente el siguiente audio y repita las palabras por el micrófono. Solo podrá reproducirlo 3 veces. Utilice el botón izquierdo para reproducir y el derecho para hablar'),
       (3, 'Cuente de 7 en 7 hacia atrás, partiendo del número 100, 5 veces'),
       (4, 'Deletree la palabra MUNDO al revés'),
       (5, '¿Puede repetir las 3 palabras del segundo juego?'),
       (6, 'Seleccione qué es lo que se ve en la siguiente imagen'),
       (7, 'Escuche bien la frase y repítala'),
       (8, 'Escuche el audio atentamente y toque los botones en el orden correspondiente'),
       (9, 'Busque una persona que tenga barba de color castaño oscuro, vista unos pantalones de tono rojizo, la parte superior de su traje es de color negro y tanto su corbata como sus zapatos son de color azul'),
       (10, 'Ordene sintácticamente la siguiente oración. Solo existe un orden correcto'),
       (11, 'Dibuje la siguiente imagen, lo más exacta posible, incluyendo en tamaño'),
       (12, 'Deletree la palabra DISCO al revés'),
       (13, 'Escuche atentamente el siguiente audio y repita las palabras por el micrófono. Solo podrá reproducirlo 3 veces. Utilice el botón izquierdo para reproducir y el derecho para hablar'),
       (14, 'Ordene sintácticamente la siguiente oración. Solo existe un orden correcto'),
       (15, 'Seleccione qué es lo que se ve en la siguiente imagen'),
       (16, 'Natalia es una maestra que va vestida con un abrigo color verde, su pelo es negro al igual que sus ojos, lleva una falda de color marrón claro y tiene en su mano un anotador de color rosa en el cual escribe todas las tareas que debe realizar'),
       (17, 'Roberto es una persona mayor que tiene su pelo cubierto de canas, le gusta vestir su chaqueta color marrón con botones dorados, usa unos jeans azules que combinan con su corbata y por último, lleva una camisa color celeste claro'),
       (18, 'Ordene sintácticamente la siguiente oración. Solo existe un orden correcto'),
       (19, 'Seleccione qué es lo que se ve en la siguiente imagen'); --26

INSERT INTO public.inputs (type, task_id)
VALUES ('NUMBER', 1),
       ('SELECT', 2),
       ('SELECT', 3),
       ('SELECT', 4),
       ('SELECT', 5),
       ('COUNTRY', 6),
       ('STATE', 7),
       ('CITY', 8),
       ('VOICE', 9),
       ('NUMBER', 10),
       ('NUMBER', 10),
       ('NUMBER', 10),
       ('NUMBER', 10),
       ('NUMBER', 10),
       ('TEXT', 11),
       ('TEXT', 11),
       ('TEXT', 11),
       ('TEXT', 11),
       ('TEXT', 11),
       ('VOICE', 12),
       ('SELECT', 13),
       ('VOICE', 14),
       ('SELECT', 16),
       ('DRAWING_PAD', 18),
       ('TEXT', 19),
       ('TEXT', 19),
       ('TEXT', 19),
       ('TEXT', 19),
       ('TEXT', 19),
       ('VOICE', 20),
       ('SELECT', 22),
       ('SELECT', 23),
       ('SELECT', 24),
       ('SELECT', 26); -- 34

INSERT INTO public.resources (game_id, type, file_name)
VALUES (2, 'AUDIO', 'Bicicleta-Cuchara-Manzana.mp3'),
       (6, 'IMAGE', 'Tigre.png'),
       (7, 'AUDIO', 'Flan-Frutilla-Frambuesas.mp3'),
       (8, 'AUDIO', 'Triangulo-Cuadrado-Circulo.mp3'),
       (9, 'IMAGE', 'lectura-1.png'),
       (10, 'TEXT', 'LluviaTejado.txt'),
       (11, 'IMAGE', 'Casa.png'),
       (13, 'AUDIO', 'Casa-Mesa-Pato.mp3'),
       (14, 'TEXT', 'InviernoCasas.txt'),
       (15, 'IMAGE', 'Gorila.jpg'),
       (16, 'IMAGE', 'lectura-2.png'),
       (17, 'IMAGE', 'lectura-3.png'),
       (18, 'TEXT', 'VeranoPlaya.txt'),
       (19, 'IMAGE', 'Lobo.jpg');

INSERT INTO public.answers (task_id, input_id, type)
VALUES (9, null, 'EXPECTED'),
       (9, null, 'EXPECTED'),
       (9, null, 'EXPECTED'),
       (10, null, 'EXPECTED'),
       (10, null, 'EXPECTED'),
       (10, null, 'EXPECTED'),
       (10, null, 'EXPECTED'),
       (10, null, 'EXPECTED'),
       (11, null, 'EXPECTED'),
       (11, null, 'EXPECTED'),
       (11, null, 'EXPECTED'),
       (11, null, 'EXPECTED'),
       (11, null, 'EXPECTED'),
       (13, null, 'EXPECTED'),
       (14, null, 'EXPECTED'),
       (15, null, 'EXPECTED'),
       (15, null, 'EXPECTED'),
       (15, null, 'EXPECTED'),
       (16, null, 'EXPECTED'),
       (17, null, 'EXPECTED'),
       (18, null, 'EXPECTED'),
       (null, 2, 'POSSIBLE'),
       (null, 2, 'POSSIBLE'),
       (null, 2, 'POSSIBLE'),
       (null, 2, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 3, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 4, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 5, 'POSSIBLE'),
       (null, 21, 'POSSIBLE'),
       (null, 21, 'POSSIBLE'),
       (null, 21, 'POSSIBLE'),
       (null, 21, 'POSSIBLE'),
       (null, 23, 'POSSIBLE'),
       (null, 23, 'POSSIBLE'),
       (null, 23, 'POSSIBLE'),
       (null, 23, 'POSSIBLE'),
       (19, null, 'EXPECTED'),
       (19, null, 'EXPECTED'),
       (19, null, 'EXPECTED'),
       (19, null, 'EXPECTED'),
       (19, null, 'EXPECTED'),
       (20, null, 'EXPECTED'),
       (20, null, 'EXPECTED'),
       (20, null, 'EXPECTED'),
       (21, null, 'EXPECTED'),
       (null, 31, 'POSSIBLE'),
       (null, 31, 'POSSIBLE'),
       (null, 31, 'POSSIBLE'),
       (null, 31, 'POSSIBLE'),
       (22, null, 'EXPECTED'),
       (null, 32, 'POSSIBLE'),
       (null, 32, 'POSSIBLE'),
       (null, 32, 'POSSIBLE'),
       (null, 32, 'POSSIBLE'),
       (23, null, 'EXPECTED'),
       (null, 33, 'POSSIBLE'),
       (null, 33, 'POSSIBLE'),
       (null, 33, 'POSSIBLE'),
       (null, 33, 'POSSIBLE'),
       (24, null, 'EXPECTED'),
       (25, null, 'EXPECTED'),
       (null, 34, 'POSSIBLE'),
       (null, 34, 'POSSIBLE'),
       (null, 34, 'POSSIBLE'),
       (null, 34, 'POSSIBLE'),
       (26, null, 'EXPECTED'); -- 113

INSERT INTO public.text_answers (id, text)
VALUES (1, 'Bicicleta'),
       (2, 'Cuchara'),
       (3, 'Manzana'),
       (14, 'Tigre'),
       (15, 'El flan tiene frutillas y frambuesas'),
       (16, 'Triangulo'),
       (17, 'Cuadrado'),
       (18, 'Circulo'),
       (20, 'Si llueve mucho, entra agua por el tejado'),
       (22, 'Otoño'),
       (23, 'Invierno'),
       (24, 'Primavera'),
       (25, 'Verano'),
       (57, 'Lunes'),
       (58, 'Martes'),
       (59, 'Miércoles'),
       (60, 'Jueves'),
       (61, 'Viernes'),
       (62, 'Sábado'),
       (63, 'Domingo'),
       (64, 'Enero'),
       (65, 'Febrero'),
       (66, 'Marzo'),
       (67, 'Abril'),
       (68, 'Mayo'),
       (69, 'Junio'),
       (70, 'Julio'),
       (71, 'Agosto'),
       (72, 'Septiembre'),
       (73, 'Octubre'),
       (74, 'Noviembre'),
       (75, 'Diciembre'),
       (76, 'Gato'),
       (77, 'Tigre'),
       (78, 'León'),
       (79, 'Puma'),
       (89, 'Casa'),
       (90, 'Mesa'),
       (91, 'Pato'),
       (92, 'En invierno la nieve cubre el techo de las casas'),
       (93, 'Loro'),
       (94, 'Jirafa'),
       (95, 'Gorila'),
       (96, 'Oso'),
       (97, 'Gorila'),
       (108, 'En verano la gente se reúne en la playa'),
       (109, 'Loro'),
       (110, 'Pulpo'),
       (111, 'Lobo'),
       (112, 'Koala'),
       (113, 'Lobo');

INSERT INTO public.numeric_answers (id, number)
VALUES (4, 93),
       (5, 86),
       (6, 79),
       (7, 72),
       (8, 65),
       (19, 4),
       (26, 1),
       (27, 2),
       (28, 3),
       (29, 4),
       (30, 5),
       (31, 6),
       (32, 7),
       (33, 8),
       (34, 9),
       (35, 10),
       (36, 11),
       (37, 12),
       (38, 13),
       (39, 14),
       (40, 15),
       (41, 16),
       (42, 17),
       (43, 18),
       (44, 19),
       (45, 20),
       (46, 21),
       (47, 22),
       (48, 23),
       (49, 24),
       (50, 25),
       (51, 26),
       (52, 27),
       (53, 28),
       (54, 29),
       (55, 30),
       (56, 31),
       (80, 1),
       (81, 2),
       (82, 3),
       (83, 4),
       (98, 1),
       (99, 2),
       (100, 3),
       (101, 4),
       (102, 1),
       (103, 1),
       (104, 2),
       (105, 3),
       (106, 4),
       (107, 2);

INSERT INTO public.char_answers (id, letter)
VALUES (9, 'O'),
       (10, 'D'),
       (11, 'N'),
       (12, 'U'),
       (13, 'M'),
       (84, 'O'),
       (85, 'C'),
       (86, 'S'),
       (87, 'I'),
       (88, 'D');

INSERT INTO public.image_answers (id, image_name)
VALUES (21, 'Casa.png')
