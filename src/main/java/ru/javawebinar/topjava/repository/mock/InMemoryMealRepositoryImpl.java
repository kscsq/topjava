package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());

        }
        // treat case: update, but absent in storage
//        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        if (repository.containsKey(id)) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal.getUserId() != userId)
            return null;
        return meal;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository
                .values()
                .stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getAllWithTimeAndDate(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate, int idUser) {
        return getAll(idUser)
                .stream()
                .filter(x -> x.getTime().isBefore(endTime)
                && x.getTime().isAfter(startTime)
                && x.getDate().isAfter(startDate.minusDays(1))
                && x.getDate().isBefore(endDate.plusDays(1)))
                .collect(Collectors.toList());
    }
}

