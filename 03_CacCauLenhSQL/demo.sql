use food_vn;
-- select * from product where price >= 40000 and quantity > 20;
select * from product where price >= 40000 or quantity > 20; -- toán tử or
select * from product where price between 40000 and 60000;-- giá từ 40k => 60k
select * from product where category_id is null; -- lấy ra sản phẩm có category_id là null
select * from product where category_id is not null;
select * from product where price <> 40000; -- giống !=
select * from product where name like "%u"; -- tìm sản phẩm có chứa chữ a
select * from product where price in (40000, 50000, 60000); -- lấy sản phẩm có giá trong dãy 40k 50k 60k
-- ngược lại in => not in
-- group by: phân nhóm
-- đếm số lượng sản phẩm theo category_id:
select category_id, count(product.id) as 'số lượng sản phẩm' 
from product group by category_id; -- count: hàm đếm 
-- tổng giá tiền theo category_id;
select category_id, sum(product.price) as 'tổng giá tiền' 
from product group by category_id; -- sum: tính tổng
select min(price) from product; -- min: lấy ra giá trị nhỏ nhất, ngược lại có hàm max

-- đếm số lượng sản phẩm theo category_id bằng 1;
select category_id, count(product.id) as 'số lượng sản phẩm' 
from product where category_id = 1 group by category_id;
-- where: nằm trước group by, chỉ đi được với cột

-- đếm số lượng sản phẩm theo category_id, lấy ra dữ liệu có lớn 1 sản phẩm:
select category_id, count(product.id) as 'Số sản phẩm'
from product group by category_id having count(product.id) > 1;
-- having: nằm sau group by, chỉ đi được với hàm

-- sắp xếp dữ liệu: order by
select * from product order by price desc;-- asc tăng dần -> mặc định, giảm dần: desc
-- limit: giới hạn số bản ghi, sử dụng phần trang 
-- nếu cần skip thì sẽ sử dụng offset
select * from product limit 4 OFFSET 10;

select p.name as 'productName', p.price, p.category_id, c.name as 'categoryName' from product p
join category c on p.category_id = c.id; 

select p.name as 'productName', p.price, p.category_id from product p;

select p.name as 'productName', p.price, p.category_id, c.name as 'categoryName' from product p
left join category c on p.category_id = c.id; -- lấy ra tất ra bản ghi bảng bên trái
drop database demo_2006;


