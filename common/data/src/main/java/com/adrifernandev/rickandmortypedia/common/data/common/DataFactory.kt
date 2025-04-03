package com.adrifernandev.rickandmortypedia.common.data.common

interface DataFactory<L, R> {
    val localDataStore: L
    val remoteDataStore: R
}
