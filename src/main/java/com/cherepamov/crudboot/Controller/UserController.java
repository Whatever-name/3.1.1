package com.cherepamov.crudboot.Controller;

import com.cherepamov.crudboot.Model.User;
import com.cherepamov.crudboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public String allUsers(ModelMap model){
        List<User> userList = userService.getUsers();
        model.addAttribute("allUsers", userList);
        return "users";

    }

    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("updatedUser", userService.findUser(id));
        return "edit";
    }


    @PatchMapping("/{id}")
    public String patchUser(@ModelAttribute("updatedUser") User user){
        userService.editUser(user);
        return "redirect:/users";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user){
        return "add";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";
    }
}
