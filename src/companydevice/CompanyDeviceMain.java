package companydevice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDeviceMain {
    private static final String INPUT_LOCATION = "src/companydevice/Data.txt";
    private static final String OUTPUT_LOCATION = "src/companydevice/result.txt";

    public static void main(String[] args) {
        List<Company> companyList = getCompaniesFromFile();

        for(Company company : companyList){
            writeDataToFile(company.toString());
        }

    }
    private static List<Company> getCompaniesFromFile() {
        List<Company> companyList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_LOCATION))) {
            String line = br.readLine();

            while (line != null) {
                companyList.add(mapCompanyData(line));
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("KLAIDA!");
        }
        return companyList;
    }

    private static Company mapCompanyData(String companyData) {
        String[] splittedLineArray = companyData.split(";"); // Company (name, address, production type, devices)
        return new Company(splittedLineArray[0], splittedLineArray[1], ProductionType.TECHNOLOGIES, mapCompanyDeviceData(splittedLineArray[3]));
    }

    private static List<Device> mapCompanyDeviceData(String deviceData){
        List<Device> authorList = new ArrayList<>();

        String[] splittedLineData = deviceData.split("-"); // name,email,phoneNumber-name,email,phoneNumber
        for (String devicesData : splittedLineData) {
            String[] deviceInfoArray = devicesData.split(",");
            authorList.add(new Device(deviceInfoArray[0], Double.parseDouble(deviceInfoArray[1]), Integer.parseInt(deviceInfoArray[2]), deviceInfoArray[3]));
        }
        return authorList;
    }

    private static void writeDataToFile(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter((OUTPUT_LOCATION)))) {
            bw.write(data);
            bw.newLine();
            bw.write(data);
        } catch (FileNotFoundException e) {
            System.out.println("Failas nerastas");
        } catch (IOException e) {
            System.out.println("Ivyko klaida rasant duomenis i faila!!");
        }
    }

}
