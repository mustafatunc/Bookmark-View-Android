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

class StripeView : FrameLayout {

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

//    private val linearGradient: LinearGradient

    private val rect = Rect()
    private val paint = Paint()

    private var stripeColor: ColorStateList? = null
    private var stripeDistanceFromEnd = 0.0f
    private var stripeWidth = 0.0f
    private var stripeHasShadow = false

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
            R.styleable.StripeView,
            0, 0
        ).apply {

            try {
                stripeColor = getColorStateList(R.styleable.StripeView_stripeColor)
                stripeDistanceFromEnd = getDimension(R.styleable.StripeView_stripeDistanceFromEnd, 16.0f)
                stripeWidth = getDimension(R.styleable.StripeView_stripeWidth, 8.0f)
                stripeHasShadow = getBoolean(R.styleable.StripeView_stripeHasShadow, false)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        paint.strokeWidth = 2.0f
        paint.color = stripeColor?.defaultColor ?: Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
//        paint.shader = linearGradient

        if (stripeHasShadow) {
            paint.setShadowLayer(Util.convertDpToPixel(2.0f, context), 2.0f, 2.0f, Color.BLACK)
            setLayerType(LAYER_TYPE_SOFTWARE, paint)
        }

        drawStripe()

        canvas?.drawRect(rect, paint)
    }


    private fun drawStripe() {
        val w = width

        val Gw = stripeDistanceFromEnd // Gap from the right edge
        val Sw = stripeWidth // Width of the stripe

        rect.set(
            (w - Gw - Sw).toInt(),
            0,
            (w - Gw).toInt(),
            height
        )
    }


}