# BuildingResources
Purpose of the project: This project was got me a job in November of 2021. I disliked that I was lied to and quit :)
The following below is the exact requirement from the employer for this project.


 Build a web application in order to manage the following resources: &quot;building&quot;, &quot;room&quot; and &quot;door&quot;.

    **General rules:**

- The app needs to be functional and run on a localhost.
- The technologies used to build the app are completely arbitrary, their type will be:

- HTML, CSS, JavaScript (framework use is discretionary or without a framework) for the graphics part;

- Backend programming language (server side) like NodeJS, PHP etc., optionally a framework;
- A database in which data will be stored (or files on disk);

- The sources will be presented in private account on [https://github.com/](https://github.com/);
- The commits on git will be explicit and atomic, for example if the code for creating a username and it implies 3 files, a commit will be made with the files named &quot;create a new user&quot;, a single commit containing all the code at the end will not be permitted;
- Implementation time, approximately a month.

**Application requirements:**

- The app will allow adding, deleting, editing, viewing and listing of each of the resources: building, room and door.
- Building, room and door resources model real objects.
- A building will have the attributes (id, name, description, number of floors).
- A room will have the attributes (id, building\_id, name, surface area).
- A door will have the attributes (id, room\_id\_1, room\_id\_2, name, exterior). A door can be indoor or exterior, if it is exterior , room\_id\_2 will be null, if the door is indoors both room\_id\_1 and room\_id\_2, will not be null.
- For each of the resources there will be a listing with pagination where limit and offset will be used.
- Managing error cases (exceptional ones) is a plus.
- Optionally a login page will be created in the app, and the user side will be hardcoded (only admin user and admin password will exist).
