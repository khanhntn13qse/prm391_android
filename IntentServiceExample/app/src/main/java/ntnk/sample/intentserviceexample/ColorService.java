package ntnk.sample.intentserviceexample;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ColorService extends Service {
    private static int[] COLORS = {Color.BLACK, Color.WHITE, Color.BLUE, Color.YELLOW, Color.RED};
    public static int INDEX_COLOR = 0;
    private Looper serviceLooper;
    private ColorServiceHandler colorServiceHandler;

    public ColorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        serviceLooper = thread.getLooper();
        colorServiceHandler = new ColorServiceHandler(serviceLooper);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = colorServiceHandler.obtainMessage();
        msg.arg1 = startId;
        colorServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private final class ColorServiceHandler extends Handler {
        public ColorServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
//                while ()
                Thread.sleep(500);
                MainActivity.changeColor(INDEX_COLOR++);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);
        }
    }

}
