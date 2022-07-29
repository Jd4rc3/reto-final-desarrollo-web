package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.ColumnForBoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColumnForBoardRepository extends JpaRepository<ColumnForBoardDomain, Integer> {
    @Modifying
    @Query(value = "INSERT INTO krl_column_for_board (brd_id_board, clm_id_column) VALUES(?1, 1), (?1, 2), (?1, 3)", nativeQuery = true)
    void createColumns(Integer boardId);

    @Modifying
    void deleteAllByBoardId( Integer boardId);


}
