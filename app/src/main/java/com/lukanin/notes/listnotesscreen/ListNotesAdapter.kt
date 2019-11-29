package com.lukanin.notes.listnotesscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukanin.notes.model.Note
import com.lukanin.notes.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.view.*


class ListNotesAdapter(): RecyclerView.Adapter<ListNotesAdapter.ViewHolder>(){

    private var notes: List<Note> = listOf()
    lateinit var clickListener: (View, Note) -> Unit
    lateinit var longClickListener: (Note) -> Boolean

    fun updateData(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    fun setOnClickMethod(method: (View, Note) -> Unit){
        clickListener = method
    }

    fun setOnLongClickMethod(method: (Note) -> Boolean){
        longClickListener = method
    }

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position], clickListener, longClickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer{

        override val containerView: View?
            get() = itemView

        fun bind(item: Note, shortClickMethod: (View, Note) -> Unit, longClickMethod: (Note) -> Boolean){
            itemView.title_list_item.text = item.title
            itemView.date_list_item.text = item.date
            itemView.setOnClickListener {
                shortClickMethod.invoke(itemView, notes.get(layoutPosition))
            }
            itemView.setOnLongClickListener{
                longClickMethod.invoke(notes.get(layoutPosition))
            }
        }

    }
}