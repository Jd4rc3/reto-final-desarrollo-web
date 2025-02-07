package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.ColumnDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ColumnRepository is an interface which inherits from the JPA repository and allows access to
 * the methods to be consumed by the service to perform the business logic.
 *
 * @author LuisaAvila <luisaavila304@gmail.com>
 */
public interface ColumnRepository extends JpaRepository<ColumnDomain, Integer> {
    /**
     * Find all columns by board id.
     *
     * @param boardId refers to the board from which we want to find the column
     * @author LuisaAvila <luisaavila304@gmail.com>
     */
    @Query(value = "SELECT * FROM krl_column C INNER JOIN krl_column_for_board kcfb on C.clm_id =" +
            " kcfb.clm_id_column WHERE brd_id_board = ?1", nativeQuery = true)
    List<ColumnDomain> findAllByBoardId(Integer boardId);
}