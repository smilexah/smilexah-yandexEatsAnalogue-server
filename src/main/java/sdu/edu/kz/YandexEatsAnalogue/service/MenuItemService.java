package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.MenuItem;

import java.util.List;

@Service
public interface MenuItemService {
    //    void createMenuItem(MenuItemDTO menuItemDTO);
//    void updateMenuItem(MenuItemDTO menuItemDTO);
//    void deleteMenuItem(String id);
//    MenuItemDTO getMenuItem(String id);
//    List<MenuItemDTO> getAllMenuItems();
    MenuItem saveMenuItem(MenuItem menuItem);
    List<MenuItem> getMenuItemsByRestaurant(Long restaurantId);
}
