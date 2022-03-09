package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * MathOperatorType
 * 数学运算符
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 17:07:03
 */
@Documented
@StringDef({MathOperatorType.NAN, MathOperatorType.ADD, MathOperatorType.SUB, MathOperatorType.MUL, MathOperatorType.DIV, MathOperatorType.EQU})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface MathOperatorType {

    final String NAN = "";
    final String ADD = "＋";
    final String SUB = "－";
    final String MUL = "×";
    final String DIV = "÷";
    final String EQU = "＝";
}
