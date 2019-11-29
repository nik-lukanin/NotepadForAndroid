package com.lukanin.notes.model

import com.orm.SugarRecord
import java.io.Serializable

class Note: SugarRecord, Serializable {
    var title:String = ""
    var text:String = ""
    var date:String = ""

    constructor()

    constructor(title:String, text:String, date:String) {
        this.title = title
        this.text = text
        this.date = date
    }


}