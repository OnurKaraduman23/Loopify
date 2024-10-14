import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.onurkaraduman.loopify.R
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SignUpUiAction
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiEffect
import com.onurkaraduman.loopify.ui.screens.sign_up.SignUpContract.SingUpUiState
import com.onurkaraduman.loopify.ui.theme.LoopifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun SignUpScreen(
    uiState: SingUpUiState,
    uiEffect: Flow<SingUpUiEffect>,
    onAction: (SignUpUiAction) -> Unit,
    onNavigateHomeScreen: () -> Unit
) {

    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(uiEffect, lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            uiEffect.collect { effect ->
                when (effect) {
                    is SingUpUiEffect.ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is SingUpUiEffect.GoToHomeScreen -> {
                        onNavigateHomeScreen()
                    }

                }

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        EmailPasswordAndUserNameContent(
            email = uiState.email,
            password = uiState.password,
            userName = uiState.userName,
            confirmPassword = uiState.confirmPassword,
            warningMessage = if (uiState.password != uiState.confirmPassword) "Passwords do not match" else null,
            onEmailChange = { onAction(SignUpUiAction.ChangeEmail(it)) },
            onPasswordChange = { onAction(SignUpUiAction.ChangePassword(it)) },
            onUserNameChange = { onAction(SignUpUiAction.ChangeUserName(it)) },
            onSignUpClick = { onAction(SignUpUiAction.SignUpClick) },
            onConfirmPasswordChange = { onAction(SignUpUiAction.ChangeConfirmPassword(it)) }
        )
    }

}

@Composable
fun EmailPasswordAndUserNameContent(
    email: String,
    password: String,
    userName: String,
    confirmPassword: String,
    warningMessage: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    Text(text = "Sign Up", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    Spacer(modifier = Modifier.height(32.dp))

    OutlinedTextField(
        value = userName,
        maxLines = 1,
        placeholder = { Text(text = "Username") },
        onValueChange = { input ->
            val formattedInput = input.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            onUserNameChange(formattedInput)
        }
    )

    Spacer(modifier = Modifier.height(32.dp))

    OutlinedTextField(
        value = email,
        maxLines = 1,
        placeholder = { Text(text = "Email") },
        onValueChange = onEmailChange
    )
    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Enter your password") },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imageRes = if (passwordVisibility) {
                R.drawable.ic_visibility
            } else {
                R.drawable.ic_visibility_off
            }
            val description = if (passwordVisibility) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = imageRes),
                    contentDescription = description
                )
            }
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    if (warningMessage != null) {
        WarningTextMessage(warningMessage = warningMessage)
    }

    OutlinedTextField(
        value = confirmPassword,
        singleLine = true,
        placeholder = { Text(text = "Confirm Password") },
        onValueChange = onConfirmPasswordChange,
        visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val imageRes = if (confirmPasswordVisibility) {
                R.drawable.ic_visibility
            } else {
                R.drawable.ic_visibility_off
            }
            val description = if (confirmPasswordVisibility) "Hide password" else "Show password"

            IconButton(onClick = { confirmPasswordVisibility = !confirmPasswordVisibility }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = imageRes),
                    contentDescription = description
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

    Spacer(modifier = Modifier.height(32.dp))

    Button(onClick = onSignUpClick) {
        Text(text = "Sign Up")
    }
}

@Composable
fun WarningTextMessage(warningMessage: String) {
    Text(
        text = warningMessage,
        color = Color.Red,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    LoopifyTheme {
        SignUpScreen(
            uiState = SingUpUiState(),
            uiEffect = flow { },
            onAction = {},
            onNavigateHomeScreen = {}
        )
    }
}
