bridges
=======

TUES Bridges Project - uniting classrooms for better student engagement

## Getting Started

### As a Student

- Download the example project for follower graphs (incomplete)
- Contains an existing Eclipse project, with maven configuration
  - You should use the eclipse-m2e plugin or similar to keep dependencies up to date

> If you decide to develop using your own environment, add the following dependency:
> group: "edu.uncc.cs.bridges", artifact: "client-scala", version: "0.2"

### As a Developer

- Clone this repository: `git clone https://github.com/SeanTater/bridges.git`
- Install [Gradle](http://gradle.org)
- Add gradle binaries to your PATH
- One step from nothing to working code with running tests:
  - `gradle --info test`
- on Linux, you can do all of that like this: (execute these one at a time so you can react if something goes wrong)
```sh
git clone https://github.com/SeanTater/bridges.git
cd bridges/client-scala
wget http://services.gradle.org/distributions/gradle-1.10-all.zip
unzip gradle-1.10-all.zip
rm gradle-1.10-all.zip
echo "PATH=\$PATH:$PWD/gradle-1.10/bin" >>~/.bashrc
# On fedora, uncomment this line too
# otherwise you will get an error telling you how javac can't be found
# sudo yum install java-1.7.0-openjdk-devel
gradle assemble
gradle --info check
```
