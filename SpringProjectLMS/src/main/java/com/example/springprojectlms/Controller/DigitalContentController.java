package com.example.springprojectlms.Controller;


import com.example.springprojectlms.Api.ApiResponse;
import com.example.springprojectlms.Model.DigitalContent;
import com.example.springprojectlms.Service.DigitalContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/v1/lms/digital-content")
@RequiredArgsConstructor
public class DigitalContentController {
    private final DigitalContentService digitalContentService;

    @GetMapping("/get")
    public ResponseEntity<?> getDigitalContent(){
        return ResponseEntity.status(200).body(digitalContentService.getDigitalContents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDigitalContent(@RequestBody @Valid DigitalContent digitalContent, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        digitalContentService.addDigitalContent(digitalContent);
        return ResponseEntity.status(200).body(new ApiResponse("Digital content with ID: " + digitalContent.getId() + " has been added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDigitalContent(@RequestBody @Valid DigitalContent digitalContent,@PathVariable String id ,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(digitalContentService.updateDigitalContent(digitalContent,id)){
            return ResponseEntity.status(200).body(new ApiResponse("Digital content has been updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Couldn't find Digital content wiht id: " + id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDigitalContent(@PathVariable String id){
        if(digitalContentService.deleteDigitalContent(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Digital content has been removed") );

        }
        return ResponseEntity.status(400).body(new ApiResponse("Digital content with ID: "+ id + " was not found"));
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getDigitalContentById(@PathVariable String id){
        if(digitalContentService.getContentById(id) == null){
            return ResponseEntity.status(400).body(new ApiResponse("Couldn't find digital content by id: " + id));
        }

       return ResponseEntity.status(200).body(digitalContentService.getContentById(id));
    }


    @GetMapping("/get-by-author/{author}")
    public ResponseEntity<?> getDigitalContentsByAuthor(@PathVariable String author){
        if(digitalContentService.getContentsByAuthor(author).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Couldn't find Digital content by the author: " + author));
        }

        return ResponseEntity.status(200).body(digitalContentService.getContentsByAuthor(author));
    }

    @GetMapping("/get-by-category-and-length/{category}/{length}")
    public ResponseEntity<?> getContentByCategoryAndLength(@PathVariable String category, @PathVariable int length){
        if (digitalContentService.getByCategoryAndTextLength(category,length).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Couldn't find any digital content with category: " + category +" and Length: " + length));

        }
        return ResponseEntity.status(200).body(digitalContentService.getByCategoryAndTextLength(category,length));
    }

    @GetMapping("/get-after-date/{date}")
    public ResponseEntity<?> getContentsAfterGivenDate(@PathVariable LocalDate date){
        if(digitalContentService.getContentsAfterGivenDate(date).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Couldn't find any digital content after the date: " + date));
        }

        return ResponseEntity.status(200).body(digitalContentService.getContentsAfterGivenDate(date));
    }



}

