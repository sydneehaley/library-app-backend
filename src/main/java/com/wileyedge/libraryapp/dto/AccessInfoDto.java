package com.wileyedge.libraryapp.dto;

public class AccessInfoDto {
    private String country;
    private String viewability;
    private boolean embeddable;
    private boolean publicDomain;
    private String textToSpeechPermission;
    private FormatDto epub;
    private FormatDto pdf;

    // Getters and Setters

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getViewability() {
        return viewability;
    }

    public void setViewability(String viewability) {
        this.viewability = viewability;
    }

    public boolean isEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(boolean embeddable) {
        this.embeddable = embeddable;
    }

    public boolean isPublicDomain() {
        return publicDomain;
    }

    public void setPublicDomain(boolean publicDomain) {
        this.publicDomain = publicDomain;
    }

    public String getTextToSpeechPermission() {
        return textToSpeechPermission;
    }

    public void setTextToSpeechPermission(String textToSpeechPermission) {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    public FormatDto getEpub() {
        return epub;
    }

    public void setEpub(FormatDto epub) {
        this.epub = epub;
    }

    public FormatDto getPdf() {
        return pdf;
    }

    public void setPdf(FormatDto pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "AccessInfoDto{" +
                "country='" + country + '\'' +
                ", viewability='" + viewability + '\'' +
                ", embeddable=" + embeddable +
                ", publicDomain=" + publicDomain +
                ", textToSpeechPermission='" + textToSpeechPermission + '\'' +
                ", epub=" + epub +
                ", pdf=" + pdf +
                '}';
    }
}
