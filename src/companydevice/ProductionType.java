package companydevice;

public enum ProductionType {
    //TECHNOLOGIES, AUTOMOBILES, GARDEN_TOOLS, FOOD
    TECHNOLOGIES("technologijos"),
    AUTOMOBILES("autombiliai"),
    GARDEN_TOOLS("sodas&darzas"),
    FOOD("maistas");

    private String productionDescription;

    ProductionType(String productionDescription) {
        this.productionDescription = productionDescription;
    }

    public String getProductionDescription() {
        return productionDescription;
    }
}

