INSERT INTO status (name)
VALUES ('PENDING'),
       ('ACCEPTED'),
       ('DECLINED'),
       ('RETURNED');

INSERT INTO users (admin, first_name, last_name, login, middle_name, password)
VALUES (false, 'Vlad', 'Kukoba', 'kukobistr', 'kukobistr',
        '$2a$10$Sdqgw.NOkcZGBHwMPjhWBuGW2kMmgeMkQc.36mOaCxsNhSB6JRwL2'),
       (false, 'Andrey', 'Egorov', 'MyFosse', 'MyFosse',
        '$2a$10$Sdqgw.NOkcZGBHwMPjhWBuGW2kMmgeMkQc.36mOaCxsNhSB6JRwL2'),
       (true, 'Nikita', 'Bukato', 'bugatti', 'bugatti',
        '$2a$10$Sdqgw.NOkcZGBHwMPjhWBuGW2kMmgeMkQc.36mOaCxsNhSB6JRwL2');

INSERT INTO item (borrowed, name, owner_id)
VALUES (false, 'MacBook', 1),
       (false, 'iPhone', 1),
       (false, 'Lenovo', 2),
       (false, 'Xiaomi', 2);