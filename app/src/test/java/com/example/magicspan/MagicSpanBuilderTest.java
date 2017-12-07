package com.example.magicspan;

import android.text.SpannableStringBuilder;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by linpeiyou on 2017/12/6.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MagicSpanBuilderTest {

    @Test
    public void test1() {
        assertEquals("1.00%+2.00%",  func("1.00",   "2.00")    );
        assertEquals("1.00%",        func("1.00",   "")        );
        assertEquals("0.00",         func("",       "")        );
        assertEquals("1.00%",        func("1.00",   null)      );
        assertEquals("0.00",         func(null,     "1.00")    );
    }


    public String func(String rate1, String rate2) {
        CharSequence cs = new MagicSpanBuilder()
                .def("0")
                .defAppend(".00")   // 设置默认
                .append(rate1)
                .append("%")
                .defAbove()         // 默认用上面的
                .append("+")
                .append(rate2)
                .append("%")
                .build();
        return cs.toString();
    }
}