package com.mustafa.bookmarkview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ribbonView.ribbonVisible = true
        ribbonView2.ribbonVisible = true
        triangleStripeView.triangleStripesVisible = true
        triangleView.triangleVisible = true
        circleView.circleVisible = true
        circleView2.circleVisible = true
        doubleStripeView.shouldShowAllStripes(true)
        stripeView.stripeVisible = true

    }
}
