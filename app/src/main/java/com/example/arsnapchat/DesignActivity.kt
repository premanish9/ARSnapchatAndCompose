package com.example.arsnapchat


import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.ViewTreeObserver
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ThumbUp
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.BitmapPainter
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

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
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
import kotlin.math.abs


class DesignActivity : ComponentActivity() {
    var intitalfont: String = ""
    var mcontext: Context? = null

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mcontext = this

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
fun GreetingSection(name: String = "Geeks") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            // heading text view
            Text(text = "Good morning, $name", style = MaterialTheme.typography.headlineSmall)
            Text(text = "We wish you have a good day!", style = MaterialTheme.typography.bodySmall)
        }
        // search icon
        Icon(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

// This is how we can create chip seaction at the top of app

@Composable
fun ChipSection(
    // function with single argument
    chips: List<String>
) {
    var selectedChipIndex by remember {
        // it will not update the string
        // but save and it will helpful for us
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        // this is basic condition for selected chip index

                        if (selectedChipIndex == it) Color.Green
                        else Color.Gray
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }

}

// This function is for suggestion secation
@Composable
fun SuggestionSection(color: Color = Color.Blue) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()

    ) {
        Column {
            // here are two text views or we can say only text

            Text(
                text = "Daily Coding",
                // it can be litile bit confusing but
                // it is just text style alternate
                // of fontfamily in XML
                style = MaterialTheme.typography.headlineMedium
            )
            Text( // same as above
                text = "do at least . 3-10 problems / day",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )

        }

        androidx.compose.foundation.layout.Box( // box containing icon
            contentAlignment = Alignment.Center, modifier = Modifier
                .size(40.dp)
                .clip(
                    CircleShape
                )
                .background(Color.Green)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_play_circle_24),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

    }
}

@Composable
// this function tells us that
// how menu item should look like
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Color.Green,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.White,
    initialSelectedItemIndex: Int = 1
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Blue)
            .padding(15.dp)
    ) {
        // it is basically what we should have
        // for creating an element of BottomMenuItem
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item, isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }


    }
}


// it's basically how menu item should look like
@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Green,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Blue,
    onItemClick: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onItemClick }) {
// here are some peremetens
        // for how elements will align
        androidx.compose.foundation.layout.Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = item.title,
            // it's basic condition
            color = if (isSelected) activeTextColor else inactiveTextColor
        )

    }

}

@ExperimentalFoundationApi
@Composable
// here we have just passed the list of courses
fun CourseSection(courses: List<Course>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "courses",
            style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(15.dp)
        )
        // we have used lazyVertically grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(courses.size) {
                // here we have to define how one of these item is look like
                // we will tell after defining item design
                // let me comment it for now and after
                // creating you just have to remove

                CourseItem(course = courses[it])
            }

        }

    }


}


@Composable
fun CourseItem(course: Course) {
    BoxWithConstraints( // Box with some attributes
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(R.color.colorAccent))
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        // setting 5 points for medium
        // color or we can say for another
        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())
        // joining points to make curves with the help of path class
        // path file that we have created earlier
        // having function that just help to reduce our code
        // and the function is standardQuadFromTo(m1,m2) taking
        // two peramente and connect them
        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // it's another part of that
        // texture with light color
        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat())

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

// canvas is used when we
        // want to draw something
        androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(// function for drawing paths
                // just pass the path
                path = mediumColoredPath, color = course.mediumColor
            )
            drawPath(// it's for the lighter path
                path = lightColoredPath, color = course.lightColor
            )
        }

// so , we have done with texture and
        // now just creating box and other things
        // box containing course elements
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = course.iconId),
                contentDescription = course.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the clicks
                    }
                    .align(Alignment.BottomEnd)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.Green)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )

        }
    }
}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    // this function is basically draw
    // a line to our second point and
    // also smooth on that line and make it curve
    quadraticBezierTo(from.x, from.y, abs(from.x + to.x) / 2f, abs(from.y + to.y) / 2f)
}

@ExperimentalFoundationApi
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // HomeSccreen() this is the most outer box that will
    // contain all the views,buttons,chips,etc.
    Box(
        modifier = Modifier
            .background(Color(R.color.Beige2))
            .fillMaxSize()
    ) {
        Column {
            // this is the function for header
            GreetingSection()
            // it's for chipsSecation, and pass
            // as many strings as you want
            ChipSection(
                chips = listOf(
                    "Data structure",
                    "Algorithm",
                    "competitive programming",
                    "python"
                )
            )
            // function for suggestionSection
            SuggestionSection()
            // this is for course secation
            CourseSection(
                // function require list of courses and
                // one course contain 5 attributes
                courses = listOf(
                    Course(
                        title = "greek of the year",
                        R.drawable.baseline_headphones_24,
                        Color.Blue,
                        Color.DarkGray,
                        Color.LightGray
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.baseline_videocam_24,
                        Color.Green,
                        Color.Black,
                        Color.Black
                    ),
                    Course(
                        title = "Advance python Course",
                        R.drawable.baseline_play_circle_24,
                        Color(R.color.skyblue1),
                        Color(R.color.skyblue2),
                        Color(R.color.skyblue3)
                    ),
                    Course(
                        title = "Advance Java Course",
                        R.drawable.baseline_headphones_24,
                        Color(R.color.Beige1),
                        Color(R.color.Beige2),
                        Color(R.color.Beige3)
                    ),
                    Course(
                        title = "prepare for aptitude test",
                        R.drawable.baseline_play_circle_24,
                        Color(R.color.OrangeYellow1),
                        Color(R.color.OrangeYellow2),
                        Color(R.color.OrangeYellow3)
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.baseline_videocam_24,
                        Color(R.color.LightGreen1),
                        Color(R.color.LightGreen2),
                        Color(R.color.LightGreen3)
                    ),

                    )
            )


        }

        // this is the final one that is bottomMenu
        BottomMenu(
            items = listOf(
                // having 5 instances
                BottomMenuContent("Home", R.drawable.baseline_home_24),
                BottomMenuContent("explore", R.drawable.baseline_explore_24),
                BottomMenuContent("dark mode", R.drawable.baseline_shield_moon_24),
                BottomMenuContent("videos", R.drawable.baseline_videocam_24),
                BottomMenuContent("Profile", R.drawable.baseline_self_improvement_24),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )

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

@Composable
fun ToolbarSection() {

    var selectedColor by remember { mutableStateOf(Color.Black) }
    var selectedBackgroundColor by remember { mutableStateOf(Color.White) }
    var selectedColorPickerfor by remember {
        mutableStateOf(3)
    }
    var selectedFont by remember { mutableStateOf("Cantataone") }
    var showColorPicker by remember { mutableStateOf(false) } // State to toggle between views
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var contents by remember { mutableStateOf(listOf<Content>()) }
    var barChartData by remember { mutableStateOf<BarChartData?>(null) }



    var shouldShowDialog by remember { mutableStateOf(false) }
    val imageList = listOf(R.drawable.blue,R.drawable.brown,R.drawable.gold,R.drawable.yellow)

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri!=null) {
                selectedImageUri = uri
                if (!isbgImagePicker) {
                    contents = contents + Content.Image(uri)
                    // setBgImage=false
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
                    mimeType?.startsWith("audio/") == true -> contents = contents + Content.Audio(it)
                    mimeType?.startsWith("video/") == true -> contents = contents + Content.Video(it)
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
                imagePickerLauncher = imagePickerLauncher,
                filePickerLauncher = filePickerLauncher,
                barChartData = barChartData,
                contents = contents,
                selectedFontcolor = selectedColor,
                selectedFontFamily = selectedFont,
                selectedFontSize = selectedFontSize.value,
                isBold = isBold,
                isItalic = isItalic,

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
                onFontColorChange = { selectedColor = it },
                onFontSizeChange = { selectedFontSize = it.sp },
                onBoldChange = { isBold = it },
                onItalicChange = { isItalic = it },
                onImageSelectURL = {onImageSelectURL=it},
                selectedImageURL = onImageSelectURL,
                selectedImageURI=selectedBgUri,
                onImageSelectedURI={selectedBgUri=it},
                onSetBgImage={setBgImage=it}

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
                selectedBackgroundColor,
                contents,
                onFontSizeChange = { selectedFontSize = it.sp },
                onBoldChange = { isBold = it },
                onItalicChange = { isItalic = it },
                onTextChange = {
                    val index = findFirstTextIndex(contents)
                    // contents=contents+Content.Text(it)
                    if (index == null) {
                        contents = contents + Content.Text(it)
                    } else {
                        contents = replaceTextAtIndex(contents, index, it)
                    }
                },
                onImageSelectURL = {onImageSelectURL=it},
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
                onSetBgImage={setBgImage=it}
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
    selectedBackgroundColor: Color,
    selectedFontSize: TextUnit,
    isBold: Boolean,
    isItalic: Boolean,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
    onTextChange: (String) -> Unit,
    onImageSelectURL:(Int)->Unit,
    selectedImageURL:Int,
    shouldShowDialog: Boolean,
    onShowBackImageDialog: (Boolean) -> Unit,
    openImagePicker: (Boolean) -> Unit,
    imageList: List<Int>,
    barChartData: BarChartData? = null,
    selectedImageUri: Uri?,
    setBgImage: Boolean,
    onSetBgImage:(Boolean)->Unit
) {
    var textInput by remember { mutableStateOf("Type here...") }
    var fontSize by remember { mutableStateOf(10.sp) }
    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }

    var onImageSelectURL by remember {
        mutableStateOf(0)
    }

    val isKeyboardVisible = remember { mutableStateOf(false) }
    Log.d("isKeyboardVisibleU",isKeyboardVisible.toString())
    // Observe keyboard visibility
   /* val context = LocalContext.current
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
    }*/
// Remember the scroll state
    val scrollState = rememberScrollState()

    KeyboardVisibilityDetector(onKeyboardVisibilityChanged = { isVisible ->
        isKeyboardVisible.value = isVisible
    })
    val fontInt = getFontListFromAssets().get(selectedFont)
    var backgroundcolor = selectedBackgroundColor

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
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // Scale the image to fill the Box
                    modifier = Modifier.fillMaxSize()
                )
                //  onImageSelectURL=0
            }

        } else if (selectedImageURL > 1) {
            Log.i("DesignActivity", "ImageListAlertDialog $selectedImageURL")
            backgroundcolor = Color.Transparent
        if (onImageSelectURL > 1) {
            Log.i("DesignActivity", "ImageListAlertDialog $onImageSelectURL")
            backgroundcolor = Color.Transparent
            Image(
                painter = painterResource(id = onImageSelectURL),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Scale the image to fill the Box
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
            onImageSelectedUrl = { onImageSelectURL = it },
            imageList
        )
        ImageListAlertDialog(
            shouldShowDialog,
            onDismiss = { onShowBackImageDialog(false) },
            onImageSelectedUrl = { onImageSelectURL( it) },
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

            Slider(
                value = selectedFontSize.value,
                onValueChange = { onFontSizeChange(it) },
                valueRange = 10f..30f,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Column(modifier = Modifier.fillMaxSize()) {
                // Display text contents in a Column
                contents.filterIsInstance<Content.Text>().forEach { content ->
                    Text(
                        text = content.text,
                        style = TextStyle(
                            color = selectedColor,
                            fontSize = fontSize,
                            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                            fontFamily = FontFamily(
                                Font(fontInt!!, FontWeight.Thin)
                            )
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                // Display image, audio, and video contents in a LazyVerticalGrid


                // Display image contents in a LazyVerticalGrid
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
                                        painter = painterResource(id = R.drawable.music_file),
                                        contentDescription = "Audio",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .padding(bottom = 4.dp)
                                    )
                                    val context = LocalContext.current
                                    Text(
                                        text = getFileNameFromUri(context,content.uri),
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
                                        painter = painterResource(id = R.drawable.video_file),
                                        contentDescription = "Video",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .padding(bottom = 4.dp)
                                    )
                                    val context = LocalContext.current
                                    Text(
                                        text = getFileNameFromUri(context,content.uri),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(fontSize = 14.sp)
                                    )
                                }
                            }

                //display bar-chart
                barChartData?.let { data ->
                    Log.d("Barchartdatwa", "$data ---dataPoints")
                    BarChart(data.labels, data.dataPoints)
                }

                // Text input field
                TextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .border(0.5.dp, Color.White),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = backgroundcolor, // Change background color to white
                        cursorColor = Color.Black // Change cursor color to black (optional)
                    ),
                    textStyle = TextStyle(
                        fontSize = fontSize,
                        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                        fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                        color = selectedColor,
                        fontFamily = FontFamily(
                            Font(fontInt!!, FontWeight.Normal)
                        )
                    )
                )
            }
        }
    }
}


fun getFontFromAssetsByName(context: Context, fontName: String): FontFamily {
    val typeface = Typeface.createFromAsset(context.assets, "fonts/$fontName")
    return FontFamily(typeface)
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
    onBarChartDataChange: (BarChartData) -> Unit,
    onTextChange: (String) -> Unit,
    onFontColorChange: (Color) -> Unit,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
    onImageSelectURL:(Int)->Unit,
    selectedImageURL:Int,
    selectedImageURI:Uri?,
    onImageSelectedURI:(Uri?)->Unit,
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
                    } else if (item.title.equals("Background Color")) {
                        onShowColorPicker(true)
                        onColorPickerFor(1)
                    } else if (item.title.equals("Bar Chart")) {
                        showDialog = true
                    }
                    } else if (item.title.equals("Bar Chart")) {
                        showDialog = true
                    } else if (item.title.equals("File")) {
                            filePickerLauncher.launch(arrayOf("audio/*", "video/*"))
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

@Composable
fun BarChart(labels: List<String>, dataPoints: List<Float>) {
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
                            textSize = 14.sp.toPx() // convert sp to px
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
            onDismissRequest = { /*shouldShowDialog = false*/ },
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


