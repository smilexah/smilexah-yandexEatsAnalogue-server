package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.edu.kz.YandexEatsAnalogue.dto.MenuItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.MenuItem;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.repository.MenuItemRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.MenuItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> findAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public Optional<MenuItem> findCustomerById(Long id) {
        return menuItemRepository.findById(id);
    }

    @Override
    public void saveMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();

        Restaurant restaurant = new Restaurant();
        restaurant.setId(menuItemDTO.getRestaurantId());
        menuItem.setRestaurant(restaurant);

        menuItem.setName(menuItemDTO.getName());
        menuItem.setWeight(menuItemDTO.getWeight());
        menuItem.setCalories(menuItemDTO.getCalories());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setIsAvailable(menuItemDTO.getIsAvailable());

        menuItemRepository.save(menuItem);
    }

    @Override
    public void updateMenuItem(MenuItemDTO menuItemDTO, Long id) {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(id);
        if (menuItemOptional.isEmpty()) {
            throw new EntityNotFoundException("Menu item not found");
        }
        MenuItem menuItem = menuItemOptional.get();

        menuItem.setName(menuItemDTO.getName());
        menuItem.setWeight(menuItemDTO.getWeight());
        menuItem.setCalories(menuItemDTO.getCalories());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setIsAvailable(menuItemDTO.getIsAvailable());

        menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
