package edu.fvtc.timediff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    public static final String TAG= "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code for program:
        timeDiffButton();
    }

    private void timeDiffButton() {
        Button btnCalc = findViewById(R.id.btnCalc);
        TextView txtResult = findViewById(R.id.txtResult);

        // Call onClick event
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button working fine");


                try {
                    // Get buttons and text
                    TextView txtStart = findViewById(R.id.txtStart);
                    TextView txtEnd = findViewById(R.id.txtEnd);
                    String startTime = txtStart.getText().toString();
                    String endTime = txtEnd.getText().toString();

                    // Get start time from txtStart
                    String[] startList = startTime.split(":");
                    int startHour = Integer.parseInt(startList[0]);
                    int startMinute = Integer.parseInt(startList[1]);
                    int startSecond = Integer.parseInt(startList[2]);

                    if (startHour > 59 || startMinute > 59 || startSecond > 59 || startList.length > 3 ){
                        // Invalid time input
                        return;
                    }

                    // Get end time from txtEnd
                    String[] endList = endTime.split(":");
                    int endHour = Integer.parseInt(endList[0]);
                    int endMinute = Integer.parseInt(endList[1]);
                    int endSecond = Integer.parseInt(endList[2]);

                    if (endHour > 59 || endMinute > 59 || endSecond > 59 || endList.length > 3 ){
                        // Invalid time input
                        return;
                    }


                    // Convert input to seconds since midnight
                    int startTimeInSeconds = startHour * 3600 + startMinute * 60 + startSecond;
                    int endTimeInSeconds = endHour * 3600 + endMinute * 60 + endSecond;

                    if (startTimeInSeconds > endTimeInSeconds){
                        // Display Text: "Start Time cannot be greater than
                        return;
                    }

                    // Calculate time difference
                    int timeElapsedInSeconds = endTimeInSeconds - startTimeInSeconds;

                    // Convert time elapsed back to hours, minutes, and seconds format
                    int elapsedHours = timeElapsedInSeconds / 3600;
                    int elapsedMinutes = (timeElapsedInSeconds % 3600) / 60;
                    int elapsedSeconds = timeElapsedInSeconds % 60;

                    // Display result
                    String message =  timeElapsedInSeconds + " = "+ elapsedHours + ":" + elapsedMinutes+ ":" + elapsedSeconds;
                    txtResult.setText(message);


                }catch (Exception ex){
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });

    }
}

