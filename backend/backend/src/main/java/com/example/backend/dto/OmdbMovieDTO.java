package com.example.backend.dto;

import lombok.Data;

@Data
public class OmdbMovieDTO {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;
}
