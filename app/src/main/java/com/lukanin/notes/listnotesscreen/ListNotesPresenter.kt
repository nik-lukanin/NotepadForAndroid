package com.lukanin.notes.listnotesscreen

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.lukanin.notes.model.Note
import com.lukanin.notes.repository.DTO
import com.lukanin.notes.repository.DataBase
import com.orm.SugarRecord
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class ListNotesPresenter() : MvpPresenter<ListNotesView>() {

    private val service: DTO
    val adapter: ListNotesAdapter = ListNotesAdapter()

    init {
        service = DataBase()
        val count: Int = SugarRecord.count<Note>(Note::class.java).toInt()
        if (count > 0 ) updateRecyclerView()

        adapter.setOnClickMethod { v,n -> onClickItemList(v,n) }
        adapter.setOnLongClickMethod { onLongClickItemList(it) }
    }

    fun onClickItemList(v: View, n: Note){
        viewState.startNoteActivity(n.id)
    }

    fun onLongClickItemList(n: Note):Boolean{
        viewState.showDialogRemoveNote(n)
        return false
    }

    fun pressedFloatButton(){
        viewState.showDialogCreateNote()
    }

    fun createNote(title: String){
        service.create(Note(title, "", getDate()))
        updateRecyclerView()
        viewState.hideMessage()
    }

    fun removeNote(n: Note){
        service.remove(n)
        updateRecyclerView()
        viewState.hideMessage()
    }

    fun updateRecyclerView(){
        val allNotes: List<Note> = SugarRecord.listAll(Note::class.java)
        adapter.updateData(allNotes)
    }

    fun getDate(): String{
        val c: Calendar = Calendar.getInstance()
        return SimpleDateFormat("dd-MM-yyyy").format(c.time)
    }

    fun onDismissMessage(){
        viewState.hideMessage()
    }
}