Đúng, khi ôn phỏng vấn nên trình bày theo form này:

```text
1. Cần hiểu: nói bản chất ngắn gọn
2. Khác nhau nhanh: liệt kê điểm phân biệt
3. Khi nào dùng: gắn với use case thực tế
4. Câu phỏng vấn: gom lại thành câu văn nói
```

Áp dụng cho **Collections Framework**:

Collections Framework trong Java là bộ interface và class trong `java.util`, dùng để lưu trữ và thao tác với nhiều object.

Cần nhớ 4 nhóm chính:

`List` dùng khi cần danh sách có thứ tự và cho phép trùng.

`Set` dùng khi cần dữ liệu không trùng.

`Map` dùng khi cần lưu dữ liệu dạng key-value, key là duy nhất.

`Queue` dùng khi cần xử lý dữ liệu theo hàng đợi hoặc theo độ ưu tiên.

Khác nhau nhanh:

```text
List  -> có thứ tự, cho phép trùng, truy cập bằng index
Set   -> không cho trùng, không truy cập bằng index
Map   -> lưu key-value, key không trùng
Queue -> xử lý theo hàng đợi hoặc độ ưu tiên
```

Các class hay dùng:

```text
ArrayList     -> List phổ biến, truy cập nhanh
LinkedList    -> List, thêm/xóa đầu/cuối tốt
HashSet       -> Set nhanh, không đảm bảo thứ tự
LinkedHashSet -> Set giữ thứ tự thêm vào
TreeSet       -> Set tự động sort
HashMap       -> Map phổ biến, tìm nhanh theo key
LinkedHashMap -> Map giữ thứ tự thêm vào
TreeMap       -> Map tự động sort theo key
Hashtable     -> Map cũ, synchronized, không cho null
PriorityQueue -> Queue xử lý theo độ ưu tiên
```

Câu phỏng vấn:

Collections Framework là framework trong Java dùng để làm việc với nhóm object. Các interface chính gồm `List`, `Set`, `Map` và `Queue`. `List` dùng cho danh sách có thứ tự và cho phép trùng, `Set` dùng khi cần dữ liệu không trùng, `Map` dùng để lưu key-value và tìm nhanh theo key, còn `Queue` dùng để xử lý dữ liệu theo hàng đợi hoặc độ ưu tiên. Trong thực tế em thường dùng `ArrayList` cho danh sách, `HashSet` để loại bỏ dữ liệu trùng, `HashMap` để lookup theo key và `PriorityQueue` khi cần xử lý theo độ ưu tiên.

Cách trả lời tốt nhất là **không đọc hết lý thuyết**, mà nói theo luồng:

> Collection là gì → có mấy nhóm chính → mỗi nhóm dùng khi nào → ví dụ class hay dùng.
