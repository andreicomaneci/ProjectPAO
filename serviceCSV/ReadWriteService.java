package serviceCSV;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadWriteService {

    public void writeTo(String fileName, List<List<String>> data) {
        try {
            File file = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (List<String> record : data) {
                for(String recordData : record) {
                    bw.write(recordData + ",");
                }
                bw.write('\n');
            }
            bw.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List< List<String> > readFrom(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            br.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return records;
    }
}
