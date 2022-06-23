package com.easylife.quotify.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.easylife.quotify.R
import com.easylife.quotify.ui.screens.onboarding.OnBoardingPage

@Composable
fun OnBoardingPagerItem(
    page: OnBoardingPage
) {
    val painter = rememberAsyncImagePainter(model = page.contentDrawable)
    
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var (titleRef, brandRef, descriptionRef, contentDrawableRef) = createRefs()
        Text(
            text = stringResource(id = page.title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        if (page.isBrandVisible) {
            Image(
                painter = painterResource(id = R.drawable.quotify_brand),
                contentDescription = "Quotify brand",
                modifier = Modifier
                    .constrainAs(brandRef) {
                        top.linkTo(titleRef.bottom, (-10).dp)
                        start.linkTo(titleRef.start)
                        end.linkTo(titleRef.end)
                    }
                    .fillMaxWidth(0.6f)
            )
        }
        Text(
            text = stringResource(id = page.description),
            modifier = Modifier.constrainAs(descriptionRef) {
                if (page.isBrandVisible) {
                    top.linkTo(brandRef.bottom, 20.dp)
                }else {
                    top.linkTo(titleRef.bottom, 55.dp)
                }
                start.linkTo(parent.start)
            }
        )
        Image(
            painter = painter,
            contentDescription = "Content image",
            modifier = Modifier.constrainAs(contentDrawableRef) {
                top.linkTo(descriptionRef.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            contentScale = ContentScale.Fit
        )
    }
}