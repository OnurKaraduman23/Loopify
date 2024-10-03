package com.onurkaraduman.loopify.data.repository.auth_repository

import com.google.firebase.auth.FirebaseAuth
import com.onurkaraduman.loopify.common.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val auth: FirebaseAuth) {

    suspend fun signUp(email: String, password: String): Resource<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            Resource.Success(result.user?.uid)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun signIn(email: String, password: String): Resource<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(result.user?.uid)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    fun signOut() = auth.signOut()
}

