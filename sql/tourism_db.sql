CREATE DATABASE IF NOT EXISTS tourism_db;
USE tourism_db;

DROP TABLE IF EXISTS tourists;

CREATE TABLE tourists (
    tourist_id   BIGINT       NOT NULL AUTO_INCREMENT,
    tourist_name VARCHAR(255),
    email        VARCHAR(255),
    destination  VARCHAR(255),
    country      VARCHAR(255),
    PRIMARY KEY (tourist_id)
);

INSERT INTO tourists (tourist_name, email, destination, country) VALUES
('Aarav Sharma',   'aarav.sharma@example.com',   'Jaipur',    'India'),
('Emily Johnson',  'emily.johnson@example.com',  'Paris',     'France'),
('Carlos Mendez',  'carlos.mendez@example.com',  'Bali',      'Indonesia'),
('Yuki Tanaka',    'yuki.tanaka@example.com',    'Kyoto',     'Japan'),
('Sophie Mueller', 'sophie.mueller@example.com', 'Santorini', 'Greece'),
('Liam OBrien',    'liam.obrien@example.com',    'Reykjavik', 'Iceland');

SELECT * FROM tourists;
