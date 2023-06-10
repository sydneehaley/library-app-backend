package com.wileyedge.libraryapp.dto;

public class ItemDto {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfoDto volumeInfo;
    private SaleInfoDto saleInfo;
    private SearchInfoDto searchInfo;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfoDto getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfoDto volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfoDto getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfoDto saleInfo) {
        this.saleInfo = saleInfo;
    }

    public SearchInfoDto getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfoDto searchInfo) {
        this.searchInfo = searchInfo;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                ", etag='" + etag + '\'' +
                ", selfLink='" + selfLink + '\'' +
                ", volumeInfo=" + volumeInfo +
                ", saleInfo=" + saleInfo +
                ", searchInfo=" + searchInfo +
                '}';
    }
}
