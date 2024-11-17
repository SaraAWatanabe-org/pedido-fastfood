CREATE TABLE lunch_lunch_item (
     lunch_id BIGINT NOT NULL AUTO_INCREMENT,
     lunch_item_id BIGINT NOT NULL,
     status VARCHAR(100) NOT NULL,
    FOREIGN KEY (lunch_id) REFERENCES lunch(id),
    FOREIGN KEY (lunch_item_id) REFERENCES lunch_item(id)
);