# Seller Register System

This project was made as the final project for the BootCamp Dev.Jr's 3rd module. The objective was to build a CRUD application allowing the managment of Sellers using Angular and SpringBoot with an embedded database.

You can find more about the program here https://bootcampdevjr.com/

# How to run this project

## Pre-requisites
To run this application you need the following:

- An IDE like [VScode](https://code.visualstudio.com).

- The folowing VScode extensions: [Java Extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) and [SpringBoot Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack).

- Java SDK 17 installed which is available [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) .

- You also need NodeJs and Node Package Manager (NPM). Both of the are available [here](https://nodejs.org/en/download/current).

- With Node and NPM both installed you can open a terminal an run the following command `npm install -g @angular/cli@16.2.0` to install Angular 16.

## Cloning the repository
Clone the repository through the link: https://github.com/Wilson-Barbosa/seller-register.git

## Runnig the Spring API

Open the cloned project on vscode and navigate to the `backend` folder. From there follow the path `src\main\java\com\abutua\sellerregister\SellerRegisterApplication.java`

Open the file `SellerRegisterApplication.java` and click on `run` to start the application. When the application is served then it's ready to receive requests.
 
## Running the Angular Application
Navigate to the `frontend` folder and open a terminal there. Run the command `npm install` to install all node dependecies required.

When NPM finishes intalling all packages, within the same terminal, enter `ng serve --open` to run the dev server. After this process is done a window on you browser should open, but in case that doesn't happen just go to `http://localhost:4200/`

## Observations
It's very important to run the the Spring API before the angular server or else any request to save, update, delete or retrive information by Angular will fail.
