DELETE FROM Product
DBCC CHECKIDENT ('Product', RESEED, 0);
INSERT INTO Product(date, name, description, currentPrice, allowOrder, categoryId, sellerId) VALUES
('2017-09-15 11:00:00', 'Cơm suất', 'Cực ngon.', 25000, 0, 1, 1),
('2017-09-15 19:30:00', 'Trà sữa socola', 'Best.', 15000, 0, 3, 1),
('2017-09-15 20:00:00', 'Sinh tố bơ', 'Làm từ bơ chất lượng.', 20000, 0, 3, 1),
('2017-09-16 11:20:00', 'Cơm rang thập cẩm', 'Tuyệt vời.', 25000, 1, 1, 1),
('2017-09-16 15:30:00', 'Bánh su kem', 'Order nhanh kẻo hết.', 18000, 1, 2, 1),
('2017-09-16 19:20:00', 'Bánh Akiko', 'Số lượng có hạn.', 16000, 1, 2, 1),
('2017-09-16 20:00:00', 'Lót chuột Razer', 'Lướt êm ru.', 90000, 1, 4, 1),
('2017-09-17 10:30:00', 'Bún chả', 'Nhiều thịt, nhiều bún.', 25000, 1, 1, 1),
('2017-09-17 18:45:00', 'Mountain Dew', 'Ship kèm đá.', 12000, 1, 3, 1),
('2017-09-17 20:10:00', 'Thuốc lá Vina', 'Phê.', 20000, 1, 4, 1)
INSERT INTO ProductPicture(fileName, productId) VALUES
('/resources/img/products/1.jpg',1),
('/resources/img/products/2.jpg',2),
('/resources/img/products/3.jpg',3),
('/resources/img/products/4.jpg',1),
('/resources/img/products/5.jpg',5),
('/resources/img/products/6.jpg',6),
('/resources/img/products/7.jpg',7),
('/resources/img/products/8.jpg',8),
('/resources/img/products/9.jpg',9),
('/resources/img/products/10.jpg',10)