INSERT INTO client(id, email, name, password, phone, role, uuid_user) VALUES
    (1,'ooo@gmail.com','client 1','papslsd','09292','CLIENT','qsqdsqd');
INSERT INTO stock(id, address, email, name, password, phone)
VALUES (1,'avenue 1 alal lfasi','stock@manager.com','staock1','passpass','067576767');

INSERT into product(id, description, initial_price, quantity, reference, title, stock_id)
VALUES (1,'test description',10,100,'P165','product1',1);

INSERT INTO orders(id, date, status, total_price, created_at, reference, client_id)
VALUES (
           1,DATE(now()),
           0,
           50,
           DATE(NOW()),
           'AFR878',
           1
       );