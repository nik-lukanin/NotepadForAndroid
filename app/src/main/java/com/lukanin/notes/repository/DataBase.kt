package com.lukanin.notes.repository

import android.util.Log
import com.lukanin.notes.model.Note
import com.orm.SugarRecord

class DataBase: DTO {
    override fun create(n: Note) {
        n.save()
    }

    override fun read(id: Long): Note {
        return SugarRecord.findById(Note::class.java, id)
    }

    override fun update(n: Note) {
        n.save()
    }

    override fun remove(n: Note) {
        n.delete()
    }
}