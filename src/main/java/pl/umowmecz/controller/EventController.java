package pl.umowmecz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.umowmecz.model.Event;
import pl.umowmecz.model.User;
import pl.umowmecz.service.EventService;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("eventList", events);
        return "list";
    }

    @PostMapping
    public String addEvent(@ModelAttribute Event eventModel, RedirectAttributes redirectAttr) {
        eventService.save(eventModel);
        redirectAttr.addFlashAttribute("message", "Event added successfuly");
        return "redirect:/";
    }
}