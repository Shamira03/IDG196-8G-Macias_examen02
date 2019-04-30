package com.gmail.macias3roa.exposed

import com.gmail.macias3roa.EXPOSED_CONNECTION_STRING
import com.gmail.macias3roa.EXPOSED_DRIVER
import com.gmail.macias3roa.EXPOSED_PASSWORD
import com.gmail.macias3roa.EXPOSED_USER
import com.gmail.macias3roa.dto.ProductDTO
import com.gmail.macias3roa.products.GetProductQueryResponse
import io.ktor.features.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class SPCallsImpl : StoreProcedureCalls {
    override fun getProduct(id: Int?): GetProductQueryResponse {
        val storedProcedureRawSQL = "exec cetyskart.get_product '$id'"
        var products = ArrayList<ProductDTO>()
        Database.connect(
            EXPOSED_CONNECTION_STRING,
            EXPOSED_DRIVER,
            EXPOSED_USER,
            EXPOSED_PASSWORD
        )
        transaction {
            execSp(storedProcedureRawSQL) {
                if (it.next()) {
                    val statusCode = it.getInt("StatusCode")
                    when (statusCode) {
                        500 -> throw Exception("FAIL")
                        404 -> throw NotFoundException()
                    }
                    while (it.next()) {
                        products.add(
                            ProductDTO(
                                it.getInt("product_id"),
                                it.getString("product_name"),
                                it.getString("product_description")

                            )
                        )
                    }
                }
            }
        }
        return GetProductQueryResponse(products)
    }
}



