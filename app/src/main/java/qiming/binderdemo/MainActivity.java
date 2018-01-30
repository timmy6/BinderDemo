package qiming.binderdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private IRemoteService mRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface(service);
            try {
                int sum = mRemoteService.add(5, 6);
                Log.e(TAG, "sum:" + sum);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    /**
     * 绑定远程服务
     *
     * @param view
     */
    public void bind(View view) {
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction(IRemoteService.class.getName());
        boolean result = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Log.e(TAG, "bind result:" + result);
    }

    /**
     * 解绑远程服务
     *
     * @param view
     */
    public void unBind(View view) {
        unbindService(mConnection);
    }

    /**
     * 杀死远程服务
     *
     * @param view
     */
    public void kill(View view) {
        try {
            android.os.Process.killProcess(mRemoteService.getPid());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}