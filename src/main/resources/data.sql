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

INSERT INTO public.games (category, name, description, is_test)
VALUES ('ORIENTATION', 'Juego de orientación', 'Responda las siguientes preguntas', true),
       ('FIXATION', 'Juego de fijación', null, true),
       ('CALCULATION', 'Juego de cálculo', null, true),
       ('ATTENTION', 'Juego de atención', null, true),
       ('MEMORY', 'Juego de memoria', null, true),
       ('VISUALIZATION', 'Juego de visualización', null, true),
       ('REPETITION', 'Juego de repetición', null, true);

INSERT INTO public.tasks (game_id, description)
VALUES (1, '¿En qué año estamos?'),
       (1, '¿En qué estación del año?'),
       (1, '¿Qué día del mes es hoy?'),
       (1, '¿Qué día de la semana es hoy?'),
       (1, '¿En qué mes del año estamos?'),
       (1, '¿En qué país estamos?'),
       (1, '¿En qué provincia estamos?'),
       (1, '¿En qué ciudad estamos?'),
       (1, '¿Dónde estamos en este momento?'),
       (1, '¿En qué piso estamos?'),
       (2, 'Voy a decirle 3 palabras y usted tiene que repetirlas una vez que yo acabe'),
       (3, '¿Cuánto es 100 menos 7?'),
       (3, 'Ahora repita la resta 4 veces más'),
       (4, 'Deletree la palabra MUNDO al revés'),
       (5, '¿Puede recordar las 3 palabras del segundo ejercicio?'),
       (6, '¿Qué es esto?'),
       (7, 'Escuche bien la frase y repítala');

INSERT INTO public.inputs (type)
VALUES ('TEXT'),
       ('NUMBER'),
       ('VOICE'),
       ('CALENDAR'),
       ('MAPS');

INSERT INTO public.input_task (task_id, input_id)
VALUES (1, 2),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 2),
       (11, 3),
       (12, 2),
       (13, 2),
       (13, 2),
       (13, 2),
       (13, 2),
       (14, 1),
       (14, 1),
       (14, 1),
       (14, 1),
       (14, 1),
       (15, 3),
       (16, 1),
       (17, 3);

INSERT INTO public.resources (game_id, type, file_name)
VALUES (2, 'AUDIO', 'Bicicleta-Cuchara-Manzana.mp3'),
       (6, 'IMAGE', 'Tigre.png'),
       (7, 'AUDIO', 'Flan-Frutilla-Frambuesas.mp3');

INSERT INTO public.answers (task_id)
VALUES (11),
       (11),
       (11),
       (12),
       (13),
       (13),
       (13),
       (13),
       (14),
       (14),
       (14),
       (14),
       (14),
       (16),
       (17);

INSERT INTO public.text_answers (id, text)
VALUES (1, 'Bicicleta'),
       (2, 'Cuchara'),
       (3, 'Manzana'),
       (14, 'Tigre'),
       (15, 'El flan tiene frutillas y frambuesas');

INSERT INTO public.numeric_answers (id, number)
VALUES (4, 93),
       (5, 86),
       (6, 79),
       (7, 72),
       (8, 65);

INSERT INTO public.char_answers (id, letter)
VALUES (9, 'O'),
       (10, 'D'),
       (11, 'N'),
       (12, 'U'),
       (13, 'M');
