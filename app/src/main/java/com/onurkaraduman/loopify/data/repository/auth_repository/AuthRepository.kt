package com.onurkaraduman.loopify.data.repository.auth_repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.onurkaraduman.loopify.common.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    suspend fun signUp(email: String, password: String, userName: String): Resource<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid
            userId?.let {
                saveUserName(userId = userId, userName = userName, email = email)
            }
            Resource.Success(userId)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun signIn(email: String, password: String): Resource<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user?.uid)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun saveUserName(userId: String, userName: String, email: String): Resource<Void> {
        return try {
            val userMap = hashMapOf(
                "userId" to userId,
                "email" to email,
                "userName" to userName
            )
            firestore.collection("users").document(userId).set(userMap).await()
            Resource.Success(null)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getUserName(userId: String): Resource<String?> {
        return try {
            val userDoc = firestore.collection("users").document(userId).get().await()
            if (userDoc.exists()) {
                Resource.Success(userDoc.getString("userName"))
            } else {
                Resource.Error("User not found")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    fun signOut() = auth.signOut()
}

