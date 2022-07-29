package org.sofka.mykrello.model.service.interfaces;

import org.sofka.mykrello.model.domain.BoardDomain;
import org.sofka.mykrello.model.dtos.BoardDTO;

import java.util.List;

/**
 * Repositorio del dominio BoardDomain
 *
 * @author Julian Lasso <julian.lasso@sofka.com.co>
 * @version 0.0.1 2022-07-26
 */
public interface BoardServiceInterface {
    /**
     * Develve el listado de todos los tableros existenes en el sistema
     *
     * @return Listado de tableros
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 0.0.1 2022-07-26
     */
    public List<BoardDomain> getAll();

    /**
     * Busca un tablero en relación al identificador
     *
     * @param id Identificador del tablero
     * @return Tablero buscado o null en caso de no encontrarlo
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 0.0.1 2022-07-26
     */
    public BoardDTO findById(Integer id);

    /**
     * Crea un nuevo tablero
     *
     * @param board Datos del tablero a crear
     * @return Tablero nuevo
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 0.0.1 2022-07-26
     */
    public BoardDTO create(BoardDomain board);

    /**
     * Actualiza el tablero indicado
     *
     * @param id    Identificador del tablero a actualizar
     * @param board Datos del tablero a actualizar
     * @return Tablero actualizado
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 0.0.1 2022-07-26
     */
    public BoardDomain update(Integer id, BoardDomain board);

    /**
     * Borra el tablero indicado
     *
     * @param id Identificador del tablero a borrar
     * @return Tablero borrado
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 0.0.1 2022-07-26
     */
    void delete(Integer id);
}