package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(
            @RequestParam(value="name", required = true) String name, // query params '?name=~' 강제
//            @RequestParam(value="name", required = false) String name, // query params '?name=~' 선택입력
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // HTTP Response Body 영역에 return value를 '그대로' 전달
    public String helloString( @RequestParam("name") String name ) {
        return "hello " + name;
    }

    // API controller
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi( @RequestParam("name") String name ) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // return type이 object일 경우, 해당 객체가 json 형식으로 HTTP Response가 전달
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
