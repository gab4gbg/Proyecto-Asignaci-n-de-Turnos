-- Inserta los administradores autorizados si aún no existen
INSERT INTO admins (email, nombre)
VALUES ('gabrielgenarobg@gmail.com', 'Gabriel Genaro')
ON CONFLICT (email) DO NOTHING;

INSERT INTO admins (email, nombre)
VALUES ('gabriel.beltran@estudiantesunibague.edu.co', 'Gabriel Beltrán')
ON CONFLICT (email) DO NOTHING;

INSERT INTO admins (email, nombre)
VALUES ('steven.correa@unibague.edu.co', 'Steven Correa')
ON CONFLICT (email) DO NOTHING;
