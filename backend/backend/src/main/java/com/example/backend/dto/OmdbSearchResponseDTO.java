package com.example.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class OmdbSearchResponseDTO {
    private List<OmdbMovieDTO> Search;
    private String totalResults;
    private String Response;
}
