package com.adonis.cvshealth.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adonis.cvshealth.R

object MainPage {

    fun verifyRecyclerViewIsVisible() {
        onView(
            withId(R.id.recyclerViewImages)
        ).check(matches(isDisplayed()))
    }
}