package com.kotlin.course.poject.SavingOfNotes.note.repository

import com.kotlin.course.poject.SavingOfNotes.note.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface INoteRepository: MongoRepository<Note, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<Note>
}
