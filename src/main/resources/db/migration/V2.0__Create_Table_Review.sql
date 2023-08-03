create table reviews (
    id SERIAL PRIMARY KEY ,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    made_by VARCHAR(255),
    stars INT NOT NULL,
    review VARCHAR(255),
    published_at DATE,
    reviewed_at DATE NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
                ON DELETE CASCADE

);
