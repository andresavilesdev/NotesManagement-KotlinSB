package com.kotlin.course.poject.SavingOfNotes

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/saving-of-notes")
class PageController {

    @GetMapping("/home")
    fun getContent(): String {
        return "Hello world!"
    }

}