package com.lukanin.notes.listnotesscreen

import com.arellomobile.mvp.MvpView
import com.lukanin.notes.model.Note

interface ListNotesView: MvpView{
    fun startNoteActivity(id: Long)
    fun showDialogCreateNote()
    fun showDialogRemoveNote(n: Note)
    fun hideMessage()
}