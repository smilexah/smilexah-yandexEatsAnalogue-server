package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.MenuItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.MenuItemService;
import sdu.edu.kz.YandexEatsAnalogue.utils.mapper.ModelMapperUtil;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu-items")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<?> getAllMenuItems() {
        return new ResponseEntity<>(menuItemService.findAllMenuItems().stream()
                .map(menuItem -> modelMapperUtil.map(menuItem, MenuItemDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItemById(@PathVariable Long id) {
        return menuItemService.findCustomerById(id)
                .map(menuItem -> new ResponseEntity<>(modelMapperUtil.map(menuItem, MenuItemDTO.class), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        menuItemService.saveMenuItem(modelMapperUtil.map(menuItemDTO, MenuItemDTO.class));
        return new ResponseEntity<>(menuItemDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO, @PathVariable Long id) {
        menuItemService.updateMenuItem(modelMapperUtil.map(menuItemDTO, MenuItemDTO.class), id);
        return new ResponseEntity<>(menuItemDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @GetMapping("/restaurants/{restaurantId}")
    // public ResponseEntity<List<MenuItemDTO>>
    // getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
    // List<MenuItem> menuItems = (List<MenuItem>)
    // menuItemService.getMenuItem(restaurantId);
    // List<MenuItemDTO> menuItemDTOs = menuItems.stream()
    // .map(item -> modelMapperUtil.map(item, MenuItemDTO.class))
    // .collect(Collectors.toList());
    // return ResponseEntity.ok(menuItemDTOs);
    // }
}
