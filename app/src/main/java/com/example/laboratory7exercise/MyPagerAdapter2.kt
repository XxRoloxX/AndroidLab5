package com.example.laboratory7exercise

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter2(fa: SwipeFragment): FragmentStateAdapter(fa) {
    override fun createFragment(position: Int): Fragment {
        return if(position==0){
            Fragment1.newInstance("Name")
        }else{
            Fragment2.newInstance("Image")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}