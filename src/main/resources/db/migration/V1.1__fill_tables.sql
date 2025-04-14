-- Datos de price
INSERT INTO price (base_price, total_price, total_tax, version) VALUES
(10.00, 30.00, 30.00, 0),
(10.00, 30.00, 30.00, NULL),
(10.00, 30.00, 30.00, NULL);

-- Datos de itinerary
INSERT INTO itinerary (version, itinerary_id) VALUES
(0, 1),
(NULL, 2),
(NULL, 3);

-- Datos de reservation
INSERT INTO reservation (creation_date, version, itinerary_id) VALUES
('2025-04-11', 0, 1),
('2025-04-12', NULL, 2),
('2025-04-12', NULL, 3);

-- Datos de passenger
INSERT INTO passenger (birthday, version, reservation_id, first_name, last_name, document_number, document_type) VALUES
('1990-01-01', 0, 1, 'Sergio', 'Martinez', '222', '333'),
('1990-01-01', NULL, 2, 'Sergio', 'Hernandez', '222', '333'),
('1990-01-01', NULL, 3, 'Sergio', 'Hernandez', '222', '333'),
('1990-01-01', NULL, 3, 'Sergio', 'Ascencio', '222', '333'),
('1990-01-01', NULL, 3, 'Sergio', 'Zetina', '222', '333');

-- Datos de segment
INSERT INTO segment (carrier, destination, origin, version, itinerary_id, arrival, departure) VALUES
('AA', 'MIA', 'BUE', 0, 1, '2023-12-31', '2023-12-31'),
('AA', 'MIA', 'BUE', NULL, 2, '2023-12-31', '2023-12-31'),
('AA', 'MIA', 'BUE', NULL, 3, '2023-12-31', '2023-12-31');