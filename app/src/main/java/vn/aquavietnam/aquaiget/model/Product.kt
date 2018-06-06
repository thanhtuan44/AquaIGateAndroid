package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 4/20/2018.
 */
@JsonObject
class ListProduct: BaseModel() {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("data"))
    var data: List<Product>? = null
}
@JsonObject
class Product: BaseModel() {
    @JsonField(name = arrayOf("Image"))
    var image: String? = ""
    @JsonField(name = arrayOf("ModelType"))
    var model: String? = ""
    @JsonField(name = arrayOf("Color"))
    var color: String? = ""
    @JsonField(name = arrayOf("Info"))
    var info: String? = ""
    @JsonField(name = arrayOf("NumberSales"))
    var number: String? = ""
    @JsonField(name = arrayOf("Price"))
    var price: String? = ""
    @JsonField(name = arrayOf("Id"))
    var id: String? = ""
}
@JsonObject
class ProductDetail: BaseModel() {
    @JsonField(name = arrayOf("status"))
    var status: String? = ""
    @JsonField(name = arrayOf("message"))
    var message: String? = ""
    @JsonField(name = arrayOf("data"))
    var dataProductDetail: ProductDetailData? = ProductDetailData()
}
@JsonObject
class ProductDetailData : BaseModel() {
    @JsonField(name = arrayOf("Features"))
    var feature: List<String>? = null
    @JsonField(name = arrayOf("Images"))
    var images: List<String>? = null
}
@JsonObject
class ProductDetailFeature: BaseModel() {
    var data: String? = ""
}
@JsonObject
class ProductDetailImage: BaseModel() {
    var data: String? = ""
}