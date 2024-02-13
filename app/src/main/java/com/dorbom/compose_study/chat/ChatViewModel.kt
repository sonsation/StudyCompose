package com.dorbom.compose_study.chat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.dorbom.compose_study.model.Message

class ChatViewModel : ViewModel() {

    val chatList by lazy {
        mutableStateListOf<Message>().apply {
            addAll(getMessages())
        }
    }


    private fun getMessages(): List<Message> {
        return mutableListOf(
            Message("Jeckie Chan", "안녕하세요?", true),
            Message("Jeckie Chan", "저는 제키 찬 입니다.", true),
            Message("Me", "안녕하세요!", false),
            Message("Jeckie Chan", "네!", true),
            Message("Me", "저는 Me예요!", false),
            Message("Jeckie Chan", "ㅋㅋ", true),
            Message("Jeckie Chan", "$100,000줄게요", true),
            Message("Me", "감사합니다!", false)
        )
    }
}