DROP TABLE IF EXISTS magazine ;

CREATE TABLE magazine  (
    asin VARCHAR(20) NOT NULL PRIMARY KEY,
    main_cat TEXT,
    title TEXT,
    category TEXT,
    imageURLHighRes TEXT
);