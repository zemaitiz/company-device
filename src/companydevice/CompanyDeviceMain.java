package companydevice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDeviceMain {
    private static final String INPUT_LOCATION = "src/companydevice/Data.txt"
    private static final String OUTPUT_LOCATION = "src/companydevice/result.txt"

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
        String[] splittedLineArray = companyData.split(";"); // title; authors[]; price; qnt
        return new Company(splittedLineArray[0], splittedLineArray[1], splittedLineArray[2], mapCompanyDeviceData(splittedLineArray[3]));
    }

    private static List<Device> mapCompanyDeviceData(String deviceData){
        List<Device> deviceList = new ArrayList<>();

        String[] splittedLineData = deviceData.split("-"); // name,email,phoneNumber-name,email,phoneNumber
        for (String deviceData : splittedLineData) {
            String[] deviceInfoArray = deviceData.split(",");
            deviceList.add(new Device(deviceInfoArray[0], deviceInfoArray[1], deviceInfoArray[2], deviceInfoArray[3]));
        }
        return deviceList;
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
