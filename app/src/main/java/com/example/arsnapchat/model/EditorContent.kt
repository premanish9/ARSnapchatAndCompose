package com.example.arsnapchat.model

data class EditorContent(val texts: List<TextContent>,
                         val images: List<ImageContent>,
                         val videos: List<ImageContent>,
                         val audios: List<ImageContent>,
                         val chartData: ChartData?)
