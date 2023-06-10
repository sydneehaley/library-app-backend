package com.wileyedge.libraryapp.dto;

public class SearchInfoDto {
    private String textSnippet;

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

    @Override
    public String toString() {
        return "SearchInfoDto{" +
                "textSnippet='" + textSnippet + '\'' +
                '}';
    }
}
