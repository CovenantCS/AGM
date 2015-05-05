Arcade Game Maker Android Port
==============================

Get Started
-----------

This section will help you get Eclipse set up to work on the project.

### To run the old J2ME version of the application:

Import the folder "B2" from [B2 121005.zip](http://kepler.covenant.edu/COS375/AGMProject/B2%20121005.zip) into Eclipse as an existing project (Go to `File->Import` and click `Existing Projects into Workspace`). If it shows up with errors, right-click on the project in Eclipse and go to `Properties`. Click on Java Build Path, click `Add Library...`, click `JRE System Library`, and click `Finish` to the build path.

Download and install the [Java Wireless Toolkit](http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javame-419430.html#sun_java_wireless_toolkit-2.5.2_01b-oth-JPR). Remember the location you install this in; you will need it later. The package you want is "Sun Java Wireless Toolkit 2.5.2_01." Unfortunately you need to sign up for a free Oracle account before you may download it. 

Go to `Help->Install New Software`. Add `http://www.eclipseme.org/updates/` as a repository, then install EclipseME. Next go to `Window->Preferences`, expand J2ME and click on `Device Management`. Click on `Import`, then `Browse` to the folder in which you installed the Java Wireless Toolkit. Then click `Refresh`, and after a moment 4 devices should appear. Make sure all 4 are selected and click `Finish`, then click `OK`.

Now we need to assign a device to the project. Right-click on the project and click on `Properties`, then click `J2ME`. Click `Manage Devices...` and pick one of the devices, then click `OK`, and then `OK` again. This should take care of all the compile errors with the imported J2ME project.

To get it to run without issue, however, we need to do two more things. One is to make sure the name of the project is at least 3 characters long (weird bug). The other is to change the Java Compliance Level. Go to `Project->Properties`, click `Java Compiler`, check the box to `Enable project specific settings`, and set the Compiler compliance level to 1.4.

Finally, you are ready to run the J2ME version! Run the games using BricklesProduct.java, PongProduct.java, and BowlingProduct.java.

### To add the Android plugin to an existing Eclipse installation (otherwise just install the bundled version from [here](http://developer.android.com/sdk/index.html)):

To install the plugin to an existing Eclipse installation, first download and run [this .exe](http://dl.google.com/android/installer_r21.0.1-windows.exe) to install the SDK.

Then install the Eclipse plugin. In Eclipse, go to `Help -> Install New Software`. Click `Add...` and type `https://dl-ssl.google.com/android/eclipse/` into the "Location" box. Put whatever you like in the "Name" box. Then click `OK`. Click the checkbox for `Developer Tools`, click `Next`, accept the license agreements, click `Finish`, and restart Eclipse. If you get a message about being unable to establish authenticity or validity, just click `OK` - this won't affect anything.

Next, run the Android SDK that we installed first. You must run it as Administrator (at least this time) or it will not work properly. In the window that appears, select the items under "Tools" and click `Install Packages`.

Now we need to create a virtual Android device to emulate for when we test our programs. Run the Android AVD Manager, and click `New...`. Give the device a name, pick `Nexus One` for the Device, select "Android 4.2" for the Target, select `ARM` for the CPU/ABI, uncheck the `Hardware keyboard present` checkbox, and click `OK`. This gives you a virtual device to test on. We can create other devices later as we get further into testing.

### Using Git in Eclipse:

You'll also need to install the EGit plugin, similar to how you installed the above plugins. Go to `Help -> Install New Software`, and use the following URL: `http://download.eclipse.org/egit/updates`. You don't need to install JGit, just EGit.

### To import the project from Github into Eclipse:

Go to `File -> Import...`, then click the Git folder and select `Projects from Git`, then `Next`, then `URI`, then `Next`, then paste `git@github.com:CovenantCS/AGM.git` into the `URI` field. The other fields should auto-populate, and you can click `Next`, `Next`, `Next`, `Next`, `Finish` (keep "Search for nested projects" checked).

You may get an error (if you click on `Details` it'll say something about "Errors running builder 'Preverification' on project 'AGM Products'"). If so, hit `OK` and right-click on the project and go to `Properties`. Click `J2ME`, then `Manage Devices...`. Make sure one of the devices is checked, click `Apply`, then `OK`, then `Apply`, then `OK`, and the project should show up as having no errors.

### Pulling from, committing to, and pushing to the git repository:

First of all, use [these instructions](http://wiki.eclipse.org/EGit/User_Guide#Git_Workbench_Toolbar_and_Git_Workbench_Menu) to add Git buttons to your toolbar. 

When working on the project, you'll want to first pull all the changes that other people have done from the repo using the "Pull" toolbar button (yellow cylinder with a green arrow coming out and down).

After you've made some changes, you'll need to push the changes back to the repo. After making some changes, 
