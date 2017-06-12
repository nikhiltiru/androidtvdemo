# androidtvdemo
This project demonstrates the steps to be taken to port/create an app for Android TV. Compile this project as is and install it on to the TV. You can see that the app is not listed in the Home Screen app drawer. You can only open it from Settings -> Apps -> Downloaded Apps -> Android TvDemo.
Experience its UI. You will find that the focus is not clear and navigation may not be as per your expectation.

Now start following the below steps to make it TV ready.

1. Modify the Android Manifest file to add the leanback launcher intent to your activity.
Without this, your app won't appear in the Home screen nor in the TV Play Store.
            
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LEANBACK_LAUNCHER" />
            </intent-filter>
2. Once you have added the intent to your manifest, you need to add a Home Banner of 320x180 px. 
Home Banner should carry the name of your application.
Specify the reference to this banner in your Manifest under your activity: android:banner="@mipmap/home_tv"

![Alt text](/app/src/main/res/mipmap-xhdpi/home_tv.png?raw=true "Home Banner")

Now you should be able to see and launch your app from the Home Screen.
Without the first 2 steps, your app wont be listed in the Home screen, app drawer.

3. Now coming to the layout of your application. It will look like this:

![Alt text](/screenshots/buttons_no_style.png?raw=true "Buttons with No Styling")

As you can see, it is difficult to identify from a distance, which is the currently focused button.

4. So, you either need to add the leanback effect (the focused element will zoom out a bit) or draw a border around it
or paint it with a different color. I have chosen the 3rd approach. This is how it looks now:

![Alt text](/screenshots/button_with_style.png?raw=true "Buttons with Styling")

You can see that the 'Left' button is highlighted in Red.
To achieve this effect, I have applied the style style="@style/button_custom" to all the buttons.
See /app/src/main/res/layout/activity_main.xml

5. To handle D-Pad navigation:

    a. First you have to decide which view gets the focus on launch.
    You have to request it using view.requestFocus().
    
    b. Now once you set the focus, you have to control the navigation.
    If you press 'up' button of your remote, from the 'Bottom' button, the focus first goes to 'Right' button, then to the 'Top' button. In case, you want the focus to reach the 'Top' button directly from 'Bottom' button, you need to set
    buttonbottom.setNextFocusUpId(R.id.buttontop);
Similarly, you can modify for the other views.

With these changes, your basic TV app is ready.

## Further enhancements - Control from mobile
Once the basic TV app is ready, you can enhance it by having your mobile exchange data and controls with the TV.
This can be done using NSD (generic) and Cast sdk. This project already has the files related to NSD already added in the folder /app/src/main/java/com/android/tv/androidtvapp/nsd.

You can follow the next project https://github.com/nikhiltiru/tv-mobilecontrol to make these enhancements.
