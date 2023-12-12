package com.example.laboratory7exercise

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "imageDescription"
private const val ARG_PARAM2 = "imageId"

/**
 * A simple [Fragment] subclass.
 * Use the [ImagePagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagePagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var imageDescription: String? = null
    private var imageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageDescription = it.getString(ARG_PARAM1)
            imageId = it.getInt(ARG_PARAM2)
        }
    }

    private fun getPreferences(): SharedPreferences {
        return requireActivity().getSharedPreferences("tab1_settings", Context.MODE_PRIVATE)
    }

//    private fun getImagePreference():Int{
//        return getPreferences().getInt("profile_image", R.drawable.strawberry)
//    }

    private fun saveSelectedImage(){
        val editor = getPreferences().edit()
        editor.putInt("profile_image", imageId!!)
        editor.apply()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_pager, container, false)
    }

    private fun initializeLayout(view:View){
        val imageContainer = view.findViewById<ImageView>(R.id.selected_image_container)
        imageContainer.setImageResource(imageId!!)
        imageContainer.setOnClickListener(imageClickListener)
        view.findViewById<TextView>(R.id.image_description).text = imageDescription

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeLayout(view)
    }

    private val imageClickListener = View.OnClickListener {
        when(it.id){
            R.id.selected_image_container -> {
                saveSelectedImage()
                requireActivity().onBackPressed()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImagePagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(imageDescription: String, imageId: Int) =
            ImagePagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, imageDescription)
                    putInt(ARG_PARAM2, imageId)
                }
            }
    }
}