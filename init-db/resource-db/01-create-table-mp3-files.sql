CREATE TABLE mp3_files
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    data BLOB                  NOT NULL,
    CONSTRAINT pk_mp3_files PRIMARY KEY (id)
);