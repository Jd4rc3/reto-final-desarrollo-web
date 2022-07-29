package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.ColumnDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColumnRepository extends JpaRepository<ColumnDomain, Integer> {
    @Query(value = "SELECT * FROM krl_column C INNER JOIN krl_column_for_board kcfb on C.clm_id =" +
            " kcfb.clm_id_column WHERE brd_id_board = ?1", nativeQuery = true)
    List<ColumnDomain> findAllByBoardId(Integer boardId);


}
