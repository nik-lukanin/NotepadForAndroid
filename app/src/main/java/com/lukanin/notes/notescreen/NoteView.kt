package com.lukanin.notes.notescreen

import com.arellomobile.mvp.MvpView

interface NoteView: MvpView {
    fun setTitle(title: String)
    fun setText(text: String)
}