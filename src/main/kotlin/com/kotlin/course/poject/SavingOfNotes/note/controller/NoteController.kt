package com.kotlin.course.poject.SavingOfNotes.note.controller

import com.kotlin.course.poject.SavingOfNotes.note.model.Note
import com.kotlin.course.poject.SavingOfNotes.note.repository.INoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*
import java.time.Instant

// https://notes.com/notes/...

@RestController
@RequestMapping("/notes")
class NoteController (private val repository: INoteRepository) {


    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long
    )
    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping()
    fun save(@RequestBody body: NoteRequest): NoteResponse {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId()
            )
        )
        return note.toResponse()
    }

    @GetMapping("/{id}")
    fun findByOwnerId(@RequestParam(required = true) ownerId: String): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map {
                it.toResponse()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) {
        repository.deleteById(ObjectId(id))
    }

    private fun Note.toResponse(): NoteController.NoteResponse{
        return NoteResponse(
            id = id.toHexString(),
            title = title,
            content = content,
            color = color,
            createdAt = createdAt,
        )
    }
}