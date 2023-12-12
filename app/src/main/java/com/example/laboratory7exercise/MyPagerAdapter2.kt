package com.example.laboratory7exercise

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter



class MyPagerAdapter2(fa: SwipeFragment): FragmentStateAdapter(fa) {
    val IMAGES = arrayOf(R.drawable.strawberry, R.drawable.pineapple, R.drawable.apple, R.drawable.watermelon)
    val IMAGE_DESCRIPTIONS = arrayOf("Strawberry", "Pineapple", "Apple", "Watermelon")

    override fun createFragment(position: Int): Fragment {

        return ImagePagerFragment.newInstance(IMAGE_DESCRIPTIONS[position], IMAGES[position])
    }

    override fun getItemCount(): Int {
        return IMAGES.size
    }
}