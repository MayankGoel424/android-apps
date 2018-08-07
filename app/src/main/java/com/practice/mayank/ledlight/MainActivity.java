package com.practice.mayank.ledlight;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton btn;

    android.hardware.Camera camera;
    android.hardware.Camera.Parameters parameters;
    boolean isflash=false;
    boolean ison=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
  camera = android.hardware.Camera.open();
     parameters = camera.getParameters();
     isflash = true;
 }
    }
    public void light(View view)
    {
        if(isflash)
        {
            if(!ison)
            {
                parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();

                ison = true;
            }
           else
            {
                parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();

                ison=false;
            }


        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error");
            builder.setMessage("Flash Light is not available");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    finish();
                }
            });
            AlertDialog alt = builder.create();
            alt.show();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera!=null)
        {
            camera.release();
            camera=null;
        }
    }
}
