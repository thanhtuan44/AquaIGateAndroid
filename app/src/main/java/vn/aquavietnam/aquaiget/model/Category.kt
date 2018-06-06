package vn.aquavietnam.aquaiget.model

import android.databinding.BaseObservable
import android.os.Parcelable


/**
 * Created by ThanhTuan on 3/21/2018.
 */
data class Category(var categoryImage: Int?, var categoryName: String) : BaseObservable()
data class CategoryInfo(var image: String?, var title: String, var subTitle: String,var url: String) : BaseObservable()
data class ProductInfoDetail(var image: String?,var model: String?,var info: String?, var listInfo: ArrayList<InfoDetail>?): BaseObservable()
data class InfoDetail(var info: String?): BaseObservable()
data class DataBase(var keyData: String, var valueData: String) : BaseModel()
data class ListProducts(var image: String, var title: String, var info1: String, var info2: String, var info3: String,var urlDetail: String, var urlParent: String) : BaseModel()
