package com.itj.bootcamp.taskflow.ai;

import com.itj.bootcamp.taskflow.Task;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

/**
 * ChatClient is the high-level LLM abstraction. We never talk to the Anthropic
 * HTTP API directly — the spring-ai starter auto-configured a ChatModel and a
 * ChatClient.Builder, just like our greeting starter auto-configured a bean.
 */
@Service
public class TaskAiService {

    private final ChatClient chatClient;

    public TaskAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String prioritize(List<Task> tasks) {
        String taskList = tasks.stream()
                .map(task -> "- " + task.getTitle())
                .collect(Collectors.joining("\n"));

        return chatClient.prompt()
                .system("You are a helpful project assistant. Be concise.")
                .user("""
                      Given the following tasks, suggest a priority order.
                      Return a numbered list, each item with a one-line reason.

                      Tasks:
                      %s
                      """.formatted(taskList))
                .call()
                .content();
    }
}
