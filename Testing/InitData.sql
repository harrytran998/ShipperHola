EXEC sp_MSForEachTable 'DISABLE TRIGGER ALL ON ?'
GO
EXEC sp_MSForEachTable 'ALTER TABLE ? NOCHECK CONSTRAINT ALL'
GO
EXEC sp_MSForEachTable 'DELETE FROM ?'
GO
EXEC sp_MSForEachTable 'DBCC CHECKIDENT (''?'', RESEED, 0)'
GO
EXEC sp_MSForEachTable 'ALTER TABLE ? CHECK CONSTRAINT ALL'
GO
EXEC sp_MSForEachTable 'ENABLE TRIGGER ALL ON ?'
GO
-- Account --
GO
INSERT INTO [Account](username, password, fullName, gender, dateOfBirth, email, phoneNumber, address, facebookUrl, role) VALUES
('admin', 'Xvideos123', N'Quản trị viên', 1, '1970-01-01', 'xvideosteam@abc.xyz', '0969696969', N'XVideos Team', NULL, 'admin'),
('test', '123456', N'Tester', 0, '1991-01-01', 'xvideosteam.tester@abc.xyz', '0969696969', N'XVideos Team', NULL, 'user'),
('test2', '123456', N'Tester 2', 0, '1995-05-05', 'xvideosteam.tester@abc.xyz', '0996969696', N'XVideos Team', NULL, 'user')
GO
-- Category --
INSERT INTO [Category](name, description) VALUES
(N'Đồ mặn', N'Cơm, bún, mì, thịt, xôi, bánh mì, ...'),
(N'Đồ ngọt', N'Bánh ngọt, bim bim, kẹo, bánh mặn, ...'),
(N'Đồ uống', N'Cafe, trà sữa, nước ngọt có ga, ...'),
(N'Khác', N'Bàn phím, sách, lót chuột, quần áo, ...')
GO
-- ShippingAddress --
INSERT INTO [ShippingAddress](address) VALUES
(N'Dom A'),
(N'Dom B'),
(N'Dom C'),
(N'Dom D'),
(N'Dom F')
GO
-- Product --
INSERT INTO [Product](date, name, description, currentPrice, allowOrder, categoryId, sellerId) VALUES
('2017-09-15 11:00:00', N'Cơm suất', N'Cực ngon.', 25000, 0, 1, 1),
('2017-09-15 19:30:00', N'Trà sữa socola', N'Best.', 15000, 0, 3, 1),
('2017-09-15 20:00:00', N'Sinh tố bơ', N'Làm từ bơ chất lượng.', 20000, 0, 3, 1),
('2017-09-16 11:20:00', N'Cơm rang thập cẩm', N'Tuyệt vời.', 25000, 0, 1, 1),
('2017-09-16 15:30:00', N'Bánh su kem', N'Order nhanh kẻo hết.', 18000, 1, 2, 1),
('2017-09-16 19:20:00', N'Bánh Akiko', N'Số lượng có hạn.', 16000, 1, 2, 1),
('2017-09-16 20:00:00', N'Lót chuột Razer', N'Lướt êm ru.', 90000, 1, 4, 1),
('2017-09-17 10:30:00', N'Bún chả', N'Nhiều thịt, nhiều bún.', 25000, 1, 1, 1),
('2017-09-17 18:45:00', N'Nước ngọt Mountain Dew', N'Ship kèm đá.', 12000, 1, 3, 1),
('2017-09-17 20:10:00', N'Thuốc lá Vina', N'Phê.', 20000, 1, 4, 1)
-- ProductPicture --
INSERT INTO [ProductPicture](fileName, productId) VALUES
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
GO
-- Order --
INSERT INTO [Order](date, productId, quantity, price, buyerId, buyerAddress, buyerPhoneNumber, paymentMethod, status) VALUES
('2017-09-15 11:10:00', 1, 1, 25000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-15 11:15:00', 1, 1, 25000, 3, N'XVideos Team', '0996969696', 'COD', 'FINISHED'),
('2017-09-15 19:40:00', 2, 3, 15000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-15 20:15:00', 3, 2, 20000, 3, N'XVideos Team', '0996969696', 'COD', 'FINISHED'),
('2017-09-15 20:20:00', 3, 1, 20000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-16 11:30:00', 4, 1, 25000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-16 16:00:00', 5, 1, 18000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-16 16:05:00', 5, 2, 18000, 3, N'XVideos Team', '0996969696', 'COD', 'FINISHED'),
('2017-09-16 20:00:00', 6, 2, 16000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-16 20:20:00', 7, 1, 90000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-17 10:45:00', 8, 1, 25000, 3, N'XVideos Team', '0996969696', 'COD', 'FINISHED'),
('2017-09-17 11:00:00', 8, 1, 25000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-17 12:00:00', 8, 1, 25000, 2, N'XVideos Team Room 2', '0969696969', 'COD', 'FINISHED'),
('2017-09-17 19:00:00', 9, 4, 12000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED'),
('2017-09-17 20:30:00', 10, 2, 20000, 2, N'XVideos Team', '0969696969', 'COD', 'FINISHED')
GO
-- ProductReview --
INSERT INTO [ProductReview](date, productId, accountId, rating, content) VALUES
('2017-09-15 11:10:00', 1, 2, 3, N'Tàm tạm.'),
('2017-09-15 11:15:00', 1, 3, 4, N'Cũng ngon đấy.'),
('2017-09-15 19:40:00', 2, 2, 5, N'Tuyệt vời.'),
('2017-09-15 20:15:00', 3, 3, 2, N'Nhạt toẹt.'),
('2017-09-15 20:20:00', 3, 2, 1, N'Quá dở. Lần sau sẽ không mua nữa.'),
('2017-09-16 11:30:00', 4, 2, 4, N'Ngon đấy.'),
('2017-09-16 16:00:00', 5, 2, 4, N'Bánh mềm và ngon.'),
('2017-09-16 16:05:00', 5, 3, 3, N'Ăn cũng được.'),
('2017-09-16 20:20:00', 7, 2, 3, N'OK trong tầm giá.'),
('2017-09-17 10:45:00', 8, 3, 5, N'Bún ngon và đầy đủ.'),
('2017-09-17 11:00:00', 8, 2, 4, N'Bún ăn ngon. Chỉ duy nhất 1 lần có sâu trong rau thơm.')
GO
-- SearchKeyword --
INSERT INTO [SearchKeyword](keyword, searchCount) VALUES
('cơm', 7),
('trà sữa', 3),
('sinh tố', 4),
('bánh', 4),
('pad chuột', 1),
('lót chuột', 2),
('phở', 1),
('bún', 8),
('nước ngọt', 3),
('thuốc lá', 2)
GO