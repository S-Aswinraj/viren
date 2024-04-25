package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

    private Properties prop;

    /**
     * this method is used to load the properties from config.properties file
     * @return
     */

    public Properties  init_prop(){
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("C:\\Users\\ashwinraj.s\\Desktop\\viren\\src\\test\\resources\\TestData.properties");
            try {
                prop.load(ip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();

        }
        return prop;


    }

}
