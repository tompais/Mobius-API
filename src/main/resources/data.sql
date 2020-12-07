INSERT INTO public.guardians (email)
VALUES ('ezequiel.allio@gmail.com');

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
       ('FIXATION', 'Fijación', null, false);

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
       (13, 'Escuche atentamente el siguiente audio y repita las palabras por el micrófono. Solo podrá reproducirlo 3 veces. Utilice el botón izquierdo para reproducir y el derecho para hablar');

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
       ('VOICE', 20); -- 30

INSERT INTO public.resources (game_id, type, file_name)
VALUES (2, 'AUDIO', 'Bicicleta-Cuchara-Manzana.mp3'),
       (6, 'IMAGE', 'Tigre.png'),
       (7, 'AUDIO', 'Flan-Frutilla-Frambuesas.mp3'),
       (8, 'AUDIO', 'Triangulo-Cuadrado-Circulo.mp3'),
       (9, 'IMAGE', 'lectura-1.png'),
       (10, 'TEXT', 'LluviaTejado.txt'),
       (11, 'IMAGE', 'Casa.png'),
       (13, 'AUDIO', 'Casa-Mesa-Pato.mp3');

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
       (20, null, 'EXPECTED'); -- 91

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
       (91, 'Pato');

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
       (83, 4);

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
