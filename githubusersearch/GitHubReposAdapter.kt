package com.example.githubusersearch

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class GitHubReposAdapter(val dataList: List<GitHubRepos>)
    : RecyclerView.Adapter<GitHubReposAdapter.ItemViewHolder>()
{
    // 뷰 홀더 클래스 ItemViewHolder
    class ItemViewHolder(val view: View)
        :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.name).text = dataList[position].name
        holder.view.findViewById<TextView>(R.id.html_url).text = dataList[position].html_url
        holder.view.findViewById<TextView>(R.id.description).text = dataList[position].description
        holder.view.findViewById<TextView>(R.id.forks_count).text = dataList[position].forks_count.toString()
        holder.view.findViewById<TextView>(R.id.watchers_count).text = dataList[position].watchers_count.toString()
        holder.view.findViewById<TextView>(R.id.stargazers_count).text = dataList[position].stargazers_count.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.github_repos_item
    }
}