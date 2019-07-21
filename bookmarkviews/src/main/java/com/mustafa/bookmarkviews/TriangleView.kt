package com.mustafa.bookmarkviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class TriangleView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        if (attrs != null)
            initWithAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
        if (attrs != null)
            initWithAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        if (attrs != null)
            initWithAttrs(attrs)
    }

    init {
        setWillNotDraw(false)
    }

    private val DpTw = 48 // Ribbon width
    private val DpTh = 32 // Ribbon height

    private val Tw: Float // Ribbon width
    private val Th: Float // Ribbon height

    private val linearGradient: LinearGradient

    private val path = Path()
    private val paint = Paint()


    init {
        Tw = Util.convertDpToPixel(DpTw.toFloat(), context)
        Th = Util.convertDpToPixel(DpTh.toFloat(), context)

        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                Th,
                Color.parseColor("#FF1111"),
                Color.parseColor("#FF7777"),
                Shader.TileMode.MIRROR
            )
    }

    private fun initWithAttrs(attrs: AttributeSet) {

    }

    private fun drawShape(canvas: Canvas) {

        paint.strokeWidth = 4.0f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
        paint.shader = linearGradient
        paint.setShadowLayer(Util.convertDpToPixel(2.0f, context), 2.0f, 2.0f, Color.BLACK)
        setLayerType(LAYER_TYPE_SOFTWARE, paint)

        drawTriangle()

        canvas.drawPath(path, paint)

    }


    private fun drawTriangle() {
        val w = width.toFloat()

        path.fillType = Path.FillType.EVEN_ODD

        path.moveTo(w - Tw, 0.0f)
        path.lineTo(w - Tw, 0.0f) // Left Corner
        path.lineTo(w, Th) // Right Bottom Corner
        path.lineTo(w, 0.0f) // Right Top Corner

        path.close()
    }

}