package com.wentianyang.androidpractice

import com.wentianyang.androidpractice.SystemBar.ColorBarActivity
import com.wentianyang.androidpractice.SystemBar.ImageBarActivity
import com.wentianyang.androidpractice.ToolBar.ToolBarActivity
import com.wentianyang.rxjavalib.RxDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
       return R.layout.activity_main
    }

    override fun initBeforeView() {
    }

    override fun initView() {
        btn_image_bar.setOnClickListener { ImageBarActivity.startActivity(this) }

        btn_color_bar.setOnClickListener { ColorBarActivity.startActivity(this) }

        btn_tool_bar.setOnClickListener { ToolBarActivity.startActivity(this) }

        btn_rxjava.setOnClickListener { RxDemoActivity.startActivity(this) }
    }
}
