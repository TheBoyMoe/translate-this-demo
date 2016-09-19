Translate This Android App
==========================
 
'Translate This' allows you to use Microsoft's Cognitive Services Speech-to-Text, Text-to-Speech and 
Translation Services Api to translate between English, French, German, Italian or Spanish. 
Based on Translation App create by Tim Buchalka, in the Udemy course Learn Android App Development
with Java https://www.udemy.com/android-marshmallow-java-app-development-course/

British English, French, German, Italian and Spanish are supported. Select spoken language
using the left spinner, and the right spinner to select the target language
The app uses a simple workflow following the buttons on the bottom toolbar.
Going from left to right:
- Record the phrase you want translated (Speech-to-text).
- Edit the returned text if required. Not implemented.
- Translate the phrase to the language of choice (translation services).
- Hear the translation (text-to-speech).
- Save the phrase and translation, which can be reviewed via the Review Tab. Not implemented.

The app is a learning exercise in the implementation of the following:
- Designed with a phone layout in mind using activities/fragments
- Implements an MVP pattern using based on the articles of Tin Megali 'How to Adopt Model View Presenter on Android
    https://code.tutsplus.com/series/how-to-adopt-model-view-presenter-on-android--cms-1012  
- Activity/fragment communication via interfaces.
- Microsoft's Cognitive Services Speech-to-Text, Translation and Text-to-Speech Api's.

Pre-requisites
--------------

- Min Android SDK supported v16
- You need to sign in to Microsoft's Azure Market place at https://datamarket.azure.com/home
  There signup for Microsoft's Text Translation Data plan - which has a free tier allowing upto 
  2,000,000 characters per month. You also need to register your app. Use the app's package name 
  for the ClientID and provide a valid url for the redirect URI.
- To use MS Cognitive Services, sign up at http://www.microsoft.com/cognitive-services, select
  the Speech, Speaker Recognition and Linguistic-Services api. Each provides a free tier of 5,000
  or more transactions per month.

Getting Started
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.

Define the following properties in your gradle.properties file:
MSCognitiveServicesPrimaryKey = "your key";
MSCognitiveServicesSecondaryKey = "your key";
MSCognitiveServicesClientIDValue = "your value";
MSCognitiveServicesClientSecretValue = "your value";

and add the key/value .....

Screenshots
-----------

![Phone](screencasts/phone-sequence-one.gif "Interacting with the app on a tablet")

![Phone](screencasts/phone-sequence-two.gif "Interacting with the app on a phone")

Credit
------
The project uses the following 3rd party libraries:
- Glide Image downloading and caching library (https://github.com/bumptech/glide)
- Chiu-Ki Chan's AutoRecyclerView (http://blog.sqisland.com/2014/12/recyclerview-autofit-grid.html)
- Timber Android logging library by Jake Wharton (https://github.com/JakeWharton/timber)
- Facebook's Stetho debugging library (https://github.com/facebook/stetho)
- Microsoft Cognitive Service's Speech-to-Text, Translation and Text-to-Speech Api's
    http://www.microsoft.com/cognitive-services

- Tin Megali's articles on implementing the MVP pattern in Android.
    https://code.tutsplus.com/series/how-to-adopt-model-view-presenter-on-android--cms-1012  
- Tim Buchalka's Udemy Course, Learning Android App Development with Java
    https://www.udemy.com/android-marshmallow-java-app-development-course/

MIT License
-----------

Copyright (c) [2016] [William Fero]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.