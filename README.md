# Eriantys
Eriantys is the final project of the "Software Engineering" course part of the "Computer Engineering" degree program held at the Polytechnic University of Milan.

Academic Year: **2021-2022**

Teacher: **Gianpaolo Cugola** 

Students: 
* **Federico Pizzi**
* **Niccolò Maria Rizzi**
* **Giacomo Savazzi**
          

## Project Specifications
Project goal was the recreation of the board game "Eriantys" in Java language.
## Implemented Features
* All basic functionalities:
  * &#9989; Simplified Rules
  * &#9989; Complete Rules
  * &#9989; Socket
  * &#9989; CLI
  * &#9989; GUI
* The following functionalities:
  * &#9989; Implementation of all twelve characters
  * &#9989; 4-players matches
  * &#9989; Multiple concurrent matches
  
## Test Coverage:


| Coverage   | Class | Method | Lines |
|------------|-------|--------|-------|
| Model      | 100%  | 92%    | 86%   |
| Controller | 100%  | 100%   | 82%   |

## Executables

Project deployment consists of three jars:
* Server Jar
* CLI Jar
* GUI Jar

### Jar Testing:
|            | Windows | Linux  |  Mac (M1 Architecture) |
|------------|---------|--------|-----------------------|
| Server Jar | &#129001; | &#129001; |   &#129001;                    |
| CLI Jar    |  &#129001; |  &#129001;|  &#129001;                    |
| GUI Jar    |  &#129001; | &#129001;|   &#129001;               |

&#129001;: Tested
&#128999;: Not completely tested

### Requirements for running Jars
All jars require at least Java 17.
## Server
Server.jar can be started from a terminal. It should start out of the box for any operating system.
## GUI
GUI.jar can be started from a terminal or by launching the jar as an application. As for the server, it should start out of the box.
## CLI
The CLI version requires UTF-8 Encoding to be displayed correctly. 
* It should be automatically displayed correctly on Mac OS and Linux terminals.
* If you are a Windows user:
  * For windows 11 run the CLI jar on the powershell
  * For earlier versions you have to enable UTF-8 Encoding by switching character encoding to codepage 65001.
Program may require resizing terminal or changing colour palette to have a better experience. 


