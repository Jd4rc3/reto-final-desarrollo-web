package org.sofka.mykrello.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sofka.mykrello.model.domain.TaskDomain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * ColumnDTO is a data transfer object that represents a column.
 *
 * @author JuanDanielArce <jdarce91@misena.edu.co>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnDTO {
    /**
     * The identifier of the column.
     */
    private Integer id;

    /**
     * The name of the column.
     */
    private String name;

    /**
     * When the column was created.
     */
    private Instant createdAt = Instant.now();

    /**
     * When the column was updated.
     */
    private Instant updatedAt;

    /**
     * The list of tasks of the column.
     */
    private List<TaskDomain> tasks = new ArrayList<>();
}
