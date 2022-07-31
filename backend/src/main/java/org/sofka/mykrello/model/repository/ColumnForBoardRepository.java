package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.ColumnForBoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * ColumnForBoardRepository is an interface which inherits from the JPA repository and allows
 * access to the methods to be consumed by the service to perform the business logic.
 *
 * @author LuisaAvila <luisaavila304@gmail.com>
 */
public interface ColumnForBoardRepository extends JpaRepository<ColumnForBoardDomain, Integer> {
    /**
     * create a new column for board in the database.
     *
     * @param boardId refers to the board to which the columns will be created.
     * @author LuisaAvila <luisaavila304@gmail.com>
     */
    @Modifying
    @Query(value = "INSERT INTO krl_column_for_board (brd_id_board, clm_id_column) VALUES(?1, 1)," +
            " (?1, 2), (?1, 3)", nativeQuery = true)
    void createColumns(Integer boardId);

    /**
     * @param boardId refers to the identifier of the table to be deleted
     * @author LuisaAvila <luisaavila304@gmail.com>
     * deleteAllBoardId deletes all columns belonging to the selected board
     */
    @Modifying
    void deleteAllByBoardId(Integer boardId);


}
