package com.abbascoban.model;

public class Main {
    public static void main(String[] args) {

    Model model=Model.builder()
            .id(1L)
            .name("Abbas")
            .surname("Çoban")
            .branch("Yazılım")
            .build();

        System.out.println(model.getName()+" "+model.getBranch());

    }
}
