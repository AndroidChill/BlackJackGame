package com.example.blackjackgame.model.profile;

public class Ref {

    private String ref_url;
    private String ref_text;

    public Ref(String ref_url, String ref_text) {
        this.ref_url = ref_url;
        this.ref_text = ref_text;
    }

    public String getRef_url() {
        return ref_url;
    }

    public void setRef_url(String ref_url) {
        this.ref_url = ref_url;
    }

    public String getRef_text() {
        return ref_text;
    }

    public void setRef_text(String ref_text) {
        this.ref_text = ref_text;
    }
}
