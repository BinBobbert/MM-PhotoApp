# MM-PhotoApp
A simple photo viewing application 

Notes:
- For some reason Glide wasn't able to find the image files from https://jsonplaceholder.typicode.com/
  Switched to Picasso

- I have not yet created layouts for different scree sizes. The app works on my old tablet but its no optimal.
  An issues I've run into is that views dont get loaded on first start (2014 samsung tablet).
  I do not have this issues on my phone. Fetching data and saving is is probably taking too long for older devices.


Initial commit time: Februari 11 14:05

End Date: Februari 13 14:05

Libraries used so far:
- Lifecycle
- Retrofit, Okhttp, Gson, Picasso
- Room
- Coroutines
- Recyclerview
- Cardview
- Dagger
