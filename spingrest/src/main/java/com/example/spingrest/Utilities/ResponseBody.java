package com.example.spingrest.Utilities;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
public class ResponseBody {
    private Boolean success;
    private String message;
    private Object data;
    private String token;
    
}