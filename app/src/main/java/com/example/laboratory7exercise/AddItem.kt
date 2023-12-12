package com.example.laboratory7exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Switch
import com.example.laboratory7exercise.databinding.ActivityAddItemBinding

class AddItem : AppCompatActivity() {
    private val dataRepo = MyRepository.getInstance(this)
    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater);
        setContentView(binding.root)
        setButtons()
        title = "Add Item"
    }

    private fun getOriginButtonValue():Origin{
        if(binding.originButton1.isChecked){
            return Origin.Europe;
        }else if(binding.originButton2.isChecked){
            return Origin.America
        }else if(binding.originButton3.isChecked){
            return Origin.Asia
        }else{
            return Origin.Europe;
        }
    }

    private fun createDataItemFromFields():DBItem{
        val mainName = binding.itemAddMainName.text.toString()
        val latinName = binding.itemAddLatinName.text.toString()
        val price = binding.itemAddPrice.text.toString()
        val quality =binding.itemAddRating.rating
        val isFruit =binding.itemAddIsFruit.isChecked
        val origin = getOriginButtonValue()

        return DBItem(mainName, latinName, price.toInt(), isFruit, quality, origin)
    }

//    val originButtonListener = View.OnClickListener {
//        when (it.id){
//            R.id.origin_button_1 -> {
//
//            }
//        }
//    }

    private fun setButtons(){
        binding.addItemSave.setOnClickListener{
            dataRepo.addItem(createDataItemFromFields())
            finish()
        }
        binding.addItemCancel.setOnClickListener{
            finish()
        }
    }

}