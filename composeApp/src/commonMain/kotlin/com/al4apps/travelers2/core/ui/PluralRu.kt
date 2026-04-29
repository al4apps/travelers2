package com.al4apps.travelers2.core.ui

/**
 * Russian plural form selector. Returns one of three localized strings depending on count.
 *
 * Examples:
 *  pluralRu(1, "место", "места", "мест") → "место"
 *  pluralRu(2, "место", "места", "мест") → "места"
 *  pluralRu(5, "место", "места", "мест") → "мест"
 *  pluralRu(11, "место", "места", "мест") → "мест"
 *  pluralRu(21, "место", "места", "мест") → "место"
 */
fun pluralRu(
    count: Int,
    one: String,
    few: String,
    many: String,
): String {
    val n = count % 100
    val n1 = count % 10

    return when {
        n in 11..14 -> many
        n1 == 1 -> one
        n1 in 2..4 -> few
        else -> many
    }
}

/** Builds a localized "<count> <plural>" phrase, e.g. "5 мест". */
fun pluralRuWithCount(
    count: Int,
    one: String,
    few: String,
    many: String,
): String = "$count ${pluralRu(count = count, one = one, few = few, many = many)}"
