package com.gmail.macias3roa.impl

import com.gmail.macias3roa.RequestHandler
import com.gmail.macias3roa.dto.ProductDTO
import com.gmail.macias3roa.products.GetProductQuery
import com.gmail.macias3roa.products.GetProductQueryResponse

class ProductApi (
    private val getProductQueryHandler: RequestHandler<GetProductQuery, GetProductQueryResponse>
){
    fun getProduct(request: GetProductRequest): GetProductResponse {
        val response = getProductQueryHandler.handle(GetProductQuery(request.id))
        return GetProductResponse(response.products)
    }


    data class GetProductRequest(
        val id: Int?
    )

    data class GetProductResponse(
        val products: List<ProductDTO>
    )
}





