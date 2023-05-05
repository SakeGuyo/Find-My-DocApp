package com.example.findmydocpharm

class User {
    var name:String = ""
    var email:String = ""
    var dob:String = ""
    var password:String = ""
    var userId:String = ""
    var id:String = ""

    constructor(
        name: String,
        email: String,
        dob: String,
        password: String,
        userId: String,
        id: String
    ) {
        this.name = name
        this.email = email
        this.dob = dob
        this.password = password
        this.userId = userId
        this.id = id
    }
    constructor()
}