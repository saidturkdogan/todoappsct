package com.ToDoAppSCT.todoappsct;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Builder
public class ApiResponse {
    private Boolean status;
    private String message;
    private Object data;

    public String getStatus() {
        if (this.status == null || this.status) {
            return "success";
        } else {
            return "unsuccess";
        }
    }

    public Object getData() {
        return this.data == null ? "" : this.data;
    }

    public Object getMessage() {
        return this.message == null ? "" : this.message;
    }
}
