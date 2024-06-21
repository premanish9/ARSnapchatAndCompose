package com.example.arsnapchat

import androidx.annotation.DrawableRes
// having two parameters title and iconid
data class BottomMenuContent(val title:String,@DrawableRes val iconId:Int)


/*@Composable
fun EditorScreen(
    selectedColor: Color,
    selectedFont: String,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onTextChange: (List<Content>) -> Unit,
    shouldShowDialog: Boolean,
    onShowBackImageDialog: (Boolean) -> Unit,
    imageList: List<Int>,
    barChartData: BarChartData? = null
) {
    var textInput by remember { mutableStateOf("Type here...") }
    var fontSize by remember { mutableStateOf(10.sp) }
    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }

    var onImageSelectURL by remember {
        mutableStateOf(0)
    }

    val scrollState = rememberScrollState()
    val fontInt = getFontListFromAssets().get(selectedFont)
    var backgroundcolor = selectedBackgroundColor

    val isKeyboardVisible = remember { mutableStateOf(false) }

    // Observe keyboard visibility
    val context = LocalContext.current
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardVisible.value = keypadHeight > screenHeight * 0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 0.dp, end = 0.dp, bottom = 5.dp)
    ) {
        if (onImageSelectURL > 1) {
            backgroundcolor = Color.Transparent
            Image(
                painter = painterResource(id = onImageSelectURL),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            backgroundcolor = selectedBackgroundColor
        }

        ImageListAlertDialog(
            shouldShowDialog,
            onDismiss = { onShowBackImageDialog(false) },
            onImageSelectedUrl = { onImageSelectURL = it },
            imageList
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundcolor)
        ) {
            Slider(
                value = fontSize.value,
                onValueChange = { fontSize = it.sp },
                valueRange = 10f..30f,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Column(modifier = Modifier.fillMaxSize()) {
                contents.filterIsInstance<Content.Text>().forEach { content ->
                    Text(
                        text = content.text,
                        style = TextStyle(
                            color = selectedColor,
                            fontSize = fontSize,
                            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            fontFamily = FontFamily(
                                Font(fontInt!!, FontWeight.Thin)
                            )
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(contents) { content ->
                        when (content) {
                            is Content.Image -> {
                                Image(
                                    painter = rememberAsyncImagePainter(model = content.uri),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .padding(bottom = 8.dp)
                                )
                            }
                            is Content.Audio -> {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.audio_icon),
                                        contentDescription = "Audio",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .padding(bottom = 4.dp)
                                    )
                                    val context = LocalContext.current
                                    Text(
                                        text = getFileNameFromUri(context, content.uri),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                }
                            }
                            is Content.Video -> {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.video_thumbnail),
                                        contentDescription = "Video",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .padding(bottom = 4.dp)
                                    )
                                    val context = LocalContext.current
                                    Text(
                                        text = getFileNameFromUri(context, content.uri),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                }
                            }
                        }
                    }
                }

                barChartData?.let { data ->
                    BarChart(data.labels, data.dataPoints)
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    TextField(
                        value = textInput,
                        onValueChange = { textInput = it },
                        modifier = Modifier
                            .fillMaxSize()
                            .border(0.5.dp, Color.White),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundcolor,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(
                            fontSize = fontSize,
                            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = selectedColor,
                            fontFamily = FontFamily(
                                Font(fontInt!!, FontWeight.Normal)
                            )
                        )
                    )
                }
            }
        }

        // Style buttons shown above the keyboard
        if (isKeyboardVisible.value) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                IconButton(onClick = { isBold = !isBold }) {
                    Icon(
                        imageVector = Icons.Default.FormatBold,
                        contentDescription = "Bold",
                        tint = if (isBold) Color.Blue else Color.Black
                    )
                }
                IconButton(onClick = { isItalic = !isItalic }) {
                    Icon(
                        imageVector = Icons.Default.FormatItalic,
                        contentDescription = "Italic",
                        tint = if (isItalic) Color.Blue else Color.Black
                    )
                }
                IconButton(onClick = { isUnderline = !isUnderline }) {
                    Icon(
                        imageVector = Icons.Default.FormatUnderlined,
                        contentDescription = "Underline",
                        tint = if (isUnderline) Color.Blue else Color.Black
                    )
                }
            }
        }
    }
}

fun getFileNameFromUri(context: Context, uri: Uri): String {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    cursor?.moveToFirst()
    val fileName = cursor?.getString(nameIndex ?: 0) ?: ""
    cursor?.close()
    return fileName
}*/
