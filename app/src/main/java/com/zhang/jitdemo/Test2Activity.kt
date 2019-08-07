package com.zhang.jitdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wushiyi.util.eeeBug
import java.util.*

/**
 * Created by zhangyuncai on 2019/7/20.
 */
class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        val map:MutableMap<String,String>
        map= mutableMapOf()
        map.put("小明1","1234")
        map.put("小明2","1234")
        map.put("小明3","1234")
        map.put("小明4","1234")
        eeeBug("map:${map}")

        //有效期
        var currentTime = Date().time
        eeeBug("currentTime:${currentTime}")
        currentTime++
        eeeBug("currentTime:${currentTime}")
        val beijia=(1000L * 60 * 60 * 24 * 30)
        eeeBug("beijia:${beijia}")
        var resultTime = currentTime + beijia
        eeeBug("resultTime:${resultTime}")
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
                layoutManager = LinearLayoutManager(this@Test2Activity)
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
            val view=       layoutInflater.inflate(R.layout.item_test2_adapter, null)
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

