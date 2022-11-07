
# Bell-Coding-Challeng

1.The Project is Built With following Technology
```
Backend - Java, Spring boot
Frontend - Angular
Testing- Junit, Mockito
```
 --------------------------------------------------------------------------------------------------------------------------------------------------------------
2.Prerequisites & Build:
The following software are required.
```
Angular - Node js Version 16.16.0
        - $ npm install -g @angular/cli
        - $ ng serve or npm start 
         - Default Server Port Is 4200 (http://localhost:4200/)

Java - Java JDK 1.8
     - Default Server Port Is 8080
     - Run as - Spring Boot App (http://localhost:8080/)
     - mvn clean install (It will run Test cases)
 ```
 --------------------------------------------------------------------------------------------------------------------------------------------------------------

3.Funcationality

#### 3.1 Backend / Java

Project contains Junit Test Cases and 3 APIs based on given Details in the backend. 

API 1: 
    -URL: http://localhost:8080/api/media/getMediaList?top=2
    -Method: GET
    -Response: It will return list of Media Details list
```
             {
              "status": "OK",
              "message": "Successfully Get Media List.",
              "error": null,
              "data": {
                  "Items": [
                      {
                          "Id": 32254,
                          "Name": "Bosch"
                      },
                      {
                          "Id": 32270,
                          "Name": "Big Love"
                      }
                      ],
                  "ItemsType": "Media"
              },
              "timeStamp": 1667770391667
            }
 ```
   --------------------------------------------------------------------------------------------------------------------------------------------------------------
API 2:
    - URL: http://localhost:8080/api/media/getMediaDetailsById/32254
    - Method: GET
    - Response: It will return Media details of provided Media Id. 
  ```   {
    "status": "OK",
    "message": "Successfully Get Media Details.",
    "error": null,
    "data": {
        "Id": 32254,
        "Name": "Bosch",
        "Desc": "Based on Michael Connelly's best-selling novels, Harry Bosch is a homicide detective in the LAPD that follows a dangerous trail of corruption and                      collusion, one that uncovers the dark side of the police department.",
        "ShortDesc": "Bosch follows the hard-boiled, relentless LAPD Homicide detective as he investigates a dangerous trail of corruption and collusion.",
        "Images": [
            {
                "Type": "thumbnail",
                "Url": "https://images2.9c9media.com/image_asset/2022_6_27_cf2443ec-ba98-4990-b845-107905837af2_png_2000x1125.jpg",
                "Width": 2000,
                "Height": 1125
            },
            {
                "Type": "poster",
                "Url": "https://images2.9c9media.com/image_asset/2022_6_27_03f1b982-ee58-4003-97e9-50173ef29800_png_2000x3000.jpg",
                "Width": 2000,
                "Height": 3000
            }
            ],
            "Genres": [
                {
                    "Id": 14,
                    "Name": "Drama"
                }
            ]
        },
        "timeStamp": 1667779514401
      }
   ```
  --------------------------------------------------------------------------------------------------------------------------------------------------------------    
API 3: 
    - URL: http://localhost:8080/api/media/getMediaDetailsByIdAndType
    - Method: POST
    -Request Body:```{
                  id":32254,
                  "imageType":"square"
                 }```
      -Response: It will Return Media Details and Image list as per given Media Id & Image Type.
 ```
              {
                "status": "OK",
                "message": "Successfully Get Media Details with Image Type.",
                "data": {
                    "Id": 32254,
                    "Name": "Bosch",
                    "Desc": "Based on Michael Connelly's best-selling novels, Harry Bosch is a homicide detective in the LAPD that follows a dangerous trail of                                      corruption and collusion, one that uncovers the dark side of the police department.",
                    "ShortDesc": "Bosch follows the hard-boiled, relentless LAPD Homicide detective as he investigates a dangerous trail of corruption and collusion.",
                    "Images": [
                        {
                            "Type": "square",
                            "Url": "https://images2.9c9media.com/image_asset/2021_11_24_78ca1572-9ed8-47c8-9f71-2d84f0aac814_JPG_930x930.JPG",
                            "Width": 930,
                            "Height": 930
                        }
                    ],
                    "Genres": [
                        {
                            "Id": 14,
                            "Name": "Drama"
                        }
                    ]
                },
                "timeStamp": 1667707259934
            }
   ```


 #### 3.2 Frontend /Angular

     - Created single page UI to display APIs response. It has table that contains List of Media and on click of Media It will show a Poster Image And Mideia Detail           of each Media.
     - Added two DropDown one Contains MediaList and second One have ImageType. 
     - If you select Media from Media List then It will shows Media Details with all of the Images with Image Type.
     - If you select Media From First DropDown and Image Type from second Drop Down Tha It will Shows MediaDetails and Display Image of selected Image Type.
