package com.kotlin.course.poject.SavingOfNotes.user.repository

import com.kotlin.course.poject.SavingOfNotes.user.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface IUserRepository: MongoRepository<User, ObjectId> {
    fun findUserByEmail(email: String): User?
}