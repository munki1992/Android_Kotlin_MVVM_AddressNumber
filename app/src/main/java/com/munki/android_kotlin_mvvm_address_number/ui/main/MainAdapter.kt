package com.munki.android_kotlin_mvvm_address_number.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.munki.android_kotlin_mvvm_address_number.R
import com.munki.android_kotlin_mvvm_address_number.databinding.ItemRecyclerviewBinding
import com.munki.android_kotlin_mvvm_address_number.ui.base.BaseViewHolder

/**
 * BaseViewHolder
 * @author 나비이쁜이
 * @since 2020.10.05
 */
class MainAdapter internal constructor(private val mContext: Context) : RecyclerView.Adapter<BaseViewHolder<String?>>() {

    private var dataList: ObservableArrayList<String?>

    /**
     * 생성자
     */
    init {
        dataList = ObservableArrayList()
    }

    /**
     * onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false))
    }

    /**
     * onBindViewHolder
     */
    override fun onBindViewHolder(holder: BaseViewHolder<String?>, position: Int) {
        holder.bind(dataList[holder.layoutPosition], position)
    }

    /**
     * getItemCount
     */
    override fun getItemCount(): Int {
        return dataList.size
    }

    /************************************************************************************************************************************************/

    /**
     * setItem
     */
    fun setItem(dataList: ObservableArrayList<String?>?) {
        if (dataList == null) return
        this.dataList = dataList
        notifyDataSetChanged()
    }

    /************************************************************************************************************************************************/

    /**
     * 뷰 활용을 위한 Viewholder
     */
    inner class ViewHolder constructor(view: View) : BaseViewHolder<String?>(view) {

        /**
         * itemView Databinding
         */
        private var mBinding: ItemRecyclerviewBinding? = DataBindingUtil.bind(view)

        /**
         * Bind
         */
        override fun bind(itemVo: String?, position: Int?) {
            mBinding!!.tvName.text = itemVo
        }
    }
}