package vn.aquavietnam.aquaiget.model

import android.databinding.BaseObservable
import android.os.Parcel
import android.os.Parcelable
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by ThanhTuan on 3/21/2018.
 */
@JsonObject
open class BaseModel() : BaseObservable(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }
    companion object CREATOR : Parcelable.Creator<BaseModel> {
        override fun createFromParcel(parcel: Parcel): BaseModel {
            return BaseModel(parcel)
        }

        override fun newArray(size: Int): Array<BaseModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}