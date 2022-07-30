package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.ColumnForBoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @LuisaAvila @DanielArce
 * ColumnForBoardRepository is an interface which inherits from the JPA repository and allows access to the methods to be consumed by the service to perform the business logic.
 */
public interface ColumnForBoardRepository extends JpaRepository<ColumnForBoardDomain, Integer> {
    /**
     * @param boardId refers to the task identifier of the board to which the columns will be created.
     * @LuisaAvila @DanielArce
     * @Query refers to a request to be made to the database in order to insert the column where the task is located.
     */
    @Modifying
    @Query(value = "INSERT INTO krl_column_for_board (brd_id_board, clm_id_column) VALUES(?1, 1), (?1, 2), (?1, 3)", nativeQuery = true)
    void createColumns(Integer boardId);

    /**
     * @param boardId refers to the identifier of the table to be deleted
     * @LuisaAvila @DanielArce
     * deleteAllBoardId deletes all columns belonging to the selected board
     */

    @Modifying
    void deleteAllByBoardId(Integer boardId);


}
