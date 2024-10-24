package com.breezefieldombooks.features.mylearning

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.breezefieldombooks.CorrectQuesAnsFrag
import com.breezefieldombooks.features.orderhistory.model.ActionFeed


class RetryTabPagerAdapter(fm: FragmentManager?, val topic_id: String, val store_content_id: String) : FragmentStatePagerAdapter(fm!!), ActionFeed {

    override fun refresh() {
        notifyDataSetChanged()
    }


    override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> {
                    CorrectQuesAnsFrag.getInstance(topic_id, store_content_id)
                }
                1 -> {
                    InCorrectQuesAnsFrag.getInstance(topic_id, store_content_id)
                }
                else -> Fragment()
            }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItemPosition(`object`: Any): Int {
        // Causes adapter to reload all Fragments when
        // notifyDataSetChanged is called
        return PagerAdapter.POSITION_NONE
    }
}