package com.solomatoff.chaptertrainee003.shape;

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   +   ");
        pic.append(System.lineSeparator());
        pic.append("  + +  ");
        pic.append(System.lineSeparator());
        pic.append(" +   + ");
        pic.append(System.lineSeparator());
        pic.append("+++++++");
        pic.append(System.lineSeparator());
        return pic.toString();
    }
}