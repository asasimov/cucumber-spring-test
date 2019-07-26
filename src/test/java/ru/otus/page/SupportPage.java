package ru.otus.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupportPage extends BasePage {

    @Autowired
    private MenuBlock menu;

    public MenuBlock getMenu() {
        return menu;
    }

    public void open() {
        openSection("help");
    }
}