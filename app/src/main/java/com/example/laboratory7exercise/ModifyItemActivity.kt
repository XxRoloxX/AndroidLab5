package com.example.laboratory7exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laboratory7exercise.databinding.ActivityAddItemBinding
import com.example.laboratory7exercise.databinding.ActivityModifyItemBinding

class ModifyItemActivity : AppCompatActivity() {
    private val dataRepo = MyRepository.getInstance(this)
    private lateinit var binding: ActivityModifyItemBinding
    private var itemId: Int = 0
    private lateinit var item: DBItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getItemDataFromRepository()
        loadItemDataIntoFields()
        setButtons()
        loadDataIntoRadioButtons()
        title="Modify item"
    }

    private fun getItemDataFromRepository(){
        itemId = intent.getIntExtra("id",0)
        item = dataRepo.findById(itemId)!!
    }

    private fun updateDataItemFromFields():DBItem{
        item.mainName = binding.itemModifyMainName.text.toString()
        item.latinName = binding.itemModifyLatinName.text.toString()
        item.price =  Integer.parseInt(binding.itemModifyPrice.text.toString())
        item.quality =binding.itemModifyRating.rating
        item.isFruit =binding.itemModifyIsFruit.isChecked
        item.origin = getDataFromRadioButtons()

        return item
    }

    private fun loadDataIntoRadioButtons(){
        if(item.origin == Origin.Europe){
            binding.modifyOriginButton1.isChecked=true
        }else if (item.origin == Origin.America){
            binding.modifyOriginButton2.isChecked=true
        }else if(item.origin == Origin.Asia){
            binding.modifyOriginButton3.isChecked=true

        }
    }
    private fun getDataFromRadioButtons():Origin{
        if(binding.modifyOriginButton1.isChecked){
            return Origin.Europe
        }else if(binding.modifyOriginButton2.isChecked){
            return Origin.America
        }else{
            return Origin.Asia
        }

    }

    private fun loadItemDataIntoFields(){
        binding.itemModifyMainName.setText(item.mainName)
        binding.itemModifyLatinName.setText(item.latinName)
        binding.itemModifyPrice.setText(item.price.toString())
        binding.itemModifyRating.rating = item.quality
        binding.itemModifyIsFruit.isChecked = item.isFruit
    }


    private fun setButtons(){
        binding.modifyItemSave.setOnClickListener{
            dataRepo.modifyItem(updateDataItemFromFields())
            finish()
        }
        binding.modifyItemCancel.setOnClickListener{
            finish()
        }
    }



}