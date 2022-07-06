CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);
INSERT INTO users(username, password)
VALUES ('admin', '$2a$10$AjHGc4x3Nez/p4ZpvFDWeO6FGxee/cVqj5KHHnHfuLnIOzC5ag4fm');