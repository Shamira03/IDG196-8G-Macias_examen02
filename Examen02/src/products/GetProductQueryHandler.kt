package com.gmail.macias3roa.products

import com.gmail.macias3roa.RequestHandler
import com.gmail.macias3roa.exposed.StoreProcedureCalls

class GetProductQueryHandler(private val spc: StoreProcedureCalls) :
    RequestHandler<GetProductQuery, GetProductQueryResponse> {
    override fun handle(message: GetProductQuery): GetProductQueryResponse {
        val response = spc.getProduct(message.id)
        return response
    }
}