package com.dorbom.compose_study.profile

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dorbom.compose_study.chat.ChatViewModel
import com.dorbom.compose_study.model.Picture
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileScreenViewModel) {
    ProfileScreen(viewModel = viewModel)
}

@Composable
private fun ProfileScreen(viewModel: ProfileScreenViewModel) {
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {

        ProfileHeader()

        Spacer(modifier = Modifier.height(20.dp))

        ProfilePictures(viewModel)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProfileHeader() {

    Column(
        modifier = Modifier
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
private fun ProfilePictures(viewModel: ProfileScreenViewModel) {

    val pages = listOf(
        "Football",
        "Baseball"
    )

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .background(Color.White),
        topBar = {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator()
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
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        },
        content = { paddingValues ->

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(paddingValues)
            ) { page ->

                val targetPictures = if (page == 0) {
                    viewModel.footballPictures
                } else {
                    viewModel.baseballPictures
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3)
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
    )
}

@Preview
@Composable
fun PreviewProfile() {
    val viewModel = ProfileScreenViewModel()
    ProfileScreen(viewModel = viewModel)
}