package com.adonis.cvshealth.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adonis.cvshealth.pages.MainPage
import com.adonis.cvshealth.ui.MainActivity
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainPageTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testToVerifyRecyclerViewIsVisible() {
        MainPage.verifyRecyclerViewIsVisible()
    }


}