INSERT INTO mobius.public.guardians (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'jaimito@gmail.com', 'Jaimito', 'Rodríguez', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO mobius.public.patients (birthday, email, first_name, last_name, password, status, genre)
VALUES ('2000-10-10', 'fulanito@gmail.com', 'Fulanito', 'De Tal', 'lala1234', 'ACTIVE', 'OTHER');

INSERT INTO mobius.public.patient_guardian (patient_id, guardian_id) VALUES (1, 1)
