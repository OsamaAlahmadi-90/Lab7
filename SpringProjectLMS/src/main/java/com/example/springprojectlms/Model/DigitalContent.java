package com.example.springprojectlms.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class DigitalContent {

    @NotEmpty(message = "id Cannot be empty")
    private String id;
    @NotEmpty(message = "Author Cannot be empty")
    private String author;
    @NotEmpty(message = "Title Cannot be empty")
    private String title;

    @NotEmpty(message = "contentText Cannot be empty")
    @Size(max = 10000, message = "ContentText cannot have more than 10000 characters")
    private String contentText;

    @NotEmpty(message = "contentText Cannot be empty")
    @Pattern(regexp = "(?i)^(Journal|Research|Book)$", message = "Type can only be Journal, Research or Book")
    private String type;

    @NotEmpty(message = "category Cannot be empty")
    @Pattern(regexp = "(?i)^(Medicine|Scientific|Technology|Sports|Geography|Astronomy|Plants|Animals)$", message = "Category can only be Medicine|Scientific|Technology|Sports|Geography|Astronomy|Plants|Animals")
    private String category;

    @NotEmpty(message = "Language Cannot be empty")
    @Pattern(regexp = "(?i)^(English|Arabic)$" , message = "Language can only be English or Arabic")
    private String language;

    @NotEmpty(message = "publishDate Cannot be empty")
    @PastOrPresent(message = "PublishDate can only be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

}
