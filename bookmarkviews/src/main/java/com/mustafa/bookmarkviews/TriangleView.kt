package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
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


    private var linearGradient: LinearGradient? = null

    private val path = Path()
    private val paint = Paint()

    var triangleHasShadow = false
        set(value) {
            field = value
            invalidate()
        }

    var triangleColor: ColorStateList? = null
        set(value) {
            field = value
            invalidate()
        }

    var triangleWidth = 8.0f
        set(value) {
            field = value
            invalidate()
        }

    var triangleHeight = 8.0f
        set(value) {
            field = value
            invalidate()
        }

    var shouldShow = false
        set(value) {
            field = value
            invalidate()
        }

    private fun initWithAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TriangleView,
            0, 0
        ).apply {

            try {
                triangleColor = getColorStateList(R.styleable.TriangleView_triangleColor)
                triangleWidth = getDimension(R.styleable.TriangleView_triangleWidth, 8.0f)
                triangleHeight = getDimension(R.styleable.TriangleView_triangleHeight, 8.0f)
                triangleHasShadow = getBoolean(R.styleable.TriangleView_triangleHasShadow, false)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!shouldShow) return

        with(paint) {
            strokeWidth = 2.0f
            color = triangleColor?.defaultColor ?: Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            if (linearGradient != null) {
                shader = linearGradient
            }

            if (triangleHasShadow) {
                setShadowLayer(Util.convertDpToPixel(2.0f, context), -2.0f, -2.0f, Color.BLACK)
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }
        }

        drawTriangle()

        canvas?.drawPath(path, paint)

    }


    private fun drawTriangle() {
        val w = width.toFloat()

        path.fillType = Path.FillType.EVEN_ODD

        val Tw = triangleWidth // Ribbon width
        val Th = triangleHeight // Ribbon height

        path.moveTo(w - Tw, 0.0f)
        path.lineTo(w - Tw, 0.0f) // Left Corner
        path.lineTo(w, Th) // Right Bottom Corner
        path.lineTo(w, 0.0f) // Right Top Corner

        path.close()
    }

    // public apis except the attributes above
    /**If this is set, the color given will be gone and the colors given here will be used*/
    fun setGradientColor(from: Int, to: Int) {
        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                triangleHeight,
                from,
                to,
                Shader.TileMode.CLAMP
            )
        invalidate()
    }

}