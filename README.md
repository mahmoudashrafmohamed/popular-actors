# popular-actors
A popular-actors app with the MoviesDB api.

## Main features
- Displaying a list of popular people (actors, directors ..etc) with infinite scrolling.
- When a person entry is selected, a Details view is opened with the basic person info and grid view of his/her images.
- When an image is selected, it’s opened in its original size in a new screen where the user can save the image locally on the phone.
- Implement unit test for usecases layer.

## Tech Stack
- kotlin 
- Clean Architecure with MVVM.
- koin for di.
- Rxjava2 and Rx-kotlin.
- Retrofit2.
- Material.
- recycler view with ListAdapter.
- Timber for logging.
- ConstraintLayout.
- viewmodel and live data.
- Glide.
- Jetpack Navigation.

## unit test
- Junit4.
- Mockito.

Note:
to run this app you need to
1- create file apikey.properties in root project
2- add you api key from the MovieDB ex: API_KEY = "123"

 
