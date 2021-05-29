package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.OrderService;
import com.company.app.util.DateValid;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Views the list of orders for a specified period of time.
 */
public class DateCommand implements ICommand {
    private final OrderService orderService;

    public DateCommand(OrderService service) {
        orderService = service;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        if (data.length != 5) {
            return "Enter complete information about orders. Template:-date yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm:ss";
        }

        Date dateBefore = DateValid.parseDate(data[1], data[2]);
        Date dateAfter = DateValid.parseDate(data[3], data[4]);

        if (dateBefore == null || dateAfter == null) {
            return "Please enter the correct date. Template:-date yyyy-MM-dd HH:mm:ss";
        }

        long longDateBefore = dateBefore.getTime();
        long longDateAfter = dateAfter.getTime();

        TreeMap<Date, Map<Frame, Integer>> orders = orderService.showOrders();
        ArrayList<Date> dates = new ArrayList<>();

        for (Date date : orders.keySet()) {
            if (longDateBefore <= date.getTime() && longDateAfter <= dateAfter.getTime()) {
                dates.add(date);
            }
        }

        if (dates.isEmpty()) {
            return "There are no orders in the given time intervals";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Date date : dates) {
            stringBuilder.append("Date: ").append(date).append(System.lineSeparator()).append(System.lineSeparator());

            Set<Map.Entry<Frame, Integer>> set = orders.get(date).entrySet();
            for (Map.Entry<Frame, Integer> element : set) {
                stringBuilder
                        .append("Product: ").append(element.getKey())
                        .append("    ->    Count: ").append(element.getValue())
                        .append(System.lineSeparator());
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
