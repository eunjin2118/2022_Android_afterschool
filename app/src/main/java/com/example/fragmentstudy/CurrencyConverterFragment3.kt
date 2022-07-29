package com.example.fragmentstudy

import android.content.Context
import android.hardware.camera2.TotalCaptureResult
import android.icu.util.CurrencyAmount
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.lang.Exception


class CurrencyConverterFragment3: Fragment() {
    interface CurrencyCalculatrtionLister{
        fun onCalculate(result: Double,
                        amount: Double,
                        from: String,
                        to: String)
    }

    lateinit var listener: CurrencyCalculatrtionLister

    // 엑티비티에 붙을 때 실행된다
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //엑티비티가 CurrencyCalculatrtionLister타입인지 물어보는 것
        if(activity is CurrencyCalculatrtionLister){
            // as가 형변환 하는 것 뒤에 있는게 형변환 하는 타입
            listener = activity as CurrencyCalculatrtionLister
        }else{
            throw Exception("CurrencyCalculatrtionLister 미구현")
        }
    }

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

    lateinit var fromCurrency: String
    lateinit var toCurrency: String
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.currency_converter_fragment2,
            container,
            false
        )

        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val exchangeType = view.findViewById<TextView>(R.id.exchange_type)

        //null일수도 있으니까까
        fromCurrency = arguments?.getString("from", "USD")!!
        toCurrency = arguments?.getString("to", "KRW")!!

        exchangeType.text = "${fromCurrency} => ${toCurrency} 변환"

        calculateBtn.setOnClickListener {
            val result = calculateCurrency(
                amount.text.toString().toDouble(),
                fromCurrency,
                toCurrency
            )

            // TODO: result값을 액티비티로 전달
            listener.onCalculate(
                result,
                amount.text.toString().toDouble(),
                fromCurrency, toCurrency
            )

        }

        return view
    }

    companion object {
        fun newInstance(from: String, to: String): CurrencyConverterFragment3{
            val fragment = CurrencyConverterFragment3()

            // 번들 객체를 만들고 필요한 데이터 저장
            val args = Bundle()
            args.putString("from", from)
            args.putString("to", to)
            fragment.arguments = args

            return fragment
        }
    }

}