# Build Automation Tools
This repository serves as a basic demonstration of the build automation tools (i.e., Maven, Gradle) for the needs of the Software Eningineering in Practice (SEiP) course offered by the [Department of Management Science & Technology](https://www.dept.aueb.gr/en/dmst) of the Athens University of Economics and Business. 

The goal of this repository is twofold:
1) To present the structure of a multi-module Maven project and,
2) to provide different settings that customize the output result of the build process. 

## Project Structure
This repository consists of a parent Maven project and three sub-project (modules), that handle the dependencies in a different way.
1) [Dummy Hello SEiP](dummyhelloworld) - that offers the most basic functionality (only prints a message)
2) [Image Manipulator](histogramgenerator) - processes an image and transforsm it to the grayscale version
3) [Histogram Generator](imagemanipulator) - creates a histogram from a given set of numbers

Execute the following command in the repository root directory in order to build all modules. 
```
mvn package
```
This command generates a seperate jar file in each module's corresponding target (```module/target```) directory.  


### Dummy Hello SEiP
This module has no dependencies and thus it requires only the definition of the class that is the main entry point of the system (the class that contains the main method). 
```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<configuration>
		<archive>
			<manifest>
				<addClasspath>true</addClasspath>
				<mainClass>dummyhelloworld.MessagePrinter</mainClass>
			</manifest>
		</archive>
	</configuration>
</plugin>
```

The produced jar is located in the target directory and can be executed as following:
```
java -jar dummyhelloworld/target/dummyhelloworld-0.0.1-SNAPSHOT.jar
```

### Image Manipulator
This modulerequires one runtime dependency which is placed in a ```lib/``` directory in the target directory where the jar is generated. To create a jar and place the dependencies in the lib directory you need to use the ```maven-jar-plugin``` and the ```maven-dependency-plugin``` plugins and also define the class that is the main entry point of the system (the class that contains the main method). 
```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<version>2.4</version>
	<configuration>
		<archive>
			<manifest>
				<classpathPrefix>lib/</classpathPrefix>
				<addClasspath>true</addClasspath>
				<mainClass>imagemanipulator.ImageManipulatorDemo</mainClass>
			</manifest>
		</archive>
	</configuration>
</plugin>
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-dependency-plugin</artifactId>
	<version>2.4</version>
	<executions>
		<execution>
			<id>copy</id>
			<phase>install</phase>
			<goals>
				<goal>copy-dependencies</goal>
			</goals>
			<configuration>
				<outputDirectory>
					${project.build.directory}/lib
				</outputDirectory>
			</configuration>
		</execution>
	</executions>
</plugin>
```
The produced jar is located in the target directory and can be executed as following:
```
java -jar imagemanipulator/target/imagemanipulator-0.0.1-SNAPSHOT.jar imagemanipulator/src/main/resources/demo.jpeg
```

### Histogram Generator
This module, just like the previous one, requires one runtime dependency which is packaged in the main jar (fat-jar). To create a fat-jar you need to use the ```maven-assembly-plugin``` plugin and also define the class that is the main entry point of the system (the class that contains the main method). 
```
<plugin>
	<artifactId>maven-assembly-plugin</artifactId>
	<configuration>
		<archive>
			<manifest>
			  <mainClass>histogramgenerator.JFreeChartXYLineChartDemo</mainClass> 
			</manifest>
		</archive>
		<descriptorRefs>
			<descriptorRef>jar-with-dependencies</descriptorRef>
		</descriptorRefs>
	</configuration>
	<executions>
		<execution>
			<id>make-assembly</id>
			<phase>package</phase>
			<goals>
				<goal>single</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

The produced jar is located in the target directory and can be executed as following:
```
java -jar histogramgenerator/target/histogramgenerator-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
Note that the histogramgenerator-0.0.1-SNAPSHOT.jar is not executable. 


## How to..
1) Setup Maven in Windows - [video tutorial](https://www.youtube.com/watch?v=JhZcaL-QRdQ&feature=youtu.be)
2) Setup Maven in Linux. Execute ```sudo apt update && sudo apt install maven``` in a terminal. 
