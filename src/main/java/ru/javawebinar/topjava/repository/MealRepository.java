package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal);

    boolean delete(int id);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllWithTimeAndDate(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate, int idUser);

}
