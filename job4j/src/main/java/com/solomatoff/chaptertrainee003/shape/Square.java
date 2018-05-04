package com.solomatoff.chaptertrainee003.shape;

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("+++++++");
        pic.append(System.lineSeparator());
        pic.append("+     +");
        pic.append(System.lineSeparator());
        pic.append("+     +");
        pic.append(System.lineSeparator());
        pic.append("+++++++");
        pic.append(System.lineSeparator());
        return pic.toString();
    }
}
