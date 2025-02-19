
package com.example.musicfinder.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicfinder.R
import com.example.musicfinder.data.repository.RecognizeAudio.getSecurePreferences
import com.example.musicfinder.data.repository.RecognizeAudio.saveSecurePreference
import com.example.musicfinder.ui.animations.SlideContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun LoginNewAPIToken(
    padding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {

    val context = LocalContext.current
    var password = rememberSaveable { mutableStateOf("") }
    var reset_token = remember { mutableStateOf(false) }
    val message = stringResource(id=R.string.saved_token_advise)

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd){
        Card(
            modifier = Modifier.width(300.dp).height(300.dp).padding(top = padding.calculateTopPadding()),
            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 30.dp, top = 20.dp)
            ) {
                val securePrefs = getSecurePreferences(context)
                val apiToken = securePrefs.getString("api_token", null)
                if (apiToken!=null && reset_token.value == false){
                    Text(stringResource(id=R.string.have_token), modifier = Modifier.padding(5.dp),fontWeight = FontWeight.Bold)
                    Button(onClick = {reset_token.value = true}, modifier = Modifier.padding(10.dp)) {
                        Text(stringResource(id=R.string.reset_token_button))
                    }
                }else{
                    TextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        modifier = Modifier.width(250.dp),
                        label = { Text(stringResource(id = R.string.login_api), modifier = Modifier.padding(5.dp),fontWeight = FontWeight.Bold) },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Button(onClick = {
                        if (password.value != "") {
                            saveSecurePreference(
                                context = context,
                                key = "api_token",
                                value = password.value
                            )
                            reset_token.value = false
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message = message)
                            }
                        }
                    },modifier = Modifier.padding(10.dp)) {
                        Text(stringResource(id=R.string.save_new_token))
                    }

                }




            }
        }
    }
}

@Composable
fun ShowLogin(
    isVisible: MutableState<Boolean>,
    padding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
){

    if(isVisible.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .clickable { isVisible.value = !isVisible.value }
        ) {

        }
    }

    SlideContent(visible = isVisible.value, topPadding=padding, enterDirection =1){
        LoginNewAPIToken(padding,snackbarHostState,coroutineScope)
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    val padding = PaddingValues(50.dp)
    //LoginNewAPIToken(padding, snackbarHostState, coroutineScope)
}