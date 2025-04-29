INSERT INTO users (username, password, active, role_id)
VALUES (
    'admin',
    '$2a$10$abcdefghijABCDEFGHIJ1234567890abcdefghiJ',
    true,
    (SELECT id FROM role WHERE name = 'Admin')
);
