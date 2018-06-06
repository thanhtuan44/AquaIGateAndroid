package vn.aquavietnam.aquaiget.common.customview.image

import android.content.Context
import android.graphics.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource

import java.security.MessageDigest

class ImageLoader {

    class CircleTransform(private val mBitmapPool: BitmapPool) : Transformation<Bitmap> {

        constructor(context: Context) : this(Glide.get(context).bitmapPool) {}

        override fun transform(context: Context, resource: Resource<Bitmap>, outWidth: Int, outHeight: Int): Resource<Bitmap> {
            val source = resource.get()
            val size = Math.min(source.width, source.height)
            val width = (source.width - size) / 2
            val height = (source.height - size) / 2
            var bitmap: Bitmap? = mBitmapPool.get(size, size, Bitmap.Config.ARGB_8888)
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(bitmap!!)
            val paint = Paint()
            val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            if (width != 0 || height != 0) {
                val matrix = Matrix()
                matrix.setTranslate((-width).toFloat(), (-height).toFloat())
                shader.setLocalMatrix(matrix)
            }
            paint.shader = shader
            paint.isAntiAlias = true
            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)
            return BitmapResource.obtain(bitmap, mBitmapPool)!!
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {

        }
    }
}
