package com.ezentwix.teamcostco.controller.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezentwix.teamcostco.service.inventory.InventoryService;
import com.ezentwix.teamcostco.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoreyController {
    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        inventoryService.configureModel(model);
        model.addAttribute("products", productService.list());
        return "index";
    }
}
