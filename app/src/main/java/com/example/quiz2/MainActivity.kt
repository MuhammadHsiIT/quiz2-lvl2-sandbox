package com.example.quiz2


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz2.ui.theme.Quiz2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Quiz2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quiz2Layout()
                }
            }
        }
    }
}

@Composable
fun Quiz2Layout() {
    var nipInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var passwordVisibility by remember {mutableStateOf(false)}
    val icon = if(passwordVisibility)
        painterResource(R.drawable.logo_visibility)
    else painterResource(R.drawable.logo_visibility_off)

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Row(modifier = Modifier) {
            Image(
                painter = painterResource(R.drawable.logo_hsi_sakinah),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }

        Column(horizontalAlignment = Alignment.Start, modifier = Modifier) {
            Text(text = stringResource(R.string.nip), fontWeight = FontWeight(500))
            EditTextField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = nipInput,
                onValueChange = {nipInput = it},
                modifier = Modifier.fillMaxWidth())
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.Start, modifier = Modifier) {
            Text(text = stringResource(R.string.password), fontWeight = FontWeight(500))
            OutlinedTextField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                value = passwordInput, onValueChange = {passwordInput = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp))
                    .border(2.5.dp, Color(0xFFF0F0F0), RoundedCornerShape(10.dp)),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility icon",
                            modifier = Modifier.size(25.dp),
                            tint = Color.Black
                        )
                    }
                },
                visualTransformation = if(passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(Color.Black, 20.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
            Toast.makeText(context,
                theToast(nipInput, passwordInput),
                Toast.LENGTH_SHORT).show() },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF5276A7))

        ) {
            Text(
                text = stringResource(R.string.login_button),
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp), color = Color.White)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = stringResource(R.string.belum_bisa_akses), fontWeight = FontWeight(500))

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.cs_ikhwan),
            modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    R.string.menguhubngi_cs_ikhwan,
                    Toast.LENGTH_SHORT).show() },
            fontWeight = FontWeight(500),
            color = Color(0xFF5276A7)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.cs_akhwat),
            modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    R.string.menguhubngi_cs_akhwat,
                    Toast.LENGTH_SHORT).show() },
            fontWeight = FontWeight(500),
            color = Color(0xFF5276A7))

        Spacer(modifier = Modifier.height(150.dp))

        Button(
            onClick = { Toast.makeText(context, R.string.menuju_halaman_faq, Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, color = Color(0xFF5276A7), shape = RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = stringResource(R.string.lihat_faq),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp), color = Color(0xFF5276A7))
                Icon(
                    painter = painterResource(R.drawable.logo_open_in_new),
                    contentDescription = null,
                    Modifier
                        .size(25.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color(0xFF5276A7))

            }
        }


    }
}


@Composable
fun EditTextField(
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        keyboardOptions = keyboardOptions,
        value = value, 
        onValueChange = onValueChange,
        modifier = modifier
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp))
            .border(2.5.dp, Color(0xFFF0F0F0), RoundedCornerShape(10.dp)),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(Color.Black, 20.sp),
    )
}

private fun theToast(nipInput: String, passwordInput: String): String {
    return when(nipInput) {
        "" -> when(passwordInput) {
            "" -> "NIP dan Password harus diisi"
            else -> "NIP harus diisi"
        }
        else -> when(passwordInput) {
            "" -> "Password harus diisi"
            else -> "Menuju Halaman Beranda..."
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Quiz2Theme {
        Quiz2Layout()
    }
}