create table reviews (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    stars INT NOT NULL,
    review VARCHAR(255),
    publishedAt DATE,
    reviewedAt DATE NOT NULL,
    userId UUID NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (userId)
            REFERENCES users(id)
                ON DELETE CASCADE

);
