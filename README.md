# Ứng Dụng Quản Lý Bán Vé Tàu 🚆 (Java Swing + MySQL)

Ứng dụng quản lý bán vé tàu với các chức năng: đặt vé, quản lý nhân viên, toa và ghế, quản lý giá vé & VAT, đăng nhập/đăng ký tài khoản.

---

## 📌 Chức năng chính
- **Đăng nhập & đăng ký** (phân quyền ADMIN / NHÂN VIÊN)
- **Quản lý vé tàu**: đặt vé, hủy vé, xem thông tin vé
- **Quản lý nhân viên**: thêm, sửa, xóa, phân quyền
- **Quản lý toa & ghế**: tạo toa, sắp xếp ghế
- **Quản lý giá & VAT**: tạo bảng giá, tính tổng tiền vé bao gồm VAT
- **Báo cáo doanh thu** (tùy chọn)

---

## 🛠️ Công nghệ sử dụng
- **Java 17**
- **Swing** (Giao diện người dùng)
- **MySQL 8**
- **JDBC** (Kết nối cơ sở dữ liệu)
- **Eclipse IDE** (hoặc IntelliJ IDEA)
- **Maven** (quản lý thư viện) – nếu có

---

## 📂 Cấu trúc thư mục
src/
main/
java/
GUI/ # Màn hình giao diện
DAO/ # Lớp truy vấn dữ liệu
Entity/ # Các lớp đối tượng
Service/ # Xử lý nghiệp vụ
resources/ # File cấu hình
db/
schema.sql # Tạo bảng DB
seed.sql # Dữ liệu mẫu

---

## ⚙️ Cấu hình cơ sở dữ liệu
📂 Khôi phục Cơ sở dữ liệu từ PTUD.bak
Mở SQL Server Management Studio (SSMS).

Kết nối với SQL Server instance của bạn.

Click phải vào mục Databases → chọn Restore Database...

Trong tab Source, chọn:

Device → click ... → Add → chọn file PTUD.bak.

Trong tab Destination, nhập tên database (ví dụ: PTUD).

Kiểm tra tab Files để đảm bảo đường dẫn file .mdf và .ldf hợp lệ trên máy bạn.

Nhấn OK để tiến hành restore.
```java
String url = "jdbc:mysql://localhost:3306/ve_tau";
String user = "root";
String password = "your_password";
🚀 Cách chạy dự án trong Eclipse
Clone dự án từ GitHub


git clone https://github.com/<tên_user>/<tên_repo>.git
Import vào Eclipse

File → Import → Existing Maven Project (nếu dùng Maven)

Hoặc File → Import → Java Project (nếu dự án thuần)

Kết nối DB

Tạo DB ve_tau

Chạy db/schema.sql và db/seed.sql trong MySQL

Chạy chương trình

Mở file Main.java trong thư mục GUI

Chuột phải → Run As → Java Application
👤 Tài khoản mẫu
Xem trong phần QLNV_GUI

