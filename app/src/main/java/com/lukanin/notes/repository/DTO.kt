package com.lukanin.notes.repository

import com.lukanin.notes.model.Note

interface DTO {
    fun create(n: Note)
    fun read(id: Long): Note
    fun update(n: Note)
    fun remove(n: Note)
}