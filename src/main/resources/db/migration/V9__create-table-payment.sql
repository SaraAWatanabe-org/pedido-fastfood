CREATE TABLE payment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         cpf VARCHAR(14) NULL,
                         price_total DECIMAL(10, 2) NULL,
                         number_lunch BIGINT NULL,
                         status VARCHAR(255) NULL,
                         transaction_id VARCHAR(255) NULL,
                         FOREIGN KEY (number_lunch) REFERENCES lunch(id)
);