# gsoc2021-enrichment-tool
code repo for GSoC 2021 Enrichment Tool project
## Instruction to make the app executable
### Pre- Requisites
```
Java
Maven
Cytoscape
```
### Step 1
Clone this repository into the local desktop.
```
Navigate to the directory using cmd 
or open Git Bash in the mentioned directory.
```
### Run the following commands
```
mvn clean
mvn compile
mvn clean install
```
A Build Success will be shown in the cmd and a jar file will be created in <b>/target</b> folder of the current directory.
### Step 2
```
1.Copy the App from the <b>/target</b> folder into "C:\Users\<your PC_NAME>\CytoscapeConfiguration\3\apps\installed".
2. Open the Cytoscape app and click on <b>Apps</b> from the menu bar and It will look similar to the following images. 
```
<img src="Images/Guide_screenshot_1.png" height=450/>
Click on the "Samples" from the list and the app will be listed there.