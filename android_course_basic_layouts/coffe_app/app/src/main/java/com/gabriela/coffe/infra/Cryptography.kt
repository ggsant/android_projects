package com.gabriela.coffe.infra

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

class Cryptography {
    @RequiresApi(Build.VERSION_CODES.M)
    fun encryptString(textToEncrypt: String): String {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)

        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        )
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            "MyKeyAlias",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setRandomizedEncryptionRequired(false)
            .build()
        keyGenerator.init(keyGenParameterSpec)
        val secretKey = keyGenerator.generateKey()

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val encryptedBytes = cipher.doFinal(textToEncrypt.toByteArray(Charsets.UTF_8))
        return android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT)
    }
}