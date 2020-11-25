INSERT INTO public.guardians (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'jaimito@gmail.com', 'Jaimito', 'Rodríguez', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'fulanito@gmail.com', 'Fulanito', 'De Tal', 'lala1234', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'toto@gmail.com', 'Toto', 'Toto', 'lala1234', 'ACTIVE', 'OTHER', 'FINISHED');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'tomas.j.pais@gmail.com', 'Tomás', 'Pais', 'lala1234', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ezequiel.allio@gmail.com', 'Ezequiel', 'Allio', 'lala1234', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ferezecarr@gmail.com', 'Fernando', 'Carreño', 'lala1234', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'ezeq.quevedo@gmail.com', 'Ezequiel', 'Quevedo', 'lala1234', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre, test_status)
VALUES ('2000-10-10', 'damiangayoso@gmail.com', 'Damián', 'Gayoso', 'lala1234', 'ACTIVE', 'OTHER', 'IN_PROGRESS');

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
       (6, 'Identifique qué es lo que se ve en la siguiente imagen'),
       (7, 'Escuche bien la frase y repítala'),
       (8, 'Escuche el audio atentamente y toque los botones en el orden correspondiente'),
       (9, 'Busque una persona que tenga barba de color castaño oscuro, vista unos pantalones de tono rojizo, la parte superior de su traje es de color negro y tanto su corbata como sus zapatos son de color azul'),
       (10, 'Ordene sintácticamente la siguiente oración. Solo existe un orden correcto'),
       (11, 'Dibuje la siguiente imagen, lo más exacta posible, incluyendo en tamaño');

INSERT INTO public.inputs (type)
VALUES ('TEXT'),
       ('NUMBER'),
       ('VOICE'),
       ('CALENDAR'),
       ('MAPS'),
       ('DRAWING_PAD');

INSERT INTO public.input_task (task_id, input_id)
VALUES (1, 2),
       (2, 1),
       (3, 2),
       (4, 1),
       (5, 2),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 3),
       (10, 2),
       (10, 2),
       (10, 2),
       (10, 2),
       (10, 2),
       (11, 1),
       (11, 1),
       (11, 1),
       (11, 1),
       (11, 1),
       (12, 3),
       (13, 1),
       (14, 3),
       (15, 1),
       (15, 1),
       (15, 1),
       (16, 2),
       (18, 6);

INSERT INTO public.resources (game_id, type, file_name)
VALUES (2, 'AUDIO', 'Bicicleta-Cuchara-Manzana.mp3'),
       (6, 'IMAGE', 'Tigre.png'),
       (7, 'AUDIO', 'Flan-Frutilla-Frambuesas.mp3'),
       (8, 'AUDIO', 'Triangulo-Cuadrado-Circulo.mp3'),
       (9, 'IMAGE', 'lectura-1.png'),
       (10, 'TEXT', 'LluviaTejado.txt'),
       (11, 'IMAGE', 'Casa.png');

INSERT INTO public.answers (task_id)
VALUES (9),
       (9),
       (9),
       (10),
       (10),
       (10),
       (10),
       (10),
       (11),
       (11),
       (11),
       (11),
       (11),
       (13),
       (14),
       (15),
       (15),
       (15),
       (16),
       (17);

INSERT INTO public.text_answers (id, text)
VALUES (1, 'Bicicleta'),
       (2, 'Cuchara'),
       (3, 'Manzana'),
       (14, 'Tigre'),
       (15, 'El flan tiene frutillas y frambuesas'),
       (16, 'Triangulo'),
       (17, 'Cuadrado'),
       (18, 'Circulo'),
       (20, 'Si llueve mucho, entra agua por el tejado');

INSERT INTO public.numeric_answers (id, number)
VALUES (4, 93),
       (5, 86),
       (6, 79),
       (7, 72),
       (8, 65),
       (19, 4);

INSERT INTO public.char_answers (id, letter)
VALUES (9, 'O'),
       (10, 'D'),
       (11, 'N'),
       (12, 'U'),
       (13, 'M');
