package org.example.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.example.utility.FileManager;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;




public class Ticket implements Comparable<Ticket> {
    @JsonProperty("id")
    private Integer id;
    //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @JsonProperty("name")
    @JsonRawValue
    private String name; //Поле не может быть null, Строка не может быть пустой
    @JsonProperty("coordinates")
    private Coordinates coordinates; //Поле не может быть null

    @JsonProperty("creationDate")
    @JsonRawValue
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm")
    @JsonDeserialize(using = FileManager.ZonedDateTimeDeserializer.class)
    // Doesn't exist, So I created a custom ZonedDateDeserializer utility class.
    private java.time.ZonedDateTime creationDate; //Пол не может быть null, Значение этого поля должно генерироваться автоматически
    @JsonProperty("price")
    private Long price; //Поле может быть null, Значение поля должно быть больше 0
    @JsonProperty("refundable")
    private Boolean refundable; //Поле не может быть null
    @JsonRawValue
    @JsonProperty("tickettype")
    private TicketType type; //Поле не может быть null
    @JsonProperty("person")
    private Person person; //Поле не может быть null
    public void set_id(Integer id){
        this.id = id;
    }
    @JsonIgnore
    public Integer get_id(){
        return this.id;
    }
    public void set_name(String name){
        this.name = name;
    }
    @JsonIgnore
    public String get_name(){
        return this.name;
    }
    public void set_coordinates(Coordinates coor){
        this.coordinates = coor;
    }
    @JsonIgnore
    public Coordinates get_coodinates(){
        return this.coordinates;
    }
    public void set_creationData(ZonedDateTime creationDate){
        this.creationDate = creationDate;
    }
    @JsonIgnore
    public ZonedDateTime get_creationDate(){
        return this.creationDate;
    }
    public void set_price(Long price){
        this.price = price;
    }
    @JsonIgnore
    public Long get_price(){
        return this.price;
    }
    public void set_refundable(Boolean refundable){
        this.refundable = refundable;
    }
    @JsonIgnore
    public Boolean get_refundable(){
        return this.refundable;
    }
    public void set_type(TicketType ticketype){
        this.type = ticketype;
    }
    @JsonIgnore
    public TicketType get_ticketType(){
        return this.type;
    }
    public void set_person(Person p){
        this.person = p;
    }
    @JsonIgnore
    public Person get_person(){
        return this.person;
    }
    public Ticket(){
        this.creationDate = ZonedDateTime.now();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "\n\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tcoordinates=" + coordinates.toString() +
                ", \n\tcreationDate=" + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDate) +
                ", \n\tprice=" + price +
                ", \n\tis able to refund=" + refundable +
                ", \n\ttype=" + type.toString() +
                ", \n\tperson=" + person.toString() +
                '}';
    }

    @Override
    public int compareTo(Ticket o) {
        int res = Integer.compare(this.get_ticketType().ordinal(), o.get_ticketType().ordinal());
        if (res == 0){
            return Integer.compare(this.get_id(), o.get_id());
        }
        else return res;
    }
}

