
DROP TABLE IF EXISTS articulo;
CREATE TABLE articulo(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    id_color INTEGER NOT NULL,
    id_forma INTEGER NOT NULL,
    codigo INTEGER UNIQUE,
    descripcion TEXT,
    FOREIGN KEY(id_color) REFERENCES color(id),
    FOREIGN KEY(id_forma) REFERENCES forma(id)
)