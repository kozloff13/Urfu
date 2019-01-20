package com;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

import java.util.Date;

@Route
public class MainView extends VerticalLayout {

    private final EmployeeRepo repo;

    final Grid<Employee> grid;
    final TextField filter;
    private final Button addNewBtn;

    public MainView(EmployeeRepo repo) {
        this.repo = repo;
        this.grid = new Grid<>(Employee.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New employee", VaadinIcon.PLUS.create());
        EmployeeEditorLayout editor = new EmployeeEditorLayout(repo);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "name", "beginDate", "endDate");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEmployee(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> editor.editEmployee(e.getValue()));

        addNewBtn.addClickListener(e -> editor.editEmployee(new Employee("", editor.calendarBeginDate(), editor.calendarEndDate())));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listEmployee(filter.getValue());
        });

        listEmployee(null);
    }

    void listEmployee(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }

    }
}
