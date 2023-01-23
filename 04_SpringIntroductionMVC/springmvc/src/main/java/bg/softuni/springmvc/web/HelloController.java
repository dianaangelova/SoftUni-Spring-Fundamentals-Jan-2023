package bg.softuni.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RestController("/hello")
public class HelloController {

//    @GetMapping("/hello")
//    public String hello(Model model, @RequestParam ("num") Integer num){
//
//        //model.addAttribute("num", 3);
//        model.addAttribute("num", num);
//
//        return "helloworld";
//    }

    @GetMapping("/hello/{id}/test")
    public String hello(Model model, @PathVariable("id") Integer id){

        //model.addAttribute("num", 3);
        model.addAttribute("num", id);

        return "helloworld";
    }

}
