package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;
import com.ezentwix.teamcostco.repository.ProductsRepository;
import com.ezentwix.teamcostco.dto.ProductsDTO;
import lombok.RequiredArgsConstructor;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<ProductsDTO> list() {

        return productsRepository.getAll();
    }

}
