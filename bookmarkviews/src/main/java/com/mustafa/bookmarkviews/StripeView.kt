package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
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

    private var linearGradient: LinearGradient? = null

    private val rect = Rect()
    private val paint = Paint()

    var stripeColor: ColorStateList? = null
        set(value) {
            field = value
            invalidate()
        }

    var stripeDistanceFromEnd = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var stripeWidth = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var stripeHasShadow = false
        set(value) {
            field = value
            invalidate()
        }

    var stripeVisible = false
        set(value) {
            field = value
            invalidate()
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
                stripeVisible = getBoolean(R.styleable.StripeView_stripeVisible, false)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!stripeVisible) return

        paint.reset()

        paint.apply {

            strokeWidth = 2.0f
            color = stripeColor?.defaultColor ?: Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            if (linearGradient != null) {
                shader = linearGradient
            }

            if (stripeHasShadow) {
                setShadowLayer(Util.convertDpToPixel(1.0f, context), 1.0f, 1.0f, Color.BLACK)
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }

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


    // public apis except the attributes above
    /**If this is set, the color given will be gone and the colors given here will be used*/
    fun setGradientColor(from: Int, to: Int) {
        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                height.toFloat(),
                from,
                to,
                Shader.TileMode.CLAMP
            )
        invalidate()
    }
}