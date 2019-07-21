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

class DoubleStripeView: FrameLayout {

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



    private val paint1 = Paint()
    private val paint2 = Paint()
    private val path1 = Path()
    private val path2 = Path()

    private val DpGw1 = 4 // Space width from right edge
    private val DpSw1 = 8 // Ribbon width

    private val DpGw2 = 18 // Space width from right edge
    private val DpSw2 = 4 // Ribbon width

    private val Gw1: Float // Ribbon width
    private val Sw1: Float // Space width from right edge

    private val Gw2: Float // Ribbon width
    private val Sw2: Float // Space width from right edge

//    private val linearGradient: LinearGradient

    init {
        Sw1 = Util.convertDpToPixel(DpSw1.toFloat(), context)
        Gw1 = Util.convertDpToPixel(DpGw1.toFloat(), context)
        Sw2 = Util.convertDpToPixel(DpSw2.toFloat(), context)
        Gw2 = Util.convertDpToPixel(DpGw2.toFloat(), context)

//        linearGradient =
//            LinearGradient(
//                0.0f,
//                0.0f,
//                0.0f,
//                height.toFloat(),
//                Color.parseColor("#FF1111"),
//                Color.parseColor("#FF6666"),
//                Shader.TileMode.MIRROR
//            )

    }

    private fun initWithAttrs(attrs: AttributeSet) {

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let{drawShape(it)}
    }

    private fun drawShape(canvas: Canvas) {
        paint1.strokeWidth = 4.0f
        paint1.style = Paint.Style.FILL_AND_STROKE
        paint1.isAntiAlias = true
//        paint.setShadowLayer(Util.convertDpToPixel(2.0f, context), 2.0f, 2.0f, Color.BLACK)
//        setLayerType(LAYER_TYPE_SOFTWARE, paint)
//        paint.shader = linearGradient

        paint1.color = Color.RED
        drawStripe1()
        canvas.drawPath(path1, paint1)


        paint2.strokeWidth = 4.0f
        paint2.style = Paint.Style.FILL_AND_STROKE
        paint2.isAntiAlias = true
        paint2.color = Color.BLACK
        drawStripe2()
        canvas.drawPath(path2, paint2)
    }


    private fun drawStripe1() {
        val w = width.toFloat()
        val h = height.toFloat()

        with(path1){

            fillType = Path.FillType.EVEN_ODD
            moveTo(w - Gw1, 0.0f)
            lineTo(w - Gw1, 0.0f) // Top Right
            lineTo(w - Gw1, h) // Bottom Right
            lineTo(w - Gw1 - Sw1, h) // Bottom Left
            lineTo(w - Gw1 - Sw1, 0.0f) // Bottom Left
            close()
        }

    }

    private fun drawStripe2() {
        val w = width.toFloat()
        val h = height.toFloat()

        with(path2){

            fillType = Path.FillType.EVEN_ODD
            moveTo(w - Gw2, 0.0f)
            lineTo(w - Gw2, 0.0f) // Top Right
            lineTo(w - Gw2, h) // Bottom Right
            lineTo(w - Gw2 - Sw2, h) // Bottom Left
            lineTo(w - Gw2 - Sw2, 0.0f) // Bottom Left
            close()

        }
    }

}