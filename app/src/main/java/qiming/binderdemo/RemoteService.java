package qiming.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by liuqiming on 2018/1/30.
 */

public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public int getPid() throws RemoteException {
            return android.os.Process.myPid();
        }
    };
}