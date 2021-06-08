package gprofiler.internal;

public class SpeciesData {
    String display_name;
    String id;
    String scientific_name;
    String taxanomy_id;
    String version;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getTaxanomy_id() {
        return taxanomy_id;
    }

    public void setTaxanomy_id(String taxanomy_id) {
        this.taxanomy_id = taxanomy_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SpeciesData{" +
                "display_name='" + display_name + '\'' +
                ", id='" + id + '\'' +
                ", scientific_name='" + scientific_name + '\'' +
                ", taxanomy_id='" + taxanomy_id + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
