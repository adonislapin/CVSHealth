package com.adonis.cvshealth.utils

import androidx.core.text.HtmlCompat

class Utils {

    companion object {

        const val SHARED_PREFERENCES = "cvsPrefs"
        const val LAST_SEARCH_KEY = "lastSearchKey"

        fun getSize(input: String): String {
            val wIndex = input.lastIndexOf("width=")
            val hIndex = input.lastIndexOf("height=")
            val aIndex = input.lastIndexOf("alt=")

            val rawW = input.substring(wIndex, hIndex)
            val rawH = input.substring(hIndex, aIndex)

            val coma = "\""
            val w = rawW.substring(rawW.indexOf(coma) +1, rawW.lastIndexOf(coma))
            val h = rawH.substring(rawH.indexOf(coma) +1, rawH.lastIndexOf(coma))

            return "Size: ${w}x${h} pixels"
        }

        fun getDescription(input: String): String {
            val splitString = input.split("</p>")

            var res = ""
            if(splitString.size == 4) {
                res = input.substring(
                    input.lastIndexOf("<p>"),
                    input.lastIndexOf("</p>")
                )
            }

            res = HtmlCompat.fromHtml(res, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

            return res.trim()
        }
    }
}