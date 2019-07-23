# Bookmark View Android
Bookmark or Tag icons for Android Views. Supports ribbon, triangle, stripes, and circle views for now.
Uses Canvas to draw all the shapes, none drawable is used.
I tried to add the things that I thought they would look cool.
I am open to new ideas, feel free to contribute.
<br>
**What do these do?**
<br>
These are custom views that extend *FrameLayout*. They draw a shape on top the view using *canvas*.

**What do these not do?**
<br>
These  don't care about what is under them. They don't check if they exceeed the limits. They don't use gradient colors (for now)

**How They Look**:
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/allviews.png" width="300">

<br>
<br>

Here's all the views explained.
<br>
<br>

**RibbonView**
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/ribbon_regular.jpg" width="300">
<br>
Positive *ribbonTriangleHeight*
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/ribbon_tie.jpg" width="300">
<br>
Negative *ribbonTriangleHeight*
<br>
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
```kotlin
// RibbonView fields
var ribbonHasShadow: Boolean
var ribbonVisible: Boolean
var ribbonColor: ColorStateList?
var ribbonWidth: Float
var ribbonHeight: Float
var ribbonTriangleHeight: Float
var ribbonDistanceFromEnd: Float
fun setGradientColor(from: Int, to: Int)
```
<br>
<br>

**StripeView**
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/stripe.jpg" width="300">
<br>
```xml
 <com.mustafa.bookmarkviews.StripeView
            app:stripeColor="#fc6471"
            app:stripeHasShadow="true"
            app:stripeDistanceFromEnd="12dp"
            app:stripeWidth="8dp">
		..Other Stuff
    </com.mustafa.bookmarkviews.StripeView>
```
```kotlin
// StripeView fields
var stripeHasShadow: Boolean
var stripeVisible: Boolean
var stripeColor: ColorStateList?
var stripeWidth: Float
var stripeDistanceFromEnd: Float
fun setGradientColor(from: Int, to: Int)
```

<br>
<br>

**TriangleStripeView**
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/triangle_stripes.jpg" width="300">
<br>
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
```kotlin
// TriangleStripeView fields
var triangleStripeHasShadow: Boolean
var triangleStripesVisible: Boolean
var triangleStripeColor: ColorStateList?
var triangleStripeDistanceFromEnd: Float
var distanceBetweenStripes: Float
var triangleStripeThickness: Float
var triangleCount: Int
```
<br>
<br>

**DoubleStripeView**
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/double_stripe.jpg" width="300">
<br>
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
```kotlin
// DoubleStripeView fields
var stripesHaveShadow: Boolean
var shouldShowLeftStripe: Boolean
var shouldShowRightStripe: Boolean
var stripeRightColor: ColorStateList?
var stripeLeftColor: ColorStateList?
var stripeRightDistanceFromEnd: Float
var stripeLeftDistanceFromRightStripe: Float
var stripeRightWidth: Float
var stripeLeftWidth: Float
fun shouldShowAllStripes(Boolean)
fun setGradientColorLeftStripe(from: Int, to: Int)
fun setGradientColorRightStripe(from: Int, to: Int)
```
<br>
<br>

**CircleView**
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/circle_corner.jpg" width="300">
<br>
*circleDistanceFromTop* and *circleDistanceFromEnd* are *0dp*
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/circle_box.jpg" width="300">
<br>
*circleDistanceFromTop* and *circleDistanceFromEnd* are *16dp*
<br>
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
```kotlin
// CircleView fields
var circleHasShadow: Boolean
var circleColor: ColorStateList?
var circleRadius: Float
var circleDistanceFromEnd: Float
var circleDistanceFromTop: Float
var circleVisible: Boolean
fun setGradientColor(from: Int, to: Int)
```

<br>
<br>


**TriangleView**
<br>
<img src="https://github.com/mustafatunc/bookmark-view-android/blob/master/screenshots/triangle.jpg" width="300">
<br>
```xml
<com.mustafa.bookmarkviews.TriangleView
            app:triangleColor="#3c4f76"
            app:triangleHeight="24dp"
            app:triangleWidth="32dp"
            app:triangleHasShadow="true">
		..Other Stuff
    </com.mustafa.bookmarkviews.TriangleView>
```
```kotlin
// TriangleView fields
var triangleHasShadow: Boolean
var triangleColor: ColorStateList?
var triangleWidth: Float
var triangleHeight: Float
var triangleVisible: Boolean
fun setGradientColor(from: Int, to: Int)
```
<br>
<br>



