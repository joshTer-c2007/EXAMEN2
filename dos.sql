CREATE TABLE proyectos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dias_estimados INTEGER NOT NULL CHECK (dias_estimados > 0)
);

CREATE TABLE tecnologias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    categoria VARCHAR(30) NOT NULL
);

CREATE TABLE proyectos_tecnologias (
    id_proyecto INTEGER NOT NULL,
    id_tecnologia INTEGER NOT NULL,
    version_utilizada VARCHAR(20),
    PRIMARY KEY (id_proyecto, id_tecnologia),
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_tecnologia) REFERENCES tecnologias(id) ON DELETE CASCADE
);

SELECT t.nombre, t.categoria 
FROM tecnologias t
JOIN proyectos_tecnologias pt ON t.id = pt.id_tecnologia
JOIN proyectos p ON pt.id_proyecto = p.id
WHERE p.nombre = 'Sistema de Matriculación';

SELECT p.nombre, p.dias_estimados, pt.version_utilizada
FROM proyectos p
JOIN proyectos_tecnologias pt ON p.id = pt.id_proyecto
WHERE pt.id_tecnologia = 1;

SELECT t.nombre AS tecnologia, COUNT(pt.id_proyecto) AS total_proyectos
FROM tecnologias t
LEFT JOIN proyectos_tecnologias pt ON t.id = pt.id_tecnologia
GROUP BY t.id, t.nombre
ORDER BY total_proyectos DESC;