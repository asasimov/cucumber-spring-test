package ru.otus.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import ru.otus.page.BasePage;
import ru.otus.page.NameOfElement;

import java.lang.reflect.Field;

@Service
public class ElementResolver {

    private static final Logger logger = LogManager.getLogger(ElementResolver.class);

    public WebElement getElementByName(String cucumberElementName, BasePage page) {
        Class<?> clazz = page.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NameOfElement.class)) {
                NameOfElement nameOfElementAnnotation = field.getAnnotation(NameOfElement.class);
                if (nameOfElementAnnotation.value().equals(cucumberElementName)) {
                    try {
                        return (WebElement) field.get(page);
                    } catch (IllegalAccessException ex) {
                        logger.error("Is no such element with name {} at page {}", cucumberElementName, page.getClass().getName());
                    }
                }
            }
        }
        throw new IllegalArgumentException("ERROR: there is no such element with name " + cucumberElementName + " at page " + page.getClass().getName());
    }

}