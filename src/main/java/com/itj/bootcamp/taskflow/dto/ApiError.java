package com.itj.bootcamp.taskflow.dto;

import java.util.List;

/**
 * A single, consistent error shape for the whole API.
 */
public record ApiError(int status, String error, String message, List<String> details) {
}
