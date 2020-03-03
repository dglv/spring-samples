package ru.dglv.springxmlcore;

public class Client {
    private final long id;
    private final String fullName;
    private String greetingMsg;

    public Client(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public void setGreetingMsg(String greetingMsg) {
        this.greetingMsg = greetingMsg;
    }

    public String getGreetingMsg() {
        return greetingMsg;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
