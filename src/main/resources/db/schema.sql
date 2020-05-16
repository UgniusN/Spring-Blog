CREATE TABLE Articles
(
    article_id      INT UNSIGNED      NOT NULL AUTO_INCREMENT,
    title           VARCHAR(50)       NOT NULL,
    author          VARCHAR(255)      NOT NULL,
    article_content  VARCHAR           NOT NULL,
    PRIMARY KEY (article_id)
);
