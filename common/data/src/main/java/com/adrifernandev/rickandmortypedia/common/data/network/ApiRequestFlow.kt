package com.adrifernandev.rickandmortypedia.common.data.network

import android.util.Log
import com.adrifernandev.rickandmortypedia.common.fundaments.exceptions.HTTPException
import com.adrifernandev.rickandmortypedia.common.fundaments.exceptions.HttpStatusCodes
import com.adrifernandev.rickandmortypedia.common.fundaments.exceptions.NoInternetException
import com.adrifernandev.rickandmortypedia.common.fundaments.exceptions.UnknownErrorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException
import retrofit2.HttpException as RetrofitHttpException

fun <T> apiRequestFlow(
    call: suspend () -> Response<T>,
): Flow<Result<T>> = flow {
    try {
        val response = call.invoke()

        if (response.isSuccessful) {
            emit(Result.success(response.body()!!))
        } else {
            throw RetrofitHttpException(response)
        }
    } catch (e: Exception) {
        val errorResult: Result<T> = when (e) {
            is RetrofitHttpException -> {
                when (val statusCode = e.code()) {
                    HttpStatusCodes.BadRequest.code -> {
                        Result.failure(HTTPException("Bad Request", e, statusCode))
                    }

                    HttpStatusCodes.Unauthorized.code -> {
                        Result.failure(HTTPException("Unauthorized", e, statusCode))
                    }

                    HttpStatusCodes.Forbidden.code -> {
                        Result.failure(HTTPException("Forbidden", e, statusCode))
                    }

                    HttpStatusCodes.NotFound.code -> {
                        Result.failure(HTTPException("Not Found", e, statusCode))
                    }

                    HttpStatusCodes.NotAcceptable.code -> {
                        Result.failure(HTTPException("Not Acceptable", e, statusCode))
                    }

                    HttpStatusCodes.Conflict.code -> {
                        Result.failure(HTTPException("Conflict", e, statusCode))
                    }

                    HttpStatusCodes.LengthRequired.code -> {
                        Result.failure(HTTPException("Length Required", e, statusCode))
                    }

                    HttpStatusCodes.PreconditionFailed.code -> {
                        Result.failure(HTTPException("Precondition Failed", e, statusCode))
                    }

                    HttpStatusCodes.RequestEntityTooLarge.code -> {
                        Result.failure(HTTPException("Request Entity Too Large", e, statusCode))
                    }

                    HttpStatusCodes.ExpectationFailed.code -> {
                        Result.failure(HTTPException("Expectation Failed", e, statusCode))
                    }

                    HttpStatusCodes.InternalServerError.code -> {
                        Result.failure(HTTPException("Internal Server Error", e, statusCode))
                    }

                    HttpStatusCodes.BadGateway.code -> {
                        Result.failure(HTTPException("Bad Gateway", e, statusCode))
                    }

                    HttpStatusCodes.ServiceUnavailable.code -> {
                        Result.failure(HTTPException("Service Unavailable", e, statusCode))
                    }

                    HttpStatusCodes.GatewayTimeout.code -> {
                        Result.failure(HTTPException("Gateway Timeout", e, statusCode))
                    }

                    HttpStatusCodes.OK.code,
                    HttpStatusCodes.Created.code,
                    HttpStatusCodes.Accepted.code,
                    HttpStatusCodes.NoContent.code -> {
                        Result.failure(HTTPException("Unexpected success status", e, statusCode))
                    }

                    else -> Result.failure(HTTPException("HTTP Error", e, statusCode))
                }
            }

            is IOException -> Result.failure(NoInternetException("No internet connection"))

            else -> {
                Log.d("Error from data", "Error: $e")
                Result.failure(UnknownErrorException("Unknown error"))
            }
        }
        emit(errorResult)
    }
}.flowOn(Dispatchers.IO)