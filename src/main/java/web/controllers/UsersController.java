package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UsersController {

    //Spring внедрит объект userService в контроллер
    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //Получаем всех людей (Read) из сервиса:
    //Передаем их на отображение в представление
    @GetMapping()
    public String back(Model model) {
        model.addAttribute("users", userService.back());
        return "allUsers";
    }

    //Получаем одного человека (Read) из сервиса:
    //Передаем его на отображение в представление
    //Число в фигурных скобках поместится в аргументы метода backByID()
    @GetMapping(value = "/{id}")
    // При помощи аннотации @PathVariable мы извлекаем число из URLа и получаем к нему доступ внутри этого метода
    public String backByID(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.backByID(id));
        return "poId";
    }

    //Получаем страницу с формой для создания нового Юзера:
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    //Post-запрос, принимаем нового человека и добавляем его в БД:
    @PostMapping()
    //Для создания нового userа и чтобы положить в него данные с формы нужна аннотация @ModelAttribute
    //User user будет получать данные с формы
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        //что-то типа обновления/перенаправления на этот URL:
        return "redirect:/users";
    }

    //Создаем страницу для редактирования Usera:
    @GetMapping("/{id}/edit")
    public String updateUserGetForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.backByID(id));
        return "update";
    }

    //Patch-запрос, принимаем исправленного человека и добавляем его в БД:
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    //Delete-запрос:
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(userService.backByID(id));
        return "redirect:/users";
    }

}
