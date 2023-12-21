package com.example.mon_api


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var muser: EditText
    private lateinit var mpass: EditText
    private lateinit var mbtn: Button
    private lateinit var mview: View
    private val PASSWORD1 = "1234567890"
    private val PASSWORD2 = "0987654321"
    private val PASSWORD3 = "1234509876"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_login, container, false)
        muser = mview.findViewById(R.id.username)
        mpass = mview.findViewById(R.id.password)
        mbtn = mview.findViewById(R.id.login)

        mbtn.setOnClickListener {
            val isCorrect = validate(muser.text.toString(), mpass.text.toString())
            if (isCorrect) {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.homeframeLayout, RecyclerFragment())
                fragmentTransaction?.commit()
                Toast.makeText(mview.context, "Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(mview.context, "Hey,Password does not match" , Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return mview
    }

    private fun validate(user: String, pass: String): Boolean {
        val storedPasswords = listOf(PASSWORD1, PASSWORD2, PASSWORD3)
        if (user.length != 10) {
            Toast.makeText(mview.context, "username contains exactly 10 Numbers", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        // validation
        try {
            val data = user.toLong()
        } catch (e: NumberFormatException) {
            Toast.makeText(mview.context, "Username contains only Numbers", Toast.LENGTH_SHORT).show()
            return false
        }

        // password
        if (pass.length != 10) {
            Toast.makeText(mview.context, "Password contains exactly 10 Numbers", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        try {
            val data = pass.toLong()
        } catch (e: NumberFormatException) {
            Toast.makeText(mview.context, "Enter only numbers in password", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (user != pass) {
            Toast.makeText(mview.context, "Username and password not match", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return storedPasswords.contains(pass)
    }
}
