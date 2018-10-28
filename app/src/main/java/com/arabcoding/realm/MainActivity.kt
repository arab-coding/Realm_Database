package com.arabcoding.realm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration


class MainActivity : AppCompatActivity() {
        var id :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .name("person.realm").build()

        val realm =Realm.getInstance(config)
        realm.beginTransaction()
            id = realm.where(Person::class.java).findAll().size

        val person = realm.createObject(Person::class.java,id+1)
        person.name = "علي"
        person.age = 22
        person.job = "مدير"
        realm.commitTransaction()

        //update
        val allPerson = realm.where(Person::class.java).equalTo("name","أحمد").findFirst()
        realm.beginTransaction()
        allPerson?.job = "سكرتير"


        /*allPerson.forEach { pers ->
            pers.job = "عامل"
        }*/
        realm.commitTransaction()

        val all = realm.where(Person::class.java).findAll()
        all.forEach { per ->
            println( "id: " + per.id +  " name: " +per.name + " job: " + per.job)
        }
    }

}
