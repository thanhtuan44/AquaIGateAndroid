package vn.aquavietnam.aquaiget.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 3/21/2018.
 */
@JsonObject
class Home1 {
    @JsonField(name = arrayOf("data"))
    var sliderList: List<Slider>? = null
}