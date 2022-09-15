package com.zqf.kotlinwanandroid.util

import com.tencent.mmkv.MMKV

/**
 * Author: zqf
 * Date: 2022/09/14
 */
class KVUtil {

    companion object {

        fun encode(k: String, v: String) {
            val kv = MMKV.defaultMMKV()
            kv.encode(k, v)
        }

        fun encode(k: String, v: Boolean) {
            val kv = MMKV.defaultMMKV()
            kv.encode(k, v)
        }

        fun decode(k: String, def: String): String? {
            val kv = MMKV.defaultMMKV()
            return kv.decodeString(k, def)
        }

        fun decode(k: String): Boolean {
            val kv = MMKV.defaultMMKV()
            return kv.decodeBool(k)
        }

        fun decode(k: String, def: Boolean): Boolean {
            val kv = MMKV.defaultMMKV()
            return kv.decodeBool(k, def)
        }
    }
}