package com;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@SpringComponent
@UIScope
public class EmployeeEditorLayout extends VerticalLayout implements KeyNotifier {

    private final EmployeeRepo repo;
    private Employee employee;

    TextField name = new TextField("Name");
    DatePicker beginDatePicker = new DatePicker("Begin Date");
    DatePicker endDatePicker = new DatePicker("End Date");

    Button saveButton = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());

    HorizontalLayout actions = new HorizontalLayout(saveButton, cancel, delete);

    Binder<Employee> binder = new Binder<>(Employee.class);
    private ChangeHandler changeHandler;

    @Autowired
    public EmployeeEditorLayout(EmployeeRepo repo) {
        this.repo = repo;

        add(name, beginDatePicker, endDatePicker, actions);

        binder.bindInstanceFields(this);
        setSpacing(true);

        saveButton.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        saveButton.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editEmployee(employee));
        setVisible(false);
    }

    void delete() {
        repo.delete(employee);
        changeHandler.onChange();
    }

    void save() {
        repo.save(employee);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editEmployee(Employee e) {
        if (e == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = e.getId() != null;
        if (persisted) {
            employee = repo.findById(e.getId()).get();
        }
        else {
            employee = e;
        }
        cancel.setVisible(persisted);
        binder.setBean(employee);
        setVisible(true);
        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public Date calendarBeginDate() {
        LocalDate begin = LocalDate.from(beginDatePicker.getValue());
        return Date.from(begin.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date calendarEndDate() {
        LocalDate end = LocalDate.from(endDatePicker.getValue());
        return Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
