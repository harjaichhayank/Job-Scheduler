package com.example.jobScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int JOB_ID = 123;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ScheduleJob(View view) {
        Toast.makeText(this, "Job Started", Toast.LENGTH_SHORT).show();
        ComponentName componentName = new ComponentName(this,ExampleJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID,componentName).setRequiresCharging(true).setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true).setPeriodic(15*60*1000).build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert jobScheduler != null;
        int resultCode = jobScheduler.schedule(jobInfo);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job Scheduled");
        }else{
            Log.d(TAG,"Job Scheduled Failed");
        }
    }

    public void CancelJob(View view) {
        Toast.makeText(this, "Job Cancelled", Toast.LENGTH_SHORT).show();
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert jobScheduler != null;
        jobScheduler.cancel(JOB_ID);
        Log.d(TAG,"Cancel Job:");
    }
}
