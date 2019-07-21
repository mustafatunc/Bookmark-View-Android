package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class DoubleStripeView : FrameLayout {

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


    private val paintRight = Paint()
    private val paintLeft = Paint()

    private val rectRight = Rect()
    private val rectLeft = Rect()


    private var stripesHaveShadow = false
    private var stripeRightColor: ColorStateList? = null
    private var stripeLeftColor: ColorStateList? = null
    private var stripeRightDistanceFromEnd = 16.0f
    private var stripeLeftDistanceFromRightStripe = 8.0f
    private var stripeRightWidth = 8.0f
    private var stripeLeftWidth = 4.0f


//    private val linearGradient: LinearGradient

    init {
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

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DoubleStripeView,
            0, 0
        ).apply {

            try {
                stripeLeftColor = getColorStateList(R.styleable.DoubleStripeView_stripeLeftColor)
                stripeRightColor = getColorStateList(R.styleable.DoubleStripeView_stripeRightColor)

                stripeLeftWidth = getDimension(R.styleable.DoubleStripeView_stripeLeftWidth, 8.0f)
                stripeRightWidth = getDimension(R.styleable.DoubleStripeView_stripeRightWidth, 16.0f)

                stripesHaveShadow = getBoolean(R.styleable.DoubleStripeView_stripesHaveShadow, false)


                stripeLeftDistanceFromRightStripe =
                    getDimension(R.styleable.DoubleStripeView_stripeLeftDistanceFromRightStripe, 8.0f)
                stripeRightDistanceFromEnd = getDimension(R.styleable.DoubleStripeView_stripeRightDistanceFromEnd, 8.0f)
            } finally {
                recycle()
            }
        }


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawShapeLeft(it)
            drawShapeRight(it)
        }
    }


    private fun drawShapeLeft(canvas: Canvas) {
        with(paintLeft) {
            strokeWidth = 2.0f
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            color = stripeLeftColor?.defaultColor ?: Color.BLACK

            if(stripesHaveShadow){
                    setShadowLayer(Util.convertDpToPixel(2.0f, context), 2.0f, 2.0f, Color.BLACK)
                    setLayerType(LAYER_TYPE_SOFTWARE, this)
            }
            //        shader = linearGradient
        }

        drawStripeLeft()
        canvas.drawRect(rectLeft, paintLeft)
    }

    private fun drawShapeRight(canvas: Canvas) {
        with(paintRight) {
            strokeWidth = 2.0f
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            color = stripeRightColor?.defaultColor?:Color.BLACK

            if(stripesHaveShadow){
                setShadowLayer(Util.convertDpToPixel(2.0f, context), 2.0f, 2.0f, Color.BLACK)
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }
        }
        drawStripeRight()
        canvas.drawRect(rectRight, paintRight)
    }


    private fun drawStripeLeft() {
        val w = width
        val h = height

        val Sw = (stripeRightDistanceFromEnd + stripeLeftDistanceFromRightStripe + stripeRightWidth).toInt()
        val Rw = stripeLeftWidth.toInt()

        rectLeft.set(
            w - Sw - Rw, 0, w - Sw, h
        )

    }

    private fun drawStripeRight() {
        val w = width
        val h = height

        val Sw = stripeRightDistanceFromEnd.toInt() // Space from right edge
        val Rw = stripeRightWidth.toInt()

        rectRight.set(
            w - Sw - Rw, 0, w - Sw, h
        )
    }

}