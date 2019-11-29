package com.lukanin.notes.notescreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.lukanin.notes.model.Note
import com.lukanin.notes.repository.DTO
import com.lukanin.notes.repository.DataBase

@InjectViewState
class NotePresenter(): MvpPresenter<NoteView>() {

    private lateinit var note: Note
    private val service: DTO = DataBase()

    fun initNote(id: Long){
        note = service.read(id)
        viewState.setTitle(note.title)
        viewState.setText(note.text)
    }

    fun saveNote(text: String){
        note.text = text
        service.update(note)
    }
}