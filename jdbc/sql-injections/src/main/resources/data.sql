-- Dummy (Mock = Fake) Data
INSERT INTO user (username, password) VALUES
                                          ('admin', 'admin123'),
                                          ('kasun', 'kasun123'),
                                          ('nuwan', 'nuwan123');

SELECT * FROM user WHERE username = 'kasun' -- ' AND password='123';