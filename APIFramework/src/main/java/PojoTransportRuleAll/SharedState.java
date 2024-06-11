package PojoTransportRuleAll;

public class SharedState {
    private static SharedState instance = null;
    private String id;

    private SharedState() {}

    public static synchronized SharedState getInstance() {
        if (instance == null) {
            instance = new SharedState();
        }
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}