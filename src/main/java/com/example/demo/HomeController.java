package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    Repository repository;
    @RequestMapping("/")
    public String listJobs(Model model) {
        model.addAttribute("jobs", repository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Job job,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "jobform";
        }
       repository.save(job);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("job", repository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updatejob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", repository.findById(id).get());
        return "jobform";
    }

    @RequestMapping("/delete/{id}")
    public String delJob(@PathVariable("id") long id){
        repository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/searchlist")
    public String search(@RequestParam("searchstring") String searchString, Model model){
        model.addAttribute("jobs", repository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCase(searchString, searchString));
        return "searchlist";
    }
}
