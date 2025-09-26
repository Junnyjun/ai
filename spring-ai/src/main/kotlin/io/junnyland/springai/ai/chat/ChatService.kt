package io.junnyland.springai.ai.chat

import org.springframework.ai.chat.messages.SystemMessage
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.ChatOptions
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Service

@Service
class ChatService(
	private val chatModel: ChatModel
) {

	suspend fun message(query: String): String? {
		val systemMessage: SystemMessage = SystemMessage.builder()
			.text("사용자 질문에는 한국어로 답변을 해야 합니다.")
			.build()

		val userMessage: UserMessage = UserMessage.builder()
			.text(query)
			.build()

		val option: ChatOptions = ChatOptions.builder()
			.model("gtp-4o-mini")
			.temperature(0.3)
			.maxTokens(1000)
			.build()

		val prompt = Prompt.builder()
			.messages(listOf(systemMessage, userMessage))
			.chatOptions(option)
			.build()

		val response : ChatResponse = chatModel.call(prompt);
		return response.result.output.text
	}
}