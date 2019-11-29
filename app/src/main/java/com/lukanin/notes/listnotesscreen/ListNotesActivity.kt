package com.lukanin.notes.listnotesscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.lukanin.notes.R
import com.lukanin.notes.model.Note
import com.lukanin.notes.notescreen.NoteActivity
import kotlinx.android.synthetic.main.activity_all_notes.*


class ListNotesActivity : MvpAppCompatActivity(), ListNotesView{

    @InjectPresenter
    lateinit var presenter: ListNotesPresenter
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_notes)
        initRecycleView()
    }

    private fun initRecycleView(){
        recycler_view_list.layoutManager = LinearLayoutManager(this)
        recycler_view_list.adapter = presenter.adapter
    }

    fun onClickFloatButton(v: View) {
        presenter.pressedFloatButton()
    }

    override fun startNoteActivity(id: Long){
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.idle)
    }

    override fun showDialogCreateNote(){
        val editText = EditText(this)

        alertDialog = AlertDialog.Builder(this)
            .setTitle("Создать новую запись")
            .setView(editText)
            .setPositiveButton("Создать"){
                    dialog, which -> presenter.createNote(editText.text.toString())
            }
            .setNegativeButton("Отмена"){
                    dialog, which -> presenter.onDismissMessage()
            }
            .setCancelable(false)
            .show()
    }

    override fun showDialogRemoveNote(n: Note){
        alertDialog = AlertDialog.Builder(this)
            .setTitle("Удалить запись?")
            .setPositiveButton("Удалить"){
                    dialog, which -> presenter.removeNote(n)
            }
            .setNegativeButton("Отмена"){
                    dialog, which -> presenter.onDismissMessage()
            }
            .setCancelable(false)
            .show()
    }

    override fun hideMessage(){
        if (alertDialog != null) {
            alertDialog?.setOnDismissListener(null)
            alertDialog?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.updateRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideMessage()
    }
}
