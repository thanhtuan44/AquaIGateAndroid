package vn.aquavietnam.aquaiget.common.customview.image

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority

import com.bumptech.glide.load.engine.DiskCacheStrategy
import vn.aquavietnam.aquaiget.R
import com.bumptech.glide.request.RequestOptions



/**
 * Created by ThanhTuan on 3/27/2018.
 */
class AquaImageView (context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    private var mMatchPresets: Boolean = false
    private var mCircleTransform: Boolean = false
    private var mSetSquare: Boolean = false

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AquaImageView, 0, 0)
        try {
            mMatchPresets = a.getBoolean(R.styleable.AquaImageView_matchPresets, false)
            mCircleTransform = a.getBoolean(R.styleable.AquaImageView_circleTransform, false)
            mSetSquare = a.getBoolean(R.styleable.AquaImageView_setSquare, false)
        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // Set a square layout.
        if (mSetSquare) {
            val width = measuredWidth
            this.setMeasuredDimension(width, width)
        }
    }

    companion object {
        @BindingAdapter("backGround")
        fun setBackGround(view: AquaImageView, backGround: String) {
            if (backGround == null || backGround.isEmpty()) return
            if (backGround.startsWith("http")) {
                val options = RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.img_place_holder_1)
                        .error(R.drawable.ic_menu_camera)
                Glide.with(view.context).load(backGround)
                        .apply(options)
                        .into(view);
                return
            }
            view.setBackgroundColor(Color.parseColor("#" + backGround))
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: AquaImageView, imageUrl: String?) {
            if (imageUrl != null && imageUrl.isEmpty()) return
            if (view.visibility != View.VISIBLE) return
            if (view.mCircleTransform) {
                val options = RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_menu_camera)
                        .error(R.drawable.ic_menu_camera)
                        .transform(ImageLoader.CircleTransform(view.context))
                Glide.with(view.context).load(imageUrl)
                        .apply(options)
                        .into(view);
            } else {
                val options = RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.img_place_holder_1)
                        .error(R.drawable.img_place_holder_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)

                Glide.with(view.context).load(imageUrl)
                        .apply(options)
                        .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("image")
        fun setImageUrl(view: AquaImageView, imageUrl: Int) {
            var imageUrl = imageUrl
            if (imageUrl == 0) return
            if (view.visibility != View.VISIBLE) {
                return
            }
            if (view.mCircleTransform) {
                val options = RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_menu_camera)
                        .error(R.drawable.ic_menu_camera)
                        .transform(ImageLoader.CircleTransform(view.context))
                Glide.with(view.context).load(imageUrl)
                        .apply(options)
                        .into(view);
            } else {
                val options = RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.img_place_holder_1)
                        .error(R.drawable.img_place_holder_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)

                Glide.with(view.context).load(imageUrl)
                        .apply(options)
                        .into(view)
            }
        }
    }
}