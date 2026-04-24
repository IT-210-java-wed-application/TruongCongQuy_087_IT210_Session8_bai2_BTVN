# Phân tích lỗi và nguyên nhân

## 1. Tại sao lọt khoảng trắng vào Database?
**Nguyên nhân:** Đoạn mã sử dụng annotation `@NotNull`.

**Cơ chế lỗi:**
- `@NotNull` chỉ kiểm tra xem giá trị có phải là null hay không.
- Khi người dùng nhập dấu cách (Space), giá trị truyền vào là một chuỗi có ký tự trắng (ví dụ: "  ").

**Kết quả:**
- Một chuỗi toàn khoảng trắng không phải là null, nên nó vượt qua được kiểm tra của `@NotNull`.
- Hệ thống coi đó là dữ liệu hợp lệ và lưu vào Database, dẫn đến việc nhãn dán vận chuyển bị trống thông tin.

---

## 2. Tại sao Postman gửi request gây lỗi 500?
Lỗi này xuất phát từ hai thiếu sót nghiêm trọng ở tầng Controller:

### a. Thiếu `@Valid`
- Trong hàm `updateAddress`, tham số `@RequestBody AddressDto addressDto` thiếu annotation `@Valid`.
- Nếu không có nhãn này, Spring Boot sẽ bỏ qua hoàn toàn việc kiểm tra các ràng buộc (như `@NotNull`) trong DTO.
- Mọi dữ liệu từ Postman (kể cả null) đều được "mời" vào sâu bên trong hệ thống.

### b. Gây lỗi 500 (Internal Server Error)
- Khi dữ liệu lỗi (null) lọt vào đến tầng nghiệp vụ (Service/Repository), nếu code bên trong thực hiện các thao tác xử lý chuỗi hoặc lưu vào DB với cột ràng buộc NOT NULL, hệ thống sẽ văng ra ngoại lệ `NullPointerException` hoặc `DataIntegrityViolationException`.
- Vì bạn chưa có bộ bắt lỗi tập trung, Spring sẽ mặc định trả về lỗi 500 thay vì thông báo lỗi 400 (Bad Request) cho người dùng.
