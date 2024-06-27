package com.example.arsnapchat

import jnr.ffi.LibraryLoader
import jnr.ffi.Pointer
import java.io.File

interface HunspellLibrary {
    fun Hunspell_create(affpath: String, dpath: String): Pointer
    fun Hunspell_destroy(handle: Pointer)
    fun Hunspell_spell(handle: Pointer, word: String): Int
}

class Hunspell(private val affPath: String, private val dicPath: String) {
    private val lib: HunspellLibrary
    private val handle: Pointer

    init {
        lib = LibraryLoader.create(HunspellLibrary::class.java).load("hunspell")
        handle = lib.Hunspell_create(affPath, dicPath)
    }

    fun spell(word: String): Boolean {
        return lib.Hunspell_spell(handle, word) == 1
    }

    fun close() {
        lib.Hunspell_destroy(handle)
    }
}
