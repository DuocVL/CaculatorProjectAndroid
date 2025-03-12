package com.example.caculatorprojectandroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.caculatorprojectandroid.ui.theme.CaculatorProjectAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        enableEdgeToEdge()
        val textView1 = findViewById<TextView>(R.id.text1)
        val textView2 = findViewById<TextView>(R.id.text2)
        val button0 = findViewById<Button>(R.id.bt_0)
        val button1 = findViewById<Button>(R.id.bt_1)
        val button2 = findViewById<Button>(R.id.bt_2)
        val button3 = findViewById<Button>(R.id.bt_3)
        val button4 = findViewById<Button>(R.id.bt_4)
        val button5 = findViewById<Button>(R.id.bt_5)
        val button6 = findViewById<Button>(R.id.bt_6)
        val button7 = findViewById<Button>(R.id.bt_7)
        val button8 = findViewById<Button>(R.id.bt_8)
        val button9 = findViewById<Button>(R.id.bt_9)

        var koToanTu = true
        var duocsuaTv1 = true
        var danhanbang = false
        var ngoaiLe = false

        var toanHangClick = View.OnClickListener { view ->
            val button = view as Button
            val buttonText = button.text.toString()
            if(ngoaiLe){
                koToanTu = true
                duocsuaTv1 = true
                danhanbang = false
                ngoaiLe = false
                textView1.setText("")
                textView2.setText(buttonText)
            }else{
                if(!danhanbang){
                    if(koToanTu){
                        if(textView2.text == "0") textView2.setText(buttonText)
                        else {
                            val newStr = textView2.text.toString() + buttonText
                            textView2.setText(newStr)
                        }
                    }else{
                        if(duocsuaTv1){
                            duocsuaTv1 = false
                            textView2.setText(buttonText)
                        }else{
                            val newStr = textView2.text.toString() + buttonText
                            textView2.setText(newStr)
                        }
                    }
                }else{
                    danhanbang = false
                    textView2.setText(buttonText)
                }
            }
        }

        button0.setOnClickListener(toanHangClick)
        button1.setOnClickListener(toanHangClick)
        button2.setOnClickListener(toanHangClick)
        button3.setOnClickListener(toanHangClick)
        button4.setOnClickListener(toanHangClick)
        button5.setOnClickListener(toanHangClick)
        button6.setOnClickListener(toanHangClick)
        button7.setOnClickListener(toanHangClick)
        button8.setOnClickListener(toanHangClick)
        button9.setOnClickListener(toanHangClick)

        val buttonClear = findViewById<Button>(R.id.bt_c)
        buttonClear.setOnClickListener {
            textView1.setText("")
            textView2.setText("0")
            koToanTu = true
            duocsuaTv1 = true
            danhanbang = false
            ngoaiLe = false
        }
        val buttonDelete = findViewById<ImageButton>(R.id.bt_delete)
        buttonDelete.setOnClickListener {
            if(ngoaiLe){
                textView1.setText("")
                textView2.setText("0")
                koToanTu = true
                duocsuaTv1 = true
                danhanbang = false
                ngoaiLe = false
            }else{
                if(textView2.text != "0"){
                    var newstr = textView2.text.dropLast(1)
                    if(newstr.isEmpty() || newstr == "-") textView2.setText("0")
                    else textView2.setText(newstr)
                }
            }
        }
        val buttonCE = findViewById<Button>(R.id.bt_ce)
        buttonCE.setOnClickListener{
            if(ngoaiLe){
                textView1.setText("")
                textView2.setText("0")
                koToanTu = true
                duocsuaTv1 = true
                danhanbang = false
                ngoaiLe = false
            }else{
                if(danhanbang) textView1.setText("")
                textView2.setText("0")
            }

        }
        val buttonAm = findViewById<Button>(R.id.bt_am)
        buttonAm.setOnClickListener {
            if(!ngoaiLe){
                if(textView2.text != "0"){
                    val newStr = "-"+textView2.text.toString()
                    textView2.setText(newStr)
                }
            }
        }

        var toanTuClick = View.OnClickListener { view ->
            val button = view as Button
            val buttontext = button.text.toString()
            if(ngoaiLe){
                textView1.setText("")
                textView2.setText("0")
                koToanTu = true
                duocsuaTv1 = true
                danhanbang = false
                ngoaiLe = false
            }else{
                if(danhanbang){
                    textView1.setText(textView2.text.toString()+buttontext)
                    koToanTu = false
                    danhanbang = false
                }
                else{
                    if(koToanTu){
                        val newstr = textView2.text.toString() + buttontext
                        textView1.setText(newstr)
                        koToanTu = false
                    }else{
                        if(duocsuaTv1){
                            val newstr = textView1.text.toString().dropLast(1) + buttontext
                            textView1.setText(newstr)
                        }else{
                            val toanHang1 = textView1.text.toString().dropLast(1).toIntOrNull() ?:0
                            val toanTu = textView1.text.toString().last()
                            val toanHang2 = textView2.text.toString().toIntOrNull() ?: 0
                            var sum = 0
                            when(toanTu){
                                '+' -> sum = toanHang1 + toanHang2
                                '-' -> sum = toanHang1 - toanHang2
                                'x' -> sum = toanHang1 * toanHang2
                                '/' -> if(toanHang2 != 0) sum = toanHang1/toanHang2 else ngoaiLe = true
                            }
                            if(ngoaiLe){
                                textView1.setText("")
                                textView2.setText("Ngoai le chia cho 0")
                            }else{
                                val newStr = sum.toString() + buttontext
                                textView1.setText(newStr)
                                textView2.setText(newStr.dropLast(1))
                            }
                            koToanTu = false
                            duocsuaTv1 = true
                        }
                    }
                }
            }
        }

        val buttonChia = findViewById<Button>(R.id.bt_chia)
        val buttonCong = findViewById<Button>(R.id.bt_cong)
        val buttonNhan = findViewById<Button>(R.id.bt_nhan)
        val buttonTru = findViewById<Button>(R.id.bt_tru)
        buttonChia.setOnClickListener(toanTuClick)
        buttonCong.setOnClickListener(toanTuClick)
        buttonNhan.setOnClickListener(toanTuClick)
        buttonTru.setOnClickListener(toanTuClick)

        val buttonBang = findViewById<Button>(R.id.bt_bang)
        buttonBang.setOnClickListener {
            danhanbang = true
            duocsuaTv1 = true
            var str1 = textView1.text.toString()
            var str2 = textView2.text.toString()
            if(koToanTu)textView1.setText(str2 + "=")
            else{
                val toanHang1 = str1.dropLast(1).toIntOrNull() ?:0
                val toanTu = str1.last()
                var toanHang2 = str2.toIntOrNull() ?: 0
                var sum = 0
                when(toanTu){
                    '+' -> sum = toanHang1 + toanHang2
                    '-' -> sum = toanHang1 - toanHang2
                    'x' -> sum = toanHang1 * toanHang2
                    '/' -> if(toanHang2 != 0) sum = toanHang1/toanHang2 else ngoaiLe = true
                }
                if(ngoaiLe){
                    textView1.setText("")
                    textView2.setText("Ngoai le chia cho 0")
                }else{
                    textView1.setText(str1+str2)
                    textView2.setText(sum.toString())
                }
            }
        }


    }
}
