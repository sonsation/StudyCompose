package com.dorbom.compose_study.chat

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dorbom.compose_study.model.Message

@Composable
fun ChatScreen(navController: NavController, viewModel: ChatViewModel) {
    ChatScreen(viewModel)
}

@Composable
fun OtherPersonItem(message: Message, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        OtherPersonHeader(nickname = message.name)

        Spacer(modifier = Modifier.width(12.dp))

        OtherPersonMessage(message = message.message)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OtherPersonHeader(nickname: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        GlideImage(
            model = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/28555_v9_bc.jpg",
            contentDescription = "Other Person",
            modifier = Modifier
                .clip(CircleShape)
                .requiredSize(40.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(text = nickname,
            modifier = Modifier)
    }
}

@Composable
fun OtherPersonMessage(message: String) {

    Row {
        Spacer(modifier = Modifier.width(50.dp))

        Text(text = message,
            modifier = Modifier
                .background(
                    color = Color(android.graphics.Color.parseColor("#d8d8d8")),
                    shape = RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
                )
                .padding(all = 10.dp)
        )
    }
}

@Composable
fun MyItem(message: Message) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        MyHeader(nickname = message.name)

        Spacer(modifier = Modifier.width(12.dp))

        MyMessage(message = message.message)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyHeader(nickname: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth(1f)
    ) {

        Text(text = nickname,
            modifier = Modifier)

        Spacer(modifier = Modifier.width(6.dp))

        GlideImage(
            model = "https://avatars.githubusercontent.com/u/26738247?v=4",
            contentDescription = "Other Person",
            modifier = Modifier
                .clip(CircleShape)
                .requiredSize(40.dp)
        )
    }
}

@Composable
fun MyMessage(message: String) {

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth(1f)
    ) {

        Text(text = message,
            modifier = Modifier
                .background(
                    color = Color(android.graphics.Color.parseColor("#d8d8d8")),
                    shape = RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp)
                )
                .padding(all = 10.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))
    }
}

@Composable
private fun ChatScreen(viewModel: ChatViewModel) {

    val messages = remember {
        viewModel.chatList
    }

    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {

        LaunchedEffect(messages.size) {
            lazyListState.scrollToItem(messages.lastIndex)
        }

        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .weight(1f)
        ) {
            items(items = messages) { message ->
                if (message.isOther) {
                    OtherPersonItem(message = message)
                } else {
                    MyItem(message = message)
                }
            }
        }

        InputBox(
            messages = messages,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(1f))
    }
}

@Composable
fun InputBox(messages: MutableList<Message>, modifier: Modifier = Modifier) {

    val text = rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val onClickButton = button@{

        if (text.value.isEmpty()) {
            Toast.makeText(context, "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            return@button
        }

        messages.add(Message("Me", text.value, false))
        text.value = ""
    }

    Column(
        modifier = modifier
    ) {
        Divider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(1f),
            color = Color.LightGray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = text.value,
                maxLines = 10,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White),
                onValueChange = {
                    text.value = it
                    Log.e("input", text.value)
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        onClickButton()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    autoCorrect = false
                )
            )
            Button(
                onClick = onClickButton,
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp)
                    .background(Color.Black),
                enabled = true
            ) {
                Text(text = "전송")
            }
        }
    }
}

@Composable
@Preview
fun PreviewChatScreen() {
    val viewModel = ChatViewModel()
    ChatScreen(viewModel)
}