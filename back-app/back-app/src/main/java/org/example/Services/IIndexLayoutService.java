package org.example.Services;

import org.example.DTOs.IndexLayoutDTO;

import java.util.List;

public interface IIndexLayoutService {

    IndexLayoutDTO getIndexLayout();

    List<IndexLayoutDTO> getAllIndexLayouts();

}
