package org.sofka.mykrello.utilities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyResponseUtility {
    private Boolean error;
    private String  message;
    private Object  data;

    public MyResponseUtility() {
        error = false;
        message = "Done";
        data = null;
    }

    public void setFields(Boolean error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public void setFields(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public void setFields(Boolean error) {
        this.error = error;
        this.data = null;
        this.message = "Done";
    }

    public void setFields(Boolean error, Object data) {
        this.message = "Done";
        this.data = data;
        this.error = error;
    }
}
