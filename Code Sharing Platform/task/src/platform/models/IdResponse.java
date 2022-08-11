package platform.models;

import java.util.UUID;

public class IdResponse {

    private final String id;
    public IdResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
