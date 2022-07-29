package com.example.fragmentstudy

import android.icu.util.CurrencyAmount
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class CurrencyConverterFragment1: Fragment() {
    val currencyExchangeMap = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "JPY" to 110.0,
        "KRW" to 1150.0
    )

    fun calculateCurrency(amount: Double, from: String, to:String) : Double{
        var USDAmount = if(from != "USD") {
            (amount/currencyExchangeMap[from]!!)
        }else{
            amount
        }

        return currencyExchangeMap[to]!! * USDAmount
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.currency_converter_fragment,
            container,
            false
        )

        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val fromCurrencySpinner = view.findViewById<Spinner>(R.id.from_currency)
        val toCurrencySpinner = view.findViewById<Spinner>(R.id.to_currency)

        val currencySelectionArrayAdapter = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_item,
            listOf("USD", "EUR", "JPY", "KRW")
        )

        currencySelectionArrayAdapter.setDropDownViewResource(
            android.R.layout.simple_dropdown_item_1line
        )
        fromCurrencySpinner.adapter = currencySelectionArrayAdapter
        toCurrencySpinner.adapter = currencySelectionArrayAdapter

        val itemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result.text = calculateCurrency(
                    amount.text.toString().toDouble(),
                    fromCurrencySpinner.selectedItem.toString(),
                    toCurrencySpinner.selectedItem.toString()
                ).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }

        fromCurrencySpinner.onItemSelectedListener = itemSelectedListener
        toCurrencySpinner.onItemSelectedListener = itemSelectedListener

        calculateBtn.setOnClickListener {
            result.text = calculateCurrency(
                amount.text.toString().toDouble(),
                fromCurrencySpinner.selectedItem.toString(),
                toCurrencySpinner.selectedItem.toString()
            ).toString()
        }




        return view
    }
}