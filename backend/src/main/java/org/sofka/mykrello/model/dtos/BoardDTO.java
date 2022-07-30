package org.sofka.mykrello.model.dtos;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * BoardDTO is a data transfer object that represents a board.
 */
@Data
public class BoardDTO {
    private Integer id;

    private String name;

    private Instant createdAt = Instant.now();

    private Instant updatedAt;

    private List<ColumnDTO> columns = new ArrayList<>();
}