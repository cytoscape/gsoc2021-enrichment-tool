package gprofiler.internal;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;

public class ProfilerParameters  {
    private String profilerDirectory;
    private Properties profilerProps;
    //three properties for now
    private String defaultSpeciesName;
    private boolean isSelectedEnabled;

    public ProfilerParameters(String profilerDirectory) throws IOException {
        this.profilerDirectory = profilerDirectory;
        profilerProps = new Properties();
        // Open the properties file, try custom properties in /plugins folder first
        try {
            profilerProps.load(PropReader("gprofiler_gui.properties"));
        } catch (IOException e) {
            try {
                profilerProps.load(JarReader("gprofiler_gui.properties"));
            } catch (IOException e2) {
                // throw e;
                String propfile_path = openResourceFile("gprofiler_gui.properties");
                System.out.println("can't find default properties file" + propfile_path);
            }
        }
    }

    public void storeParameterSettings() throws IOException {
        OutputStream os = PropWriter("gprofiler_gui.properties");
        profilerProps.store(os, "");
        os.close();
    }

    public String getDefaultSpeciesName() {
        return defaultSpeciesName;
    }

    public void setDefaultSpeciesName(String defaultSpeciesName) {
        this.defaultSpeciesName = defaultSpeciesName;
    }

    public boolean isSelectedEnabled() {
        return isSelectedEnabled;
    }

    public void setSelectedEnabled(boolean selectedEnabled) {
        isSelectedEnabled = selectedEnabled;
    }

    private String openResourceFile(String name) {
        return getClass().getResource("/" + name).toString();
    }

    public InputStream JarReader(String name) throws IOException {
        InputStream is = getClass().getResourceAsStream("/props/" + name);
        return is;
    }

    public InputStream PropReader(String name) throws IOException {
            File propFile = new File(profilerDirectory, name);
            FileInputStream is = new FileInputStream(propFile);
            return is;
    }

    public OutputStream PropWriter(String name) throws IOException {
        File propFile = new File(profilerDirectory, name);
        FileOutputStream os = new FileOutputStream(propFile);
        return os;
    }

    public Properties getProfilerProps() {
        return profilerProps;
    }
}
