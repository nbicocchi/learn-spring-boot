package com.baeldung.ls.persistence.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Project {

    private Long id;

    private String name;

    private LocalDate dateCreated;

    public Project(Long id, String name, LocalDate dateCreated) {
        if(Objects.isNull(id)) {
            id = new Random().nextLong();
        }
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Project(String name, LocalDate dateCreated) {
        this.id = new Random().nextLong();
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Project(Project project) {
        this(project.getId(), project.getName(), project.getDateCreated());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        } else if (!dateCreated.equals(other.dateCreated))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + "] \n";
    }

}