# MagicSpan
封装SpannableStringBuilder  
流式调用  

demo  
```
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
```

效果：  
<img src="/image/pic1.png" width = "360" height ="640"/>
