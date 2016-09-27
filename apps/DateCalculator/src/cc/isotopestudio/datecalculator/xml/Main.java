package cc.isotopestudio.datecalculator.xml;

public class Main {

	public static void main(String[] args) {
        XMLImpl dd=new XMLImpl();
        String str="C:\\Onedrive\\Coding\\Workspace\\Playground\\apps\\DateCalculator\\src\\cc\\isotopestudio\\datecalculator\\data.xml";
        dd.init();
        dd.createXML(str);
        dd.parserXML(str);
	}

}
