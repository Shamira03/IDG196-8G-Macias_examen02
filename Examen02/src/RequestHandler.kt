package com.gmail.macias3roa

interface RequestHandler<in TRequest : Request, out TResponse> {
    fun handle(message: TRequest): TResponse
}