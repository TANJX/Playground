package io.xml;

public class Main {

	public static void main(String[] args) {
        XMLImpl dd=new XMLImpl();
        String str="C:\\Users\\Mars\\Desktop\\games\\grade.xml";
        dd.init();
        dd.createXML(str);
        dd.parserXML(str);
	}

}
