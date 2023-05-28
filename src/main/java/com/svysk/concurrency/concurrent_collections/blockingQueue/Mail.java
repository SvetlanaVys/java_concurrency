package com.svysk.concurrency.concurrent_collections.blockingQueue;

public class Mail {

    private final String sender;

    private final String content;

    public Mail(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
