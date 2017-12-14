package andoird.fiestapp.Object;

/**
 * Created by mathieu on 14/12/2017.
 */

public class ParticipantSoiree {
    private String id;
    private String status;

    public ParticipantSoiree(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

