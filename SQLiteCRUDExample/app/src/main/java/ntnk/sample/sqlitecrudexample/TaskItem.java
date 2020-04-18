package ntnk.sample.sqlitecrudexample;

import java.io.Serializable;
import java.util.Date;

public class TaskItem implements Serializable {
    private Integer id;
    private String title;
    private String note;
    private Date date;
    private  Boolean isDome;

    public TaskItem() {

    }

    public TaskItem(String title, String note, Date date, Boolean isDome) {
        this.title = title;
        this.note = note;
        this.date = date;
        this.isDome = isDome;
    }

    public TaskItem(Integer id, String title, String note, Date date, Boolean isDome) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.date = date;
        this.isDome = isDome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getDome() {
        return isDome;
    }

    public void setDome(Boolean dome) {
        isDome = dome;
    }

    @Override
    public String toString() {
        return "title: " + title + ", date: " + date ;
    }
}
