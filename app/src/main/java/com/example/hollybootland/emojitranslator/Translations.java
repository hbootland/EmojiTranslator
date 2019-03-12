package com.example.hollybootland.emojitranslator;

class Translations {
    String inLang;
    String inText;
    String outLang;
    String outText;
    boolean isChecked;
    Translations(String inLang, String inText, String outLang,
                 String outText){
        this.inLang = inLang;
        this.inText = inText;
        this.outLang = outLang;
        this.outText = outText;
    }

    public  String getInLang() {
        return inLang;
    }

    public String getInText() {
        return inText;
    }

    public String getOutLang() {
        return outLang;
    }

    public String getOutText() {
        return outText;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setInLang(String inLang){
        this.inLang = inLang;
    }

    public void setInText(String inText) {
        this.inText = inText;
    }

    public void setOutLang(String outLang) {
        this.outLang = outLang;
    }

    public void setOutText(String outText) {
        this.outText = outText;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
