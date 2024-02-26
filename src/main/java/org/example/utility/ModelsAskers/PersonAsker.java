package org.example.utility.ModelsAskers;

import org.example.models.Person;
import org.example.utility.Console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PersonAsker {
    private final Person person;
    public PersonAsker(){
        this.person = new Person();
    }
    public boolean validateHeight(String height){
        try {
            double i = Double.parseDouble(height);
            if (i <= 0) {Console.print_error("поле height должно быть >0");
                return false;}

            this.person.setHeight(Double.valueOf(height));
            return true;
        }
        catch (NumberFormatException e){
            Console.print_error("поле height должно иметь тип double((");
        }
        return false;
    }
    public boolean validateBirthday(String bh){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate birthday = LocalDate.parse(bh, formatter);
            this.person.setBirthday(birthday.atStartOfDay());
            return true;
        } catch (DateTimeParseException e){
            Console.print_error("неверный формат введенных данных (");
        }
        return false;
    }
    public void askPerson(){
        Console.println_with_t("создание нового экземпляра класса Person:");
        AtomicBoolean correctHeight = new AtomicBoolean(false);
        while (!correctHeight.get()){
            Console.printf_with_t("введите рост (>0)"+Console.promt2);
            String height = Console.in.nextLine().trim();
            correctHeight.set(validateHeight(height));
        }
        AtomicBoolean correctBirthday = new AtomicBoolean(false);
        while (!correctBirthday.get()){
            Console.printf_with_t("введите дату рождения в формате yyyy MM dd"+Console.promt2);
            String birthday = Console.in.nextLine().trim();
            correctBirthday.set(validateBirthday(birthday));
        }
    }
    public Person getPerson(){
        if (!Console.notPrinting) askPerson();
        return this.person;
    }
}
