package org.cufy.mmrpc.editor.scripts

import net.harawata.appdirs.AppDirsFactory

private val adf = AppDirsFactory.getInstance()
private const val NAME = "mmrpc-editor"
private const val VERSION = "default"
private const val AUTHOR = "mmRPC"

fun getStdDataDir(): String = adf.getUserConfigDir(NAME, VERSION, AUTHOR)
fun getStdCacheDir(): String = adf.getUserCacheDir(NAME, VERSION, AUTHOR)
