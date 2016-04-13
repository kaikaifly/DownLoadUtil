package tt.chiao.com.myapplication;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class VersionUpdateUtil {

    private static VersionUpdateUtil versionUpdateUtil = null;
    private Receiver receiver = new Receiver();

    private VersionUpdateUtil() {

    }

    public static VersionUpdateUtil getInstance() {
        if (versionUpdateUtil == null) {
            versionUpdateUtil = new VersionUpdateUtil();
        }
        return versionUpdateUtil;
    }

    public void startDownLoad(Context context) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        String url = "http://gdown.baidu.com/data/wisegame/573aaed593e236f4/weixin_760.apk";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir("downloadManager", "weixin.apk");
        request.setTitle("微信");
        request.setDescription("下载微信");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
    }

    public void registerReceiver(Context context) {
        context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(receiver);
    }

    /**
     * 接收下载完成的广播
     */
    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //打开安装应用的界面
            Intent intent1 = new Intent();
            intent1.setAction(Intent.ACTION_VIEW);
            intent1.setDataAndType(
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/downloadManager/weixin.apk"))
                    , "application/vnd.android.package-archive");
            context.startActivity(intent1);
        }
    }
}
