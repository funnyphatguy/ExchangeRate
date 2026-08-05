package io.github.funnyphatguy.exchangerate.presentation.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.funnyphatguy.exchangerate.R

private const val GITHUB_PROFILE = "https://github.com/funnyphatguy"

@Composable
fun AboutScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AboutContent()
    }
}

@Composable
fun AboutContent() {

    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = "О себе",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )

    }

    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.profile_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(
                    CircleShape
                ),
            contentDescription = "Фотография разработчика"
        )

        Text(
            text = "Алексей Бересток",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
        )

        Text(
            text = "Android-разработчик с опытом создания, " +
                    "и развития мобильных приложений. " +
                    "Работаю с Kotlin и современным Android-стеком.",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp),
        )

        OutlinedButton(
            onClick = { uriHandler.openUri(GITHUB_PROFILE) },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Link,
                modifier = Modifier.size(20.dp),
                contentDescription = "Ссылка на профиль gitHub"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Перейти на GitHub")
        }
    }
}