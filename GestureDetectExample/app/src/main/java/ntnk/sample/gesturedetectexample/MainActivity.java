package ntnk.sample.gesturedetectexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private GestureDetectorCompat detector;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView = findViewById(R.id.imageView);
        detector = new GestureDetectorCompat(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN: {
//                Toast.makeText(this, "MotionEvent.ACTION_DOWN: X = "+ event.getX()+", Y =" + event.getY(), Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                Toast.makeText(this, "MotionEvent.ACTION_UP", Toast.LENGTH_SHORT).show();
//                break;
//            }
////            MotionEvent.AC
//        }
//        return super.onTouchEvent(event);
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getPointerCount() > 1 && e2.getPointerCount() > 1) {
            //distance 1
            int touchId1 = e1.getPointerId(0);
            int touchId2 = e1.getPointerId(1);

            int pointerIndex1 = e1.findPointerIndex(touchId1);
            int pointerIndex2 = e1.findPointerIndex(touchId2);

            double distanceBefore = Math.pow(e1.getX(pointerIndex1) - e1.getX(pointerIndex2), 2)
                    + Math.pow(e1.getY(pointerIndex1) - e1.getY(pointerIndex2), 2);

            //distance after
            int touchId1A = e2.getPointerId(0);
            int touchId2A = e2.getPointerId(1);

            int pointerIndex1A = e2.findPointerIndex(touchId1A);
            int pointerIndex2A = e2.findPointerIndex(touchId2A);

            double distanceAfter = Math.pow(e2.getX(pointerIndex1A) - e2.getX(pointerIndex2A), 2)
                    + Math.pow(e2.getY(pointerIndex1) - e2.getY(pointerIndex2), 2);


            //distance 2
            if (distanceAfter - distanceBefore > 10) {
                imageView.setScaleX(2);
                imageView.setScaleY(2);
//            Toast.makeText(this, "Left to right", Toast.LENGTH_SHORT).show();
            } else {
                imageView.setScaleX(0.5f);
                imageView.setScaleY(0.5f);
//            Toast.makeText(this, "Right to left", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Less then 2 touch", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
