INSERT INTO users (username, password, email, active, role_id)
VALUES (
    'admin',
    '$2a$10$abcdefghijABCDEFGHIJ1234567890abcdefghiJ',
    'rushikesh@gmail.com',
    true,
    (SELECT id FROM role WHERE name = 'Admin')
);
