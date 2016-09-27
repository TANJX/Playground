package cc.isotopestudio.datecalculator.xml;

import java.io.FileNotFoundException;

public interface XMLInterface {
	
	public void createXML(String fileName) throws FileNotFoundException;
	
	public void parserXML(String fileName);

}
