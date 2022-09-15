package com.psplog.kakaowebtoon

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.psplog.kakaowebtoon.SpecialWebtoonEntity.Companion.dummyList
import com.psplog.kakaowebtoon.ui.theme.KakaowebtoonTheme

@OptIn(ExperimentalPagerApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KakaowebtoonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Column {
                        mainTab {

                        }
                        mainPager()
                    }
                }
            }
        }
    }

    @Composable
    private fun mainPager() {

        specialTabContent()
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center
//        ) {
//            HorizontalPager(
//                modifier = Modifier.fillMaxSize(),
//                count = 1
//            ) { page ->
//            }
//        }
    }

    @Composable
    private fun mainTab(onTabSelected: () -> Unit) {
        val tabItems = mutableListOf("추천" to 0, "스페셜" to 1)
        val selectedPosition = remember { mutableStateOf(0) }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(tabItems) { tab ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            selectedPosition.value = tab.second
                        }
                ) {
                    val backgroundColor =
                        if (tab.second == selectedPosition.value) Color.White else Color.Black
                    val textColor =
                        if (tab.second == selectedPosition.value) Color.Black else Color.White
                    Text(
                        text = tab.first,
                        color = textColor,
                        modifier = Modifier
                            .background(backgroundColor)
                            .padding(16.dp, 8.dp),
                    )
                }
            }
        }
    }

    @Composable
    fun specialTabContent() {
        val context = LocalContext.current
        val itemState = rememberLazyListState(Int.MAX_VALUE / 2)
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        val itemList = dummyList



        LazyColumn(state = itemState) {
            items(Int.MAX_VALUE, itemContent = { index ->
                val webtoon = itemList[index % itemList.size]
                Box() {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(applicationContext)
                                .data(data = webtoon.thumbnail)
                                .apply(block = { size(Size.ORIGINAL) })
                                .build(),
                            imageLoader = imageLoader
                        ),
                        contentDescription = webtoon.title,
                        modifier = Modifier
                            .fillMaxWidth()

                            .height(
                                with(LocalDensity.current) {
                                    (context.resources.displayMetrics.heightPixels * 0.7)
                                        .toInt()
                                        .toDp()
                                })
                    )
                    val infiniteTransition = rememberInfiniteTransition()
                    val scrollPosition by infiniteTransition.animateValue(
                        initialValue = 0,
                        targetValue = webtoon.tags.size - 1,
                        typeConverter = Int.VectorConverter,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    val tagState = rememberLazyListState(scrollPosition)
                    var direction by remember {
                        mutableStateOf(1)
                    }

                    LaunchedEffect(scrollPosition) {
                        if (tagState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == tagState.layoutInfo.totalItemsCount - 1) {
                            direction = -1
                            Log.d("asd", "${direction} + ${this.hashCode()}")
                        }
                        if (tagState.layoutInfo.visibleItemsInfo.firstOrNull()?.index == 0) {
                            direction = 1
                            Log.d("asd", "${direction} + ${this.hashCode()}")
                        }
                        tagState.scrollBy(direction * scrollPosition.toFloat())
                    }
                    LazyRow(
                        state = tagState,
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.BottomCenter)
                    ) {
                        items(webtoon.tags.size, itemContent = { index ->
                            val tag = webtoon.tags[index]
                            Text(
                                text = tag,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(8.dp, 8.dp)
                                    .background(Color(scrollPosition * 8, 0, 0))
                            )
                        })
                    }
                }
            })
        }
    }
}

