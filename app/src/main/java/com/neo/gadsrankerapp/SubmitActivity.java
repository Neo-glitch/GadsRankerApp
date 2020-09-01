package com.neo.gadsrankerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.neo.gadsrankerapp.Utility.ServiceBuilder;
import com.neo.gadsrankerapp.Utility.SubmissionService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener{

    // const
    private static final String TAG = "SubmitActivity";
    private static final int SUBMISSION_SUCCESS_DIALOG = 0;
    private static final int SUBMISSION_FAILURE_DIALOG = 1;
    private static final String SUBMISSION_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    // widgets
    private Window mWindow;
    private Toolbar mToolbar;
    private Button mButton;
    private Dialog mDialog;
    private TextView mFirstName, mLastName, mEmail, mGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // sets activity layout to fullScreen and enables transparent statusBar implementation
        mWindow = getWindow();
        mWindow.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email_address);
        mGithubLink = findViewById(R.id.project_link);

        mButton = findViewById(R.id.submit_btn);
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submit_btn:
                showConfirmationDialog();
                break;
            case R.id.positive_btn:
//                showSubmissionStatusDialog();
                submitProject();
                break;
            case R.id.negative_btn:
                mDialog.dismiss();

        }

    }

    private void submitProject() {
        mDialog.dismiss();
        if(!mFirstName.getText().toString().equals("") && !mLastName.getText().toString().equals("")
        && !mEmail.getText().toString().equals("") && !mGithubLink.getText().toString().equals("")){
            SubmissionService service = ServiceBuilder.buildService(SubmissionService.class);
            final Call<ResponseBody> request = service.submitResult(
                    SUBMISSION_URL,
                    mEmail.getText().toString(),
                    mFirstName.getText().toString(),
                    mLastName.getText().toString(),
                    mGithubLink.getText().toString()
                    );

            request.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful() || response.code() == 200){
                        showSubmissionStatusDialog(SUBMISSION_SUCCESS_DIALOG);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG, "onFailure: failure");
                    showSubmissionStatusDialog(SUBMISSION_FAILURE_DIALOG);
                }
            });
        } else{
            if(mFirstName.getText().toString().equals("")){
                mFirstName.setError("no name");
            }
            if(mLastName.getText().toString().equals("")){
                mLastName.setError("no name");
            }
            if(mEmail.getText().toString().equals("")){
                mEmail.setError("no email");
            }
            if(mGithubLink.getText().toString().equals("")){
                mGithubLink.setError("github link needed");
            }
        }

    }


    private void showConfirmationDialog() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.confirmation_dialog);
        Button positiveButton = mDialog.findViewById(R.id.positive_btn);
        ImageView negativeButton = mDialog.findViewById(R.id.negative_btn);

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
        mDialog.show();
    }

    private void showSubmissionStatusDialog(int DialogId) {
        Dialog dialog = new Dialog(this);
        switch (DialogId){
            case SUBMISSION_SUCCESS_DIALOG:
                dialog.setContentView(R.layout.sub_success_dialog);
                break;
            case SUBMISSION_FAILURE_DIALOG:
                dialog.setContentView(R.layout.sub_unsuccess_dialog);
                break;
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}