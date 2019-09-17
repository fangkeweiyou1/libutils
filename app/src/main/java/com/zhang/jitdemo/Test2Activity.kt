package com.zhang.jitdemo

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wushiyi.util.DeviceIdUtil
import com.wushiyi.util.fffBug

/**
 * Created by zhangyuncai on 2019/7/20.
 */
class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) < 0) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 99);
        }
        val deviceId = DeviceIdUtil.getDeviceId(this)
        fffBug("deviceId:${deviceId}")

    }

    inner class MyPagerAdapter : PagerAdapter() {
        override fun isViewFromObject(p0: View, p1: Any): Boolean {
            return p0 == p1
        }

        override fun getCount(): Int {
            return 3
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(R.layout.item_test2_pageradapter, null) as RecyclerView
            when (position) {
                0 -> {
                    view.setBackgroundColor(Color.BLUE)
                }
                1 -> {
                    view.setBackgroundColor(Color.GRAY)
                }
                2 -> {
                    view.setBackgroundColor(Color.GREEN)
                }
            }
            view.run {
                layoutManager = LinearLayoutManager(this@Test2Activity!!)
                adapter = MyAdapter()
                addItemDecoration(DividerItemDecoration(this@Test2Activity, DividerItemDecoration.VERTICAL))

            }
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            super.destroyItem(container, position, `object`)
            container.removeView(`object` as View)
        }

    }

    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            val view = layoutInflater.inflate(R.layout.item_test2_adapter, null)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        }

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }
    }
}

