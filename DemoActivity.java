package tt.chiao.com.myapplication;


/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class DemoActivity extends BaseActivity {
    @Override
    public int getLayoutResourseId() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView() {
        VersionUpdateUtil.getInstance().registerReceiver(this);
        VersionUpdateUtil.getInstance().startDownLoad(this);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        VersionUpdateUtil.getInstance().unregisterReceiver(this);
    }
}
