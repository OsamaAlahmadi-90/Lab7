package com.example.springprojectlms.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Course {

    @NotEmpty(message = "id Cannot be empty")
    private String id;

    @NotEmpty(message = "title Cannot be empty")
    private String title;

    @NotEmpty(message = "major Cannot be empty")
    private String major;

    @NotEmpty(message = "code Cannot be empty")
    private String code;

    @NotNull(message = "hours Cannot be null")
    @Positive(message = "hours must be a positive number")
    private int hours;

    @NotNull(message = "isCore Cannot be null")
    private boolean isCore;

    @NotNull(message = "FinalExamTime cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime FinalExamTime;

}
