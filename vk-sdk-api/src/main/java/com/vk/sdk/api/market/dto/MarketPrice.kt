/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 vk.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/
// *********************************************************************
// THIS FILE IS AUTO GENERATED!
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING.
// *********************************************************************
package com.vk.sdk.api.market.dto

import com.google.gson.annotations.SerializedName
import kotlin.Int
import kotlin.String

/**
 * @param amount - Amount
 * @param currency
 * @param text - Text
 * @param amountTo - Amount to for price_type=2
 * @param priceType
 * @param priceUnit
 * @param discountRate
 * @param oldAmount
 * @param oldAmountText - Textual representation of old price
 */
data class MarketPrice(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: MarketCurrency,
    @SerializedName("text")
    val text: String,
    @SerializedName("amount_to")
    val amountTo: String? = null,
    @SerializedName("price_type")
    val priceType: MarketPrice.PriceType? = null,
    @SerializedName("price_unit")
    val priceUnit: MarketPrice.PriceUnit? = null,
    @SerializedName("discount_rate")
    val discountRate: Int? = null,
    @SerializedName("old_amount")
    val oldAmount: String? = null,
    @SerializedName("old_amount_text")
    val oldAmountText: String? = null
) {
    enum class PriceType(
        val value: Int
    ) {
        @SerializedName("0")
        EXACT(0),

        @SerializedName("2")
        RANGE(2),

        @SerializedName("3")
        BY_AGREEMENT(3);
    }

    enum class PriceUnit(
        val value: Int
    ) {
        @SerializedName("0")
        ITEM(0),

        @SerializedName("2")
        HOUR(2),

        @SerializedName("3")
        M2(3),

        @SerializedName("4")
        M3(4);
    }
}
