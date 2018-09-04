package com.wentianyang.androidpractice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.wentianyang.base.eventbus.MsgEvent;
import com.wentianyang.base.rx.RxBus;

public class ToolBarActivity extends BaseActivity implements OnMenuItemClickListener {

    private static final String TAG = "ToolBarActivity";

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_context_menu)
    TextView mContextMenu;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ToolBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tool_bar;
    }

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected void initView() {
        initToolBar();
        registerForContextMenu(mContextMenu);
    }

    @SuppressLint("RestrictedApi")
    private void initToolBar() {
        // 导航栏icon
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);
//        mToolbar.setLogo(R.drawable.ic_code_pink_200_24dp);
//        mToolbar.setTitle("这是主标题");
//        mToolbar.setSubtitle("这是副标题");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.base_tool_bar_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_top:
                Toast.makeText(this, "Sticky to top", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                Toast.makeText(this, "home...", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.action_backup:
                Toast.makeText(this, "backup...", Toast.LENGTH_SHORT).show();
                DemoActivity.startActivity(this);
                return true;
            case R.id.action_search:
                Toast.makeText(this, "search...", Toast.LENGTH_SHORT).show();
                ColorBarActivity.startActivity(this);
                RxBus.getInstance().postSticky(new MsgEvent(11, 45, "今天天气不好"));
                return true;
            case R.id.action_scan:
                Toast.makeText(this, "scan...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_message:
                Toast.makeText(this, "message...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
