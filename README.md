# Project Title
JavaSketchPad README

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See “How to work on this project?” for further development details. 

### Prerequisites

```
Eclipse for Java (Eclipse Neon recommended)
Java System Library [JavaSE 1.8]
```

### Installing

git clone the project through “github.com/UCSDOalads/JavaSketchPad” or through the following command:

```
git clone https://github.com/UCSDOalads/JavaSketchPad.git
cd JavaSketchPad
```

## Authors

See also the list of [contributors](https://github.com/UCSDOalads/JavaSketchPad/graphs/contributors) who participated in this project.
(in alphabetical order)
Original Author/ ex-Project Lead: Zhibo Chen.
Project Lead: Cheng Gong
(UI) User Interface Lead:     Kaijie Cai.
              UI members:     Zhien Ke.
(ID) Interactive Design Lead: Muyuan Chi.
              ID members:     Zhongyu Chen, Yaobang Deng, Xiangyi Gong, Xiaoquan Jiang, Kent, Feng Shan, Tan Su.
(BE) Back End Lead:           Yidong Luo.
              BE members:     Sean Z, Tianyi Zhang.

## Acknowledgments
UCSD CSE B260 lab
Helen, Zhening Huang, Mossaka, Jiheng Wang, Tony Yang, and any other who support or contribute to this project.

## User Interface Introduction
![alt text](https://github.com/UCSDOalads/JavaSketchPad/blob/demoImage/demo%201.png)
### Menu bar:
Contains all the functionalities that you can use on JavaSketchPad, mainly including Data (Input/Output Box, Annotations, etc), Lazy (Java class, method, etc), Edit (Zoom, font, etc), File (e.g. c.xml), Script (same as the “:” commands to quickly add objects). Among them, Generate, Developer, and View are deprecated or under-development.

### Canvas:
You can interact with the program on the canvas.

### Action History Dialog:
Action History: This table shows the actions user performed in order and each individual action could be highlighted for undo.
Undo button: undo the last action. You can also select a row to highlight that action, so that undoing again will undo until that row (inclusive).
Redo button: Similiar to Undo button.

### Six Button tools: click on the button and click on canvas where you want to put the object (except for Line tool). 
Simple Point: a basic developer point that can be connected with Lines
Select: the default mode that can select other components on canvas
Line: select the Line tool, then connect one point to another. If you want to connect points to pass on data, you have to select the point that contains data and then select the point that the data is going to. This sequence does not matter for Simple Point.
Class (C): create a Java class constructor that can be used to create its methods. To create a method, only select one class, and then click on menu bar (Lazy -> Add -> Java Method). The dot on the left side of a method is input/argument, while the dot on the right side is output/return value.
InputBox (I): A data input box that can be updated with double click or a click on menu bar (Data -> InputBox -> Update). It can also be connected to the left dots of method to pass in argument for methods.
OutputBox (O): A data output box that can be updated with a click on menu bar (Data -> OutputBox -> Update), but only after it is connected to the right dots of method and all the left dots are connected with data.

### MUST READ THIS!
For any class other than java.lang.String class, please use a Constructor that takes in a ***String*** type data to initialize the value to be the corresponding type before constructing lines.

E.g.:
![alt text](https://github.com/UCSDOalads/JavaSketchPad/blob/demoImage/demo%202.png)


## Contributing

Please read “How to work on this project?” for details on our code of conduct, and the process for submitting pull requests to develop branch.

## Open Source Code: Package Description

```
actions: implements all the actions that users could make interactions from the menu bar
actions.edit.undoredo: handles undo and redo actions
actions.global: defines the general structure of global actions
actions.global.globalactions: all the global actions, which are utilized in the actions package
actions.menu: make connections with the menu items and the associated actions
actions.singleinstanceoperactions: actions on one and only one instance.
buttons: define the structure of tool buttons, which are displayed on the top of the frame
classpathutil: contains the utility classes to generate the names and paths of all the classes in the java standard library and external jar files. The package also contains a search engine that allows users search for classes
file: implements I/O functionality
paintcomponents: define the components in the main panel, including line segments, single point, etc.
paintcomponents.annotations: implement the annotation functionality
paintcomponents.data: define the components that is associated with data
paintcomponents.haskell:
paintcomponents.java.interactive: the interactive version of paint components of class, constructor, method and fields
paintcomponents.java.lazy: the lazy version of paint components of class, constructor, method, and fields
painttools.toolbar: define the structure of the tool bar, which is displayed on the top of the frame
painttools.tools: define tool buttons in the toolbar
script: connect several commands to actions
settings: settings of the program
typesystem: check the type of input and output data box
ui: main frame of the program
ui.cursor: set custom cursor 
ui.general: include input manager of the program
ui.helper.classsearch: help do the search for class
ui.helper.historyui: frame for history(undo, redo) actions
ui.helper.historyui.undoredoLog: generate log for undo/redo Log
ui.helper.methodinput: implements the method input functionality. The package contains a method input frame and a method searching engine. 
ui.icons: icons used in the program
```
