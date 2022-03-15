package com.githubyss.mobile.common.kit.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * NodeTapState
 * <Description> 节点点击状态
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/04 16:31:07
 */
@Documented
@StringDef({NodeTapState.CLICKABLE, NodeTapState.UNCLICKABLE, NodeTapState.CLICKED, NodeTapState.UNCLICKED, NodeTapState.NULL})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface NodeTapState {
    final String CLICKABLE = "可点击";
    final String UNCLICKABLE = "不可点击";
    final String CLICKED = "已点击";
    final String UNCLICKED = "未点击";
    final String NULL = "空节点";
}
