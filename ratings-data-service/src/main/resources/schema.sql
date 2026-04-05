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
('user1', 'tt0111161', 5),
('user1', 'tt0068646', 4),
('user1', 'tt0071562', 4),
('user2', 'tt0111161', 3),
('user2', 'tt0110912', 5);