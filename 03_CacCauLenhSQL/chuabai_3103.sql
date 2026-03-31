use demo2006;
-- In ra các sản phẩm có số lượng từ 30 trở lên.
select * from product where quantity >= 30;
-- In ra các sản phẩm có số lượng từ 30 trở lên và có giá trong khoảng 100 đến 200.
select * from product where quantity >= 30 and price between 100 and 200;
-- In ra thông tin khách hàng tuổi lớn hơn 18
select * from customer where age > 18;
-- In ra thông tin khách hàng họ Nguyễn và lớn hơn 20 tuổi
select * from customer where name like "%Nguyễn%" and age > 20;
-- In ra sản phẩm tên bắt đầu bằng chữ M
select * from product where name like "M%";
-- In ra sản phẩm kết thúc bằng chữ E
select * from product where name like "%E";
-- In ra danh sách sản phẩm số lượng tăng dần
select * from product order by quantity; -- default: asc
-- In ra danh sách sản phẩm giá giảm dần
select * from product order by price desc; 

-- In ra tổng số lượng sản phẩm giá nhỏ hơn 300
select sum(quantity) as 'tổng số lượng' from product where price < 300;
-- In tổng số sản phẩm theo từng giá
select price, count(id) as 'số sản phẩm' from product group by price; -- từng, gom nhóm
-- In ra sản phẩm có giá cao nhất
select * from product where price = (select max(price) from product); -- truy vấn lồng
-- In ra giá trung bình của tất cả các sản phẩm
select avg(price) as 'giá trung bình' from product;
-- In ra tổng số tiền nếu bán hết tất cả sản phẩm.
select sum(price * quantity) as 'totalPrice' from product;
-- Tính tổng số tiền của các sản phẩm giá <300.
select sum(price) from product where price < 300;
-- Tìm giá bán cao nhất của các sản phẩm bắt đầu bằng chữ M.
select name as 'productName', price from product 
where name like 'M%' and price = (select max(price) from product);
-- Tìm giá bán thấp nhất của các sản phẩm bắt đầu bằng chữ M.
select name as 'productName', price from product 
where name like 'M%' and price = (select min(price) from product);
-- Tìm giá bán trung bình của các sản phẩm bắt đầu bằng chữ M.
select avg(price) from product where name like 'M%';

-- Mức 3: Thêm bảng category: id, tên. Thêm trường idCategory cho bảng Product
create table category(
	id int primary key auto_increment,
	name varchar(100) not null
);
insert into category(name) values ("Đồ ăn");
select * from category;
alter table product add column idCategory int;
alter table product add foreign key product(idCategory) references category(id);
-- update
-- In ra tên khách hàng và thời gian mua hàng.
select c.name, o.time from customer c 
join `order` o on c.id = o.customerId;
-- In ra tên khách hàng và tên sản phẩm đã mua
select c.name as 'customerName', p.name as 'productName' from customer c
join `order` o on c.id = o.customerId
join orderdetail od on o.id = od.orderId
join product p on od.productId = p.id;
-- In ra tổng số lượng sản phẩm từng loại
select p.idCategory, c.name as 'categoryName', sum(quantity) from product p
join category c on p.idCategory = c.id
group by idCategory;
-- Đếm số mặt hàng từng loại
select p.idCategory, c.name as 'categoryName', count(p.id) from product p
join category c on p.idCategory = c.id
group by idCategory;
-- Tính giá trung bình tất cả các sản phẩm
select avg(price) from product;
-- Tính giá trung bình từng loại
select p.idCategory, c.name as 'categoryName', avg(price) from product p
join category c on p.idCategory = c.id
group by p.idCategory;
-- Tìm giá lớn nhất theo từng loại
select idCategory,c.name as 'categoryName', max(price) as 'giá lớn nhất' from product p
join category c on c.id = p.idCategory
group by idCategory;
-- Tính tuổi trung bình của các khách hàng
select avg(age) from customer;
-- Đếm số khách hàng tuổi lớn hơn 30
-- phân biệt where và having
-- where thì dùng cho cột và trước group by
-- having thì dùng cho hàm và sau group by
select count(id) from customer where age > 20;
-- Đếm số lần mua hàng của từng khách hàng
select o.customerId, c.name as 'tên khách hàng', count(o.id) as 'số lần mua hàng' from `order` o 
join customer c on o.customerId = c.id
group by o.customerId;
-- Đếm số lượng hóa đơn theo từng tháng
select month(o.time), year(o.time), count(o.id) as 'số hóa đơn' 
from `order` o group by month(o.time), year(o.time);
-- In ra mã hoá đơn và giá trị từng hoá đơn
select o.id, sum(p.price * od.quantity) from `order` o
join orderdetail od on o.id = od.orderId
join product p on p.id = od.productId
group by o.id;
-- In ra mã hoá đơn và giá trị hoá đơn giảm dần
select o.id, sum(p.price * od.quantity) as total from `order` o
join orderdetail od on o.id = od.orderId
join product p on p.id = od.productId
group by o.id order by total desc;
-- Tính tổng tiền từng khách hàng đã mua
select c.id, c.name as 'customerName', sum(p.price * od.quantity) as 'total' from customer c 
join `order` o on o.customerId = c.id
join orderdetail od on o.id = od.orderId
join product p on p.id = od.productId
group by c.id;





