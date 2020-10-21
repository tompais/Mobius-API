INSERT INTO public.guardians (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'jaimito@gmail.com', 'Jaimito', 'Rodríguez', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'fulanito@gmail.com', 'Fulanito', 'De Tal', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'toto@gmail.com', 'Toto', 'Toto', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'tomas.j.pais@gmail.com', 'Tomás', 'Pais', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'ezequiel.allio@gmail.com', 'Ezequiel', 'Allio', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'ferezecarr@gmail.com', 'Fernando', 'Carreño', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'ezeq.quevedo@gmail.com', 'Ezequiel', 'Quevedo', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'damiangayoso@gmail.com', 'Damián', 'Gayoso', 'lala1234', 'ACTIVE', 'OTHER');

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

INSERT INTO public.categories (type, description)
VALUES ('ORIENTATION', 'ORIENTATION'),
       ('FIXATION', 'FIXATION'),
       ('ATTENTION', 'ATTENTION'),
       ('CALCULATION', 'CALCULATION'),
       ('MEMORY', 'MEMORY'),
       ('LANGUAGE_AND_PRAXIS', 'LANGUAGE_AND_PRAXIS');

INSERT INTO public.games (category_id, name, description)
VALUES (1, 'Juego de orientación', 'Responda las siguientes preguntas'),
       (2, 'Juego de fijación', null);

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
       (2, 'Voy a decirle 3 palabras y usted tiene que repetirlas una vez que yo acabe');

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
       (11, 3);

INSERT INTO public.resources (game_id, type, file_name)
VALUES (2, 'AUDIO', 'audio-file.mp3');

INSERT INTO public.answers (task_id)
VALUES (11),
       (11),
       (11);

INSERT INTO public.text_answers (id, text)
VALUES (1, 'Bicicleta'),
       (2, 'Cuchara'),
       (3, 'Manzana');

INSERT INTO public.test_progresses (status, patient_id, last_category_played_id)
VALUES ('IN_PROGRESS', 1, 1);

INSERT INTO public.test_progresses (status, patient_id, last_category_played_id)
VALUES ('FINISHED', 2, 6);
