import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by nicod on 14/12/2017.
 */

public class ServiceNotification extends IntentService {


    public ServiceNotification(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
