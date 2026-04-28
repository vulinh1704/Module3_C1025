Ghi chú về session.

Hiểu nhanh về session theo kiểu “đời thường”:

Session là gì?

Session giống như một “phiên làm việc” riêng của từng người dùng khi họ truy cập website.

👉 Khi bạn vào web, server sẽ tạo một session để “ghi nhớ” bạn là ai trong suốt thời gian bạn dùng web đó.

Không có session → mỗi request là “người lạ”
Có session → server nhớ bạn giữa các request
Ví dụ dễ hiểu

Bạn đăng nhập vào một website:

Bạn nhập username + password
Server kiểm tra đúng → tạo session
Server lưu: user = Linh trong session
Những lần request sau → server biết bạn đã đăng nhập rồi

👉 Nếu không dùng session, bạn phải đăng nhập lại mỗi lần click trang 😅

Session hoạt động như thế nào?
Server tạo 1 session ID
Trình duyệt lưu ID này (thường bằng cookie)
Mỗi lần gửi request → gửi kèm session ID
Server dùng ID → lấy đúng dữ liệu của bạn

======
Ghép bảo mật vào bài kiểm tra;

👉 Gợi ý case study (Nên sử dụng AI ~ kiểm soát AI):
+ Bán hàng
+ Blog 
+ Thư viện
Các bước làm case study:
+ Nêu ý tưởng (chọn chủ đề)
+ Phân tích các thực thể -> thiết kế database -> tạo database (tương đối)
+ Thiết kế giao diện (tham khảo các website lớn) -> sử dụng css thuần hoặc bootstrap để xây dựng giao diện
  (Dễ nhìn, dễ thao tác)
+ Ghép logic hệ thống với Java Servlet
  * Nên ghép bảo mật ngay từ đầu.
  * Nếu không cần thiết cần user có thể ghép bảo mật sau.