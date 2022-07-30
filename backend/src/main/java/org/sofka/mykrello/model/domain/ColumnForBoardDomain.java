package org.sofka.mykrello.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Column for board domain is used as a link between a board and a column.
 */
@Data
@Entity
@Table(name = "krl_column_for_board")
public class ColumnForBoardDomain {
    /**
     * preUpdate is a method that is called before the update of a columnForBoard.
     */
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null)
            this.updatedAt = Instant.now();
    }

    /**
     * id is the primary key of the columnForBoard.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cfb_id", nullable = false)
    private Integer id;

    /**
     * createdAt is the date when the columnForBoard was created.
     */
    @Column(name = "cfb_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /**
     * updatedAt is the date when the columnForBoard was updated.
     */
    @Column(name = "cfb_updated_at")
    private Instant updatedAt;

    /**
     * boardId is the id of the board.
     */
    @Column(name = "brd_id_board")
    private Integer boardId;
}