INSERT INTO status (name)
VALUES ('PENDING'),
       ('ACCEPTED'),
       ('DECLINED'),
       ('RETURNED');

INSERT INTO users (id, admin, first_name, last_name, login, middle_name, password)
VALUES (1, false, 'Vlad', 'Kukoba', 'kukobistr', 'kukobistr',
        '$2a$10$Sdqgw.NOkcZGBHwMPjhWBuGW2kMmgeMkQc.36mOaCxsNhSB6JRwL2'),
       (2, false, 'Andrey', 'Egorov', 'MyFosse', 'MyFosse',
        '$2a$10$Sdqgw.NOkcZGBHwMPjhWBuGW2kMmgeMkQc.36mOaCxsNhSB6JRwL2'),
       (3, true, 'Nikita', 'Bukato', 'bugatti', 'bugatti',
        '$2a$10$Sdqgw.NOkcZGBHwMPjhWBuGW2kMmgeMkQc.36mOaCxsNhSB6JRwL2');

INSERT INTO item (id, borrowed, name, owner_id)
VALUES (1, false, 'MacBook', 1),
       (2, false, 'iPhone', 1),
       (3, false, 'Lenovo', 2),
       (4, false, 'Xiaomi', 2);