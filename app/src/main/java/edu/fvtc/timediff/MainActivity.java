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
        // Set object variables
        Button btnCalc = findViewById(R.id.btnCalc);
        TextView txtResult = findViewById(R.id.txtResult);
        TextView tvValidate1 = findViewById(R.id.tvValidate1);
        TextView tvValidate2 = findViewById(R.id.tvValidate2);


        // Call onClick event
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button working fine");

                try {
                    // Clear validations
                    tvValidate1.setText("");
                    tvValidate2.setText("");

                    // Get buttons and text
                    TextView txtStart = findViewById(R.id.txtStart);
                    TextView txtEnd = findViewById(R.id.txtEnd);
                    String startTime = txtStart.getText().toString();
                    String endTime = txtEnd.getText().toString();

                    // Get and validate start time from txtStart
                    String[] startList = startTime.split(":");
                    if (startList.length != 3) {
                        tvValidate1.setText(R.string.invalid_time_input);
                        return;
                    }
                    int startHour = Integer.parseInt(startList[0]);
                    int startMinute = Integer.parseInt(startList[1]);
                    int startSecond = Integer.parseInt(startList[2]);

                    if (startHour > 23 || startMinute > 59 || startSecond > 59){
                        tvValidate1.setText(R.string.invalid_time_input);
                        return;
                    }

                    // Get and validate end time from txtEnd
                    String[] endList = endTime.split(":");
                    if (endList.length != 3) {
                        tvValidate2.setText(R.string.invalid_time_input);
                        return;
                    }
                    int endHour = Integer.parseInt(endList[0]);
                    int endMinute = Integer.parseInt(endList[1]);
                    int endSecond = Integer.parseInt(endList[2]);

                    if (endHour > 23 || endMinute > 59 || endSecond > 59){
                        tvValidate2.setText(R.string.invalid_time_input);
                        return;
                    }

                    // Convert input to seconds since midnight
                    int startTimeInSeconds = startHour * 3600 + startMinute * 60 + startSecond;
                    int endTimeInSeconds = endHour * 3600 + endMinute * 60 + endSecond;

                    if (startTimeInSeconds > endTimeInSeconds){
                        tvValidate1.setText(R.string.start_time_cannot_be_greater_than_end_time);
                        return;
                    }

                    // Calculate time difference
                    int timeElapsedInSeconds = endTimeInSeconds - startTimeInSeconds;

                    // Convert time elapsed back to hours, minutes, and seconds format
                    int elapsedHours = timeElapsedInSeconds / 3600;
                    int elapsedMinutes = (timeElapsedInSeconds % 3600) / 60;
                    int elapsedSeconds = timeElapsedInSeconds % 60;

                    // Add zeros to the time if needed
                    String formattedHours = String.format("%02d", elapsedHours);
                    String formattedMinutes = String.format("%02d", elapsedMinutes);
                    String formattedSeconds = String.format("%02d", elapsedSeconds);

                    // Display result
                    String message = timeElapsedInSeconds + " = " + formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;
                    txtResult.setText(message);


                }catch (Exception ex){
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });

    }
}

