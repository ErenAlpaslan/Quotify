package com.easylife.quotify.ui.screens.onboarding

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.*
import com.google.accompanist.pager.*

class OnBoardingScreen : BaseScreen<OnBoardingViewModel, OnBoardingNavigationActions>() {

    @Composable
    override fun Content() {

        val uiState by viewModel.uiState.collectAsState()

        val contentAnimationState = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        if (uiState.isStartClicked) {
            LaunchedEffect(key1 = "") {
                navigationActions.navigateToHome()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visibleState = contentAnimationState,
                enter = fadeIn(animationSpec = tween(1000, 200))
            ) {
                OnBoardingContent(
                    currentPage = uiState.currentPage,
                    buttonName = uiState.buttonName,
                    pageList = uiState.pageList
                )
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun OnBoardingContent(
        currentPage: Int,
        buttonName: Int,
        pageList: List<OnBoardingPage>
    ) {
        val pagerState = rememberPagerState()

        LaunchedEffect(key1 = currentPage) {
            pagerState.animateScrollToPage(currentPage)
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 16.dp)
        ) {
            val (pager, bottomRow, skipButton) = createRefs()

            if (currentPage != 0) {
                Box(
                    modifier = Modifier
                        .constrainAs(skipButton) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                        .clickable {
                            viewModel.onPreviousClicked(currentPage)
                        }
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Arrow Back")
                }
            }
            HorizontalPager(
                count = pageList.size,
                state = pagerState,
                modifier = Modifier.constrainAs(pager) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 56.dp)
                    bottom.linkTo(bottomRow.top)
                    height = Dimension.fillToConstraints
                },
                itemSpacing = 1.dp
            ) { pageIndex: Int ->
                val page = pageList[pageIndex]
                OnBoardingPagerItem(page = page)
            }
            Row(
                modifier = Modifier.constrainAs(bottomRow) {
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Blue
                )
                Button(onClick = {
                    viewModel.onNextClicked(currentPage)
                }) {
                    Text(text = stringResource(id = buttonName))
                }
            }
        }
    }

}
