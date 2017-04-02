package com.example.aidldemo.myaidlclient;


import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.aidldemo.myaidlservice.ISchool;

public class MainActivity extends AppCompatActivity {
    private ISchool iSchool;

    private Intent intent;

    private TextView schoolText;

    private boolean isBind = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iSchool = ISchool.Stub.asInterface(service);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        schoolText = (TextView) findViewById(R.id.tv_school);
        intent = new Intent();
        intent.setAction("com.example.aidldemo.myaidlservice.school");
        intent.setPackage("com.example.aidldemo.myaidlservice");
        bind();
    }

    private void bind() {

        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    public void getSchool(View view) {
        try {
            if (!isBind) {
                bind();
            }
            schoolText.setText("schoolName:" + iSchool.getSchoolName() + "\nschoolNum:" + iSchool.getStudentNum() + "\nstudent:" + iSchool.getStudent());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
