package vn.aquavietnam.aquaiget.base

import android.content.Context
import android.graphics.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper.Callback
import android.support.v7.widget.helper.ItemTouchHelper.*
import android.view.View
import android.graphics.RectF
import android.graphics.Bitmap
import vn.aquavietnam.aquaiget.R
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class Swipe(context: Context?) : Callback() {
    private var swipeBack = false
    private var context : Context? = null

    init {
        this.context = context
    }
    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return makeMovementFlags(0, LEFT)
    }
    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        val position = viewHolder!!.getAdapterPosition()
    }
    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val paint = Paint()
        val icon1: Bitmap
        val icon2: Bitmap
        val icon3: Bitmap

        if (actionState == ACTION_STATE_SWIPE) {
            var itemView: View = viewHolder!!.itemView
            viewHolder.itemView.setTranslationX(dX/2)

            val background = RectF(itemView.right.toFloat(), itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            c!!.drawRect(background, paint)

            val height = itemView.getBottom().toFloat() - itemView.getTop().toFloat()
            val width = height

            val dr1 = context!!.getResources().getDrawable(R.drawable.ic_menu_camera)
            icon1 = drawableToBitmap(dr1);
            val icon_dest1 = RectF(itemView.right.toFloat() -  width, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            c.drawBitmap(icon1, null, icon_dest1, paint)

            val dr2 = context!!.getResources().getDrawable(R.drawable.ic_menu_gallery)
            icon2 = drawableToBitmap(dr2);
            val icon_dest2 = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat(), itemView.right.toFloat() - width, itemView.bottom.toFloat())
            c.drawBitmap(icon2, null, icon_dest2, paint)

            val dr3 = context!!.getResources().getDrawable(R.drawable.ic_menu_send)
            icon3 = drawableToBitmap(dr3);
            val icon_dest3 = RectF(itemView.right.toFloat() - 3 * width, itemView.top.toFloat(), itemView.right.toFloat() - 2 * width, itemView.bottom.toFloat())
            c.drawBitmap(icon3, null, icon_dest3, paint)

        }else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}