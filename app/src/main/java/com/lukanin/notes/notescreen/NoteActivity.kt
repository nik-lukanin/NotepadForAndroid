package com.lukanin.notes.notescreen

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.lukanin.notes.R
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity: MvpAppCompatActivity(), NoteView{

    @InjectPresenter
    lateinit var presenter: NotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val id = intent.getLongExtra("id", 1)
        presenter.initNote(id)
    }

    override fun setTitle(title: String){
        title_note.text = title
    }

    override fun setText(text: String){
        editor_field.setText(text)
    }

    override fun onPause() {
        super.onPause()
        presenter.saveNote(editor_field.text.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.idle, R.anim.right_out)
    }

}