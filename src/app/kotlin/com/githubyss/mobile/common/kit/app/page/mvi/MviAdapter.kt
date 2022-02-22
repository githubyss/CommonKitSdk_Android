package com.githubyss.mobile.common.kit.app.page.mvi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvi.model.User


class MviAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MviAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var textViewUserName: AppCompatTextView = itemView.findViewById(R.id.textViewUserName) as AppCompatTextView
        private var textViewUserEmail: AppCompatTextView = itemView.findViewById(R.id.textViewUserEmail) as AppCompatTextView
        private var imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar) as ImageView
        fun bind(user: User) {
            textViewUserName.text = user.first_name
            textViewUserEmail.text = user.email
            Glide.with(imageViewAvatar.context).load(user.avatar).into(imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}
