USE event_manager;

SELECT * FROM user;
SELECT * FROM event;
SELECT * FROM media;
SELECT * FROM reservation;
SELECT * FROM review;
SELECT * FROM guestshaveeventinwishlist;

INSERT INTO event (capacity, category, creationDate, description, endTime, location, price, startTime, title, creator_id)
VALUES 
    (150, 'CULTURE', '2024-12-15 18:00:00', 'Shakespeare Play Night', '2024-12-15 22:00:00', 'Grand Theater', 40.00, '2024-12-15 19:00:00', 'Hamlet Live', 1),
    (80, 'MUSIC', '2025-01-10 14:00:00', 'Local Basketball Tournament', '2025-01-10 18:00:00', 'City Gym', 20.00, '2025-01-10 16:00:00', 'Winter Basketball Cup', 2);

INSERT INTO event (capacity, category, creationDate, description, endTime, location, price, startTime, title, creator_id)
VALUES 
    (200, 'BUSINESS', '2025-02-01 10:00:00', 'Business Networking Event', '2025-02-03 20:00:00', 'Downtown Hotel', 75.00, '2025-02-03 15:00:00', 'Startup Connect 2025', 3),
    (50, 'EDUCATION', '2025-02-02 12:00:00', 'Learn Python Basics', '2025-02-03 18:00:00', 'Tech Hub', 25.00, '2025-02-03 14:00:00', 'Python for Beginners', 4);

INSERT INTO event (capacity, category, creationDate, description, endTime, location, price, startTime, title, creator_id)
VALUES 
    (300, 'MUSIC', '2025-02-05 11:00:00', 'Spring Music Festival', '2025-04-10 23:00:00', 'Open Air Park', 60.00, '2025-04-10 18:00:00', 'Spring Beats 2025', 1),
    (100, 'BUSINESS', '2025-02-07 09:00:00', 'AI and Robotics Expo', '2025-06-15 18:00:00', 'Tech Center', 120.00, '2025-06-15 09:00:00', 'AI & Robotics 2025', 2),
    (75, 'EDUCATION', '2025-02-10 13:00:00', 'Advanced Web Development', '2025-03-20 17:00:00', 'Coding Academy', 40.00, '2025-03-20 10:00:00', 'Mastering Full Stack Dev', 3);

INSERT INTO reservation (email, firstName, lastName, purchaseDate, event_id, guest_id)
VALUES 
    ('emma.johnson@example.com', 'Emma', 'Johnson', '2025-01-15 12:00:00', 1, 4),
    ('emma.johnson@example.com', 'Emma', 'Johnson', '2025-01-15 12:00:00', 2, 4),
    ('emma.johnson@example.com', 'Emma', 'Johnson', '2025-01-15 12:00:00', 1, 1),
    ('michael.williams@example.com', 'Michael', 'Williams', '2025-02-02 15:00:00', 3, 1),
    ('sophia.davis@example.com', 'Sophia', 'Davis', '2025-02-02 18:00:00', 5, 2),
    ('sophia.davis@example.com', 'Sophia', 'Davis', '2025-02-02 18:00:00', 1, 4),
    ('david.martinez@example.com', 'David', 'Martinez', '2025-02-03 10:00:00', 6, 3);

INSERT INTO media (public_id, url, event_id)
VALUES 
    ('media111', 'https://res-console.cloudinary.com/dtqmlqc0d/media_explorer_thumbnails/ffacb9c60e30f1ed29ad59e437a5ac35/detailed', 1),
    ('media222', 'https://res.cloudinary.com/dtqmlqc0d/image/upload/v1738526228/media/image/bb7ec8b6-47d1-4c41-adc3-76445c232d93.jpg.jpg', 2),
    ('media333', 'https://res.cloudinary.com/dtqmlqc0d/image/upload/v1738513151/media/image/4d0c7302-e11d-4653-bdb8-86981f105b33.jfif.jpg', 3),
    ('media444', 'https://res.cloudinary.com/dtqmlqc0d/image/upload/v1738513122/media/image/ef05a31b-845b-4e51-bcb8-f702921e2f76.jpg.jpg', 4),
    ('media555', 'https://res.cloudinary.com/dtqmlqc0d/image/upload/v1738513120/media/image/cd1f5765-e59c-4c23-98cf-8a17c10308a9.jpg.jpg', 5),
    ('media666', 'https://res.cloudinary.com/dtqmlqc0d/image/upload/v1738426595/media/image/95432a41-6ac6-412a-aef8-a8c8d52a5fd7.png.png', 6),
    ('media777', 'https://res.cloudinary.com/dtqmlqc0d/image/upload/v1738426501/media/image/d72ee181-4f57-43b5-bce5-853e5d842333.png.png', 7);

INSERT INTO review (comment, rating, review_time, reservation_id)
VALUES 
    ('Great performance!', 4, '2025-02-02 22:32:23.081163', 1),
    ('Great performance!', 4, '2025-02-02 22:32:23.081163', 16),
    ('Had a fantastic time!', 4, '2025-02-02 22:32:23.081163', 2);

INSERT INTO guestshaveeventinwishlist (guestsHaveEventInWishlist_id, wishlist_id)
VALUES 
    (4, 1),
    (3, 2),
    (2, 3),
    (1, 4);
