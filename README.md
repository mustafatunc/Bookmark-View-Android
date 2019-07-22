# Bookmark View Android
Bookmark icons for android Views. Supports ribbon, triangle, stripes, and circle views for now.
Uses Canvas to draw all the shapes, none drawable is used.

How They Look:
<p/>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/allviews.png" width="300">

<p/>
<p/>

RibbonView
```xml
<com.mustafa.bookmarkviews.RibbonView
		app:ribbonColor="#ece2d0"
		app:ribbonHeight="32dp"
		app:ribbonWidth="16dp"
		app:ribbonTriangleHeight="8dp"
		app:ribbonDistanceFromEnd="16dp"
		app:ribbonHasShadow="true">
	..Other Stuff
</com.mustafa.bookmarkviews.RibbonView>
```
<p/>
<p/>

StripeView
```xml
 <com.mustafa.bookmarkviews.StripeView
            app:stripeColor="#fc6471"
            app:stripeHasShadow="true"
            app:stripeDistanceFromEnd="12dp"
            app:stripeWidth="8dp">
		..Other Stuff
    </com.mustafa.bookmarkviews.StripeView>
```
<p/>
<p/>

TriangleStripeView
```xml
<com.mustafa.bookmarkviews.TriangleStripeView
		app:triangleCount="3"
		app:triangleStripeThickness="2dp"
		app:triangleStripeColor="#d0db97"
		app:triangleStripeDistanceFromEnd="4dp"
		app:triangleStripeHasShadow="true"
		app:distanceBetweenStripes="8dp">
	..Other Stuff
</com.mustafa.bookmarkviews.TriangleStripeView>
```
<p/>
<p/>

DoubleStripeView
```xml
<com.mustafa.bookmarkviews.DoubleStripeView
            app:stripeRightColor="#e63946"
            app:stripeLeftColor="#f1faee"
            app:stripeRightDistanceFromEnd="4dp"
            app:stripeRightWidth="8dp"
            app:stripeLeftWidth="4dp"
            app:stripeLeftDistanceFromRightStripe="4dp"
            app:stripesHaveShadow="true">
	..Other Stuff
    </com.mustafa.bookmarkviews.DoubleStripeView>
```
<p/>
<p/>

CircleView
```xml
<com.mustafa.bookmarkviews.CircleView
            app:circleColor="#0d2149"
            app:circleHasShadow="true"
            app:circleDistanceFromTop="0dp"
            app:circleDistanceFromEnd="0dp"
            app:circleRadius="16dp">
        ..Other Stuff
    </com.mustafa.bookmarkviews.CircleView>
```
<p/>
<p/>

TriangleView
```xml
<com.mustafa.bookmarkviews.TriangleView
            app:triangleColor="#3c4f76"
            app:triangleHeight="24dp"
            app:triangleWidth="32dp"
            app:triangleHasShadow="true">
		..Other Stuff
    </com.mustafa.bookmarkviews.TriangleView>
```
<p/>
<p/>



