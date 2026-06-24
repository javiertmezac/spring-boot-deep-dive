package com.itj.bootcamp.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Constraints live on the contract itself. They are enforced when the
 * controller marks the parameter @Valid.
 */
public record CreateTaskRequest(

        @NotBlank(message = "title must not be blank")
        @Size(min = 3, max = 120, message = "title must be 3-120 characters")
        String title) {
}
