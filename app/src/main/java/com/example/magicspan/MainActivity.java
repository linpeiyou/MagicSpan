package com.example.magicspan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.widget.TextView;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        CharSequence cs = TextStyleUtil.color("haha", Color.BLUE);
        CharSequence cs2 = TextStyleUtil.size(cs, 50);
        tv1.setText(rpb("1.00", "2.00"));
        tv2.setText(rpb("1.00", ""));
        tv3.setText(rpb("", ""));
        tv4.setText(rpb("1.00", null));
        tv5.setText(rpb(null, null));
    }


    /**
     * "1.00"   "2.00"  ->  "1.00%+2.00%"
     * "1.00"   ""      ->  "1.00%"
     * ""       ""      ->  ""
     */
    public CharSequence rpb(String str1, String str2) {
        CharSequence cs = new MagicSpanBuilder()
                .def("0")
                .color(Color.GREEN)
                .defAppend(".00")
                .color(Color.RED)
                .textSize(10)           // 默认"0.00"
                .append(str1)
                .append("%")
                .color(Color.BLUE)
                .textSize(10)
                .defAbove()           // 上面字符串构造成功，后面不成功则用上面的
                .append("+")
                .textSize(15)
                .append(str2)
                .color(Color.MAGENTA)
                .append("%")
                .color(0xFFFF5151)
                .textSize(10)
                .build();
        return cs;
    }


    private void findViews() {
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);
    }
}











