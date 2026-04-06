CREATE TABLE IF NOT EXISTS ratings (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id   VARCHAR(100) NOT NULL,
    movie_id  VARCHAR(100) NOT NULL,
    rating    INT          NOT NULL CHECK (rating BETWEEN 1 AND 5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_user (user_id)
);

-- Seed some test data
INSERT INTO ratings (user_id, movie_id, rating) VALUES
    ('user1', 'tt0068646', 5),
    ('user2', 'tt0068646', 4),
    ('user3', 'tt0068646', 5),
    ('user4', 'tt0068646', 3),

    ('user1', 'tt0071562', 4),
    ('user2', 'tt0071562', 5),
    ('user5', 'tt0071562', 4),
    ('user6', 'tt0071562', 5),

    ('user1', 'tt0111161', 5),
    ('user2', 'tt0111161', 4),
    ('user3', 'tt0111161', 5),
    ('user7', 'tt0111161', 4),

    ('user3', 'tt0110912', 4),
    ('user4', 'tt0110912', 5),
    ('user6', 'tt0110912', 5),
    ('user8', 'tt0110912', 4),

    ('user2', 'tt0109830', 5),
    ('user4', 'tt0109830', 4),
    ('user5', 'tt0109830', 5),
    ('user9', 'tt0109830', 4),

    ('user3', 'tt0137523', 4),
    ('user5', 'tt0137523', 5),
    ('user7', 'tt0137523', 4),
    ('user8', 'tt0137523', 5),

    ('user1', 'tt0167260', 5),
    ('user6', 'tt0167260', 5),
    ('user7', 'tt0167260', 4),
    ('user9', 'tt0167260', 5),

    ('user2', 'tt0120737', 4),
    ('user3', 'tt0120737', 5),
    ('user8', 'tt0120737', 4),
    ('user10', 'tt0120737', 5),

    ('user4', 'tt1375666', 5),
    ('user5', 'tt1375666', 4),
    ('user6', 'tt1375666', 5),
    ('user9', 'tt1375666', 4),

    ('user7', 'tt0133093', 4),
    ('user8', 'tt0133093', 5),
    ('user9', 'tt0133093', 4),
    ('user10', 'tt0133093', 5);