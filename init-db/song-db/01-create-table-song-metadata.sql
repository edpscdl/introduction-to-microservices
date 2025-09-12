CREATE TABLE songs_metadata
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NOT NULL,
    artist   VARCHAR(255) NOT NULL,
    album    VARCHAR(255) NOT NULL,
    duration VARCHAR(255) NOT NULL,
    year     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_songs_metadata PRIMARY KEY (id)
);