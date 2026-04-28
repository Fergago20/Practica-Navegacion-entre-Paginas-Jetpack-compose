package ni.edu.uam.practicanavegacionentrepginas.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = Pink500,
    onPrimary = OnPink,
    secondary = Pink300,
    surface = Gray50,
    background = Gray100,
    onBackground = OnGray,
    onSurface = OnGray
)

private val DarkColors = darkColorScheme(
    primary = Pink700,
    onPrimary = OnPink,
    secondary = Pink300,
    surface = Gray800,
    background = Gray800,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun PracticaNavegacionEntrePáginasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkColors else LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
