package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class CircleView : FrameLayout {

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


    var circleColor: ColorStateList? = null
        set(value) {
            field = value
            invalidate()
        }

    var hasShadow = false
        set(value) {
            field = value
            invalidate()
        }

    var circleRadius = 8.0f
        set(value) {
            field = value
            invalidate()
        }
    var distanceFromEnd = 16.0f
        set(value) {
            field = value
            invalidate()
        }
    var distanceFromTop = 16.0f
        set(value) {
            field = value
            invalidate()
        }

    var shouldShow = false
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint()
    private var linearGradient: LinearGradient? = null


    private fun initWithAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleView,
            0, 0
        ).apply {

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
        if (shouldShow) {
            canvas?.let { drawShape(it) }
        }
    }

    private fun drawShape(canvas: Canvas) {
        paint.apply {
            strokeWidth = 2.0f
            color = circleColor?.defaultColor ?: Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            if (linearGradient != null) {
                shader = linearGradient
            }

            if (hasShadow) {
                setShadowLayer(Util.convertDpToPixel(1.0f, context), 1.0f, 1.0f, Color.BLACK)
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }
        }

        canvas.drawCircle(width.toFloat() - distanceFromEnd, distanceFromTop, circleRadius, paint)
    }


    // public apis except the attributes above
    /**If this is set, the color given will be gone and the colors given here will be used*/
    fun setGradientColor(from: Int, to: Int) {
        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                2 * circleRadius,
                from,
                to,
                Shader.TileMode.CLAMP
            )
        invalidate()
    }


}