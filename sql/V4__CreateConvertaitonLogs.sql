CREATE TABLE convertationlogs
(
    id          SERIAL PRIMARY KEY,
    login       VARCHAR(255),
    type        VARCHAR(255),
    inner_value VARCHAR(255),
    outer_value VARCHAR(255),
    create_date TIMESTAMP
);