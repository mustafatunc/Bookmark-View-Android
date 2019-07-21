package com.mustafa.bookmarkviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class TriangleStripeView: FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0){
        if(attrs!=null)
            initWithAttrs(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr, 0){
        if(attrs!=null)
            initWithAttrs(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ){
        if(attrs!=null)
            initWithAttrs(attrs)
    }

    init{
        setWillNotDraw(false)
    }

    private val DpTw = 8 // Ribbon width
    private val DpTh = 8 // Ribbon height
    private val DpD = 8

    private val Tw: Float // Ribbon width
    private val Th: Float // Ribbon height
    private val d: Float // Ribbon height

    private val path = Path()
    private val paint = Paint()

    init {
        Tw = Util.convertDpToPixel(DpTw.toFloat(), context)
        Th = Util.convertDpToPixel(DpTh.toFloat(), context)
        d = Util.convertDpToPixel(DpD.toFloat(), context)

    }

    private fun initWithAttrs(attrs: AttributeSet) {

    }

    private fun drawShape(canvas: Canvas) {

        paint.strokeWidth = 6.0f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
//        paint.setShadowLayer(Util.convertDpToPixel(2.0f, context), 2.0f, 2.0f, Color.BLACK)
//        setLayerType(LAYER_TYPE_SOFTWARE, paint)

        drawTriangles()

        canvas.drawPath(path, paint)

    }


    private fun drawTriangles() {
        val w = width.toFloat()
        path.fillType = Path.FillType.EVEN_ODD

        for (i in 0..2) {
            path.moveTo(w - Tw - i * d, 0.0f)
            path.lineTo(w - Tw - i * d, 0.0f) // Left Corner
            path.lineTo(w, Th + i * d) // Right Bottom Corner
        }

    }
}