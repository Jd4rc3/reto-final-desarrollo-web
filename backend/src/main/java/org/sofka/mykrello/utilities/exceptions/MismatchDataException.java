package org.sofka.mykrello.utilities.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * MismatchDataException is used to throw an exception when the data is not
 * matching with the expected data.
 *
 * @author JuanDanielArce <jdarce91@misena.edu.co>
 */
public class MismatchDataException extends DataAccessException {
    public MismatchDataException(String msg) {
        super(msg);
    }

    public MismatchDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
