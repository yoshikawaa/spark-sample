package io.github.yoshikawaa.spark.sample.app.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private String id;
    @NotBlank
    private String title;
    private LocalDateTime created;
    private boolean finished;
}
