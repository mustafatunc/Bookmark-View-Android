package com.mustafa.bookmarkviews

import android.content.Context
import android.graphics.*
import android.graphics.Path.FillType
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

/**
 * Draws a ribbon shape on top of the current View\
 *
 * FOR NOW, It only supports drawing to the right hand side of the view
 *
 * Width, Height, Triangle Height, and Distance From The Right Edge can be changed
 *
 * */
class RibbonView: FrameLayout {

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


    private val DpSw = 16 // Space width from right edge
    private val DpRw = 32 // Ribbon width
    private val DpRh = 54 // Ribbon height
    private val DpRt = 16 // Ribbon triangle height

    private val Sw: Float // Space width from right edge
    private val Rw: Float // Ribbon width
    private val Rh: Float // Ribbon height
    private val Rt: Float // Ribbon triangle height

    private val linearGradient: LinearGradient

    private val path = Path()
    private val paint = Paint()


    init {
        Sw = Util.convertDpToPixel(DpSw.toFloat(), context)
        Rw = Util.convertDpToPixel(DpRw.toFloat(), context)
        Rh = Util.convertDpToPixel(DpRh.toFloat(), context)
        Rt = Util.convertDpToPixel(DpRt.toFloat(), context)

        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                Rh,
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
        drawRibbon()

        canvas.drawPath(path, paint)
    }

    private fun drawRibbon() {
        val w = width.toFloat()

        with(path) {
            fillType = FillType.EVEN_ODD
            moveTo(w - Sw - Rw, 0.0f)
            lineTo(w - Sw - Rw, 0.0f) // Top Left
            lineTo(w - Sw - Rw, Rh) // Bottom Left
            lineTo(w - Sw - Rw / 2.0f, Rh - Rt) // Triangle Top
            lineTo(w - Sw, Rh) // Bottom Right
            lineTo(w - Sw, 0.0f) // Top Right
            close()
        }

    }


}