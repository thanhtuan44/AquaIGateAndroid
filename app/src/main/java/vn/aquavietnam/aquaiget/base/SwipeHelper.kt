package vn.aquavietnam.aquaiget.base

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.GestureDetector
import java.util.*
import android.graphics.*
import android.view.MotionEvent
import android.content.Context
import android.view.View
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import vn.aquavietnam.aquaiget.R

abstract class SwipeHelper(context: Context, recyclerView: RecyclerView) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

    companion object {
        @JvmField val BUTTON_WIDTH = 100f
    }
    private var context: Context? = null
    private var recyclerView: RecyclerView? = null
    private var buttons: MutableList<UnderlayButton>? = null
    private var gestureDetector: GestureDetector? = null
    private var swipedPos = -1
    private var swipeThreshold = 0.5f
    private var buttonsBuffer: MutableMap<Int, MutableList<UnderlayButton>>? = null
    private var recoverQueue: Queue<Int>? = null
    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            for (button in buttons!!) {
                if (button.onClick(e.x, e.y))
                    break
            }
            return true
        }
    }
    private val onTouchListener = object : View.OnTouchListener {
        override fun onTouch(view: View, e: MotionEvent): Boolean {
            if (swipedPos < 0) return false
            val point = Point(e.x.toInt(), e.y.toInt())

            val swipedViewHolder = recyclerView!!.findViewHolderForAdapterPosition(swipedPos)
            val swipedItem = swipedViewHolder.itemView
            val rect = Rect()
            swipedItem.getGlobalVisibleRect(rect)

            if (e.action == MotionEvent.ACTION_DOWN || e.action == MotionEvent.ACTION_UP || e.action == MotionEvent.ACTION_MOVE) {
                if (rect.top < point.y && rect.bottom > point.y)
                    gestureDetector!!.onTouchEvent(e)
                else {
                    recoverQueue!!.add(swipedPos)
                    swipedPos = -1
                    recoverSwipedItem()
                }
            }
            return false
        }
    }
    init{
        this.context = context
        this.recyclerView = recyclerView
        this.buttons = ArrayList()
        this.gestureDetector = GestureDetector(context, gestureListener)
        this.recyclerView!!.setOnTouchListener(onTouchListener)
        buttonsBuffer = HashMap()
        recoverQueue = object : LinkedList<Int>() {
            fun add(o: Int?): Boolean {
                return if (contains(o))
                    false
                else
                    super.add(o!!)
            }
        }
        attachSwipe()
    }
    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        val pos = viewHolder!!.getAdapterPosition()

        if (swipedPos !== pos)
            recoverQueue!!.add(swipedPos)

        swipedPos = pos

        if (buttonsBuffer!!.containsKey(swipedPos))
            buttons = buttonsBuffer!![swipedPos]
        else
            buttons!!.isEmpty()

        buttonsBuffer!!.isEmpty()
        swipeThreshold = 0.5f * buttons!!.size * BUTTON_WIDTH
        recoverSwipedItem()
    }
    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        val pos = viewHolder!!.getAdapterPosition()
        val itemView = viewHolder.itemView
        //itemView.setTranslationX(buttonsBuffer!!.size * BUTTON_WIDTH)
        if (pos < 0) {
            swipedPos = pos
            return
        }

        if (actionState === ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX < 0) {
                var buffer: MutableList<UnderlayButton> = ArrayList()
                if (!buttonsBuffer!!.containsKey(pos)) {
                    instantiateUnderlayButton(viewHolder, buffer)
                    buttonsBuffer!!.put(pos, buffer)
                } else {
                  buffer = buttonsBuffer!!.get(pos)!!
                }
                drawButtons(c!!, itemView, buffer, pos)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder?): Float {
        return swipeThreshold
    }
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue
    }
    override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
        return defaultValue
    }
    @Synchronized
    private fun recoverSwipedItem() {
        while (!recoverQueue!!.isEmpty()) {
            val pos = recoverQueue!!.poll()
            if (pos > -1) {
                recyclerView!!.getAdapter().notifyItemChanged(pos)
            }
        }
    }
    private fun drawButtons(c: Canvas, itemView: View, buffer: List<UnderlayButton>, pos: Int) {
        var right = itemView.right.toFloat()
        val dButtonWidth = BUTTON_WIDTH
        var left = 0f

        for (button in buffer) {
            left = right - dButtonWidth
            button.onDraw( c, RectF( left, itemView.top.toFloat(), right, itemView.bottom.toFloat()), pos )
            right = left - 30
        }
    }
    fun attachSwipe() {
        val itemTouchHelper = ItemTouchHelper(this)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    abstract fun instantiateUnderlayButton(viewHolder: RecyclerView.ViewHolder, underlayButtons: MutableList<UnderlayButton>)
    class UnderlayButton(private val context: Context,private val text: String, private val imageResId: Int, private val color: Int, private val clickListener: UnderlayButtonClickListener) {
        private var pos: Int = 0
        private var clickRegion: RectF? = null

        fun onClick(x: Float, y: Float): Boolean {
            if (clickRegion != null && clickRegion!!.contains(x, y)) {
                clickListener.onClick(pos)
                return true
            }

            return false
        }

        fun onDraw(c: Canvas, rect: RectF, pos: Int) {
            val p = Paint()

            // Draw background
            p.setColor(color)
            c.drawRect(rect, p)

            // Draw Text
            //p.setColor(Color.WHITE)
            //p.setTextSize(12f)

            //val r = Rect()
            //val cHeight = rect.height()
            //val cWidth = rect.width()
            //p.setTextAlign(Paint.Align.LEFT)
            //p.getTextBounds(text, 0, text.length, r)
            //val x = cWidth / 2f - r.width() / 2f - r.left
            //val y = cHeight / 2f + r.height() / 2f - r.bottom
            //c.drawText(text, rect.left + x, rect.top + y, p)

            val dr = this.context.getResources().getDrawable(imageResId)
            val icon: Bitmap = drawableToBitmap(dr);
            val icon_dest = RectF(rect.left, rect.top, rect.right, rect.bottom)
            c.drawBitmap(icon, null, icon_dest, p)

            clickRegion = rect
            this.pos = pos
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
    interface UnderlayButtonClickListener {
        fun onClick(pos: Int)
    }
}