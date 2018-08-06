package com.wentianyang.androidpractice

import android.annotation.SuppressLint
import android.widget.Toast
import com.wentianyang.androidpractice.action.imagebar.ImageBarActionName
import com.wentianyang.androidpractice.systembar.ColorBarActivity
import com.wentianyang.androidpractice.systembar.ImageBarActivity
import com.wentianyang.androidpractice.toolbar.ToolBarActivity
import com.wentianyang.routerlib.Router
import com.wentianyang.routerlib.RouterRequest
import com.wentianyang.rxjavalib.RxDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initBeforeView() {
    }

    @SuppressLint("ShowToast")
    override fun initView() {
        btn_image_bar.setOnClickListener {
            //            ImageBarActivity.startActivity(this)
            val routerResponse = Router.getInstance().sendMessage(this,
                    RouterRequest.create()
                            .action(ImageBarActionName.NAME)
                            .data(ImageBarActivity.EXTRA_DATA, "from main"))
            Toast.makeText(this, routerResponse.result.toString(), Toast.LENGTH_SHORT).show()
        }

        btn_color_bar.setOnClickListener { ColorBarActivity.startActivity(this) }

        btn_tool_bar.setOnClickListener { ToolBarActivity.startActivity(this) }

        btn_rxjava.setOnClickListener { RxDemoActivity.startActivity(this) }
    }
}
