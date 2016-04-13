package tt.chiao.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourseId());
        initView();
        initData();
    }

    protected void initData() {

    }

    public abstract int getLayoutResourseId();

    protected abstract void initView();

    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void startActivity(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void showToast(String text) {
        if (!isEmpty(text)){
            Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }
    public void showToast(String text, String length) {
        if (!isEmpty(text)){
            if (length.equals("short")){
                Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
            }else if (length.equals("long")){
                Toast.makeText(BaseActivity.this, text, Toast.LENGTH_LONG).show();
            }

        }
    }

    public Boolean isEmpty(String text) {
        if ( TextUtils.isEmpty(text))
            return true;
        else
            return false;
    }

}
