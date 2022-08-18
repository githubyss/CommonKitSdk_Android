package com.githubyss.mobile.common.kit.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * VersionCode
 * 安卓系统版本号
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/29 14:14:43
 */
@Documented
@IntDef({VersionCode.BASE, VersionCode.BASE_1_1, VersionCode.CUPCAKE, VersionCode.DONUT, VersionCode.ECLAIR, VersionCode.ECLAIR_0_1, VersionCode.ECLAIR_MR1, VersionCode.FROYO, VersionCode.GINGERBREAD, VersionCode.GINGERBREAD_MR1, VersionCode.HONEYCOMB, VersionCode.HONEYCOMB_MR1, VersionCode.HONEYCOMB_MR2, VersionCode.ICE_CREAM_SANDWICH, VersionCode.ICE_CREAM_SANDWICH_MR1, VersionCode.JELLY_BEAN, VersionCode.JELLY_BEAN_MR1, VersionCode.JELLY_BEAN_MR2, VersionCode.KITKAT, VersionCode.KITKAT_WATCH, VersionCode.LOLLIPOP, VersionCode.LOLLIPOP_MR1, VersionCode.M, VersionCode.N, VersionCode.N_MR1, VersionCode.O, VersionCode.O_MR1, VersionCode.P, VersionCode.Q})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface VersionCode {
    
    /**
     * October 2008: The original, first, version of Android.  Yay!
     */
    final int BASE = 1;
    
    /**
     * February 2009: First Android update, officially called 1.1.
     */
    final int BASE_1_1 = 2;
    
    /**
     * May 2009: Android 1.5.
     */
    final int CUPCAKE = 3;
    
    /**
     * September 2009: Android 1.6.
     */
    final int DONUT = 4;
    
    /**
     * November 2009: Android 2.0
     */
    final int ECLAIR = 5;
    
    /**
     * December 2009: Android 2.0.1
     */
    final int ECLAIR_0_1 = 6;
    
    /**
     * January 2010: Android 2.1
     */
    final int ECLAIR_MR1 = 7;
    
    /**
     * June 2010: Android 2.2
     */
    final int FROYO = 8;
    
    /**
     * November 2010: Android 2.3
     */
    final int GINGERBREAD = 9;
    
    /**
     * February 2011: Android 2.3.3.
     */
    final int GINGERBREAD_MR1 = 10;
    
    /**
     * February 2011: Android 3.0.
     */
    final int HONEYCOMB = 11;
    
    /**
     * May 2011: Android 3.1.
     */
    final int HONEYCOMB_MR1 = 12;
    
    /**
     * June 2011: Android 3.2.
     */
    final int HONEYCOMB_MR2 = 13;
    
    /**
     * October 2011: Android 4.0.
     */
    final int ICE_CREAM_SANDWICH = 14;
    
    /**
     * December 2011: Android 4.0.3.
     */
    final int ICE_CREAM_SANDWICH_MR1 = 15;
    
    /**
     * June 2012: Android 4.1.
     */
    final int JELLY_BEAN = 16;
    
    /**
     * November 2012: Android 4.2, Moar jelly beans!
     */
    final int JELLY_BEAN_MR1 = 17;
    
    /**
     * July 2013: Android 4.3, the revenge of the beans.
     */
    final int JELLY_BEAN_MR2 = 18;
    
    /**
     * October 2013: Android 4.4, KitKat, another tasty treat.
     * For more information about this release, see the [Android KitKat overview](/about/versions/kitkat/).
     */
    final int KITKAT = 19;
    
    /**
     * June 2014: Android 4.4W. KitKat for watches, snacks on the run.
     */
    final int KITKAT_WATCH = 20;
    
    /**
     * Temporary until we completely switch to [.LOLLIPOP].
     *
     * @hide
     */
    // final int L = 21;
    
    /**
     * November 2014: Lollipop.  A flat one with beautiful shadows.  But still tasty.
     * For more information about this release, see the [Android Lollipop overview](/about/versions/lollipop/).
     */
    final int LOLLIPOP = 21;
    
    /**
     * March 2015: Lollipop with an extra sugar coating on the outside!
     * For more information about this release, see the [Android 5.1 APIs](/about/versions/android-5.1).
     */
    final int LOLLIPOP_MR1 = 22;
    
    /**
     * M is for Marshmallow!
     * For more information about this release, see the [Android 6.0 Marshmallow overview](/about/versions/marshmallow/).
     */
    final int M = 23;
    
    /**
     * N is for Nougat.
     * For more information about this release, see the [Android Nougat overview](/about/versions/nougat/).
     */
    final int N = 24;
    
    /**
     * N MR1: Nougat++.
     * For more information about this release, see [Android 7.1 for Developers](/about/versions/nougat/android-7.1).
     */
    final int N_MR1 = 25;
    
    /**
     * O.
     * For more information about this release, see the [Android Oreo overview](/about/versions/oreo/).
     */
    final int O = 26;
    
    /**
     * O MR1.
     * For more information about this release, see [Android 8.1 features and APIs](/about/versions/oreo/android-8.1).
     */
    final int O_MR1 = 27;
    
    /**
     * P.
     * For more information about this release, see the [Android 9 Pie overview](/about/versions/pie/).
     */
    final int P = 28;
    
    /**
     * Q.
     */
    final int Q = 29;
}
