package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController mealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            AdminRestController adminUserController = context.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "UserName", "email", "password", Role.ROLE_ADMIN));
            mealRestController = context.getBean(MealRestController.class);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String form = request.getParameter("form");

        if (form.equalsIgnoreCase("AuthorizedId")) {
            int userId = Integer.parseInt(request.getParameter("AuthorizedId"));
            AuthorizedUser.setId(userId);
            response.sendRedirect("meals");
            log.info("set userId {}", userId);
            return;
        } else if (form.equalsIgnoreCase("Search")) {
            String st = request.getParameter("startTime");
            String et = request.getParameter("endTime");
            String sd = request.getParameter("startDate");
            String ed = request.getParameter("endDate");
            LocalTime startTime = !st.equalsIgnoreCase("")
                    ? LocalTime.parse(st)
                    : LocalTime.MIN;
            LocalTime endTime = !et.equalsIgnoreCase("")
                    ? LocalTime.parse(et)
                    : LocalTime.MAX;
            LocalDate startDate = !sd.equalsIgnoreCase("")
                    ? LocalDate.parse(sd)
                    : LocalDate.MIN;
            LocalDate endDate = !ed.equalsIgnoreCase("")
                    ? LocalDate.parse(ed)
                    : LocalDate.MAX;

            request.setAttribute("meals",
                    mealRestController.getAllWithTimeAndDate(startTime, endTime, startDate, endDate, AuthorizedUser.getId()));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            log.info("filtered by date and time");
            return;
        }
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")), AuthorizedUser.getId());

        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        mealRestController.create(meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealRestController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(getId(request), AuthorizedUser.getId());
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        mealRestController.getAll(AuthorizedUser.getId()));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
