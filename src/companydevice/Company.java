package companydevice;

public class Company {
    private String name;
    private String adress;
    private ProductionType productionType;
    private Device devices;

    public Company(String name, String adress, ProductionType productionType, Device devices) {
        this.name = name;
        this.adress = adress;
        this.productionType = productionType;
        this.devices = devices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public ProductionType getProductionType() {
        return productionType;
    }

    public void setProductionType(ProductionType productionType) {
        this.productionType = productionType;
    }

    public Device getDevices() {
        return devices;
    }

    public void setDevices(Device devices) {
        this.devices = devices;
    }
}
