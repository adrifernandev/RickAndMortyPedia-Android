package com.adrifernandev.rickandmortypedia.common.fundaments.exceptions

import java.io.IOException

class NullResponseBodyException(message: String = NullResponseBodyException::class.java.simpleName) :
    IOException(message)
