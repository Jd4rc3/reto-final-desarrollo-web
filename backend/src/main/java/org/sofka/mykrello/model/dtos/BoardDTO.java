package org.sofka.mykrello.model.dtos;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * BoardDTO is a data transfer object that represents a board.
 *
 * @author JuanDanielArce <jdarce91@misena.edu.co>
 */
@Data
public class BoardDTO {
    /**
     * The identifier of the board.
     */
    private Integer id;

    /**
     * The name of the board.
     */
    private String name;

    /**
     * When the board was created.
     */
    private Instant createdAt = Instant.now();

    /**
     * When the board was updated.
     */
    private Instant updatedAt;

    /**
     * The list of columns of the board.
     */
    private List<ColumnDTO> columns = new ArrayList<>();
}