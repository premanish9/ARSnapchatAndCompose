package com.example.arsnapchat;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;

public class MyAdminReceiver extends DeviceAdminReceiver {
    @Override
    public void onPasswordFailed(Context context, Intent intent, UserHandle user) {
        super.onPasswordFailed(context, intent, user);
        // Code to handle failed attempt
        takePhoto(context);
    }

    public void takePhoto(Context context) {
       /* Intent intent = new Intent(context, CameraActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);*/
        Log.i("MyAdminReceiver","iincorrect attempt");
    }

}
