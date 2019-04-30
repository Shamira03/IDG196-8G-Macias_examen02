package com.gmail.macias3roa.exposed

import com.gmail.macias3roa.products.GetProductQueryResponse

interface StoreProcedureCalls {
    fun getProduct(id: Int?): GetProductQueryResponse
}