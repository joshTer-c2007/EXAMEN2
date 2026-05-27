CREATE TABLE vuelos (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    precio_boleto NUMERIC(10,2) NOT NULL CHECK (precio_boleto >= 0),
    asientos_disponibles INTEGER NOT NULL CHECK (asientos_disponibles >= 0)
);

INSERT INTO vuelos (codigo, precio_boleto, asientos_disponibles) VALUES 
('AA-123', 250.50, 45),
('LA-456', 180.00, 2),
('AV-789', 320.00, 120),
('CM-101', 450.99, 0),
('IB-202', 850.00, 15),
('UX-303', 720.50, 4),
('AR-404', 210.00, 88),
('JA-505', 95.00, 3),
('WJ-606', 115.00, 50),
('EQ-707', 130.00, 0),
('AA-808', 299.99, 12),
('LA-909', 199.50, 1),
('AV-111', 340.00, 75),
('CM-222', 490.00, 6),
('IB-333', 920.00, 40),
('UX-444', 680.00, 0),
('AR-555', 240.00, 95),
('JA-666', 89.99, 2),
('WJ-777', 105.00, 110),
('EQ-888', 125.50, 18);

SELECT * FROM vuelos WHERE asientos_disponibles < 5;

UPDATE vuelos SET precio_boleto = precio_boleto * 1.15 WHERE id = 1;

DELETE FROM vuelos WHERE asientos_disponibles = 0;
ALTER TABLE vuelos ADD COLUMN destino VARCHAR(100);