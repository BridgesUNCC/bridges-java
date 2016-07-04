bridges  ![https://travis-ci.org/krs-world/bridges](https://travis-ci.org/krs-world/bridges.svg?branch=version2.0)
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
- on Linux, you can do all of that like this: (execute these one at a time so you can react if something goes wrong)
```sh
git clone https://github.com/SeanTater/bridges.git
cd bridges
wget http://services.gradle.org/distributions/gradle-1.12-bin.zip
unzip gradle-1.12-all.zip
rm gradle-1.12-all.zip
# for mac osx use ~/.bash_profile in place of ~/.bashrc
echo "export PATH=\$PATH:$PWD/gradle-1.12/bin" >>~/.bashrc
# On fedora, uncomment this line too
# otherwise you will get an error telling you how javac can't be found
# sudo yum install java-1.7.0-openjdk-devel
gradle assemble
gradle --info check
```
