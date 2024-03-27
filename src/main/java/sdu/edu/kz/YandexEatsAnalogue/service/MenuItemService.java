package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;

import sdu.edu.kz.YandexEatsAnalogue.dto.MenuItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.MenuItem;

import java.util.List;
import java.util.Optional;

@Service
public interface MenuItemService {
    List<MenuItem> findAllMenuItems();
    Optional<MenuItem> findCustomerById(Long id);
    void saveMenuItem(MenuItemDTO menuItemDTO);
    void updateMenuItem(MenuItemDTO menuItemDTO, Long id);
    void deleteMenuItem(Long id);
}
