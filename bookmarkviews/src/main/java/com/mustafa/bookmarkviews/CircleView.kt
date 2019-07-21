package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class CircleView: FrameLayout {

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


    private var circleColor: ColorStateList? = null
    private var hasShadow = false
    private var circleRadius = 8.0f
    private var distanceFromEnd = 16.0f
    private var distanceFromTop = 16.0f

    private val paint = Paint()


//    init {
//        linearGradient =
//            LinearGradient(
//                0.0f,
//                0.0f,
//                0.0f,
//                2 * Cr,
//                Color.parseColor("#FF1111"),
//                Color.parseColor("#FF7777"),
//                Shader.TileMode.CLAMP
//            )
//    }

    private fun initWithAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleView,
            0, 0).apply {

            try {
                circleColor = getColorStateList(R.styleable.CircleView_circleColor)
                hasShadow = getBoolean(R.styleable.CircleView_circleHasShadow, false)
                circleRadius = getDimension(R.styleable.CircleView_circleRadius, 8.0f)
                distanceFromEnd = getDimension(R.styleable.CircleView_circleDistanceFromEnd, 16.0f)
                distanceFromTop = getDimension(R.styleable.CircleView_circleDistanceFromTop, 16.0f)

            } finally {
                recycle()
            }
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let{drawShape(it)}
    }

    private fun drawShape(canvas: Canvas) {
        with(paint) {
            strokeWidth = 2.0f
            color = circleColor?.defaultColor?: Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
//            shader

            if(hasShadow){
                setShadowLayer(Util.convertDpToPixel(1.0f, context), 1.0f, 1.0f, Color.BLACK)
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }
        }

        canvas.drawCircle(width.toFloat() - distanceFromEnd, distanceFromTop, circleRadius, paint)
    }

}