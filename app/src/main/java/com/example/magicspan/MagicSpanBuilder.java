package com.example.magicspan;

import android.text.SpannableStringBuilder;

/**
 * Created by linpeiyou on 2017/12/6.
 */

public class MagicSpanBuilder {

    /** 正常情况下————结果 */
    private SpannableStringBuilder  ssbNormal;
    /** 正常情况下————当前处理的字符串 */
    private CharSequence            curNormal;

    /** 异常情况下————默认结果 */
    private SpannableStringBuilder  ssbDef;
    /** 异常情况下————当前处理的默认字符串 */
    private CharSequence            curDef;

    /** 当前操作对象 */
    private CURRENT_OP              curOp;


    public MagicSpanBuilder() {
        ssbNormal   = new SpannableStringBuilder();
        ssbDef      = new SpannableStringBuilder();
        curOp       = CURRENT_OP.NON;
    }


    /**
     * 添加新的需要处理的字符串
     * @param cs 需要处理的字符串，当cs为 null 或 length==0 时会使用默认字符串
     */
    public MagicSpanBuilder append(CharSequence cs) {
        handleLastOperation();

        // 判断是否中断所有后续操作
        judgeIfStopHandleNextOperation(cs);

        if(curOp != CURRENT_OP.END) {
            curNormal = cs;
            curOp = CURRENT_OP.NORMAL;
        }
        return this;
    }

    /**
     * 是否中断所有后续操作
     */
    private void judgeIfStopHandleNextOperation(CharSequence cs) {
        if(cs == null || cs.length() == 0) {
            curOp = CURRENT_OP.END;
        }
    }

    /**
     * 设置默认字符串，会删除之前设置的默认字符串
     */
    public MagicSpanBuilder def(CharSequence cs) {
        handleLastOperation();

        ssbDef.clear();

        if(curOp != CURRENT_OP.END) {
            curDef = cs;
            curOp = CURRENT_OP.DEF;
        }
        return this;
    }

    /**
     * 对默认字符串进行Append
     */
    public MagicSpanBuilder defAppend(CharSequence cs) {
        handleLastOperation();

        if(curOp != CURRENT_OP.END) {
            curDef = cs;
            curOp = CURRENT_OP.DEF;
        }
        return this;
    }

    /**
     * 设置默认字符串为 当前正常情况下的结果 的值
     */
    public MagicSpanBuilder defAbove() {
        handleLastOperation();

        if(curOp != CURRENT_OP.END) {
            ssbDef = new SpannableStringBuilder(ssbNormal);
            curOp = CURRENT_OP.NON;
        }
        return this;
    }

    public CharSequence build() {
        handleLastOperation();
        return curOp == CURRENT_OP.END ? ssbDef : ssbNormal;
    }

    /**
     * 字体大小
     */
    public MagicSpanBuilder textSize(int sp) {
        if (curOp == CURRENT_OP.NORMAL) {
            curNormal = TextStyleUtil.size(curNormal, sp);
        } else if(curOp == CURRENT_OP.DEF) {
            curDef = TextStyleUtil.size(curDef, sp);
        }
        return this;
    }

    /**
     * 字体颜色
     */
    public MagicSpanBuilder color(int color) {
        if (curOp == CURRENT_OP.NORMAL) {
            curNormal = TextStyleUtil.color(curNormal, color);
        } else if(curOp == CURRENT_OP.DEF) {
            curDef = TextStyleUtil.color(curDef, color);
        }
        return this;
    }

    /**
     * 处理上一次的操作
     */
    private void handleLastOperation() {
        if(curOp == CURRENT_OP.NORMAL) {
            ssbNormal.append(getNonNullCS(curNormal));
            curOp = CURRENT_OP.NON;

        } else if(curOp == CURRENT_OP.DEF) {
            ssbDef.append(getNonNullCS(curDef));
            curOp = CURRENT_OP.NON;
        }
    }

    private CharSequence getNonNullCS(CharSequence cs) {
        return cs == null ? "" : cs;
    }

    /**
     * 当前操作对象
     */
    private enum CURRENT_OP {
        NORMAL, // 处理正常情况下的字符串
        DEF,    // 处理默认显示的字符串
        NON,    // 暂时没有要处理的
        END,    // 接下去所有操作都不处理
    }
}
















