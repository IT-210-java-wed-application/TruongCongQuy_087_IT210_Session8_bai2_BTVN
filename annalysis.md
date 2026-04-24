# 1. Nguyên nhân cốt lõi
Trong Spring MVC, khi bạn sử dụng `@Valid` (hoặc `@Validated`), Spring sẽ thực hiện kiểm tra dữ liệu ngay lập tức. Kết quả của việc kiểm tra đó (có lỗi hay không) sẽ được nạp vào đối tượng `BindingResult`.

> **Quy tắc vàng:** `BindingResult` bắt buộc phải đứng ngay sau đối tượng mà nó kiểm tra.

---

# 2. Phân tích lỗi trong code
Hãy nhìn vào danh sách tham số trong hàm:

```java
@Valid @ModelAttribute("employee") EmployeeDto employee; // Đối tượng cần kiểm tra
Model model;
BindingResult bindingResult; // Kết quả kiểm tra
```

**Kết quả:**

Vì có `Model model` nằm xen giữa, Spring bị "mất dấu". Nó không biết phải nạp kết quả validation của `employee` vào đâu.

Khi Spring thấy dữ liệu sai (ví dụ: Tuổi 16), nó tìm biến `BindingResult` ngay kế tiếp nhưng không thấy (vì gặp `Model` trước).

Theo cơ chế mặc định, nếu không tìm thấy chỗ để ghi lỗi, Spring sẽ coi đây là một lỗi nghiêm trọng và ném ra ngoại lệ `MethodArgumentNotValidException`.

Ngoại lệ này khiến Server trả về mã **400 Bad Request** và dừng chương trình ngay lập tức. Đó là lý do code của bạn không bao giờ chạy đến dòng `if (bindingResult.hasErrors())`.
