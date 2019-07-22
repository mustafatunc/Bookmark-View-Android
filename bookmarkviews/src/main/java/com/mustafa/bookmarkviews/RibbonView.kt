package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
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
class RibbonView : FrameLayout {

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

    var ribbonHasShadow = false
        set(value) {
            field = value
            invalidate()
        }

    var ribbonColor: ColorStateList? = null
        set(value) {
            field = value
            invalidate()
        }

    var ribbonWidth = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var ribbonHeight = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var ribbonTriangleHeight = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var distanceFromEnd = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var ribbonVisible = false
        set(value) {
            field = value
            invalidate()
        }

    private fun initWithAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RibbonView,
            0, 0
        ).apply {

            try {
                ribbonColor = getColorStateList(R.styleable.RibbonView_ribbonColor)
                ribbonHasShadow = getBoolean(R.styleable.RibbonView_ribbonHasShadow, false)
                ribbonHeight = getDimension(R.styleable.RibbonView_ribbonHeight, 24.0f)
                ribbonWidth = getDimension(R.styleable.RibbonView_ribbonWidth, 8.0f)
                ribbonTriangleHeight = getDimension(R.styleable.RibbonView_ribbonTriangleHeight, 8.0f)
                distanceFromEnd = getDimension(R.styleable.RibbonView_ribbonDistanceFromEnd, 16.0f)
                ribbonVisible = getBoolean(R.styleable.RibbonView_ribbonVisible, false)

            } finally {
                recycle()
            }
        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!ribbonVisible) return

        path.reset()
        paint.reset()

        paint.apply {
            strokeWidth = 2.0f
            color = ribbonColor?.defaultColor ?: Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            if (linearGradient != null) {
                shader = linearGradient
            }

            if (ribbonHasShadow) {
                setShadowLayer(
                    Util.convertDpToPixel(1.0f, context),
                    1.0f,
                    1.0f,
                    Color.BLACK
                )
            }
            setLayerType(LAYER_TYPE_SOFTWARE, this)
        }

        drawRibbon()

        canvas?.drawPath(path, paint)
    }

    private fun drawRibbon() {
        val w = width.toFloat()

        val Sw = distanceFromEnd // Space width from right edge
        val Rw = ribbonWidth // Ribbon width
        val Rh = ribbonHeight // Ribbon height
        val Rt = ribbonTriangleHeight // Ribbon triangle height


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

    // public apis except the attributes above
    /**If this is set, the color given will be gone and the colors given here will be used*/
    fun setGradientColor(from: Int, to: Int) {
        linearGradient =
            LinearGradient(
                0.0f,
                0.0f,
                0.0f,
                ribbonHeight,
                from,
                to,
                Shader.TileMode.CLAMP
            )
        invalidate()
    }

}