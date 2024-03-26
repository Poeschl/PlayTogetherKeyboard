# Play Together Keyboard

I simply needed something that grabs keystrokes from serveral hosts via a webpage and calculates the key with the most keypresses.
This keypress is then executed on the server host. The idea is to use it for playing singleplayer games "together" with more people.

## Features

* Simple Key Press website
* Adjustable evaluation intervals for key presses.

## Setup

The application is packed into an executable jar file, which can be downloaded via the actions page.
Grab the last one and also make sure to have at least [Java 21](https://adoptium.net/de/temurin/releases/?package=jdk&version=21) installed on your system.

After downloading the server can be started via commandline as follows:

```bash
java -jar PlayTogetherKeyboard-x.x.x.jar
```

## Note

This software will get no versioning and lives on the bloody main branch.

## Development

### Requirements

Have a [Java 21 LTS](https://adoptium.net/de/temurin/releases/?package=jdk&version=21), [node 20](https://nodejs.org/en/download/) and
[python 3.10](https://www.python.org/downloads/) installation is required to make it all run.
Make sure you have [podman](https://podman.io/docs/installation) and [podman-compose](https://github.com/containers/podman-compose)
(or docker and docker-compose) installed on your system, since the dev environment runs on a container-based reverse proxy.

For the database test testcontainers is used. So make sure you make the podman socket available.
([See here for more](https://podman-desktop.io/docs/migrating-from-docker/using-the-docker_host-environment-variable))
As a second step the used builder `ryuk` needs to be disabled, since it doesn't play well with podman right now.
For this set the environment variable `TESTCONTAINERS_RYUK_DISABLED` to `true`.

### Set up

For development there is a little script in the project root named `start_dev_env.(sh|bat)` (according to your OS).
Execute this first, before you do anything else.

#### Run via Intellij

Navigate to the `Application.kt` file and click on the green play button beside the main method. The backend is now running.

For the frontend open up the `package.json` file and click the green play button to the left of the `dev` script.
Make sure you have all dependencies installed via the Intellij UI or install them with `npm install --dev` from terminal.

Now the application can be reached by http://localhost:8888 and http://localhost:8888/api/swagger-ui.

#### Run via terminal

Then run `./gradlew backend:bootRun` (from project root) (or execute it through your IDE) to start your backend.

For the frontend install all packages with `npm install --dev` and run the VUE app with `npm run dev` (from the `frontend` folder).

Now the application can be reached by http://localhost:8888 and http://localhost:8888/api/swagger-ui..

#### Coding Styles

To comply to the coding style, there are some [pre-commit](https://pre-commit.com/) rules.
Those should be automatically executed before every git commit.
To make this happen automatically, make sure to have `pre-commit` installed on your machine (`pip install pre-commit`)
and set up your git hooks (`pre-commit install` | from project root).
