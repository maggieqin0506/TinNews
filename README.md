# TinNews
Introduction: this is an android news application, where users can first browse the snapshot of news, like and unlike the news, and then read the liked news later in the saved bar.

- Implemented the bottom bar and page navigation using JetPack navigation component.
- Employed 3rd party CardStackView (RecyclerView) to support swipe gestures for liking/disliking the news. 
- Built the Room Database with LiveData and ViewModel to support local cache and offline model.
- Integrated Retrofit and LiveData to pull the latest news data from a RESTful endpoint (newsapi.org).

# Architecture
![TinderNews MVVM](https://github.com/user-attachments/assets/4b1608b9-f90f-4ba7-8913-efb653bcb7ca)
<img width="419" alt="Architecture" src="https://github.com/user-attachments/assets/eaf233ee-ca65-4b53-9033-f1478b888184">


## Demo

#### Main Page:

<img width="349" alt="Screen Shot 2021-03-19 at 1 39 44 PM" src="https://user-images.githubusercontent.com/54367563/111828368-2ff75e00-88b9-11eb-88d7-75afc6ec56a3.png">


#### Saved Page:

Item that users liked before will be saved to this page, user can click in to those news and browse them

<img width="345" alt="Screen Shot 2021-03-19 at 1 40 12 PM" src="https://user-images.githubusercontent.com/54367563/111828407-40a7d400-88b9-11eb-8c62-92bd14666c56.png">

After clicking to an item:

<img width="344" alt="Screen Shot 2021-03-19 at 1 40 21 PM" src="https://user-images.githubusercontent.com/54367563/111828469-5917ee80-88b9-11eb-9314-5ff428f5e112.png">


#### Search Page:

Users can search for certain news by key words and then browse those news:

<img width="341" alt="Screen Shot 2021-03-19 at 1 44 05 PM" src="https://user-images.githubusercontent.com/54367563/111828514-6af99180-88b9-11eb-8d36-cca275c5ab4f.png">

