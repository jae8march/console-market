package com.company.app.controller.command;


import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.CatalogService;
import com.company.app.service.OrderService;
import com.company.app.util.DateValid;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * Selections of an order by the nearest date. Finds one closest order to the entered date.
 */
public class OrderCommand implements ICommand {
    private final OrderService orderService;
    private final CatalogService catalogService;

    public OrderCommand(OrderService serviceOrder, CatalogService serviceCatalog) {
        orderService = serviceOrder;
        catalogService = serviceCatalog;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        if (data.length != 3) {
            return "Enter complete information about orders. Template:-order yyyy-MM-dd HH:mm:ss";
        }

        Date date = DateValid.parseDate(data[1], data[2]);

        if (date == null) {
            return "Please enter the correct date. Template:-order yyyy-MM-dd HH:mm:ss";
        }

        TreeMap<Date, Map<Frame, Integer>> orders = orderService.showOrders();
        Date targetDate = mappedValue(orders, date);

        if (targetDate == null) {
            return "There is no order close to this date";
        }

        Map<Frame, Integer> order = orders.get(targetDate);
        return printData(order);
    }

    private String printData(Map<Frame, Integer> order) {
        StringBuilder stringBuilder = new StringBuilder();
        double price = 0;

        Set<Map.Entry<Frame, Integer>> set = order.entrySet();
        Map<Frame, Double> catalog = catalogService.showCatalog();

        for (Map.Entry<Frame, Integer> element : set) {
            stringBuilder.append("Product: ").append(element.getKey())
                    .append("    ->    Count: ").append(element.getValue())
                    .append(System.lineSeparator());
            price += element.getValue() * catalog.get(element.getKey());
        }

        price = (double) Math.round(price * 100) / 100;
        stringBuilder.append("Total price: ").append(price);
        return stringBuilder.toString();
    }

    private Date mappedValue(NavigableMap<Date, Map<Frame, Integer>> map, Date key) {
        Date above = map.ceilingKey(key);
        Date below = map.floorKey(key);
        if (below == null && above == null) {
            return null;
        } else if (above == null) {
            return below;
        } else if (below == null) {
            return above;
        }
        return key.getTime() - below.getTime() > above.getTime() - key.getTime() ? above : below;
    }
}
