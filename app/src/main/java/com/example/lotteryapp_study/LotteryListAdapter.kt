package com.example.lotteryapp_study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 주생성자(수정할 일 없기 때문에 List로)
class LotteryListAdapter(val dataList: List<String>)
    : RecyclerView.Adapter<LotteryListAdapter.ItemViewHolder>()
{
    class ItemViewHolder(val view: View)
        :RecyclerView.ViewHolder(view)

    // lotto_item안에 있는 linearlayout을 만들어주는거
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // 한 항목을 표시할 레이아웃 관련 뷰를 만들어 줌
        // viewType 값이 바로 getItemViewType에서 반환한 레이아웃 리소스 식별자 == lotto_item
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return ItemViewHolder(view)
    }

    // 재활용 하는 시점에서 내용도 같이 바꿔야 함
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.num).text = dataList[position]
    }

    // 목록에 들어가는 함수의 개수(로또번호의 개수)
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.lotto_item
    }
}