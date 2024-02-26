package org.example.utility;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.example.models.Ticket;
import org.example.models.Tickets;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;


public class FileManager {
    public String filename;
    public Hashtable<Integer, Ticket> collection;

    public FileManager(String filename) {
        this.filename = filename;

    }

    public String readFileText() {
        try {
            FileReader reader = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(reader);
            String line;
            StringBuilder file_text = new StringBuilder();
            while ((line = br.readLine()) != null) {
                file_text.append(line);
            }
            return file_text.toString();
        } catch (FileNotFoundException e) {
            Console.print_error("файл с таким названием не найден((");
        } catch (IOException e) {
            Console.print_error(e.getMessage());
        }
        return "";

    }

    public void addDataToCollection(CollectionManager collectionManager) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            SimpleModule module = new SimpleModule();

            LocalDateTimeDeserializer localDateTimeDeserializer = new
                    LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
            module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
            xmlMapper.registerModule(module);

            String readContent = this.readFileText().trim().replaceFirst("\uFFFD", "");
            Tickets data = xmlMapper.readValue(readContent, Tickets.class);

            if (data.addToCollectionIfOkay(collectionManager))
                Console.println("тооп! валидные билеты из файла " + new File(filename).getName() + " добавлены в коллекцию!");
            else Console.println("все билеты в файле оказались не валидны и не добавлены в коллекцию(");


        } catch (InvalidFormatException e) {
            Console.print_error("введены невалидные данные(( (несоответствие типа поля)");
        } catch (IOException e) {
            Console.print_error(e.getMessage());
        }

    }

    ;

    public static class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

        @Override
        public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            LocalDate localDate = LocalDate.parse(p.getText(), dateTimeFormatter);

            return localDate.atStartOfDay(ZoneOffset.UTC);
        }
    }

    public boolean writeCollectionToFile(CollectionManager collectionManager) {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        xmlMapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy HH:mm"));

        Hashtable<Integer, Ticket> ht = collectionManager.getTicketsCollection();
        Tickets tickets = new Tickets();
        ht.forEach((k, v) -> tickets.addToList(v));

        try {
            String data = xmlMapper.writeValueAsString(tickets);
            OutputStream outputStream = new FileOutputStream(this.filename);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            if (data == null) return true;

            bufferedOutputStream.write(data.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            Console.println("топчик! коллекция успешно сохранена в файл " + new File(this.filename).getName());
            return true;
        } catch (JsonProcessingException e) {
            Console.print_error(e.getMessage());
        } catch (FileNotFoundException e) {
            Console.print_error("файл с таким названием не найден.");
        } catch (IOException e) {
            Console.print_error("ошибка сохранения коллекции в файл(");
        }
        return false;
    }}


