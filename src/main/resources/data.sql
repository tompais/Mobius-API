INSERT INTO public.guardians (email)
VALUES ('jaimito@gmail.com');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'fulanito@gmail.com', 'Fulanito', 'De Tal', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'toto@gmail.com', 'Toto', 'Toto', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'FINISHED');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'tomas.j.pais@gmail.com', 'Tomás', 'Pais', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ezequiel.allio@gmail.com', 'Ezequiel', 'Allio', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ferezecarr@gmail.com', 'Fernando', 'Carreño', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ezeq.quevedo@gmail.com', 'Ezequiel', 'Quevedo', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'damiangayoso@gmail.com', 'Damián', 'Gayoso', 'c9fe26d11b5fd337fdde5fea13b18865faff10f4f775cb05682437ce34d69222', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

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
       ('DRAWING', 'Dibujo', null, true);

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
       (11, 'Dibuje la siguiente imagen, lo más exacta posible, incluyendo en tamaño');

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
       ('DRAWING_PAD', 18); -- 24

INSERT INTO public.resources (game_id, type, file_name)
VALUES (2, 'AUDIO', 'Bicicleta-Cuchara-Manzana.mp3'),
       (6, 'IMAGE', 'Tigre.png'),
       (7, 'AUDIO', 'Flan-Frutilla-Frambuesas.mp3'),
       (8, 'AUDIO', 'Triangulo-Cuadrado-Circulo.mp3'),
       (9, 'IMAGE', 'lectura-1.png'),
       (10, 'TEXT', 'LluviaTejado.txt'),
       (11, 'IMAGE', 'Casa.png');

-- Expected answers
INSERT INTO public.answers (task_id, type)
VALUES (9, 'EXPECTED'),
       (9, 'EXPECTED'),
       (9, 'EXPECTED'),
       (10, 'EXPECTED'),
       (10, 'EXPECTED'),
       (10, 'EXPECTED'),
       (10, 'EXPECTED'),
       (10, 'EXPECTED'),
       (11, 'EXPECTED'),
       (11, 'EXPECTED'),
       (11, 'EXPECTED'),
       (11, 'EXPECTED'),
       (11, 'EXPECTED'),
       (13, 'EXPECTED'),
       (14, 'EXPECTED'),
       (15, 'EXPECTED'),
       (15, 'EXPECTED'),
       (15, 'EXPECTED'),
       (16, 'EXPECTED'),
       (17, 'EXPECTED');
-- 20

-- Possible answers
INSERT INTO public.answers (input_id, type)
VALUES (2, 'POSSIBLE'), -- 21
       (2, 'POSSIBLE'),
       (2, 'POSSIBLE'),
       (2, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (3, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (4, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (5, 'POSSIBLE'),
       (21, 'POSSIBLE'),
       (21, 'POSSIBLE'),
       (21, 'POSSIBLE'),
       (21, 'POSSIBLE'),
       (23, 'POSSIBLE'),
       (23, 'POSSIBLE'),
       (23, 'POSSIBLE'),
       (23, 'POSSIBLE'); -- 82

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
       (21, 'Otoño'),
       (22, 'Invierno'),
       (23, 'Primavera'),
       (24, 'Verano'),
       (56, 'Lunes'),
       (57, 'Martes'),
       (58, 'Miércoles'),
       (59, 'Jueves'),
       (60, 'Viernes'),
       (61, 'Sábado'),
       (62, 'Domingo'),
       (63, 'Enero'),
       (64, 'Febrero'),
       (65, 'Marzo'),
       (66, 'Abril'),
       (67, 'Mayo'),
       (68, 'Junio'),
       (69, 'Julio'),
       (70, 'Agosto'),
       (71, 'Septiembre'),
       (72, 'Octubre'),
       (73, 'Noviembre'),
       (74, 'Diciembre'),
       (75, 'Gato'),
       (76, 'Tigre'),
       (77, 'León'),
       (78, 'Puma');

INSERT INTO public.numeric_answers (id, number)
VALUES (4, 93),
       (5, 86),
       (6, 79),
       (7, 72),
       (8, 65),
       (19, 4),
       (25, 1),
       (26, 2),
       (27, 3),
       (28, 4),
       (29, 5),
       (30, 6),
       (31, 7),
       (32, 8),
       (33, 9),
       (34, 10),
       (35, 11),
       (36, 12),
       (37, 13),
       (38, 14),
       (39, 15),
       (40, 16),
       (41, 17),
       (42, 18),
       (43, 19),
       (44, 20),
       (45, 21),
       (46, 22),
       (47, 23),
       (48, 24),
       (49, 25),
       (50, 26),
       (51, 27),
       (52, 28),
       (53, 29),
       (54, 30),
       (55, 31),
       (79, 1),
       (80, 2),
       (81, 3),
       (82, 4);

INSERT INTO public.char_answers (id, letter)
VALUES (9, 'O'),
       (10, 'D'),
       (11, 'N'),
       (12, 'U'),
       (13, 'M');
