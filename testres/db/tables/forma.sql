
DROP TABLE IF EXISTS forma;
CREATE TABLE forma(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombre TEXT UNIQUE
);