package com.mustafa.bookmarkviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class StripeView: FrameLayout {

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

    private val DpGw = 8 // Space width from right edge
    private val DpSw = 6 // Ribbon width

    private val Gw: Float // Ribbon width
    private val Sw: Float // Space width from right edge

    private val linearGradient: LinearGradient

    private val path = Path()
    private val paint = Paint()



    init {
        Sw = Util.convertDpToPixel(DpSw.toFloat(), context)
        Gw = Util.convertDpToPixel(DpGw.toFloat(), context)

        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                height.toFloat(),
                Color.parseColor("#FF1111"),
                Color.parseColor("#FF6666"),
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

        drawStripe()

        canvas.drawPath(path, paint)
    }


    private fun drawStripe() {
        val w = width.toFloat()
        val h = height.toFloat()

        path.fillType = Path.FillType.EVEN_ODD

        path.moveTo(w - Gw, 0.0f)
        path.lineTo(w - Gw, 0.0f) // Top Right
        path.lineTo(w - Gw, h) // Bottom Right
        path.lineTo(w - Gw - Sw, h) // Bottom Left
        path.lineTo(w - Gw - Sw, 0.0f) // Bottom Left

        path.close()
    }


}