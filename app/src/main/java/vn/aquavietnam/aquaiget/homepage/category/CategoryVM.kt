package vn.aquavietnam.aquaiget.homepage.category

import vn.aquavietnam.aquaiget.base.BaseVM
import android.content.Context
import vn.aquavietnam.aquaiget.model.CategoryInfo
import java.util.ArrayList

class CategoryVM(context: Context?, type: Int) : BaseVM() {

    var categorytAdapter: CategorytAdapter
    init {
        this.context = context
        if(type == 1){
            categorytAdapter = CategorytAdapter(context!!,initDataWM(),type)
        } else if(type == 2){
            categorytAdapter = CategorytAdapter(context!!,initDataREF(),type)
        } else if(type == 3){
            categorytAdapter = CategorytAdapter(context!!,initDataAIR(),type)
        } else if(type == 4){
            categorytAdapter = CategorytAdapter(context!!,initDataHotWaterMachine(),type)
        } else{
            categorytAdapter = CategorytAdapter(context!!,initDataOthers(),type)
        }

        initRecycler(categorytAdapter,1)
    }

    private fun initDataWM(): List<CategoryInfo> {
        val prods = ArrayList<CategoryInfo>()
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/04/may-giat-cua-truoc.png","Máy Giặt Lồng Ngang","Công nghệ Auto Dosing - tự động phân bổ nước giặt và xả: đổ đầy 1 lần giặt khoảng 20 lần.","http://aquavietnam.com.vn/danh-muc-san-pham/may-giat/may-giat-long-ngang/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/09/may-giat-long-dung.png","Máy Giặt Lồng Đứng","Công nghệ mới \"Mâm giặt kép\" mang lại hiệu quả giặt tốt nhất.","http://aquavietnam.com.vn/danh-muc-san-pham/may-giat/may-giat-long-dung/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/09/may-giat-long-nghieng.png","Máy Giặt Thùng Nghiêng","Máy giặt với tác động giặt 3 chiều, tăng hiệu quả giặt sạch.","http://aquavietnam.com.vn/danh-muc-san-pham/may-giat/may-giat-long-nghieng/"))
        return prods
    }
    private fun initDataREF(): List<CategoryInfo> {
        val prods = ArrayList<CategoryInfo>()
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/AQR-IG585AS-L-copy1.png","Side By Side","Tủ lạnh thiết kế mặt gương sang trọng, dung tích ngăn đông lớn, tiện dụng.","http://aquavietnam.com.vn/danh-muc-san-pham/tu-lanh/side-by-side/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/01/1111.png","Nhiều Cửa","Tủ lạnh với ngăn khô và ẩm riêng biệt, giúp bảo vệ đa dạng thực phẩm.","http://aquavietnam.com.vn/danh-muc-san-pham/tu-lanh/tu-lanh-nhieu-cua/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/03/thuc-pham-tren-2.png","Ngăn Thực Phẩm Trên","Thiết kế ngăn thực phẩm trên nâng tầm tiện nghi, bảo vệ cột sống.","http://aquavietnam.com.vn/danh-muc-san-pham/tu-lanh/ngan-thuc-pham-tren/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/AQR-IG377DN-GBOPEN12.png","Ngăn Đá Trên","Thiết kế mặt gương sang trọng, Inverter tiết kiệm điện.","http://aquavietnam.com.vn/danh-muc-san-pham/tu-lanh/ngan-da-tren/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/AQR-95AR_L-1.png","Làm Lạnh Trực Tiếp","Nhỏ gọn, phù hợp với mọi không gian.","http://aquavietnam.com.vn/danh-muc-san-pham/tu-lanh/lam-lanh-truc-tiep/"))
        return prods
    }
    private fun initDataAIR(): List<CategoryInfo> {
        val prods = ArrayList<CategoryInfo>()
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/04/dieu-hoa-dawn1.png","Điều Hòa Cao Cấp","Thiết kế độc đáo cùng công nghệ AQUA Fresh – Tự làm sạch 3 bước tiên tiến.","http://aquavietnam.com.vn/danh-muc-san-pham/may-dieu-hoa/dieu-hoa-cao-cap/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/04/dieu-hoa-flexis1.png","Điều Hòa Inverter","Hoạt động êm ái và tiết kiệm điện lên đến 63%.","http://aquavietnam.com.vn/danh-muc-san-pham/may-dieu-hoa/may-dieu-hoa-inverter/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/AQA-KCH12JA-Right.png","Điều Hòa Hai Chiều","Làm lạnh và sưởi ấm.","http://aquavietnam.com.vn/danh-muc-san-pham/may-dieu-hoa/may-dieu-hoa-hai-chieu/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/AQA-KR12JA-Left.png","Điều Hòa Phổ Thông","Làm lạnh nhanh.","http://aquavietnam.com.vn/danh-muc-san-pham/may-dieu-hoa/may-lanh-pho-thong/"))
        return prods
    }
    private fun initDataHotWaterMachine(): List<CategoryInfo> {
        val prods = ArrayList<CategoryInfo>()
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/04/dieu-hoa-dawn1.png","Máy Nước Nóng Gián Tiếp","Công nghệ chống sốc điện - Shock Proof (Safe Care) - an toàn tuyệt đối, lựa chọn tối ưu, bảo vệ cho người sử dụng.","http://aquavietnam.com.vn/danh-muc-san-pham/may-nuoc-nong/may-nuoc-nong-gian-tiep/"))
        return prods
    }
    private fun initDataOthers(): List<CategoryInfo> {
        val prods = ArrayList<CategoryInfo>()
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2018/03/tudong-icon.jpg","Tủ Đông","Tủ đông Aqua được sản xuất theo công nghệ tiên tiến từ Nhật Bản, làm lạnh nhanh chóng và tiết kiệm điện năng tối ưu.","http://aquavietnam.com.vn/danh-muc-san-pham/san-pham-khac/tu-dong/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/may-giat-long-ngang.png","Tủ Mát","Tủ mát AQUA sở hữu thiết kế thanh gọn, hiện đại phù hợp với mọi không gian, cùng hệ thống làm bằng lồng sóc giúp duy trì nhiệt độ làm lạnh ổn định, bảo vệ thực phẩm, đồ uống luôn tươi ngon, mát lạnh mỗi ngày.","http://aquavietnam.com.vn/danh-muc-san-pham/san-pham-khac/tu-mat/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/WS49GDB-800x800-copy.png","Tủ Rượu","Tủ rượu AQUA hoạt động êm ái, cùng hệ thống bảo quản nhiệt độ tiên tiến giúp giữ nguyên sức hấp dẫn và mùi vị cay nồng đặc trưng của từng loại rượu.","http://aquavietnam.com.vn/danh-muc-san-pham/san-pham-khac/tu-ruou/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/coton02.png","Dụng Cụ Cầm Tay - Tẩy Rửa Quần Áo","Dụng cụ cầm tay - tẩy rửa quần áo với thiết kế siêu nhỏ gọn và tiện lợi, giặt nhanh chóng chỉ trong 30s giúp cho cuộc sống đơn giản hơn.","http://aquavietnam.com.vn/danh-muc-san-pham/san-pham-khac/dung-cu-tay-rua-quan-ao/"))
        prods.add(CategoryInfo("http://aquavietnam.com.vn/wp-content/uploads/2017/10/121312.png","Hàng Gia Dụng","Sự kết hợp tinh tế giữa thiết kế trang nhã mang phong cách đương đại cùng các tính năng tiên tiến Nhật Bản, sản phẩm gia dụng AQUA tự tin mang đến người sử dụng những tiện ích tuyệt vời, đồng thời góp phần làm tăng thêm vẻ đẹp cho không gian sống của bạn.","http://aquavietnam.com.vn/danh-muc-san-pham/san-pham-khac/hang-gia-dung/"))
        return prods
    }
}