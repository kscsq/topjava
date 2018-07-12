package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    void update(Meal meal);

    Collection<MealWithExceed> getAll(int userId);

    Collection<MealWithExceed> getAllWithTimeAndDate(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate, int userId);
}