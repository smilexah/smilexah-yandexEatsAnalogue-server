package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.MenuItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.MenuItem;
import sdu.edu.kz.YandexEatsAnalogue.service.MenuItemService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menuItems")
@RequiredArgsConstructor
public class MenuItemController {
    @Autowired
    private final MenuItemService menuItemService;
    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        MenuItem menuItemRequest = modelMapperUtil.map(menuItemDTO, MenuItem.class);
        MenuItem menuItem = menuItemService.saveMenuItem(menuItemRequest);
        return new ResponseEntity<>(modelMapperUtil.map(menuItem, MenuItemDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurant(restaurantId);
        List<MenuItemDTO> menuItemDTOs = menuItems.stream()
                .map(item -> modelMapperUtil.map(item, MenuItemDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(menuItemDTOs);
    }
}
