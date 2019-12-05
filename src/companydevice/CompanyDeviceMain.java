package companydevice;

import com.sun.xml.internal.bind.v2.model.core.EnumConstant;

import java.io.*;
import java.util.*;

import static companydevice.ProductionType.TECHNOLOGIES;

public class CompanyDeviceMain {
    private static final String INPUT_LOCATION = "src/companydevice/Data.txt";
    private static final String OUTPUT_LOCATION = "src/companydevice/result.txt";

    public static void main(String[] args) {
        List<Company> companyList = getCompaniesFromFile();

        // Atvaizduoti kiekvieno company objekto brangiausią device objektą;
        System.out.println("Most expensive devices:");
        for(Company company : companyList){
            String mostExpensiveDeviceName = findMostExpensiveDevice(company.getDevices());
            System.out.println(mostExpensiveDeviceName);
            writeDataToFile(mostExpensiveDeviceName);
        }

        System.out.println(); // New line

        // Atvaizduoti visus company objektus, kurių productionType -> TECHNOLOGIES;
        System.out.println("Technology companies:");
        for(Company company : companyList){
            if (company.getProductionType() == TECHNOLOGIES) {
                System.out.println(company.getName());
                writeDataToFile(company.getName());
            }
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
        return new Company(splittedLineArray[0], splittedLineArray[1], ProductionType.valueOf(splittedLineArray[2]), mapCompanyDeviceData(splittedLineArray[3]));
    }

    private static List<Device> mapCompanyDeviceData(String deviceData){
        List<Device> deviceList = new ArrayList<>();

        String[] splittedLineData = deviceData.split("-"); // name,email,phoneNumber-name,email,phoneNumber
        for (String devicesData : splittedLineData) {
            String[] deviceInfoArray = devicesData.split(",");
            deviceList.add(new Device(deviceInfoArray[0], Double.parseDouble(deviceInfoArray[1]), Integer.parseInt(deviceInfoArray[2]), deviceInfoArray[3]));
        }
        return deviceList;
    }

    private static String writeDataToFile(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_LOCATION, true))) {
            bw.write(data);
            bw.newLine();
        } catch (FileNotFoundException e) {
            System.out.println("Failas nerastas");
        } catch (IOException e) {
            System.out.println("Ivyko klaida rasant duomenis i faila!!");
        }
        return data;
    }

    private static String findMostExpensiveDevice(List<Device> deviceData) {
        Device device = Collections.max(deviceData, Comparator.comparing(s -> s.getPrice()));
        return device.getName();
    }
}
