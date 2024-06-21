package com.example.arsnapchat.model

import android.net.Uri
import androidx.compose.ui.graphics.Color

data class TextContent(val text: String,
                       val color: Color,
                       val fontSize: Float,
                       val isBold: Boolean,
                       val isItalic: Boolean,
                       val fontFamily: String,val selectedImageURL:Int,val selectedImageURI:String?)

