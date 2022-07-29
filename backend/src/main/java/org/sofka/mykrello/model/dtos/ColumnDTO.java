package org.sofka.mykrello.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sofka.mykrello.model.domain.TaskDomain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnDTO {
    private Integer id;

    private String name;

    private Instant createdAt = Instant.now();

    private Instant updatedAt;

    private List<TaskDomain> tasks = new ArrayList<>();
}
