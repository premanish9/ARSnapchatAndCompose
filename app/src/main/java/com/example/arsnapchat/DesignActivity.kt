package com.example.arsnapchat


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.ViewTreeObserver
import android.view.textservice.SentenceSuggestionsInfo
import android.view.textservice.SpellCheckerSession
import android.view.textservice.SuggestionsInfo
import android.view.textservice.TextInfo
import android.view.textservice.TextServicesManager
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.util.Locale
import kotlin.math.abs



class DesignActivity : ComponentActivity(),SpellCheckerSession.SpellCheckerSessionListener {
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

    override fun onGetSuggestions(results: Array<out SuggestionsInfo>?) {
        TODO("Not yet implemented")
    }

    override fun onGetSentenceSuggestions(results: Array<out SentenceSuggestionsInfo>?) {
        TODO("Not yet implemented")
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

@SuppressLint("RememberReturnType")
@Composable
fun ToolbarSection() {

    var selectedColor by remember { mutableStateOf(Color.Black) }
    var selectedBackgroundColor by remember { mutableStateOf(Color.White) }
    var selectedFontSize by remember { mutableStateOf(10.sp) }
    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }

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
    var barChartData by remember { mutableStateOf<BarChartData?>(null) }


    var shouldShowDialog by remember { mutableStateOf(false) }
    val imageList = listOf(R.drawable.blue, R.drawable.brown, R.drawable.gold, R.drawable.yellow)


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
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
                    mimeType?.startsWith("audio/") == true -> contents =
                        contents + Content.Audio(it)

                    mimeType?.startsWith("video/") == true -> contents =
                        contents + Content.Video(it)
                }
            }
        }
    )
//    val dictionaryPath = File(context.filesDir, "hunspell").absolutePath

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
                onSetBgImage = { setBgImage = it },
//                dictionaryPath = dictionaryPath
            )
        }


    }

}
fun copyHunspellDictionaries(context: Context) {
    val assetManager = context.assets
    val files = listOf("en_US.aff", "en_US.dic")
//    val files = listOf("hunspell/en_US.aff", "hunspell/en_US.dic")
    val destDir = File(context.filesDir, "hunspell")
    if (!destDir.exists()) {
        destDir.mkdirs()
    }

    for (file in files) {
        val inputStream = assetManager.open(file)
        val outFile = File(destDir, file.split("/").last())
        val outputStream = FileOutputStream(outFile)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()
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
fun EditorScreen1(
    selectedColor: Color,
    selectedFont: String,
    selectedFontSize: TextUnit,
    isBold: Boolean,
    isItalic: Boolean,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
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
    onSetBgImage: (Boolean) -> Unit,
) {
    var textInput by remember { mutableStateOf("Type here...") }
    var fontSize by remember { mutableStateOf(10.sp) }
    var isBold1 by remember { mutableStateOf(false) }
    var isItalic1 by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }
    var misspelledWords by remember { mutableStateOf(listOf<String>()) }

    val isKeyboardVisible = remember { mutableStateOf(false) }


    KeyboardVisibilityDetector(onKeyboardVisibilityChanged = { isVisible ->
        isKeyboardVisible.value = isVisible
    })
    // Initialize the spell checker session
    val cont = LocalContext.current
//    val dictionary = remember { loadDictionary(cont) }
//    Log.d("isKeyboardVisibleU", "$dictionary")
    val spellCheckerSession = remember {
        val textServicesManager = cont.getSystemService(TextServicesManager::class.java)
        textServicesManager.newSpellCheckerSession(null, null, object : SpellCheckerSession.SpellCheckerSessionListener {
            override fun onGetSuggestions(results: Array<SuggestionsInfo>) {
                val newMisspelledWords = mutableListOf<String>()
                for (result in results) {
                    if (result.suggestionsCount > 0) {
                        newMisspelledWords.add(result.getSuggestionAt(0))
                    }
                }
                misspelledWords = newMisspelledWords
            }

            override fun onGetSentenceSuggestions(results: Array<SentenceSuggestionsInfo>) {}
        }, true)
    }
// Remember the scroll state
    val scrollState = rememberScrollState()


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

            Slider(
                value = selectedFontSize.value,
                onValueChange = { onFontSizeChange(it) },
                valueRange = 10f..30f,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Column(modifier = Modifier.fillMaxSize()) {
                // Display image, audio, and video contents in a LazyVerticalGrid
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

                            else -> {}
                        }
                    }
                }

                //display bar-chart
                barChartData?.let { data ->
                    Log.d("Barchartdatwa", "$data ---dataPoints")
                    BarChart(data.labels, data.dataPoints)
                }

                /*val annotatedText = buildAnnotatedString {
                    val words = textInput.split(" ")
                    words.forEach { word ->
                        if (word in misspelledWords) {
                            withStyle(style = SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline)) {
                                append("$word ")
                            }
                        } else {
                            append("$word ")
                        }
                    }
                }*/

                if (isKeyboardVisible.value) {
                    Row(
                        modifier = Modifier
                            .background(Color.LightGray)
                    ) {
                        IconButton(
                            onClick = { isBold1 = !isBold1 },
                            modifier = Modifier.background(if (isBold1) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_bold_24),
                                contentDescription = "Bold"
                            )
                        }
                        IconButton(
                            onClick = { isItalic1 = !isItalic1 },
                            modifier = Modifier.background(if (isItalic1) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_italic_24),
                                contentDescription = "Italic"
                            )
                        }
                        IconButton(
                            onClick = { isUnderline = !isUnderline },
                            modifier = Modifier.background(if (isUnderline) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_underlined_24),
                                contentDescription = "Underline"
                            )
                        }
                    }
                }
                // Simulate spell checking when text input changes
                LaunchedEffect(textInput) {
                    spellCheckerSession?.getSuggestions(TextInfo(textInput), 5)
                }

                contents.filterIsInstance<Content.Text>().forEach { content ->
                    isdata = true
                    // textInput=content.text
                    TextField(
                        value = content.text,
                        onValueChange = {
                            textInput = it
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
                            fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                            color = selectedColor,
                            fontFamily = FontFamily(
                                Font(fontInt!!, FontWeight.Normal)
                            )
                        )
                    )

                }
                // Text input field
                if (!isdata) {
                    TextField(
                        value = textInput,
                        onValueChange = {
                            textInput = it
                            onTextChange(it)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .border(0.5.dp, Color.White),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundcolor, // Change background color to white
                            cursorColor = Color.Black // Change cursor color to black (optional)
                        ),
                        textStyle = TextStyle(
                            fontSize = fontSize,
                            fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = selectedColor,
                            fontFamily = FontFamily(
                                Font(fontInt!!, FontWeight.Normal)
                            )
                        )
                    )
                    if (misspelledWords.isNotEmpty()) {
                        Log.d("misspelledWords",misspelledWords.toString())
                        Column(modifier = Modifier.padding(top = 16.dp)) {
                            Text("Misspelled Words:", color = Color.Red)
                            misspelledWords.forEach { word ->
                                Text(word, color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen2(
    selectedColor: Color,
    selectedFont: String,
    selectedFontSize: TextUnit,
    isBold: Boolean,
    isItalic: Boolean,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
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
    onSetBgImage: (Boolean) -> Unit,
) {
    var textInput by remember { mutableStateOf("Type here...") }
    var fontSize by remember { mutableStateOf(10.sp) }
    var isBold1 by remember { mutableStateOf(false) }
    var isItalic1 by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }
    var misspelledWords by remember { mutableStateOf(listOf<String>()) }

    val isKeyboardVisible = remember { mutableStateOf(false) }
    Log.d("isKeyboardVisibleU", isKeyboardVisible.toString())

    KeyboardVisibilityDetector(onKeyboardVisibilityChanged = { isVisible ->
        isKeyboardVisible.value = isVisible
    })

    val cont = LocalContext.current
    // Initialize the spell checker session
    val spellCheckerSession = remember {
        val textServicesManager = cont.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager
        textServicesManager.newSpellCheckerSession(null, null, object : SpellCheckerSession.SpellCheckerSessionListener {
            override fun onGetSuggestions(results: Array<SuggestionsInfo>) {
                val newMisspelledWords = mutableListOf<String>()
                for (result in results) {
                    if (result.suggestionsCount > 0) {
                        newMisspelledWords.add(result.getSuggestionAt(0))
                    }
                }
                misspelledWords = newMisspelledWords
                Log.d("SpellChecker", "Misspelled words: $misspelledWords")
            }

            override fun onGetSentenceSuggestions(results: Array<SentenceSuggestionsInfo>) {
                // Not implemented for this example
            }
        }, true)
    }

    val scrollState = rememberScrollState()
    val fontInt = getFontListFromAssets().get(selectedFont)
    var backgroundcolor = selectedBackgroundColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
    ) {
        if (selectedImageURL == 2) {
            openImagePicker(true)
            onImageSelectURL(3)
        } else if (selectedImageURL == 3) {
            if (setBgImage) {
                backgroundcolor = Color.Transparent
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else if (selectedImageURL > 1) {
            backgroundcolor = Color.Transparent
            Image(
                painter = painterResource(id = selectedImageURL),
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
            onImageSelectedUrl = { onImageSelectURL(it) },
            pickfromGallery = { onSetBgImage(it) },
            imageList
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundcolor)
        ) {
            Slider(
                value = selectedFontSize.value,
                onValueChange = { onFontSizeChange(it) },
                valueRange = 10f..30f,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Column(modifier = Modifier.fillMaxSize()) {
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
                            else -> {}
                        }
                    }
                }

                barChartData?.let { data ->
                    Log.d("Barchartdatwa", "$data ---dataPoints")
                    BarChart(data.labels, data.dataPoints)
                }

                if (isKeyboardVisible.value) {
                    Row(
                        modifier = Modifier.background(Color.LightGray)
                    ) {
                        IconButton(
                            onClick = { isBold1 = !isBold1 },
                            modifier = Modifier.background(if (isBold1) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_bold_24),
                                contentDescription = "Bold"
                            )
                        }
                        IconButton(
                            onClick = { isItalic1 = !isItalic1 },
                            modifier = Modifier.background(if (isItalic1) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_italic_24),
                                contentDescription = "Italic"
                            )
                        }
                        IconButton(
                            onClick = { isUnderline = !isUnderline },
                            modifier = Modifier.background(if (isUnderline) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_underlined_24),
                                contentDescription = "Underline"
                            )
                        }
                    }
                }

                LaunchedEffect(textInput) {
                    spellCheckerSession?.getSuggestions(TextInfo(textInput), 5)
                }

                contents.filterIsInstance<Content.Text>().forEach { content ->
                    TextField(
                        value = content.text,
                        onValueChange = {
                            textInput = it
                            onTextChange(it)
                            spellCheckerSession?.getSuggestions(TextInfo(it), 5)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .border(0.5.dp, Color.White),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundcolor,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(
                            fontSize = selectedFontSize,
                            fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                            color = selectedColor,
                            fontFamily = FontFamily(Font(fontInt!!, FontWeight.Normal))
                        )
                    )
                }

                if (contents.isEmpty()) {
                    BasicTextField(
                        value = textInput,
                        onValueChange = {
                            textInput = it
                            onTextChange(it)
                            spellCheckerSession?.getSuggestions(TextInfo(it), 5)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .border(0.5.dp, Color.White),
                        textStyle = TextStyle(
                            fontSize = fontSize,
                            fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = selectedColor,
                            fontFamily = FontFamily(Font(fontInt!!, FontWeight.Normal))
                        ),
                        decorationBox = { innerTextField ->
                            val annotatedText = buildAnnotatedString {
                                val words = textInput.split(" ")
                                words.forEach { word ->
                                    if (word in misspelledWords) {
                                        withStyle(style = SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline)) {
                                            append("$word ")
                                        }
                                    } else {
                                        append("$word ")
                                    }
                                }
                            }
                            Text(
                                text = annotatedText,
                                modifier = Modifier.fillMaxSize()
                            )
                            innerTextField()
                        }
                    )

                    if (misspelledWords.isNotEmpty()) {
                        Log.d("misspelledWords", misspelledWords.toString())
                        Column(modifier = Modifier.padding(top = 16.dp)) {
                            Text("Misspelled Words:", color = Color.Red)
                            misspelledWords.forEach { word ->
                                Text(word, color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    selectedColor: Color,
    selectedFont: String,
    selectedFontSize: TextUnit,
    isBold: Boolean,
    isItalic: Boolean,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
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
    onSetBgImage: (Boolean) -> Unit,
) {
    var textInput by remember { mutableStateOf("Zukor ") }
    var fontSize by remember { mutableStateOf(10.sp) }
    var isBold1 by remember { mutableStateOf(false) }
    var isItalic1 by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }
    var isMissSpelled by remember { mutableStateOf(false) }
    var misspelledWords by remember { mutableStateOf(listOf<String>()) }
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    // State for dropdown menu
    var isMenuOpen by remember { mutableStateOf(false) }
    var selectedWord by remember { mutableStateOf("") }

    val isKeyboardVisible = remember { mutableStateOf(false) }
    Log.d("isKeyboardVisibleU", isKeyboardVisible.toString())

    KeyboardVisibilityDetector(onKeyboardVisibilityChanged = { isVisible ->
        isKeyboardVisible.value = isVisible
    })

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

    Log.d("isKeyboardVisibleUD", "$dictionary")

    // Remember the scroll state
    val scrollState = rememberScrollState()
    val fontInt = getFontListFromAssets().get(selectedFont)
    var backgroundcolor = selectedBackgroundColor
    val scope = rememberCoroutineScope()
    var lastWord = ""

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
    ) {
        if (selectedImageURL == 2) {
            openImagePicker(true)
            onImageSelectURL(3)
        } else if (selectedImageURL == 3 && setBgImage) {
            backgroundcolor = Color.Transparent
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else if (selectedImageURL > 1) {
            backgroundcolor = Color.Transparent
            Image(
                painter = painterResource(id = selectedImageURL),
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
            onImageSelectedUrl = { onImageSelectURL(it) },
            pickfromGallery = { onSetBgImage(it) },
            imageList
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundcolor)
        ) {
            var isdata = false

            Slider(
                value = selectedFontSize.value,
                onValueChange = { onFontSizeChange(it) },
                valueRange = 10f..30f,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Column(modifier = Modifier.fillMaxSize()) {
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

                            else -> {}
                        }
                    }
                }

                barChartData?.let { data ->
                    Log.d("Barchartdatwa", "$data ---dataPoints")
                    BarChart(data.labels, data.dataPoints)
                }

                if (isKeyboardVisible.value) {
                    Row(
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Row(
                            modifier = Modifier.background(Color.LightGray)
                        ) {
                            IconButton(
                                onClick = { isBold1 = !isBold1 },
                                modifier = Modifier.background(if (isBold1) Color.DarkGray else Color.Transparent)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_format_bold_24),
                                    contentDescription = "Bold"
                                )
                            }
                            IconButton(
                                onClick = { isItalic1 = !isItalic1 },
                                modifier = Modifier.background(if (isItalic1) Color.DarkGray else Color.Transparent)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_format_italic_24),
                                    contentDescription = "Italic"
                                )
                            }
                            IconButton(
                                onClick = { isUnderline = !isUnderline },
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



                val newMisspelledWords = mutableListOf<String>()
                val annotatedText = buildAnnotatedString {
                    var words = textInput.lowercase(Locale.ROOT).split(" ")
                    contents.filterIsInstance<Content.Text>().forEach{
                        words =  it.text.lowercase(Locale.ROOT).split(" ")
                    }

                    words.forEach { word ->
                        if (word in dictionary) {
                            append("$word ")
                            Log.d("IFBLOCKIF", "Text input: $word")
                        } else {
                            withStyle(style = SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline)) {
                                append("$word ")
                                newMisspelledWords.add(word)
                                misspelledWords= newMisspelledWords
                                val nonEmptyWords = newMisspelledWords.filter { it.isNotEmpty() }
                                    .toSet()
                                (misspelledWords as MutableList<String>).addAll(nonEmptyWords)
                                if (misspelledWords[0] != "") {
//                                    isMissSpelled = true
                                    Log.d("IFBLOCKIF112", "rlsr 1212input: -- $misspelledWords")
                                }
                            }
                        }
                    }
                }

                var txt = annotatedText.text
                Log.d("misspelledWords121", "annoted "+ misspelledWords)
                contents.filterIsInstance<Content.Text>().forEach { content ->

                    isdata = true
                    TextField(
                        value = content.text,
                        onValueChange = {
                            txt = it
                            onTextChange(it)
                            if (!it.isEmpty()) {
//                                spellCheckerSession?.getSuggestions(TextInfo(it), 5)
                                Log.d("TextField", "Text input: $it")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .border(0.5.dp, Color.White),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundcolor,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(
                            fontSize = selectedFontSize,
                            fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = selectedColor,
                            fontFamily = FontFamily(Font(fontInt!!, FontWeight.Normal))
                        )

                    )
                    scope.cancel()
                    scope.launch {
                        delay(3 * 1000L) // 1 second delay
                        lastWord = content.text.split(" ").lastOrNull() ?: ""
                        isMissSpelled = !dictionary.contains(lastWord)
                        if (isMissSpelled) suggestions = getRelatedWords(lastWord = lastWord,dictionary)
                        Log.d("TextFieldisMissSpelled", "Text input:, isMissSpelled: $isMissSpelled")
                        Log.d("isdatais ",lastWord)
                    }
                }

//                 lastWord = textInput.split(" ").lastOrNull() ?: ""


                if (!isdata) {
                    TextField(
                        value = txt,
                        onValueChange = {
                            textInput = it
                            onTextChange(it)
//                            spellCheckerSession?.getSuggestions(TextInfo(it), 5)
                            Log.d("TextField", "Text input: $it")
                        },

                        modifier = Modifier
                            .fillMaxSize()
                            .border(0.5.dp, Color.White),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = backgroundcolor,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(
                            fontSize = fontSize,
                            fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = selectedColor,
                            fontFamily = FontFamily(Font(fontInt!!, FontWeight.Normal)),
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, // Specify the keyboard type here
                            imeAction = ImeAction.Done // Optional: Define action button on keyboard
                        )
                    )
                }
            }

        }
    }
    // Dropdown menu
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
                        textInput = textInput.replace(selectedWord, suggestion, true)
                        isMenuOpen = false
                    }
                )
            }
        }else isMenuOpen = false
    }
}


fun showMenu() {

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen11(
    selectedColor: Color,
    selectedFont: String,
    selectedFontSize: TextUnit,
    isBold: Boolean,
    isItalic: Boolean,
    selectedBackgroundColor: Color,
    contents: List<Content>,
    onFontSizeChange: (Float) -> Unit,
    onBoldChange: (Boolean) -> Unit,
    onItalicChange: (Boolean) -> Unit,
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
    onSetBgImage: (Boolean) -> Unit,
) {
    var textInput by remember { mutableStateOf("Zukor ") }
    var fontSize by remember { mutableStateOf(10.sp) }
    var isBold1 by remember { mutableStateOf(false) }
    var isItalic1 by remember { mutableStateOf(false) }
    var isUnderline by remember { mutableStateOf(false) }
    var misspelledWords by remember { mutableStateOf(listOf<String>()) }

    val isKeyboardVisible = remember { mutableStateOf(false) }
    Log.d("isKeyboardVisibleU", isKeyboardVisible.toString())

    KeyboardVisibilityDetector(onKeyboardVisibilityChanged = { isVisible ->
        isKeyboardVisible.value = isVisible
    })

    // Initialize the spell checker session
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

    val spellCheckerSession = remember {
        val textServicesManager = cont.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager
        textServicesManager.newSpellCheckerSession(null, null, object : SpellCheckerSession.SpellCheckerSessionListener {
            override fun onGetSuggestions(results: Array<SuggestionsInfo>) {
                val newMisspelledWords = mutableListOf<String>()
                for (result in results) {
                    val wordId = result.suggestionsAttributes and SuggestionsInfo.RESULT_ATTR_IN_THE_DICTIONARY.inv()
                    val originalWord = textInput.split(" ")[wordId]
                    if (result.suggestionsCount > 0) {
                        newMisspelledWords.add(originalWord)
                    }
                }
                misspelledWords = newMisspelledWords
                Log.d("SpellChecker", "Misspelled words: $misspelledWords")
            }

            override fun onGetSentenceSuggestions(results: Array<SentenceSuggestionsInfo>) {
                // Not implemented for this example
            }
        }, true)
    }

    // Remember the scroll state
    val scrollState = rememberScrollState()
    val fontInt = getFontListFromAssets().get(selectedFont)
    var backgroundcolor = selectedBackgroundColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
    ) {
        if (selectedImageURL == 2) {
            openImagePicker(true)
            onImageSelectURL(3)
        } else if (selectedImageURL == 3 && setBgImage) {
            backgroundcolor = Color.Transparent
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else if (selectedImageURL > 1) {
            backgroundcolor = Color.Transparent
            Image(
                painter = painterResource(id = selectedImageURL),
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
            onImageSelectedUrl = { onImageSelectURL(it) },
            pickfromGallery = { onSetBgImage(it) },
            imageList
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundcolor)
        ) {
            var isdata = false

            Slider(
                value = selectedFontSize.value,
                onValueChange = { onFontSizeChange(it) },
                valueRange = 10f..30f,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Column(modifier = Modifier.fillMaxSize()) {
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

                            else -> {}
                        }
                    }
                }

                barChartData?.let { data ->
                    Log.d("Barchartdatwa", "$data ---dataPoints")
                    BarChart(data.labels, data.dataPoints)
                }

                if (isKeyboardVisible.value) {
                    Row(
                        modifier = Modifier.background(Color.LightGray)
                    ) {
                        IconButton(
                            onClick = { isBold1 = !isBold1 },
                            modifier = Modifier.background(if (isBold1) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_bold_24),
                                contentDescription = "Bold"
                            )
                        }
                        IconButton(
                            onClick = { isItalic1 = !isItalic1 },
                            modifier = Modifier.background(if (isItalic1) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_italic_24),
                                contentDescription = "Italic"
                            )
                        }
                        IconButton(
                            onClick = { isUnderline = !isUnderline },
                            modifier = Modifier.background(if (isUnderline) Color.DarkGray else Color.Transparent)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_format_underlined_24),
                                contentDescription = "Underline"
                            )
                        }
                    }
                }

                TextField(
                    value = textInput,
                    onValueChange = {
                        textInput = it
                        onTextChange(it)
                        spellCheckerSession?.getSuggestions(TextInfo(it), 5)
                        Log.d("TextField", "Text input: $it")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .border(0.5.dp, Color.White),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = backgroundcolor,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        fontSize = selectedFontSize,
                        fontWeight = if (isBold1) FontWeight.Bold else FontWeight.Normal,
                        fontStyle = if (isItalic1) FontStyle.Italic else FontStyle.Normal,
                        textDecoration = if (isUnderline) {
                            TextDecoration.Underline
                        } else TextDecoration.None,
                        color = selectedColor,
                        fontFamily = FontFamily(Font(fontInt!!, FontWeight.Normal))
                    ),
                    visualTransformation = VisualTransformation { text ->
                        val builder = AnnotatedString.Builder(text)
                        misspelledWords.forEach { word ->
                            val startIndex = text.indexOf(word)
                            if (startIndex != -1) {
                                builder.addStyle(
                                    style = SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline),
                                    start = startIndex,
                                    end = startIndex + word.length
                                )
                            }
                        }
                        TransformedText(builder.toAnnotatedString(), OffsetMapping.Identity)
                    }
                )

                if (misspelledWords.isNotEmpty()) {
                    Log.d("misspelledWords121", misspelledWords.toString())
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        Text("Misspelled Words:", color = Color.Red)
                        misspelledWords.forEach { word ->
                            Text(word, color = Color.Red)
                        }
                    }
                }
            }
        }
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

fun checkSpelling(text: String, hunspell: Hunspell): List<String> {
    val misspelledWords = mutableListOf<String>()
    val words = text.split(" ")
    words.forEach { word ->
        if (!hunspell.spell(word)) {
            misspelledWords.add(word)
        }
    }
    return misspelledWords
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
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    cursor?.moveToFirst()
    val fileName = cursor?.getString(nameIndex ?: 0) ?: ""
    cursor?.close()
    return fileName
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
                        Log.i("DesignActivity", "save $selectedImageURI")
                        val editorContent = EditorContent(
                            texts = contents.filterIsInstance<Content.Text>().map {
                                TextContent(
                                    text = it.text,
                                    color = selectedFontcolor,
                                    fontSize = selectedFontSize,
                                    isBold = isBold,
                                    isItalic = isItalic,
                                    fontFamily = selectedFontFamily,
                                    selectedImageURL = selectedImageURL,
                                    selectedImageURI = selectedImageURI.toString()
                                )
                            },
                            images = contents.filterIsInstance<Content.Image>().map {
                                ImageContent(uri = it.uri.toString())
                            },
                            chartData = barChartData?.let {
                                ChartData(it.labels, it.dataPoints)
                            }
                        )

                        val serializedData = serializeEditorContent(editorContent)

                        saveToSharedPreferences(context = context, "editorContent", serializedData)

                    } else if (item.title.equals("Open")) {
                        val jsonData = loadFromSharedPreferences(context, "editorContent")
                        jsonData?.let {
                            val editorContent = deserializeEditorContent(it)

                            editorContent?.let {

                                onTextChange(
                                    it.texts.get(0).text
                                )
                                //  imagePickerLauncher.launch("image/*")
                                onFontSizeChange(it.texts.get(0).fontSize)
                                onFontChange(it.texts.get(0).fontFamily)
                                onFontColorChange(it.texts.get(0).color)
                                onImageSelectURL(it.texts.get(0).selectedImageURL)
                                onImageSelectedURI(Uri.parse(it.texts.get(0).selectedImageURI))
                                if (it.texts.get(0).selectedImageURI != null) {
                                    onSetBgImage(true)
                                }


                                Log.i(
                                    "DesignActivity",
                                    "editorContent $editorContent ${contents.size}"
                                )

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


