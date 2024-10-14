import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.ui.screens.main.MainContract.ToolbarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    toolbarState: ToolbarState,
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar(
            title = { Text(text = toolbarState.title) },
            navigationIcon = {
                if (toolbarState.showBackButton) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            },
            actions = {
                IconButton(onClick = {
                    onCartClick()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_cart),
                        contentDescription = "Cart"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black,
                actionIconContentColor = Color.Black
            ),
            modifier = modifier
        )
        Divider(
            thickness = 2.dp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// Preview'lar
@Preview(showBackground = true)
@Composable
fun PreviewAppToolbarHome() {
    AppToolbar(
        toolbarState = ToolbarState("Hi John", false),
        onCartClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppToolbarDetail() {
    AppToolbar(
        toolbarState = ToolbarState("Product Detail", true),
        onBackClick = {},
        onCartClick = {}
    )
}