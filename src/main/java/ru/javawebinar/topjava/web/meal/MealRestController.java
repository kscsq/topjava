package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public void create(Meal meal) {
        service.create(meal);
    }

    public void update(Meal meal) {
        service.update(meal);
    }

    public void delete(int id) throws NotFoundException {
        service.delete(id);
    }

    public Meal get(int id, int unseId) throws NotFoundException {
        return service.get(id, unseId);
    }

    public Collection<MealWithExceed> getAll(int userId){
        return service.getAll(userId);
    }

    public Collection<MealWithExceed> getAllWithTimeAndDate(LocalTime startTime,
                                             LocalTime endTime,
                                             LocalDate startDate,
                                             LocalDate endDate,
                                             int userId) {
        return service.getAllWithTimeAndDate(startTime, endTime, startDate, endDate, userId);
    }

}