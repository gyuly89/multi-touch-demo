package com.giulia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {

    private View listenerTouchView;
    private TextView eventInfoText;

    private static final int SIZE = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        listenerTouchView = findViewById(R.id.listener_touch_view);
        listenerTouchView.setOnTouchListener(this);
        eventInfoText = (TextView) findViewById(R.id.event_text);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();
        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);
        int index = event.getPointerId(event.getActionIndex());

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                eventInfoText.setText("First touch event arrived at x: " + x + " and y: " + y);
                break;
            case MotionEvent.ACTION_UP:
                view.setBackgroundColor(getResources().getColor(android.R.color.white));
                eventInfoText.setText("All fingers lifted!");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                eventInfoText.setText("Touch event arrived at x: " + x + " and y: " + y + " with index " + (index +1));
                break;
            case MotionEvent.ACTION_POINTER_UP:
                eventInfoText.setText("Finger lifted at x: " + x + " and y: " + y + " with index: " + (index + 1));
                break;
        }
        return true;
    }
}
