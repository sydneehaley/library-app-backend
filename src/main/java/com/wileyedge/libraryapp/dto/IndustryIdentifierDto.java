package com.wileyedge.libraryapp.dto;

import com.wileyedge.libraryapp.entity.Book;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Embeddable
public class IndustryIdentifierDto {
    @Column(name = "type")
    private String type;

    @Column(name = "identifier")
    private String identifier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "IndustryIdentifierDto{" +
                "type='" + type + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
