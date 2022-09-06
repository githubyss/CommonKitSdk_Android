package com.githubyss.mobile.common.kit.enumeration


/**
 * KeyboardKey
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:46:15
 */
sealed class KeyboardKey(val value: String) {
    class ONE : KeyboardKey("1")
    class TWO : KeyboardKey("2")
    class THREE : KeyboardKey("3")
    class FOUR : KeyboardKey("4")
    class FIVE : KeyboardKey("5")
    class SIX : KeyboardKey("6")
    class SEVEN : KeyboardKey("7")
    class EIGHT : KeyboardKey("8")
    class NINE : KeyboardKey("9")
    class ZERO : KeyboardKey("0")
    class DOT : KeyboardKey(".")
    class DELETE : KeyboardKey("delete")
    class CONFIRM : KeyboardKey("confirm")
}
