CREATE TABLE lunch (
     id BIGINT NOT NULL AUTO_INCREMENT,
     usuario_id BIGINT,
     status VARCHAR(100) NOT NULL,
     PRIMARY KEY(id),
    FOREIGN KEY (usuario_id) REFERENCES client(id)
);