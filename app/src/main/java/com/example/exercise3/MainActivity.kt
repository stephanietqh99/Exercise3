package com.example.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //get spinner

        val selectedItem = spinnerAge.selectedItemPosition
        Toast.makeText(this,"Selected Item =" + selectedItem,Toast.LENGTH_LONG).show()

        //another way to get spinner item in string
        // val selectedItem = spinnerAge.getItemAtPosition(position)
    }


    /* Part 2
    override fun onClick(v: View?){
        when(v){
            buttonCalculate -> calculatePremium()
            buttonReset -> resetOutput()
            else -> //TODO do something here
        }
    }
    */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

        /* Another way to use onClick
        part1**
        val buttonThird:Button
        buttonThird.setOnClickListener{
            calculatePremium()
        }
        */
    }

    private fun calculatePremium(){
        //get age group : position of an array
        val age: Int = spinnerAge.selectedItemPosition

        var premium = when(age){
            0 -> 60 //less then 17
            1 -> 70 //17 to 25
            2 -> 90 //26 to 30
            3 -> 120 //31 to 40
            4 -> 150 //41 to 55
            else -> 150 //more than 55
        }

        //another way
        /*
        when(age){
            0 -> premium = 60 //less then 17
            1 -> premium = 70 //17 to 25
            2 -> premium = 90 //26 to 30
            3 -> premium = 120 //31 to 40
            4 -> premium = 150 //41 to 55
            else -> premium = 150 //more than 55
        }
         */

        //get radioBtn of gender selection : position is getting ID of radio button
        val gender: Int = radioGroupGender.checkedRadioButtonId
        var extraPaymentMale = 0
        var extraPaymentSmoker = 0
        if(gender == R.id.radioButtonMale){
            //calculate premium for male
           when(age){
                0 -> 0 //less then 17
                1 -> 50 //17 to 25
                2 -> 100 //26 to 30
                3 -> 150 //31 to 40
                4 -> 200 //41 to 55
                else -> 200 //more than 55
            }


        }
        //determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        if(smoker){
            //calculate premium for smoker
            extraPaymentSmoker = when(age){
                0 -> 0 //less then 17
                1 -> 100 //17 to 25
                2 -> 150 //26 to 30
                3 -> 200 //31 to 40
                4 -> 250 //41 to 55
                else -> 300 //more than 55
            }
        }


        val totalInsurance = (premium + extraPaymentMale + extraPaymentSmoker)
        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text = getString(R.string.insurance_premium) + String.format("%s %d", symbol, premium)
}

    fun reset(view: View) {
        checkBoxSmoker.setChecked(false)
        spinnerAge.setSelection(0)
        radioButtonFemale.setChecked(false)
        radioButtonMale.setChecked(false)
        textViewPremium.text = getString(R.string.insurance_premium)
    }
}
