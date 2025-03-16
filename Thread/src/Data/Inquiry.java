package Data;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Inquiry {
    static Integer nextCodeVal = 0;
    protected  Integer code;
    protected String description;
    protected LocalDateTime creationDate;

    Scanner scanner=new Scanner(System.in);

    public  Inquiry(){
        code=nextCodeVal++;
        creationDate=LocalDateTime.now();
        fillDataByUser();
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public  void fillDataByUser(){
        System.out.println("insert description");
        String des=scanner.nextLine();
        this.description=des;
    }

    public String handling(){
          return "";
    }
}
