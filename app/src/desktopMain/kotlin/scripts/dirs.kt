package org.cufy.mmrpc.editor.scripts

import net.harawata.appdirs.AppDirsFactory
import java.io.File

private val adf = AppDirsFactory.getInstance()
private const val NAME = "mmrpc-editor"
private const val VERSION = "default"
private const val AUTHOR = "mmRPC"

fun getStdDataDir(): File = File(adf.getUserConfigDir(NAME, VERSION, AUTHOR))
fun getStdCacheDir(): File = File(adf.getUserCacheDir(NAME, VERSION, AUTHOR))
