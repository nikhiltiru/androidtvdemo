# androidtvdemo
This project demostrates the steps to be taken to port/create an app for Android TV

1. Build and launch the application. It will open up the below layout:
![screenshots](buttons_no_style.png.png)
As you can see, it is difficult to identify from a distance, which is the currently focussed button.

2. So, you either need to add the leanback effect (the focussed element will zoom out a bit) or draw a border around it
or paint it with a different color. I have chosen the 3rd approach. This is how it looks now:
![screenshots](button_with_style.png)
