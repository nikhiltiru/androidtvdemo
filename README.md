# androidtvdemo
This project demostrates the steps to be taken to port/create an app for Android TV

1. Build and launch the application. It will open up the below layout:
![Alt text](/screenshots/buttons_no_style.png?raw=true "Buttons with No Styling")
As you can see, it is difficult to identify from a distance, which is the currently focussed button.

2. So, you either need to add the leanback effect (the focussed element will zoom out a bit) or draw a border around it
or paint it with a different color. I have chosen the 3rd approach. This is how it looks now:
![Alt text](/screenshots/button_with_style.png?raw=true "Buttons with Styling")
You can see that the 'Left' button is highlighted in Red.

3. Modify the Android Manifest file to add the leanback launcher intent to your activity.
Without this, your app wont appear in the Home screen nor in the TV Play Store.

4. Add the Home Banner of 320x180 px. Home Banner should carry the name of your application.
![Alt text](/app/src/main/res/mipmap-xhdpi/home_tv.png?raw=true "Home Banner")

5. To handle D-Pad navigation:

    a. First you have to decide which view gets the focus on launch.
    You have to request it using view.requestFocus().
    
    b. Now once you set the focus, you have to control the navigation.
    If you press 'up' button of your remote, from the 'Bottom' button, the focus first goes to 'Right' button, then to the 'Top' button. In case, you want the focus to reach the 'Top' button directly from 'Bottom' button, you need to set
    buttonbottom.setNextFocusUpId(R.id.buttontop);
Similarly, you can modify for the other views.

With these changes, your basic TV app is ready.
