package com.example.arsnapchat


import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.example.arsnapchat.model.BarChartData
import com.example.arsnapchat.model.BottomMenuContent
import com.example.arsnapchat.model.ChartData
import com.example.arsnapchat.model.Course
import com.example.arsnapchat.model.EditorContent
import com.example.arsnapchat.model.ImageContent
import com.example.arsnapchat.model.TextContent
import com.example.arsnapchat.ui.theme.ARSnapchatTheme
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import kotlin.math.abs


class DesignActivity : ComponentActivity() {
    var intitalfont: String = ""
    var mcontext: Context? = null
    /*val RESULT_ENABLE: Int = 1
    var devicePolicyManager: DevicePolicyManager? = null
    var compName: ComponentName? = null*/
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mcontext = this

        /*devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        compName = ComponentName(this, MyAdminReceiver::class.java)


        if(intitalfont.isEmpty()) {
            intitalfont="hello"
            val intent: Intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
            intent.putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "Your explanation message here"
            )
            startActivityForResult(intent, RESULT_ENABLE)
        }*/

        setContent {
            ARSnapchatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotepadDesign(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_ENABLE && resultCode == RESULT_OK) {
            // Device Admin enabled
        }
    }*/
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ARSnapchatTheme {
        NotepadDesign("Android")

    }
}



@Composable
@ExperimentalFoundationApi
fun NotepadDesign(name: String, modifier: Modifier = Modifier) {

    // HomeSccreen() this is the most outer box that will
    // contain all the views,buttons,chips,etc.
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            // this is the function for header
            RequestPermissions()
            ToolbarSection()
            // it's for chipsSecation, and pass


        }
    }
}

fun findFirstTextIndex(contents: List<Content>): Int? {
    return contents.indexOfFirst { it is Content.Text }.takeIf { it != -1 }
}


fun replaceTextAtIndex(contents: List<Content>, index: Int?, newText: String): List<Content> {
    /*val mutableList = contents.toMutableList()
    mutableList[index!!] = Content.Text(newText)
    val updatedList: List<Content> = mutableList.toList()
    println(updatedList)*/
    return contents.mapIndexed { i, content ->
        if (i == index && content is Content.Text) {
            content.copy(text = newText)
        } else {
            content
        }
    }

}



@Composable
fun ToolbarSection() {

    var selectedColor by remember { mutableStateOf(Color.Black) }
    var selectedBackgroundColor by remember { mutableStateOf(Color.White) }
    var selectedFontSize by remember { mutableStateOf(10.sp) }
    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }
    var isUnderLine by remember { mutableStateOf(false) }

    var selectedColorPickerfor by remember {
        mutableStateOf(3)
    }
    var selectedFont by remember { mutableStateOf("Cantataone") }
    var showColorPicker by remember { mutableStateOf(false) } // State to toggle between views
    var isbgImagePicker by remember { mutableStateOf(false) }
    var setBgImage by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedBgUri by remember { mutableStateOf<Uri?>(null) }
    var onImageSelectURL by remember {
        mutableStateOf(0)
    }
    var contents by remember { mutableStateOf(listOf<Content>()) }
    val indexii = findFirstTextIndex(contents)
    if(indexii==null) {
        contents = contents + Content.Text("Type here !!")
    }
    var barChartData by remember { mutableStateOf<BarChartData?>(null) }


    var shouldShowDialog by remember { mutableStateOf(false) }
    val imageList = listOf(R.drawable.blue, R.drawable.brown, R.drawable.gold, R.drawable.yellow)

    Log.i("DesignActivity", "ToolbarSection initialised")
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                if (!isbgImagePicker) {
                    contents = contents + Content.Image(uri)
                    // setBgImage=false
                    Log.i("DesignActivity", "ToolbarSection initialised")
                } else {
                    selectedBgUri = uri
                    setBgImage = true
                    isbgImagePicker = false
                }
            }
            Log.i("DesignActivity", "selectedImageUri $selectedImageUri $uri")
        }
    )

    val context = LocalContext.current
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uri?.let {
                val mimeType = context.contentResolver.getType(it)
                when {
                    mimeType?.startsWith("audio/") == true -> contents =
                        contents + Content.Audio(it)

                    mimeType?.startsWith("video/") == true -> contents =
                        contents + Content.Video(it)
                }
            }
        }
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(R.color.notepadtool))
            .padding(top = 50.dp) // Colorful padding area
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Wordpad",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(end = 40.dp, start = 20.dp)
            )

            // this is the final one that is bottomMenu
            BottomMenuColumn(
                items = listOf(
                    // having 5 instances
                    BottomMenuContent("Home", R.drawable.baseline_format_color_text_24),
                    BottomMenuContent("explore", R.drawable.baseline_add_24),
                    BottomMenuContent("dark mode", R.drawable.baseline_store_24),
                    BottomMenuContent("videos", R.drawable.baseline_save_24),
                    BottomMenuContent("Profile", R.drawable.baseline_print_24),
                    BottomMenuContent("Profile", R.drawable.baseline_more_vert_24),
                ),
                modifier = Modifier.align(Alignment.CenterVertically),
                onFontChange = { selectedFont = it },
                onColorPickerFor = { selectedColorPickerfor = it },
                onShowBackImageDialog = { shouldShowDialog = it },
                onShowColorPicker = { showColorPicker = it },
                imagePickerLauncher = imagePickerLauncher,
                filePickerLauncher = filePickerLauncher,
                barChartData = barChartData,
                contents = contents,
                selectedFontcolor = selectedColor,
                selectedFontFamily = selectedFont,
                selectedFontSize = selectedFontSize.value,
                isBold = isBold,
                isItalic = isItalic,
                isUnderline = isUnderLine,

                onBarChartDataChange = { barChartData = it },
                onTextChange = {
                    val index = findFirstTextIndex(contents)
                    // contents=contents+Content.Text(it)
                    if (index == null) {
                        contents = contents + Content.Text(it)
                    } else {
                        contents = replaceTextAtIndex(contents, index, it)
                    }
                },
                onImageChange={contents = contents + Content.Image(it)},
                onVideoChange ={contents = contents + Content.Video(it)},
                onAudioChange={contents = contents + Content.Audio(it)},
                onFontColorChange = { selectedColor = it },
                onFontSizeChange = { selectedFontSize = it.sp },
                onBoldChange = { isBold = it },
                onItalicChange = { isItalic = it },
                onUnderLineChange = {isUnderLine=it},
                onImageSelectURL = { onImageSelectURL = it },
                selectedImageURL = onImageSelectURL,
                selectedImageURI = selectedBgUri,
                onImageSelectedURI = { selectedBgUri = it },
                onSetBgImage = { setBgImage = it }

            )

        }

        if (showColorPicker) {
            if (selectedColorPickerfor == 0) {
                colorPicker(
                    onColorChange = { selectedColor = it },
                    onShowColorPicker = { showColorPicker = it })
            } else {
                colorPicker(
                    onColorChange = { selectedBackgroundColor = it },
                    onShowColorPicker = { showColorPicker = it })
            }
        } else {
            //  EditorScreen(selectedColor,selectedFont)

            EditorScreen(
                selectedColor,
                selectedFont,
                selectedFontSize,
                isBold,
                isItalic,
                isUnderLine,
                selectedBackgroundColor,
                contents,
                onFontSizeChange = { selectedFontSize = it.sp },
                onBoldChange = { isBold = it },
                onItalicChange = { isItalic = it },
                onUnderLineChange = {isUnderLine=it},
                onTextChange = {
                    val index = findFirstTextIndex(contents)
                    // contents=contents+Content.Text(it)
                    if (index == null) {
                        contents = contents + Content.Text(it)
                    } else {
                        contents = replaceTextAtIndex(contents, index, it)
                    }
                },
                onImageSelectURL = { onImageSelectURL = it },
                selectedImageURL = onImageSelectURL,
                shouldShowDialog,
                onShowBackImageDialog = { shouldShowDialog = it },
                openImagePicker = {
                    isbgImagePicker = true
                    imagePickerLauncher.launch("image/*")
                },
                imageList,
                barChartData = barChartData,
                selectedBgUri,
                setBgImage,
                onSetBgImage = { setBgImage = it }
            )
        }


    }

}


@Composable
fun RequestPermissions() {
    val context = LocalContext.current
    val activity = context as Activity
    val permissions = listOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_MEDIA_IMAGES,
        android.Manifest.permission.CAMERA
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val allPermissionsGranted = permissionsMap.entries.all { it.value }
        if (!allPermissionsGranted) {
            // Handle the case where some permissions are not granted
            Toast.makeText(
                context,
                "Permissions are required for this app to function",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(permissions.toTypedArray())
    }
}


@Composable
fun colorPicker(onColorChange: (Color) -> Unit, onShowColorPicker: (Boolean) -> Unit) {
    // on below line we are creating a variable for controller
    val controller = rememberColorPickerController()

    // on below line we are creating a column,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 250.dp, start = 30.dp, end = 30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp)), controller = controller
            )
        }

        HsvColorPicker(modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(10.dp),
            controller = controller,
            onColorChanged = { colorEnvelope -> onColorChange(colorEnvelope.color) })

        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )

        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp), controller = controller
        )


        Spacer(modifier = Modifier.height(28.dp))

        Button(onClick = {
            onShowColorPicker(false)
        }) {
            Text("Apply and Return")
        }
    }
}


sealed class Content {
    data class Text(val text: String) : Content()
    data class Image(val uri: Uri) : Content()
    data class Audio(val uri: Uri) : Content()
    data class Video(val uri: Uri) : Content()
}


fun saveToSharedPreferences(context: Context, key: String, content: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("EditorPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, content)
    editor.apply()
}


fun loadFromSharedPreferences(context: Context, key: String): String? {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("EditorPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, null)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    selectedColor: Color,
    selectedFont: String,
    selectedFontSize: TextUnit,
    isBold: Boolean,
    isItalic: Boolean,
    isUnderline: Boolean,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
    onUnderLineChange: (Boolean) -> Unit,
    onTextChange: (String) -> Unit,
    onImageSelectURL: (Int) -> Unit,
    selectedImageURL: Int,
    shouldShowDialog: Boolean,
    onShowBackImageDialog: (Boolean) -> Unit,
    openImagePicker: (Boolean) -> Unit,
    imageList: List<Int>,
    barChartData: BarChartData? = null,
    selectedImageUri: Uri?,
    setBgImage: Boolean,
    onSetBgImage: (Boolean) -> Unit
) {

    var isMissSpelled by remember { mutableStateOf(false) }
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    // State for dropdown menu
    var isMenuOpen by remember { mutableStateOf(false) }
    var selectedWord by remember { mutableStateOf("") }
    val cont = LocalContext.current
    // Use remember for the dictionary to ensure it's loaded once and retained across recompositions
    var dictionary by remember {
        // Initialize the dictionary in a coroutine to load asynchronously
        mutableStateOf(setOf<String>()) // Initial empty list
    }

    LaunchedEffect(Unit) {
        // Load the dictionary asynchronously when the component launches
        val loadedDictionary = loadDictionary(cont)
        dictionary = loadedDictionary
    }
    val scope = rememberCoroutineScope()
    var lastWord = ""

    val isKeyboardVisible = remember { mutableStateOf(false) }
    Log.d("isKeyboardVisibleU", isKeyboardVisible.toString())

    KeyboardVisibilityDetector(onKeyboardVisibilityChanged = { isVisible ->
        isKeyboardVisible.value = isVisible
    })



    val fontInt = getFontListFromAssets().get(selectedFont)

    var backgroundcolor = selectedBackgroundColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
        // Ensure the entire Box is scrollable
    ) {


        // Background image
        if (selectedImageURL == 2) {


            openImagePicker(true)
            onImageSelectURL(3)


        } else if (selectedImageURL == 3) {
            // onSetBgImage(true)


            if (setBgImage) {
                backgroundcolor = Color.Transparent
                val context = LocalContext.current
               // val bitmap= selectedImageUri?.let { loadBitmapFromUri(context =context , it) }

                var bitmap= base64ToBitmap(selectedImageUri.toString(),context)
                Log.i("DesignActivity", "setBgImage $bitmap")
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop, // Scale the image to fill the Box
                        modifier = Modifier.fillMaxSize()
                    )
                }else{
                     bitmap= selectedImageUri?.let { loadBitmapFromUri(context =context , it) }
                    Log.i("DesignActivity", "setBgImage after null $bitmap")
                    if (bitmap != null) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop, // Scale the image to fill the Box
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                //  onImageSelectURL=0
            }

        } else if (selectedImageURL > 1) {
            Log.i("DesignActivity", "ImageListAlertDialog $selectedImageURL")
            backgroundcolor = Color.Transparent
            Image(
                painter = painterResource(id = selectedImageURL),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Scale the image to fill the Box
//            contentScale = ContentScale.Crop, // Scale the image to fill the Box
                modifier = Modifier.fillMaxSize()
            )
        } else {
            backgroundcolor = selectedBackgroundColor
        }


        ImageListAlertDialog(
            shouldShowDialog,
            onDismiss = { onShowBackImageDialog(false) },
            onImageSelectedUrl = { onImageSelectURL(it) },
            pickfromGallery = { onSetBgImage(it) },
            imageList
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundcolor)
            //verticalScroll(scrollState)
        ) {

            var isdata = false

               /* Slider(
                value = selectedFontSize.value,
                onValueChange = { onFontSizeChange(it) },
                valueRange = 10f..72f,
                modifier = Modifier.padding(vertical = 8.dp)
            )*/
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Slider(
                    value = selectedFontSize.value,
                    onValueChange = { onFontSizeChange(it) },
                    valueRange = 10f..30f,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = selectedFontSize.value.toString(),
                    onValueChange = {
                        val size = it.toFloatOrNull()
                        if (size != null) {
                            onFontSizeChange(size)
                        }
                    },
                    label = { Text("Size") },
                    modifier = Modifier
                        .width(80.dp)
                        .border(0.5.dp, Color.White),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black
                    )
                )
            }
            Column(modifier = Modifier.fillMaxSize()) {
                // Display text contents in a Column


                // Display image contents in a LazyVerticalGrid
                 LazyVerticalGrid(
                     columns = GridCells.Adaptive(minSize = 100.dp),
                     modifier = Modifier.fillMaxWidth()
                 ) {
                     items(contents.filterIsInstance<Content.Image>()) { content ->
                          val context = LocalContext.current
                         // val bitmap= selectedImageUri?.let { loadBitmapFromUri(context =context , it) }
                         var bitmap= base64ToBitmap(content.uri.toString(),context)
                        // Log.i("DesignActivity", "setlistImage $bitmap")
                         if (bitmap != null) {
                             Image(
                                 bitmap = bitmap!!.asImageBitmap(),
                                 contentDescription = null,
                                 contentScale = ContentScale.Crop, // Scale the image to fill the Box
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .height(200.dp)
                                     .padding(2.dp)
                             )
                         }else{
                             bitmap= content.uri?.let { loadBitmapFromUri(context =context , it) }
                            // Log.i("DesignActivity", "setBgImage after null $bitmap")
                             if (bitmap != null) {
                                 Image(
                                     bitmap = bitmap!!.asImageBitmap(),
                                     contentDescription = null,
                                     contentScale = ContentScale.Crop, // Scale the image to fill the Box
                                     modifier = Modifier
                                         .fillMaxWidth()
                                         .height(200.dp)
                                         .padding(2.dp)
                                 )
                             }
                         }

                     }


                     items(contents.filterIsInstance<Content.Audio>()) { content ->
                         Column(
                             horizontalAlignment = Alignment.CenterHorizontally,
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .padding(bottom = 8.dp)
                         ) {
                             Image(
                                 painter = painterResource(id = R.drawable.music_file),
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


                     items(contents.filterIsInstance<Content.Video>()) { content ->
                         Column(
                             horizontalAlignment = Alignment.CenterHorizontally,
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .padding(bottom = 8.dp)
                         ) {
                             Image(
                                 painter = painterResource(id = R.drawable.video_file),
                                 contentDescription = "Video",
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .height(100.dp)
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
                // Display image, audio, and video contents in a LazyVerticalGrid

                //display bar-chart
                barChartData?.let { data ->
                    Log.d("Barchartdatwa", "$data ---dataPoints")
                    BarChart(data.labels, data.dataPoints,selectedFontSize.value)
                }

                if (isKeyboardVisible.value) {
                    Row(
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color.LightGray)
                        ) {
                            IconButton(
                                onClick = { onBoldChange(!isBold) },
                                modifier = Modifier.background(if (isBold) Color.DarkGray else Color.Transparent)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_format_bold_24),
                                    contentDescription = "Bold"
                                )
                            }
                            IconButton(
                                onClick = { onItalicChange(!isItalic) },
                                modifier = Modifier.background(if (isItalic) Color.DarkGray else Color.Transparent)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_format_italic_24),
                                    contentDescription = "Italic"
                                )
                            }
                            IconButton(
                                onClick = { onUnderLineChange(!isUnderline) },
                                modifier = Modifier.background(if (isUnderline) Color.DarkGray else Color.Transparent)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_format_underlined_24),
                                    contentDescription = "Underline"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(start = 10.dp))
                        if (isMissSpelled) {
                            Row {
                                IconButton(
                                    onClick = {
//                                    suggestions = getSuggestions(misspelledWord)
                                        isMenuOpen = true
                                    },
                                    modifier = Modifier
                                        .background(Color.Red)

                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.baseline_error_24),
                                        contentDescription = "Underline",
                                    )
                                }
                            }
                        }
                    }

                }



                contents.filterIsInstance<Content.Text>().forEach { content ->
                    isdata = true
                    // textInput=content.text
                    TextField(
                        value = content.text,
                        onValueChange = {
                          //  textInput = it
                            onTextChange(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .border(0.5.dp, Color.White),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundcolor, // Change background color to white
                            cursorColor = Color.Black // Change cursor color to black (optional)
                        ),
                        textStyle = TextStyle(
                            fontSize = selectedFontSize,
                            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = selectedColor,
                            fontFamily = FontFamily(
                                Font(fontInt!!, FontWeight.Normal)
                            )
                        )
                    )

                    scope.launch {
                        delay(3 * 1000L) // 1 second delay
                        lastWord = content.text.split(" ").lastOrNull() ?: ""
                        isMissSpelled = !dictionary.contains(lastWord)
                        if (isMissSpelled) suggestions = getRelatedWords(lastWord = lastWord,dictionary)
                        Log.d("TextFieldisMissSpelled", "Text input:, isMissSpelled: $isMissSpelled")
                        Log.d("isdatais ",lastWord)
                    }

                }

            }
        }
    }
    DropdownMenu(
        expanded = isMenuOpen,
        onDismissRequest = { isMenuOpen = false },
        offset = DpOffset(x = 30.dp, y = 4.dp)
    ) {

        if (suggestions.isNotEmpty()) {
            suggestions.forEach { suggestion ->
                DropdownMenuItem(
                    text = { Text(suggestion) },
                    onClick = {
                        selectedWord = suggestion
//                        textInput = textInput.replace(selectedWord, suggestion, true)
                        isMenuOpen = false
                    }
                )
            }
        }else isMenuOpen = false
    }
}
suspend fun loadDictionary(context: Context): Set<String> {
    return withContext(Dispatchers.IO) {
        val words = mutableSetOf<String>()
        context.assets.open("en_US.dic").use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).useLines { lines ->
                lines.forEach { line ->
                    val word = line.trim().removeSuffix("/").substringBefore("/").toLowerCase()
                    if (word.isNotEmpty()) {
                        words.add(word)
                    }
                }
            }
        }
        return@withContext words
    }
}
fun getRelatedWords(lastWord: String, dictionary: Set<String>): List<String> {
    val suggestions = mutableListOf<String>()

    // Case 1: Check for words starting with the same characters as lastWord
    val startingWithSameChars = dictionary.filter { it.startsWith(lastWord, ignoreCase = true) }
    suggestions.addAll(startingWithSameChars.take(5))

    // Case 2: If no words found in Case 1, check for words containing lastWord
    if (suggestions.isEmpty()) {
        val containingChars = dictionary.filter { it.contains(lastWord, ignoreCase = true) }
        suggestions.addAll(containingChars.take(5))
    }

    Log.i("DesignActivity","suggestionList- $suggestions")

    return suggestions
}
fun getBitmapFromUri(context: Context,uri: Uri):Bitmap? {

        val contentResolver = context.contentResolver
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {
            inputStream = contentResolver.openInputStream(uri)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            // Handle exceptions, e.g., file not found
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }

        return bitmap

}


fun bitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}


fun base64ToBitmap(base64String: String,context : Context): Bitmap? {
    return try {
        val byteArray = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    } catch (e: Exception) {
        e.printStackTrace()
        try {
            val inputStream = context.contentResolver.openInputStream(base64String.toUri())
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

@Composable
fun KeyboardVisibilityDetector(
    onKeyboardVisibilityChanged: (Boolean) -> Unit
) {
    val view = LocalView.current
    val isKeyboardVisible = remember { mutableStateOf(false) }

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.height
            val keypadHeight = screenHeight - rect.bottom
            val isKeyboardNowVisible = keypadHeight > screenHeight * 0.15
            if (isKeyboardNowVisible != isKeyboardVisible.value) {
                isKeyboardVisible.value = isKeyboardNowVisible
                onKeyboardVisibilityChanged(isKeyboardNowVisible)
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}

fun getFileNameFromUri(context: Context, uri: Uri): String {
    var fileName=uri.toString()
    try{
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor?.moveToFirst()
         fileName = cursor?.getString(nameIndex ?: 0) ?: ""
        cursor?.close()
    }catch (e:Exception){
        e.printStackTrace()
        if (fileName.contains("video")) {
            fileName = extractTextAfterVideo(fileName, "/video")
        }else {
            fileName = extractTextAfterVideo(fileName, "/audio")
        }
    }

    return fileName
}


fun extractTextAfterVideo(uri: String,from:String): String {
    return uri.substringAfter(from)
}


fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}



@Composable
fun BottomMenuColumn(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    onFontChange: (String) -> Unit,
    onColorPickerFor: (Int) -> Unit,
    onShowBackImageDialog: (Boolean) -> Unit,
    onShowColorPicker: (Boolean) -> Unit,
    imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>,
    filePickerLauncher: ManagedActivityResultLauncher<Array<String>, Uri?>,
    activeHighlightColor: Color = Color.Green,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.White,
    initialSelectedItemIndex: Int = 0,
    barChartData: BarChartData? = null,
    contents: List<Content>,
    selectedFontcolor: Color,
    selectedFontFamily: String,
    selectedFontSize: Float,
    isBold: Boolean,
    isItalic: Boolean,
    isUnderline: Boolean,
    onBarChartDataChange: (BarChartData) -> Unit,
    onTextChange: (String) -> Unit,
    onImageChange:(Uri)->Unit,
    onVideoChange:(Uri)->Unit,
    onAudioChange:(Uri)->Unit,
    onFontColorChange: (Color) -> Unit,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
    onUnderLineChange: (Boolean) -> Unit,
    onImageSelectURL: (Int) -> Unit,
    selectedImageURL: Int,
    selectedImageURI: Uri?,
    onImageSelectedURI: (Uri?) -> Unit,
    onSetBgImage: (Boolean) -> Unit

) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    var showDialog by remember { mutableStateOf(false) }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        // it is basically what we should have
        // for creating an element of BottomMenuItem
        items.forEachIndexed { index, item ->
            BottomMenuItemNotepad(
                item = item, isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                selectedItemIndex = selectedItemIndex,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
                expanded =
                    selectedItemIndex == 0 || selectedItemIndex == 1 || selectedItemIndex == 5 || selectedItemIndex == 3

                if (selectedItemIndex == 2)
                    onShowColorPicker(false)
                Log.i("DEsignActivity", "index" + index + selectedItemIndex)
                if (item.title.equals("Bar Chart")) {
                    showDialog = true
                }
            }

        }
    }


    val dropdownOffset = 100.dp

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        offset = if (selectedItemIndex == 1) DpOffset(dropdownOffset, 5.dp)
        else DpOffset(
            dropdownOffset + (selectedItemIndex * 50).dp,
            5.dp
        ) // Adjust the offset for above position

        ,
        properties = PopupProperties(dismissOnClickOutside = false)
    ) {
        Log.i("DesignActivity", "DropdownMenu $selectedItemIndex")

        /* if (selectedItemIndex != 5) {
             onShowColorPicker(false)
         }*/

        val fontList = getFontListFromAssets()
        val listFont = ArrayList<BottomMenuContent>()

        fontList.forEach { fontName ->
            listFont.add(BottomMenuContent(fontName.key, fontName.value))
        }


        // Determine dropdown items based on selected index
        val dropdownItems = when (selectedItemIndex) {
            0 -> listFont


            1 -> listOf(
                BottomMenuContent("Image", R.drawable.baseline_image_search_24),
                BottomMenuContent("Link", R.drawable.baseline_link_24),
                BottomMenuContent("File", R.drawable.baseline_attach_file_24),
                BottomMenuContent("Background Color", R.drawable.baseline_format_color_fill_24),
                BottomMenuContent("Background Image", R.drawable.baseline_image_aspect_ratio_24),
                BottomMenuContent("Bar Chart", R.drawable.baseline_bar_chart_24)
            )

            3 -> listOf(
                BottomMenuContent("Save", R.drawable.baseline_save_24),
                BottomMenuContent("Open", R.drawable.baseline_link_24),
            )

            5 -> listOf(BottomMenuContent("Color Picker", R.drawable.baseline_format_color_text_24))

            else -> emptyList()
        }



        dropdownItems?.forEach { item ->
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    Log.i("DesignActivity", "Dropdown item ${item.title} clicked")
                    //   changeEditorScreenProperties(selectedItemIndex,item.title,selectedColor,selectedFont)
                    if (selectedItemIndex == 0) {
                        Log.i("DesignActivity", "onfontchange")
                        onFontChange(item.title)
                    }

                    if (item.title.contains("Color Picker")) {
                        onShowColorPicker(true)
                        onColorPickerFor(0)
                    } else if (item.title.equals("Image")) {
                        imagePickerLauncher.launch("image/*")
                    } else if (item.title.equals("Background Image")) {
                        onShowBackImageDialog(true)
                    } else if (item.title.equals("File")) {
                        filePickerLauncher.launch(arrayOf("audio/*", "video/*"))
                    } else if (item.title.equals("Save")) {
                        /*val uri = sampleBitmap?.let {
                           saveImageToGallery(context,
                               it, "sample_image")
                       }*/


                        val editorContent = EditorContent(
                            texts = contents.filterIsInstance<Content.Text>().map {
                                val sampleBitmap = selectedImageURI?.let { getBitmapFromUri(context, it) }
                                val uri= sampleBitmap?.let { bitmapToBase64(it) }
                                TextContent(
                                    text = it.text,
                                    color = selectedFontcolor,
                                    fontSize = selectedFontSize,
                                    isBold = isBold,
                                    isItalic = isItalic,
                                    fontFamily = selectedFontFamily,
                                    selectedImageURL = selectedImageURL,
                                    selectedImageURI = uri.toString(),
                                    isUnderline=isUnderline
                                )
                            },
                            images = contents.filterIsInstance<Content.Image>().map {
                                val sampleBitmap = it.uri?.let { getBitmapFromUri(context, it) }
                                val uri= sampleBitmap?.let { bitmapToBase64(it) }
                                ImageContent(uri = uri.toString())
                            },
                            audios = contents.filterIsInstance<Content.Audio>().map {
                                ImageContent(uri = it.uri.toString())
                            },
                            videos = contents.filterIsInstance<Content.Video>().map {
                                ImageContent(uri = it.uri.toString())
                            },
                            chartData = barChartData?.let {
                                BarChartData(it.labels, it.dataPoints)
                            }
                        )






                        val serializedData = serializeEditorContent(editorContent)

                       // Log.i("DesignActivity", "save $serializedData")

                        saveToSharedPreferences(context = context, "editorContent", serializedData)

                    } else if (item.title.equals("Open")) {
                        val jsonData = loadFromSharedPreferences(context, "editorContent")
                        jsonData?.let {
                            val editorContent = deserializeEditorContent(it)

                            editorContent?.let {
                                for(imageitem in it.images){
                                    onImageChange(Uri.parse(imageitem.uri))
                                }

                                for (videoitem in it.videos){
                                    onVideoChange(Uri.parse(videoitem.uri))
                                }


                                for (audioitem in it.audios){
                                    onAudioChange(Uri.parse(audioitem.uri))
                                }




                                onTextChange(
                                    it.texts.get(0).text
                                )
                                //  imagePickerLauncher.launch("image/*")
                                onFontSizeChange(it.texts.get(0).fontSize)
                                onBoldChange(it.texts.get(0).isBold)
                                onItalicChange(it.texts.get(0).isItalic)
                                onFontChange(it.texts.get(0).fontFamily)
                                onUnderLineChange(it.texts.get(0).isUnderline)
                                onFontColorChange(it.texts.get(0).color)
                                onImageSelectURL(it.texts.get(0).selectedImageURL)
                                onImageSelectedURI(Uri.parse(it.texts.get(0).selectedImageURI))

                                if (it.texts.get(0).selectedImageURI != null) {
                                    onSetBgImage(true)
                                }


                                it.chartData?.let { it1 -> onBarChartDataChange(it1) }
                               // showDialog = false

                               /* Log.i(
                                    "DesignActivity",
                                    "editorContent $editorContent ${contents.size}"
                                )*/

                                // Set other properties like barChartData, etc.
                            }
                        }
                    } else if (item.title.equals("Background Color")) {
                        onShowColorPicker(true)
                        onColorPickerFor(1)
                    } else if (item.title.equals("Bar Chart")) {
                        showDialog = true
                    }
                },
                leadingIcon = {
                    if (selectedItemIndex != 0) {
                        Icon(
                            painter = painterResource(id = item.iconId),
                            contentDescription = item.title,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                },
                text =
                {
                    if (selectedItemIndex == 0) {
                        Text(
                            item.title, style = TextStyle(
                                fontFamily = when (item.title) {
                                    item.title -> FontFamily(
                                        Font(item.iconId, FontWeight.Normal)
                                    )

                                    else -> FontFamily.Default
                                }
                            )
                        )
                    } else {
                        Text(
                            item.title, style = TextStyle(
                                fontFamily = FontFamily.Default
                            )
                        )
                    }

                }
            )
        }

    }
    if (showDialog) {
        BarChartInputDialog(
            onDismiss = { showDialog = false },
            onConfirm = { labels, dataPoints ->
                onBarChartDataChange(BarChartData(labels, dataPoints))
                showDialog = false
            }
        )
    }

}


val gson = Gson()

fun serializeEditorContent(editorContent: EditorContent): String {
    return gson.toJson(editorContent)
}

fun deserializeEditorContent(json: String): EditorContent? {
    return gson.fromJson(json, EditorContent::class.java)
}

@Composable
fun BarChart(labels: List<String>, dataPoints: List<Float>,txtSize: Float) {
    Log.d("Barchartdata", "$labels ---$dataPoints")
    val maxValue = dataPoints.maxOrNull() ?: 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 5.dp)
    ) {
        Text(
            "",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val gap = 2.dp.toPx()
            val totalGapsWidth = gap * (labels.size - 1)
            val barWidth =
                (size.width / labels.size) - 5.dp.toPx() // Subtract 5dp from each bar width for gap
            val maxValueHeight = size.height

            // Draw Y-axis line
            drawLine(
                color = Color.Black,
                start = Offset(0f, 0f),
                end = Offset(0f, maxValueHeight + 2),
                strokeWidth = 2.dp.toPx()
            )

            // Draw X-axis line
            drawLine(
                color = Color.Black,
                start = Offset(0f, maxValueHeight),
                end = Offset(size.width, maxValueHeight + 3),
                strokeWidth = 2.dp.toPx()
            )

            dataPoints.forEachIndexed { index, dataPoint ->
                val barHeight = (dataPoint / maxValue) * maxValueHeight
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(index * (barWidth + gap), maxValueHeight - barHeight),
                    size = Size(barWidth, barHeight)
                )

                // Draw text below each bar
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawText(
                        labels[index],
                        index * (barWidth + gap) + (barWidth / 2), // center text horizontally under the bar
                        maxValueHeight + 16.dp.toPx(), // position text below the bars
                        Paint().apply {
                            textAlign = Paint.Align.CENTER
                            color = android.graphics.Color.BLACK
                            textSize = txtSize.sp.toPx() // convert sp to px
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BarChartInputDialog(onDismiss: () -> Unit, onConfirm: (List<String>, List<Float>) -> Unit) {
    var labels by remember { mutableStateOf("") }
    var dataPoints by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Bar Chart Data") },
        text = {
            Column {
                OutlinedTextField(
                    value = labels,
                    onValueChange = { labels = it },
                    label = { Text("Labels (comma-separated)") }
                )
                OutlinedTextField(
                    value = dataPoints,
                    onValueChange = { dataPoints = it },
                    label = { Text("Data Points (comma-separated)") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val labelsList = labels.split(",").map { it.trim() }
                    val dataPointsList =
                        dataPoints.split(",").map { it.trim().toFloatOrNull() ?: 0f }
                    onConfirm(labelsList, dataPointsList)
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}


fun getFontListFromAssets(): HashMap<String, Int> {
    val hashMap = HashMap<String, Int>()
    hashMap.put("Bevan", R.font.bevan)
    hashMap.put("Cantataone", R.font.cantataone_regular)
    hashMap.put("CaslonAntique", R.font.caslonantique)
    hashMap.put("Poppins", R.font.poppins_black)
    hashMap.put("Sumana", R.font.sumana_bold)
    hashMap.put("BilloDream", R.font.billodream)
    hashMap.put("GoldyPersonal", R.font.goldypersonal)
    hashMap.put("KakeKake", R.font.kakekae)
    hashMap.put("OrganicPerson", R.font.organicperson)


    return hashMap
}

// it's basically how menu item should look like
@Composable
fun BottomMenuItemNotepad(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Green,
    selectedItemIndex: Int,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Blue,
    onItemClick: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onItemClick() }) {
// here are some peremetens
        // for how elements will align
        androidx.compose.foundation.layout.Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }


    }

}


@Composable
fun ImageListAlertDialog(
    shouldShowDialog: Boolean,
    onDismiss: () -> Unit,
    onImageSelectedUrl: (Int) -> Unit,
    pickfromGallery: (Boolean) -> Unit,
    imageList: List<Int>
) {

    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = { /*shouldShowDialog = false*/  },
            title = { Text(text = "Choose background") },
            text = {

                // Display image contents in a LazyVerticalGrid
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(imageList) { content ->
                        Image(
                            painter = painterResource(id = content),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable {
                                    onImageSelectedUrl(content)
                                    //  pickfromGallery(false)
                                    onDismiss()
                                }
                                .padding(bottom = 8.dp)
                        )
                    }
                }


            },
            confirmButton = {
                Button(
                    onClick = {
                        onImageSelectedUrl(2)
                        pickfromGallery(false)
                        onDismiss()
                    }
                ) {
                    Text("Pick From Gallery")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onImageSelectedUrl(1)
                        //  pickfromGallery(false)
                        onDismiss()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}





@Composable
fun ItemCard(item: BottomMenuContent) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        ) {
        Text(
            text = item.title,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
fun ItemList(items: List<BottomMenuContent>) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        ) {
        LazyColumn {
            items(items) { item ->
                ItemCard(item)
            }
        }
    }
}

fun saveImageToGallery(context: Context, bitmap: Bitmap, displayName: String): Uri? {
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MyAppImages")
        }
    }

    val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    imageUri?.let {
        val outputStream: OutputStream? = resolver.openOutputStream(it)
        outputStream?.use { stream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        }
    }
    return imageUri
}

fun loadImageFromUri(context: Context, uri: Uri): Bitmap? {
    val inputStream = context.contentResolver.openInputStream(uri)
    return BitmapFactory.decodeStream(inputStream)
}

fun getFontFromAssetsByName(context: Context, fontName: String): FontFamily {
    val typeface = Typeface.createFromAsset(context.assets, "fonts/$fontName")
    return FontFamily(typeface)
}

@Composable
fun LoadImageFromStorage(filePath: String): Painter {
    val imagePainter = rememberAsyncImagePainter(model = File(filePath))
    return imagePainter
}