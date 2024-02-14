package com.dorbom.compose_study.profile

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.util.TypedValueCompat.pxToDp
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dorbom.compose_study.util.Display.toDp
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileScreenViewModel) {
    ProfileScreen(viewModel = viewModel)
}

@Composable
private fun ProfileScreen(viewModel: ProfileScreenViewModel) {

    var contentHeight by rememberSaveable {
        mutableIntStateOf(0)
    }
    val scrollState = rememberScrollState()
    var toolbarOffsetHeightPx by remember {
        mutableIntStateOf(0)
    }
    var toolbarHeightPx by remember {
        mutableIntStateOf(0)
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Updates the toolbar offset based on the scroll to enable
                // collapsible behaviour

                val delta = available.y
                val newOffset = toolbarOffsetHeightPx + delta
                toolbarOffsetHeightPx = newOffset.coerceIn((-toolbarHeightPx).toFloat(), 0f).toInt()

                Log.e("asdfsdf", "asdfasdfsdfa")

                return Offset.Zero
            }
        }
    }

    val modifier = Modifier
        .background(Color.White)
        .fillMaxHeight()
        .verticalScroll(scrollState)
        .nestedScroll(nestedScrollConnection)
        .onSizeChanged { size ->

            if (contentHeight != 0) {
                return@onSizeChanged
            }

            contentHeight = size.height
        }

    Column(
        modifier = modifier
    ) {

        ProfileHeader(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .onSizeChanged {
                    toolbarHeightPx = it.height
                }
                .absoluteOffset(y = toolbarOffsetHeightPx.dp)
        )

        ProfilePictures(
            viewModel = viewModel,
            offset = toolbarOffsetHeightPx.dp,
            modifier = Modifier
                .fillMaxHeight()
                .height(contentHeight.dp)
                .background(Color.Transparent)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProfileHeader(modifier: Modifier) {

    Column(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                model = "https://avatars.githubusercontent.com/u/26738247?v=4",
                contentDescription = "my_profile",
                modifier = Modifier
                    .clip(CircleShape)
                    .requiredSize(80.dp)
            )

            Spacer(
                modifier = Modifier.width(40.dp)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "N",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "게시물")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "N",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "팔로워")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "N",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "팔로잉")
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Sonsation",
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(

        ) {
            Text(
                text = "프로필 편집",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray, shape = RoundedCornerShape(6.dp))
                    .padding(top = 4.dp, bottom = 4.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "프로필 공유",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray, shape = RoundedCornerShape(6.dp))
                    .padding(top = 4.dp, bottom = 4.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
private fun ProfilePictures(viewModel: ProfileScreenViewModel,
                            offset: Dp,
                            modifier: Modifier) {

    val pages = listOf(
        "Football",
        "Baseball"
    )

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })

    val coroutineScope = rememberCoroutineScope()

    var tableRowHeight by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier
    ) {

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color.Black
                )
            },
            containerColor = Color.Transparent,
            modifier = Modifier
                .absoluteOffset(y = offset)
                .onSizeChanged {
                    tableRowHeight = it.height
                }
        ) {
            pages.forEachIndexed { index, page ->
                Tab(
                    text = {
                        Text(
                            text = page,
                            color = Color.Black
                        )
                    },
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black,
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.wrapContentHeight()
        ) { page ->

            val targetPictures = if (page == 0) {
                viewModel.footballPictures
            } else {
                viewModel.baseballPictures
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                items(targetPictures) { picture ->
                    GlideImage(
                        model = picture.url,
                        contentDescription = "picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfile() {
    val viewModel = ProfileScreenViewModel()
    ProfileScreen(viewModel = viewModel)
}