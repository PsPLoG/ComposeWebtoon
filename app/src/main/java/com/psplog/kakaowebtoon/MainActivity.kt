package com.psplog.kakaowebtoon

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                count = 1
            ) { page ->
                specialTabContent()
            }
        }
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
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        LazyColumn {
            items(SpecialWebtoonEntity.dummyList) { webtoon ->
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
                            .height(400.dp)
                    )
                    LazyRow(
                        modifier = Modifier.wrapContentHeight()
                            .align(Alignment.BottomCenter)
                    ) {
                        items(webtoon.tags) { tag ->
                            Text(
                                text = tag,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(4.dp, 8.dp)
                                    .background(Color.Gray)
                            )
                        }
                    }
                }
            }
        }
    }
}

