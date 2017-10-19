package edu.harding.pinchgesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ScaleGestureDetector scaleGestureDetector;
    private ImageView imageView;
    private float scale = 1f;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = (ImageView) findViewById(R.id.imageView);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        float onScaleBegin = 0;
        float onScaleEnd = 0;


        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {

            scale = scale * scaleGestureDetector.getScaleFactor();
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            Toast.makeText(getApplicationContext(),"Scale Begin", Toast.LENGTH_SHORT).show();
            onScaleBegin = scale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            onScaleEnd = scale;
            if (onScaleEnd > onScaleBegin){
                Toast.makeText(getApplicationContext(),"Scale up by "+ String.valueOf(onScaleEnd/onScaleBegin), Toast.LENGTH_SHORT).show();
            } else if (onScaleEnd < onScaleBegin){
                Toast.makeText(getApplicationContext(),"Scale Down by "+ String.valueOf(onScaleBegin/onScaleEnd), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
